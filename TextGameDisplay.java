import java.util.Scanner;

public class TextGameDisplay implements GameDisplay {

    protected boolean playersTurn = true; // Indicates if it's the player's turn

    protected int oldRow, oldCol, newRow, newCol; // Variables to store coordinates of moves

    protected int playAgain; // Stores the player's choice to play again

    private Scanner input; // Scanner object to read input from the console

    protected boolean endGame = false; // Flag to indicate if the game has ended

    public TextGameDisplay() {
        this.input = new Scanner(System.in); // Initialize Scanner object to read input
    }

    @Override
    public int promptForOpponentDifficulty(int maxDifficulty) {
        int difficulty;
        do {
            // Prompt user to enter opponent's difficulty level
            System.out.println("Please enter the difficulty (0,1): ");
            difficulty = input.nextInt(); // Read user input
        } while (difficulty < 0 || difficulty > maxDifficulty); // Ensure input is within valid range
        System.out.println("Difficulty set to: " + difficulty); // Confirm the selected difficulty
        return difficulty; // Return the selected difficulty level
    }

    @Override
    public void promptForMove(Board board) {
        do {
            // Prompt the player to enter the coordinates of the piece they want to move
            System.out.println("Please enter the row of the piece you would like to move. Enter 0 to forfeit game.");
            oldRow = input.nextInt(); // Read the row input
            if (oldRow == 0) {
                // If the player chooses to forfeit
                if (promptPlayAgain(board)) {
                    return; // Exit the method
                } else {
                    endGame = true; // Set endGame flag to true if the player chooses not to play again
                    break;
                }
            }
            if (!endGame) {
                // If the game is not over, prompt for the destination coordinates
                System.out.println("Please enter the column of the piece you would like to move.");
                oldCol = input.nextInt();
                System.out.println("Please enter the row of the destination.");
                newRow = input.nextInt();
                System.out.println("Please enter the column of the destination.");
                newCol = input.nextInt();
            }
        } while (!isValidInput()); // Continue prompting until valid input is provided
    }

    // Check if the entered coordinates are within the board's bounds
    private boolean isValidInput() {
        return (oldRow >= 1 && oldRow <= 8) && (oldCol >= 1 && oldCol <= 8) && (newRow >= 1 && newRow <= 8) && (newCol >= 1 && newCol <= 8);
    }

    @Override
    public void displayBoard(Board board) {
        // Display the current state of the game board
        System.out.println("    1   2   3   4   5   6   7   8   ");
        System.out.println("-----------------------------------");
        for (int i = 0; i < board.getSize(); i++) {
            System.out.print(i+1 + " | ");
            for (int j = 0; j < board.getSize(); j++) {
                System.out.print(board.board[i][j] + " | ");
            }
            System.out.print("\n-----------------------------------\n");
        }
    }

    @Override
    public void summarizeMove(Move move) {
        // Summarize the move made by the player or AI
        System.out.printf(move.piece.name + " moved from (%d, %d) to (%d, %d) ", move.oldRow, move.oldCol, move.newRow, move.newCol);
        if (move.capture != null) {
            System.out.print("  " + move.capture.name + " captured.");
        } else {
            System.out.print(" No capture made.");
        }
        System.out.println();
    }

    @Override
    public boolean gameOver(int winner) {
        // Check if the game is over and display appropriate message
        if (winner == -1) {
            System.out.println("You Forfeit");
            return true;
        } else if (winner == 0) {
            System.out.println("You Win !!!");
            playersTurn = true;
            return true;
        } else if (winner == 1) {
            System.out.println("AI Wins. Better luck next time!");
            playersTurn = true;
            return true;
        }
        return false; // Game is not over yet
    }

    @Override
    public boolean promptPlayAgain(Board board) {
        // Prompt the player if they want to play again
        System.out.println("Would you like to play again ? Yes(1) or No(0)");
        playAgain = input.nextInt(); // Read player's choice
        if (playAgain == 1) {
            // If player chooses to play again
            playersTurn = true; // Reset player's turn
            reset(board); // Reset the game board
            displayBoard(board); // Display the reset board
        } else {
            endGame = true; // Set endGame flag to true if player chooses not to play again
        }

        return playAgain == 1; // Return true if player chooses to play again, false otherwise
    }

    public void reset(Board board) {
        // Reset the game board and set player's turn to true
        board.reset(board);
        playersTurn = true;
    }
}
