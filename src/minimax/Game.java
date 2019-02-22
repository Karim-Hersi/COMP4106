package minimax;

public abstract class Game {
    protected char[][] gameState;
    protected char currentTurn;

    public char[][] getGameState() {
        return gameState;
    }

    public char getCurrentTurn() {
        return currentTurn;
    }

    public void switchTurn() {
        if (currentTurn == 'X') {
            currentTurn = 'O';
        } else {
            currentTurn = 'X';
        }
    }

    public char[][] cloneState() {
        char[][] clone = new char[SETTINGS.NUM_OF_ROWS][SETTINGS.NUM_OF_COLUMNS];
        for (int i = 0; i <= SETTINGS.MAX_ROW; i++) {
            for (int j = 0; j <= SETTINGS.MAX_COLUMN; j++) {
                clone[i][j] = gameState[i][j];
            }
        }
        return clone;
    }

    public void print() {
        for (int i = 0; i <= SETTINGS.MAX_ROW; i++) {
            for (int j = 0; j <= SETTINGS.MAX_COLUMN; j++) {
                System.out.print(" | ");
                System.out.print(gameState[i][j]);
            }
            System.out.println("| ");
        }
        System.out.println("\n");
    }

    public void applyMove(Move move) {
        int row = move.getRow();
        int column = move.getColumn();
        gameState[row][column] = currentTurn;
        switchTurn();
    }

    public boolean isGameOver() {
        if (checkVictor('X')) {
            System.out.println("Player X won!!");
            return true;
        } else if (checkVictor('O')) {
            System.out.println("Player O won!!");
            return true;
        } else if (isDraw()) {
            System.out.println("It's a draw!!");
            return true;
        } else {
            return false;
        }
    }

    public Integer evalState(char symbol) {
        if (checkVictor(symbol)) {
            return Integer.MAX_VALUE;
        } else if ((symbol == 'X') && checkVictor('O')) {
            return Integer.MIN_VALUE;
        } else if ((symbol == 'O') && checkVictor('X')) {
            return Integer.MIN_VALUE;
        } else if (isDraw()) {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean isDraw() {
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 2; j++) {
                if (gameState[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    public boolean checkVictor(char symbol) {
        return checkDiagonals(symbol) || checkColumns(symbol) || checkRows(symbol);
    }

    public boolean checkDiagonals(char symbol) {
        int TLDiagonal = 0;
        int BLDiagonal = 0;
        for (int i = 0; i <= 2; i++) {
            if (gameState[i][i] == symbol) {
                TLDiagonal++;
            }
            if (gameState[i][2 - i] == symbol) {
                BLDiagonal++;
            }
        }
        return (BLDiagonal == SETTINGS.NUM_OF_ROWS || TLDiagonal == SETTINGS.NUM_OF_ROWS);
    }

    public boolean checkColumns(char symbol) {
        int sumCols = 0;
        for (int i = 0; i <= SETTINGS.MAX_ROW; i++) {
            for (int j = 0; j <= SETTINGS.MAX_COLUMN; j++) {
                if (symbol == gameState[i][j])
                    sumCols++;
            }
            if (sumCols == SETTINGS.NUM_OF_ROWS) {
                return true;
            }
            sumCols = 0;
        }
        return false;
    }

    public boolean checkRows(char symbol) {
        int sumRows = 0;
        for (int i = 0; i <= SETTINGS.MAX_COLUMN; i++) {
            for (int j = 0; j <= SETTINGS.MAX_ROW; j++) {
                if (symbol == gameState[j][i])
                    sumRows++;
            }
            if (sumRows == SETTINGS.NUM_OF_ROWS) {
                return true;
            }
            sumRows = 0;
        }
        return false;
    }
}