package me.dev.knn;

import java.util.Arrays;


public class KDTreeNode {
    private KDTreeNode parent;
    private KDTreeNode leftChild;
    private KDTreeNode rightChild;
    private int[] data;
    private int depth;
    private int splitDimension;
    private int nodeVal;  // split value

    public boolean samePoint(int[] point) {
        return Arrays.equals(data, point);
    }

    void setLeft(KDTreeNode leftChild) {
        this.leftChild = leftChild;
        leftChild.parent = this;
    }

    void setRight(KDTreeNode rightChild) {
        this.rightChild = rightChild;
        rightChild.parent = this;
    }

    void setData(int[] data) {
        this.data = data;
    }

    void setSplitDimension(int splitDimension) {
        this.splitDimension = splitDimension;
    }

    void setNodeVal(int nodeVal) {
        this.nodeVal = nodeVal;
    }

    void setDepth(int depth) {
        this.depth = depth;
    }

    public KDTreeNode getParent() {
        return parent;
    }

    public KDTreeNode getLeftChild() {
        return leftChild;
    }

    public KDTreeNode getRightChild() {
        return rightChild;
    }

    public int[] getData() {
        return data;
    }

    public int getSplitDimension() {
        return splitDimension;
    }

    public int getNodeVal() {
        return nodeVal;
    }

    @Override
    public String toString() {
        return "KDTreeNode{" + "data=" + Arrays.toString(data) + ", depth=" + depth + ", splitDimension=" + splitDimension + ", nodeVal=" + nodeVal + '}';
    }
}
