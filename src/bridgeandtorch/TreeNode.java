package bridgeandtorch;

import java.util.*;
public class TreeNode extends Node{
    private int depth;
    private Integer[] destinationState;
    private List<TreeNode> children = new ArrayList<TreeNode>();
    private static Set<String> visitedStates = new HashSet<String>();
    public TreeNode(TreeNode parent){
        this.state = parent.cloneState();
        destinationState = initializeState(SETTINGS.DESTINATION_STATE);
        depth = parent.getDepth() + 1;
        movesFromRoot = new ArrayList<MovePair>(parent.getMovesFromRoot());
        totalTime = parent.getTotalTime();
    }
    
    public TreeNode(Integer[] state){
        this.state = state;
        destinationState = initializeState(SETTINGS.DESTINATION_STATE);
        depth = 0;
        totalTime = 0;
        movesFromRoot = new ArrayList<MovePair>();
    }
    
    public int getDepth(){
        return depth;
    }
    
    public Integer getTotalTime(){
        return totalTime;
    }
    
    public void setMoveTime(Integer moveTime){
        this.moveTime = moveTime;
    }
    
    public List<MovePair> getMovesFromRoot(){
        return movesFromRoot;
    }
    
    public Integer getMoveTime(){
        return moveTime;
    }
    
    public int getLeftSideTime(){
        int sum = 0;
        for(int i = 0; i < SETTINGS.NUM_OF_PERSONS; i++){
            if(state[i] == 0){
                sum += SETTINGS.TIMES[i];
            }
        }
        return sum;
    }
    
    public int getAverage(){
        int leftSideCount = getLeftSideTime();
        return (leftSideCount + moveTime) / 2;
    }
    
    public int getHeuristicValue(Heuristic heuristic){
        if(heuristic == Heuristic.MOVE_TIME){
            return moveTime;
        }else if(heuristic == Heuristic.LEFT_SIDE_TIME){
            return getLeftSideTime();
        }else if (heuristic == Heuristic.AVERAGE){
            return getAverage();
        }else{
            return -1;
        }
    }
    
    public TreeNode chooseNode(Heuristic heuristic){
        List<TreeNode> children = spawnChildren();
        TreeNode bestMove = null;
        Integer minimumValue = Integer.MAX_VALUE;
        for(TreeNode child : children){
            int value = child.getHeuristicValue(heuristic);
            if(value < minimumValue){
                minimumValue = value;
                bestMove = child;
            }
        }
        return bestMove;
    }
    
    public List<TreeNode> spawnChildren(){
        for(int i = 0; i <  SETTINGS.NUM_OF_PERSONS; i ++ ){
            for(int j = 0; j <  SETTINGS.NUM_OF_PERSONS; j ++){
                if(isMoveRightValid(i,j)){
                    TreeNode child = new TreeNode(this);
                    Integer moveTime = child.moveRight(i,j);
                    child.setMoveTime(moveTime);
                    if(!child.hasBeenVisited()){
                        children.add(child);
                    }
                }
            }
        }
        
        for(int i = 0; i < SETTINGS.NUM_OF_PERSONS; i ++ ){
            if(isBringBackTorchValid(i)){
                TreeNode child = new TreeNode(this);
                Integer moveTime = child.bringBackTorch(i);
                child.setMoveTime(moveTime);
                if(!child.hasBeenVisited()){
                    children.add(child);
                }
            }
        }
        
        return children;
    }
    
    public boolean hasBeenVisited(){
        return visitedStates.contains(toString());
    }
    
    public void markStateVisited(){
        visitedStates.add(toString());
    }
    
    public boolean isDestinationState(){
        return toString().equals(SETTINGS.DESTINATION_STATE);
    }
}
