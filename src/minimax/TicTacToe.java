package minimax;

public class TicTacToe {
    public static void main(String[] args) {
        System.out.println("TIC TAC TOE!");

        //Determine who starts  and the symbol for each/player
        char startTurn = 'X';
        char PLAYER_ONE_SYMBOL = 'X';
        char PLAYER_TWO_SYMBOL = 'O';

        //Initialize the board and the players
        GameTree game = new GameTree(startTurn);
        Player player1 = new AiPlayer(game, PLAYER_ONE_SYMBOL);
        Player player2 = new AiPlayer(game, PLAYER_TWO_SYMBOL);
        for (; ; ) {
            game.applyMove(player1.chooseMove());
            game.print();
            if (game.isGameOver()) {
                break;
            }

            game.applyMove(player2.chooseMove());
            game.print();
            if (game.isGameOver()) {
                break;
            }
        }
    }
}