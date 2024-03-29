public interface ChessController {
    public boolean movePiece(Board board, Move move);

    public void reset(Board board);

    public void playGame(Board board);
}
