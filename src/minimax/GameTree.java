package minimax;

import java.util.*;

public class GameTree extends Game {
    private Move move;

    public GameTree(char currentTurn) {
        this.currentTurn = currentTurn;
        gameState = new char[SETTINGS.NUM_OF_ROWS][SETTINGS.NUM_OF_COLUMNS];
        for (int i = 0; i <= SETTINGS.MAX_ROW; i++) {
            for (int j = 0; j <= SETTINGS.MAX_COLUMN; j++) {
                gameState[i][j] = ' ';
            }
        }
    }

    public GameTree(char[][] gameState, char currentTurn) {
        this.gameState = gameState;
        this.currentTurn = currentTurn;
    }

    public GameTree(GameTree parent, Move move) {
        this.move = move;
        this.gameState = parent.cloneState();
        this.currentTurn = parent.getCurrentTurn();
        applyMove(move);
    }

    public Move getMove() {
        return move;
    }

    public List<Move> getAvailableMoves() {
        List<Move> availableMoves = new ArrayList<Move>();
        for (int i = 0; i <= SETTINGS.MAX_ROW; i++) {
            for (int j = 0; j <= SETTINGS.MAX_COLUMN; j++) {
                if (gameState[i][j] == ' ') {
                    Move move = new Move(i, j);
                    availableMoves.add(move);
                }
            }
        }
        return availableMoves;
    }

    public List<GameTree> getChildren() {
        List<GameTree> children = new ArrayList<GameTree>();
        List<Move> availableMoves = getAvailableMoves();
        for (Move move : availableMoves) {
            children.add(new GameTree(this, move));
        }
        return children;
    }
}