public class Piece {

    protected int col, row; //for the position of the pieces
    protected boolean isPlayer; //check if the piece belongs to player or AI
    protected String name; //name of the piece
    protected char symbol; //symbol of the piece

    Board board;

    public Piece(Board board){
        this.board = board;
    }

    public boolean isValidMovement(int col, int row){ //declaration of method in parent class
        return true;
    }

    public boolean moveCollidesWithPiece(int col, int row){ //declaration of the method in the parent class
        return false;
    }

}
