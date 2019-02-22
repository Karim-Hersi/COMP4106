package minimax;

import java.util.List;

public class AiPlayer extends Player {
    public AiPlayer(GameTree game, char symbol) {
        super(game, symbol);
    }

    public Move chooseMove() {
        List<GameTree> children = game.getChildren();
        Integer maxValue = Integer.MIN_VALUE;
        Integer value;
        Move bestMove = null;
        for (GameTree child : children) {
            value = minValue(child, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (value > maxValue) {
                maxValue = value;
                bestMove = child.getMove();
            }
        }
        return bestMove;
    }

    public int maxValue(GameTree node, int alpha, int beta) {
        Integer heuristicValue = node.evalState(symbol);
        if (heuristicValue != -1) {
            return heuristicValue;
        }
        Integer value = Integer.MIN_VALUE;
        List<GameTree> children = node.getChildren();
        for (GameTree child : children) {
            value = Integer.max(value, minValue(child, alpha, beta));
            if (value > beta) {
                return value;
            }
            alpha = Integer.max(alpha, value);
        }
        return value;
    }

    public int minValue(GameTree node, int alpha, int beta) {
        Integer heuristicValue = node.evalState(symbol);
        if (heuristicValue != -1) {
            return heuristicValue;
        }

        Integer value = Integer.MAX_VALUE;
        List<GameTree> children = node.getChildren();
        for (GameTree child : children) {
            value = Integer.min(value, maxValue(child, alpha, beta));
            if (value <= alpha) {
                return value;
            }
            beta = Integer.min(beta, value);
        }
        return value;
    }
}