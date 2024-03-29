/**
A Simplified Game of Chess

 Note: This code does not check for King checks and checkmates.
 Also, en passant feature is not implemented as well.
 Other than that all the required chess logic is implemented
 */


public class ChessTerminal {
    public static void main(String[] args) {
        //Welcome to the Game of Chess
        GameLogic chess = new GameLogic();
        Board chessBoard = new Board();
        chess.playGame(chessBoard);
    }
}