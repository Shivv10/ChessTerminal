import java.util.ArrayList;

public class Board {
    protected int size = 8;
    char[][] board;

    ArrayList<Piece> pieceList = new ArrayList<>();

    public Piece selectedPiece;

    public Board(){
        board = new char[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                board[i][j] = ' ';
            }
        }

        //adds the list of pieces to the board
        addPieces(this);

        //getting the symbol of the pieces
        for (Piece curr : pieceList) {
            board[curr.row - 1][curr.col - 1] = curr.symbol;
        }
    }

    public void reset(Board board) {
        // Clear the current list of pieces
        board.pieceList.clear();

        // Reset the board by setting each cell to empty
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board.board[i][j] = ' ';
            }
        }
        // Add the initial pieces again
        board.addPieces(board);

        for (Piece curr : pieceList) {
            board.board[curr.row - 1][curr.col - 1] = curr.symbol;
        }
    }


    public int getSize(){return size;}

    public Piece getPiece(int col, int row){
        for(Piece piece : pieceList){
            if (piece.col == col && piece.row == row){
                return piece;
            }
        }
        return null;
    }

    public void addPieces(Board board){
        //King
        pieceList.add(new King(board,4,1,false));
        pieceList.add(new King(board,4,8,true));

        //Queen
        pieceList.add(new Queen(board,5,1,false));
        pieceList.add(new Queen(board,5,8,true));

        //Knights
        pieceList.add(new Knight(board, 2,1,false));
        pieceList.add(new Knight(board, 7,1,false));
        pieceList.add(new Knight(board, 2,8,true));
        pieceList.add(new Knight(board, 7,8,true));

        //Bishops
        pieceList.add(new Bishop(board,3,1,false));
        pieceList.add(new Bishop(board,6,1,false));
        pieceList.add(new Bishop(board,3,8,true));
        pieceList.add(new Bishop(board,6,8,true));

        //Rooks
        pieceList.add(new Rook(board, 1, 1, false));
        pieceList.add(new Rook(board, 8, 1, false));
        pieceList.add(new Rook(board, 1, 8, true));
        pieceList.add(new Rook(board, 8, 8, true));

        //Pawns
        for (int i = 1; i <= 8; i++) {
            pieceList.add(new Pawn(board,i,2,false));
            pieceList.add(new Pawn(board,i,7,true));
        }
    }

}
