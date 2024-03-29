public class Move {
    protected int oldCol;
    protected int oldRow;
    protected int newCol;
    protected int newRow;

    Piece piece;
    Piece capture;

    public Move(Board board, Piece piece, int newCol, int newRow){
        this.oldCol = piece.col;
        this.oldRow = piece.row;
        this.newCol = newCol;
        this.newRow = newRow;

        this.piece = piece;
        this.capture = board.getPiece(newCol,newRow);
    }
}
