import java.util.Scanner;

public class GameLogic extends TextGameDisplay implements ChessController {

    protected int winner = Integer.MIN_VALUE; // Stores the winner of the game

    public void executePlayerMove(Board board) {
        if (board.getPiece(oldCol, oldRow) != null && board.getPiece(oldCol, oldRow).isPlayer) {
            // If the selected piece belongs to the player
            Move move = new Move(board, board.getPiece(oldCol, oldRow), newCol, newRow); // Create a move object
            if (movePiece(board, move)) { // If the move is valid
                summarizeMove(move); // Summarize the move
                displayBoard(board); // Display the updated board
                playersTurn = false; // Switch to AI's turn
            }
        } else {
            if (!endGame) {
                System.out.println("Invalid Input."); // Inform the player about invalid input
            }
        }
    }

    private void executeAIMove(Board board, int difficulty) {
        if (!playersTurn) { // If it's AI's turn
            if (difficulty == 0) { // If AI difficulty is set to 0
                boolean moveMade = false;

                while (!moveMade) {
                    int randomPiece = (int) (Math.random() * board.pieceList.size()); // Choose a random piece
                    if (!board.pieceList.get(randomPiece).isPlayer) { // Ensure it's AI's piece
                        int randomOldCol = board.pieceList.get(randomPiece).col; // Get random piece's column
                        int randomOldRow = board.pieceList.get(randomPiece).row; // Get random piece's row
                        int randomNewCol = (int) (Math.random() * 8 + 1); // Generate a random column for the move
                        int randomNewRow = (int) (Math.random() * 8 + 1); // Generate a random row for the move

                        Move move = new Move(board, board.getPiece(randomOldCol, randomOldRow), randomNewCol, randomNewRow);
                        // Create a move object
                        if (movePiece(board, move)) { // If the move is valid
                            moveMade = true; // Set moveMade to true
                            summarizeMove(move); // Summarize the move
                            displayBoard(board); // Display the updated board
                        }
                    }
                }
            } else { // If AI difficulty is set to 1
                /*
                Here, I am checking for the first 500 random moves if any of the AI's piece can capture any of the
                player's piece. If yes, then make the move. If there's no possible capture in the first 500 moves,
                then just randomly perform a move which is valid. If we want AI to be more tough, we can simply
                change 500 to a bigger no. such as 10000, then it will check for the first possible 10000 moves in
                which capture can be made. So there is VERY HIGH possibility, that any existing capture will be made.
                 */
                boolean moveMade = false;
                int moveAttempts = 0; // Counter for move attempts

                while (!moveMade && moveAttempts < 500) { // Limit the attempts to avoid infinite loops
                    int randomPiece = (int) (Math.random() * board.pieceList.size()); // Choose a random piece
                    if (!board.pieceList.get(randomPiece).isPlayer) { // Ensure it's AI's piece
                        int randomOldCol = board.pieceList.get(randomPiece).col; // Get random piece's column
                        int randomOldRow = board.pieceList.get(randomPiece).row; // Get random piece's row
                        int randomNewCol = (int) (Math.random() * 8 + 1); // Generate a random column for the move
                        int randomNewRow = (int) (Math.random() * 8 + 1); // Generate a random row for the move

                        Move move = new Move(board, board.getPiece(randomOldCol, randomOldRow), randomNewCol, randomNewRow);
                        // Create a move object
                        if (board.getPiece(randomNewCol, randomNewRow) != null && board.getPiece(randomNewCol, randomNewRow).isPlayer) {
                            // Check if there's a capture available
                            if (movePiece(board, move)) { // If the move is valid
                                moveMade = true; // Set moveMade to true
                                summarizeMove(move); // Summarize the move
                                displayBoard(board); // Display the updated board
                            }
                        }

                        moveAttempts++; // Increment move attempts counter
                    }
                }

                // If no move made after 500 attempts or if a capture is not found, make a random move
                if (!moveMade) {
                    while (!moveMade) {
                        int randomPiece = (int) (Math.random() * board.pieceList.size()); // Choose a random piece
                        if (!board.pieceList.get(randomPiece).isPlayer) { // Ensure it's AI's piece
                            int randomOldCol = board.pieceList.get(randomPiece).col; // Get random piece's column
                            int randomOldRow = board.pieceList.get(randomPiece).row; // Get random piece's row
                            int randomNewCol = (int) (Math.random() * 8 + 1); // Generate a random column for the move
                            int randomNewRow = (int) (Math.random() * 8 + 1); // Generate a random row for the move

                            Move move = new Move(board, board.getPiece(randomOldCol, randomOldRow), randomNewCol, randomNewRow);
                            // Create a move object
                            if (movePiece(board, move)) { // If the move is valid
                                moveMade = true; // Set moveMade to true
                                summarizeMove(move); // Summarize the move
                                displayBoard(board); // Display the updated board
                            }
                        }
                    }
                }
            }
            playersTurn = true; // Switch back to player's turn
        }
    }

