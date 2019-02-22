package bridgeandtorch;

public class MovePair{
    private String move;
    private Integer time;
    
    public MovePair(String move, Integer time){
        this.move = move;
        this.time = time;
    }
    
    public Integer getTime(){
        return time;
    }
    
    public String getMove(){
        return move;
    }
}
