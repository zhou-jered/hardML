package me.dev.decesiontree;

import java.util.List;

public interface DecisionTree {
    void learn(List<int[]> input, int[] output);

    int predict(int[] input);
}
