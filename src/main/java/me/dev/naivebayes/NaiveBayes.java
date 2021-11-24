package me.dev.naivebayes;

import me.dev.utils.MathUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class NaiveBayes {

    private int n; // the size of the input dimension
    private int k; // the output category number

    private int[][] inputDimensionCardinalNumber;
    private int[] kCardinalNumber;
    //    private double[][] xPossibility; // store X(xi) xPossibility[dimension][val] = possibility
    private double[] yPossibility; // store P(Y)


    /**
     * the condition possibility  Pyx[i][j][p]  means the category i 's j dimension's p value's possibility
     * a little complicated, but remember this important field
     */
    private double[][][] pyxPossibility; //

    private AtomicBoolean learn = new AtomicBoolean(false);

    public NaiveBayes(int n, int k) {
        this.n = n;
        this.k = k;
        kCardinalNumber = new int[k]; // record the output cardinal number
        yPossibility = new double[k]; //
        inputDimensionCardinalNumber = new int[n][];
        pyxPossibility = new double[k][n][];
    }

    public void learn(List<int[]> input, int[] output) {
        if (!learn.compareAndSet(false, true)) {
            throw new RuntimeException("Had Learn");
        }
        for (int i = 0; i < output.length; i++) {
            kCardinalNumber[output[i]]++;
        }
        for (int i = 0; i < k; i++) {
            yPossibility[i] = kCardinalNumber[i] * 1.0 / output.length;
        }
        assert Math.abs(MathUtils.sum(yPossibility) - 1.0) < 1e-8;
        int[] dimensionCardinal = new int[n];

        //count each dimension's cardinal
        for (int i = 0; i < input.size(); i++) {
            int[] d = input.get(i);
            for (int j = 0; j < n; j++) {
                dimensionCardinal[j] = Math.max(dimensionCardinal[j], d[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            final int sz = dimensionCardinal[i] + 1;
            inputDimensionCardinalNumber[i] = new int[sz];
//            xPossibility[i] = new double[sz];
        }

        for (int k_idx = 0; k_idx < k; k_idx++) {
            for (int i = 0; i < n; i++) {
                Arrays.fill(inputDimensionCardinalNumber[i], 0);
            }
            for (int i = 0; i < input.size(); i++) {
                if (output[i] != k_idx) {
                    continue;
                }
                int[] d = input.get(i);
                for (int j = 0; j < n; j++) {
                    inputDimensionCardinalNumber[j][d[j]]++;
                }
            }

            for (int i = 0; i < n; i++) {
                //calculate the i dimension's independent conditional possibility
                // the naive bayes' Hypothesis
                final int total = MathUtils.sum(inputDimensionCardinalNumber[i]);
                final int dimenSize = dimensionCardinal[i];
                pyxPossibility[k_idx][i] = new double[dimenSize + 1];
                for (int dimension = 0; dimension <= dimenSize; dimension++) {
                    pyxPossibility[k_idx][i][dimension] = inputDimensionCardinalNumber[i][dimension] * 1.0 / total;
                }
            }
        }


    }


    public PredictResult predict(int[] input) {
        double[] possibility = new double[k];
        for (int k_idx = 0; k_idx < k; k_idx++) {
            double kPossibility = yPossibility[k_idx];
            for (int j = 0; j < n; j++) {
                kPossibility *= pyxPossibility[k_idx][j][input[j]];
            }
            possibility[k_idx] = kPossibility;
        }
        double max = -1;
        PredictResult result = null;
        for (int k_idx = 0; k_idx < k; k_idx++) {
            if (possibility[k_idx] > max) {
                result = new PredictResult(k_idx, possibility[k_idx]);
                max = possibility[k_idx];
            }
        }
        return result;
    }

}
