public class Bishop extends Piece{

    public Bishop(Board board, int col, int row, boolean isPlayer) {
        super(board);
        this.col = col;
        this.row = row;
        this.isPlayer = isPlayer;

        this.name = "Bishop";

        this.symbol = isPlayer ? 'B' : 'b';
    }

    @Override
    public boolean isValidMovement(int col, int row) {
        return Math.abs(this.col - col) == Math.abs(this.row - row);
    }

    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        //up left
        if (this.col > col && this.row > row){
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (board.getPiece(this.col - i, this.row - i) != null) return true;
            }
        }

        //up right
        if (this.col < col && this.row > row){
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (board.getPiece(this.col + i, this.row - i) != null) return true;
            }
        }

        //down left
        if (this.col > col && this.row < row){
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (board.getPiece(this.col - i, this.row + i) != null) return true;
            }
        }

        //down right
        if (this.col < col && this.row < row){
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (board.getPiece(this.col + i, this.row + i) != null) return true;
            }
        }
        return false;
    }
}
