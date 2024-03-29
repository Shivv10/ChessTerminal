public interface GameDisplay {
    public int promptForOpponentDifficulty(int maxDifficulty);
    public void promptForMove(Board board);
    public void displayBoard(Board board);
    public void summarizeMove(Move move);
    public boolean gameOver(int winner);
    public boolean promptPlayAgain(Board board);
}