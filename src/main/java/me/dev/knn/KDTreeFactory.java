package me.dev.knn;

import java.util.Comparator;
import java.util.List;

public class KDTreeFactory {

    public static KDTreeNode buildTree(List<int[]> points) {
        KDTreeNode root = buildInternal(points, 0);
        return root;
    }

    private static KDTreeNode buildInternal(List<int[]> points, int depth) {
        if (points.size() == 0) {
            return null;
        }
        int k = points.get(0).length;
        int splitIdx = (depth % k);
        if (points.size() == 0) {
            KDTreeNode node = newNode(points.get(0), splitIdx, depth);
            node.setSplitDimension(splitIdx);
            return node;
        } else {
            sortBySingleDimension(points, splitIdx);
            int midNodeIdx = points.size() / 2;
            int[] midData = points.get(midNodeIdx);
            KDTreeNode midNode = newNode(midData, splitIdx, depth);
            List<int[]> leftPart = points.subList(0, midNodeIdx);
            List<int[]> rightPart = points.subList(midNodeIdx + 1, points.size());
            KDTreeNode leftChild = buildInternal(leftPart, depth + 1);
            KDTreeNode rightChild = buildInternal(rightPart, depth + 1);
            if (leftChild != null) {
                midNode.setLeft(leftChild);
            }
            if (rightChild != null) {
                midNode.setRight(rightChild);
            }
            return midNode;
        }
    }

    private static void sortBySingleDimension(List<int[]> points, int sortDimentionIdx) {
        points.sort(Comparator.comparingInt(ints -> ints[sortDimentionIdx]));
    }

    private static KDTreeNode newNode(int[] data, int splitIdx, int depth) {
        KDTreeNode node = new KDTreeNode();
        node.setData(data);
        node.setSplitDimension(splitIdx);
        node.setNodeVal(data[splitIdx]);
        node.setDepth(depth);
        return node;
    }
}
