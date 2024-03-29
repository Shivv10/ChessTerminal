public class Pawn extends Piece{

    public Pawn(Board board, int col, int row, boolean isPlayer) {
        super(board);
        this.col = col;
        this.row = row;
        this.isPlayer = isPlayer;

        this.name = "Pawn";

        this.symbol = isPlayer ? 'P' : 'p';
    }


    @Override
    public boolean isValidMovement(int col, int row) {
        if (!this.isPlayer){
            return (row - this.row == 1 && this.col == col && this.board.getPiece(col, row) == null) || (board.getPiece(col, row) != null && board.getPiece(col, row).isPlayer && (row - this.row == 1 && Math.abs(this.col - col) == 1));

        }
        return (this.row - row == 1 && this.col == col && this.board.getPiece(col, row) == null) || (board.getPiece(col, row) != null && !board.getPiece(col, row).isPlayer && (this.row - row == 1 && Math.abs(this.col - col) == 1));
    }


    @Override
    public boolean moveCollidesWithPiece(int col, int row) {
        //up
        if (this.row == row + 1){
                if(board.getPiece(this.col, row) != null && board.getPiece(this.col, row).isPlayer) return true;
        }

        //down
        if (this.row + 1 == row){
                if(board.getPiece(this.col, row) != null && !board.getPiece(this.col, row).isPlayer) return true;
        }

        return false;
    }
}
