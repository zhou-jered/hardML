package me.dev.decesiontree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class TreeNode {
    private int splitDimension;
    private TreeNode[] children;
    private int category;
    private boolean isLeaf;

    public TreeNode getChild(int[] data) {
        int val = data[splitDimension];
        return children[val];
    }
}
