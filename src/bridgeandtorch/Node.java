package bridgeandtorch;

import java.util.*;
public class Node{
    public static final int TORCH_INDEX = SETTINGS.NUM_OF_PERSONS;
    protected Integer[] state;
    protected List<MovePair> movesFromRoot = new ArrayList<MovePair>();
    protected Integer moveTime;
    protected Integer totalTime;
    
    public Integer[] getState(){
        return state;
    }
    
    public static Integer[] initializeState(String value){
        Integer[] state = new Integer[SETTINGS.NUM_OF_PERSONS + 1];
        for(int i = 0; i < state.length; i++){
            char c = value.charAt(i);
            state[i] = Character.getNumericValue(c);
        }
        return state;
    }
    
    public Integer[] cloneState(){
        Integer[] clone = new Integer[SETTINGS.NUM_OF_PERSONS + 1];
        for(int i = 0; i < state.length; i++){
            clone[i] = state[i];
        }
        return clone;
    }
    
    public void print(){
        for(int i = 0; i < state.length; i++){
            System.out.print(state[i] + " | ");
        }
        System.out.println("\n\n");
    }
    
    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i < state.length; i ++ ){
          s += state[i];
        }
        return s;
    }
    
    public Integer moveRight(int firstPerson, int secondPerson){
        state[firstPerson] = 1;
        state[secondPerson] = 1;
        state[TORCH_INDEX] = 1;
        String move = "p" + (firstPerson + 1) + " and p" + (secondPerson + 1) + " to the right side.";
        Integer moveTime = Integer.max(SETTINGS.TIMES[firstPerson], SETTINGS.TIMES[secondPerson]);
        movesFromRoot.add(new MovePair(move,moveTime));
        totalTime += moveTime;
        return moveTime;
    }
    
    public void printMovesFromRoot(){
        System.out.println("\n\n\n===================== Moves =========================");
        int i = 1;
        for(MovePair move : movesFromRoot){
            System.out.println("Move " + i + ": " + move.getMove() + " which took " + move.getTime() + " minutes.");
            i++;
        }
        System.out.println("The total time was " + totalTime + " minutes.");
    }
    
    public Integer bringBackTorch(int person){
        state[person] = 0;
        state[TORCH_INDEX] = 0;
        String move = "p" + (person + 1) + " brings back the torch.";
        Integer moveTime = (SETTINGS.TIMES[person]);
        movesFromRoot.add(new MovePair(move,moveTime));
        totalTime += moveTime;
        return moveTime;
    }
    
    public boolean isMoveRightValid(int firstPerson, int secondPerson){
        if(firstPerson == secondPerson){
            return false;
        }else if(firstPerson == TORCH_INDEX || secondPerson == TORCH_INDEX ){
            return false;
        }
        return (state[firstPerson] == 0) && (state[secondPerson] == 0) && (state[TORCH_INDEX] == 0);
    }
    
    public boolean isBringBackTorchValid(int person){
        return (state[person] == 1) && (state[TORCH_INDEX] == 1) && (person != TORCH_INDEX);
    }
}