public class Knight extends Piece{

    public Knight(Board board, int col, int row, boolean isPlayer) {
        super(board);
        this.col = col;
        this.row = row;
        this.isPlayer = isPlayer;

        this.name = "Knight";

        this.symbol = isPlayer ? 'N' : 'n';
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        return Math.abs(col - this.col)*Math.abs(row - this.row) == 2;
    }

    //no moveCollidesWithPiece required since it can jump over pieces
}
