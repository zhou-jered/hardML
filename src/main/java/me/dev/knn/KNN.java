package me.dev.knn;

import java.util.List;

public class KNN {

    KDTreeNode kdTreeRoot;

    public void init(List<int[]> points) {
        kdTreeRoot = KDTreeFactory.buildTree(points);
    }

    public KDTreeNode searchNearest(int[] targetPoint) {
        return searchNearestNodeInternal(kdTreeRoot, targetPoint);

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

    public KDTreeNode findNearestLeaf(int[] point) {
        return findNearestLeaf(kdTreeRoot, point);
    }


    public KDTreeNode findNearestLeaf(KDTreeNode node, int[] point) {
        KDTreeNode currentNode = node;
        while (true) {
            if (currentNode.samePoint(point)) {
                return currentNode;
            }
            if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
                return currentNode;
            }
            if (currentNode.getLeftChild() == null) {
                currentNode = currentNode.getRightChild();
                continue;
            }
            if (currentNode.getRightChild() == null) {
                currentNode = currentNode.getLeftChild();
                continue;
            }
            int splitIdx = currentNode.getSplitDimension();
            int splitVal = currentNode.getNodeVal();
            int pointVal = point[splitIdx];
            if (pointVal <= splitVal) {
                currentNode = currentNode.getLeftChild();
            } else {
                currentNode = currentNode.getRightChild();
            }
        }
    }

    /**
     * 从fromNode开始查找距离point最近的点
     *
     * @param fromNode
     * @param targetPoint
     * @return
     */
    private KDTreeNode searchNearestNodeInternal(KDTreeNode fromNode, int[] targetPoint) {
        KDTreeNode nearestLeaf = findNearestLeaf(fromNode, targetPoint);
        if (nearestLeaf.samePoint(targetPoint)) {
            return nearestLeaf;
        }

        KDTreeNode currentNearest = nearestLeaf;
        double currentDistance = currentNearest.distance(targetPoint);
        KDTreeNode candidate = null;
        while (candidate != null && candidate != fromNode) {
            candidate = currentNearest.getParent();

            double candidateDistance = candidate.distance(targetPoint);
            if (candidateDistance < currentDistance) {
                currentDistance = candidateDistance;
                nearestLeaf = candidate;
            }

            if (Math.abs(targetPoint[candidate.getSplitDimension()] - candidate.getNodeVal()) < currentDistance) {
                KDTreeNode otherSiteNearestNode = searchNearestNodeInternal(getBrother(candidate, currentNearest), targetPoint);
                double otherSideDistance = otherSiteNearestNode.distance(targetPoint);
                if (otherSideDistance < currentDistance) {
                    currentDistance = otherSideDistance;
                    currentNearest = otherSiteNearestNode;
                }
            }
        }
        return nearestLeaf;
    }

    private KDTreeNode getBrother(KDTreeNode parent, KDTreeNode child) {
        if (child == null) {
            return parent.getLeftChild() == null ? parent.getRightChild() : parent.getLeftChild();
        }
        return parent.getLeftChild() == child ? parent.getRightChild() : parent.getLeftChild();
    }

}
