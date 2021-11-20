package me.dev.knn;

import java.util.List;

public class KNN {

    KDTreeNode kdTreeRoot;

    public void init(List<int[]> points) {
        kdTreeRoot = KDTreeFactory.buildTree(points);
    }

    public KDTreeNode searchNearest(int[] targetPoint) {
        return null;
    }


    public KDTreeNode searchTarget(int[] targetPoint) {
        KDTreeNode current = kdTreeRoot;
        while (current != null) {
            if (current.samePoint(targetPoint)) {
                return current;
            }
            int splitIdx = current.getSplitDimension();
            int splitVal = current.getNodeVal();
            int targetDimVal = targetPoint[splitIdx];
            if (targetDimVal <= splitVal) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }
        return current;
    }

}
