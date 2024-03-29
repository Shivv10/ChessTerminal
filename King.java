public class King extends Piece{

    public King(Board board, int col, int row, boolean isPlayer) {
        super(board);
        this.col = col;
        this.row = row;
        this.isPlayer = isPlayer;

        this.name = "King";

        this.symbol = isPlayer ? 'K' : 'k';
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        return Math.abs((col - this.col)*(row - this.row)) == 1 || Math.abs(col - this.col) + Math.abs(row - this.row) == 1;
    }
}
