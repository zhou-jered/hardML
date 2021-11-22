package me.dev.knn;

import java.util.ArrayList;
import java.util.List;

public class KDTreeMain {

    public static void main(String[] args) {
        List<int[]> points = new ArrayList<>();
        points.add(new int[]{2, 3});
        points.add(new int[]{5, 4});
        points.add(new int[]{9, 6});
        points.add(new int[]{4, 7});
        points.add(new int[]{8, 1});
        points.add(new int[]{7, 2});
        KDTreeNode root = KDTreeFactory.buildTree(points);
        seeTree(root);

        KNN knn = new KNN();
        knn.init(points);
        KDTreeNode targetNode = knn.searchTarget(new int[]{7, 2});

        System.out.println("search [7,2]: " + targetNode);
        System.out.println("search [9,6]: " + knn.searchNearest(new int[]{8, 6}));
    }

    static void seeTree(KDTreeNode root) {
        List<KDTreeNode> levelNodes = new ArrayList<>();
        levelNodes.add(root);
        while (!levelNodes.isEmpty()) {
            List<KDTreeNode> nextLevelNodes = new ArrayList<>();
            for (KDTreeNode node : levelNodes) {
                System.out.print(node + "  ");
                if (node.getLeftChild() != null) {
                    nextLevelNodes.add(node.getLeftChild());
                }
                if (node.getRightChild() != null) {
                    nextLevelNodes.add(node.getRightChild());
                }
            }
            System.out.println();
            levelNodes = nextLevelNodes;
        }
    }

}
