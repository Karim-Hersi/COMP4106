package minimax;

import java.util.*;

public class HumanPlayer extends Player {
    public HumanPlayer(GameTree game, char symbol) {
        super(game, symbol);
    }

    public Move chooseMove() {
        int row = getIntegerFromUser("Enter the row for your next move");
        int column = getIntegerFromUser("Enter the column for your next move");
        return new Move(row, column);
    }

    public boolean isValidMove(Move move) {
        return true;
    }

    public int getIntegerFromUser(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message + ": ");
        return scanner.nextInt();
    }
}
