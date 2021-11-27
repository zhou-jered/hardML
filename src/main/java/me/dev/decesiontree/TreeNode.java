package me.dev.decesiontree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@AllArgsConstructor
public class TreeNode {
    private int splitDimension;
    private int dimVal;
    private Map<Integer, TreeNode> children;
    private int category;
    private boolean isLeaf;

    public TreeNode getChild(int[] data) {
        if (isLeaf) {
            return null;
        }
        int val = data[splitDimension];
        return children.get(val);
    }

    public synchronized void addChild(TreeNode child, int childDimensionVal) {
        if (children == null) {
            children = new HashMap<>();
        }
        children.put(childDimensionVal, child);
    }
}