    @Override
    public boolean movePiece(Board board, Move move) {
        if (sameTeam(move.piece, move.capture)) {
            return false; // Return false if trying to capture own piece
        }
        if (!move.piece.isValidMovement(move.newCol, move.newRow)) {
            return false; // Return false if the move is invalid for the piece
        }
        if (move.piece.moveCollidesWithPiece(move.newCol, move.newRow)) {
            return false; // Return false if the move collides with another piece
        }

        board.board[move.piece.row - 1][move.piece.col - 1] = ' '; // Clear the old position of the piece

        if (move.piece.name.equals("Pawn")) {
            captureByPawn(board, move); // Handle capture logic specific to pawns
        } else {
            capture(board, move); // Handle capture for other pieces
        }

        // Update the piece's position after capturing
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;

        if (move.piece.name.equals("Pawn")) {
            if (move.newRow == 8 || move.newRow == 1) {
                // If pawn reaches the opposite end of the board, prompt for promotion
                board.pieceList.remove(move.piece); // Remove the pawn

                // Prompt the player to select the piece for promotion
                System.out.println("Your pawn is ready to promote. Please enter the desired type of piece: 0 for Queen, 1 for Bishop, 2 for Rook, 3 for Knight");
                Scanner in = new Scanner(System.in);
                int promote = in.nextInt();
                // Promote pawn to the selected piece type
                Piece newPiece;
                if (promote == 1) {
                    newPiece = new Bishop(board, move.newCol, move.newRow, move.piece.isPlayer);
                } else if (promote == 2) {
                    newPiece = new Rook(board, move.newCol, move.newRow, move.piece.isPlayer);
                } else if (promote == 3) {
                    newPiece = new Knight(board, move.newCol, move.newRow, move.piece.isPlayer);
                } else {
                    newPiece = new Queen(board, move.newCol, move.newRow, move.piece.isPlayer);
                }
                board.pieceList.add(newPiece); // Add the promoted piece to the piece list
                move.piece = newPiece; // Update the moved piece to the promoted piece
            }
        }

        board.board[move.newRow - 1][move.newCol - 1] = move.piece.symbol; // Update the board with the new piece symbol
        return true; // Return true indicating successful move
    }

    public void capture(Board board, Move move) {
        if (!move.piece.name.equals("Pawn")) { // For non-pawn pieces
            if (move.capture != null && move.capture.name.equals("King")) {
                if (!move.capture.isPlayer) {
                    winner = 0; // Player wins if capturing opponent's king
                } else {
                    winner = 1; // AI wins if capturing player's king
                }
                gameOver(winner); // End the game with the winner
                promptPlayAgain(board); // Prompt for playing again
                playersTurn = true; // Reset turn to player
            }
            board.pieceList.remove(move.capture); // Remove the captured piece from the board
        } else {
            captureByPawn(board, move); // Handle capture logic specific to pawns
        }
    }

    private void captureByPawn(Board board, Move move) {
        boolean pawnDiagonalMove = (Math.abs(move.piece.col - move.newCol) == 1 && Math.abs(move.piece.row - move.newRow) == 1);
        if (pawnDiagonalMove) { // If pawn moves diagonally
            if (move.capture != null && move.capture.name.equals("King")) {
                if (!move.capture.isPlayer) {
                    winner = 0; // Player wins if capturing opponent's king
                } else {
                    winner = 1; // AI wins if capturing player's king
                }
                gameOver(winner); // End the game with the winner
                promptPlayAgain(board); // Prompt for playing again
                playersTurn = true; // Reset turn to player
            }
            board.pieceList.remove(move.capture); // Remove the captured piece from the board
        }
    }

    private boolean sameTeam(Piece p1, Piece p2) {
        if (p1 == null || p2 == null) return false;
        return p1.isPlayer == p2.isPlayer; // Check if both pieces belong to the same player
    }

    @Override
    public void reset(Board board) {
        super.reset(board); // Reset the board
        playersTurn = true; // Reset the turn to player
    }

    public void playGame(Board board) {
        int difficulty = promptForOpponentDifficulty(1); // Prompt for opponent's difficulty
        displayBoard(board); // Display the initial board

        while (!endGame) { // Loop until the game ends
            promptForMove(board); // Prompt the player for a move
            executePlayerMove(board); // Execute the player's move
            if (!playersTurn) { // If it's AI's turn
                executeAIMove(board, difficulty); // Execute the AI's move
            }
        }

        System.out.println("GAME OVER."); // Display game over message
    }
}
