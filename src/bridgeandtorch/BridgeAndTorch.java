package bridgeandtorch;

import java.util.*;

public class BridgeAndTorch {
    public void depthFirstSearch(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Set<String> visitedStates = new HashSet<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode currentNode = stack.pop();
            visitedStates.add(currentNode.toString());
            if (currentNode.isDestinationState()) {
                return;
            }
            List<TreeNode> children = currentNode.spawnChildren();
            for (TreeNode child : children) {
                if (!visitedStates.contains(child)) {
                    stack.add(child);
                }
            }
        }
    }

    public void breadthFirstSearch(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Set<String> visitedStates = new HashSet<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.remove();
            visitedStates.add(currentNode.toString());
            if (currentNode.isDestinationState()) {
                return;
            }
            List<TreeNode> children = currentNode.spawnChildren();
            for (TreeNode child : children) {
                if (!visitedStates.contains(child)) {
                    queue.add(child);
                }
            }
        }
    }

    public void aStarSearch(TreeNode root) {
        Set<String> visitedStates = new HashSet<>();
        TreeNode currentNode = root;
        while (currentNode != null) {
            visitedStates.add(currentNode.toString());
            if (currentNode.isDestinationState()) {
                return;
            }
            currentNode = chooseNodeUsingHeuristic(currentNode, visitedStates);
        }
    }

    public TreeNode chooseNodeUsingHeuristic(TreeNode currentNode, Set<String> visitedStates){
        List<TreeNode> children = currentNode.spawnChildren();
        TreeNode bestMove = null;
        Integer minimumValue = Integer.MAX_VALUE;
        for(TreeNode child : children){
            if(visitedStates.contains(child.toString())){
                continue;
            }
            int value = -1;//
            if(value < minimumValue){
                minimumValue = value;
                bestMove = child;
            }
        }
        return bestMove;
    }
}