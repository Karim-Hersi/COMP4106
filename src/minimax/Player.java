package minimax;

public abstract class Player {
    protected GameTree game;
    protected char symbol;

    public Player(GameTree game, char symbol) {
        this.game = game;
        this.symbol = symbol;
    }

    public abstract Move chooseMove();
}
