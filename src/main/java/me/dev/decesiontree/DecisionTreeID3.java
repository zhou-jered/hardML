package me.dev.decesiontree;

import com.google.common.collect.Sets;
import me.dev.common.TrainData;
import me.dev.utils.DataUitls;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * build decision tree based on information gain
 */
public class DecisionTreeID3 {

    private final int n;
    TreeNode root;

    // the input dimension
    public DecisionTreeID3(int n) {
        this.n = n;
    }


    public void learn(List<int[]> input, int[] output) {
        root = buildTreeInternal(input, output, new HashSet<>());
    }


    public int predict(int[] input) {
        TreeNode current = root;
        while (current != null && !current.isLeaf()) {
            current = current.getChild(input);
        }
        return current.getCategory();
    }


    private TreeNode buildTreeInternal(List<int[]> input, int[] y, Set<Integer> usedDimensions) {
        if (y.length == 1) {
            TreeNode treeNode = TreeNode.builder().category(y[0]).isLeaf(true).build();
            return treeNode;
        }
        if (usedDimensions.size() == n) {
            return null;
        }

        double maxIG = -1;
        int maxIGDimension = -1;


        final double yEntropy = Entropy.calEntropy(y);
        for (int i = 0; i < n; i++) {
            if (usedDimensions.contains(i)) {
                continue;
            }
            double infGain = yEntropy - Entropy.calConditionalEntropy(input, y, i);
            if (infGain > maxIG) {
                maxIG = infGain;
                maxIGDimension = i;
            }
        }

        TreeNode thisNode = TreeNode.builder().splitDimension(maxIGDimension).isLeaf(false).build();
        final int cardinalNumber = DataUitls.counterDimensionCardinalNumber(input, maxIGDimension);
        for (int i = 0; i < cardinalNumber; i++) {
            TrainData trainData = DataUitls.filterTrainDataByDimensionValue(input, y, maxIGDimension, i);
            if (trainData.getInput().size() > 0) {
                Set<Integer> childUsedDimensions = Sets.newHashSet(usedDimensions);
                childUsedDimensions.add(maxIGDimension);
                TreeNode childNode = buildTreeInternal(trainData.getInput(), trainData.getOutput(), childUsedDimensions);
                thisNode.addChild(childNode, i);
            }
        }
        return thisNode;

    }
}
