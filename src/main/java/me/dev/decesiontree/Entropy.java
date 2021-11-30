package me.dev.decesiontree;

import me.dev.utils.DataUitls;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Entropy {

    public static double calEntropy(int[] vars) {
        int total = vars.length;
        int maxEle = Arrays.stream(vars).max().getAsInt();
        int[] counters = new int[maxEle + 1]; // note equals
        for (int i = 0; i < vars.length; i++) {
            counters[vars[i]]++;
        }
        final double log2Base = Math.log(2);
        double negativeTemp = 0;
        for (int i = 0; i <= maxEle; i++) {
            final int cnt = counters[i];
            if (cnt > 0) {
                double p = cnt * 1. / total;
                negativeTemp += p * (Math.log(p) / log2Base);
            }
        }
        return -negativeTemp;
    }

    /**
     * 条件熵H(Y|X)表示在已知随机变量X的条件下随机变量Y的不确定性，
     * 随机变量X给定的条件下随机变量Y的条件熵 H（Y｜X）定义为
     * X 给定条件下Y的条件概率分布的熵对X的数学期望
     * H(Y|X) = ∑p(i) * H(Y | X=x(i))
     *
     * @return
     */
    public static double calConditionalEntropy(List<int[]> input, int[] output, int conditionDimension) {
        int inputDimensionValCardinal = DataUitls.counterDimensionCardinalNumber(input, conditionDimension);
        int[] inputDimValCounter = new int[inputDimensionValCardinal];
        double[] dimPossibility = new double[inputDimensionValCardinal];
        double[] conditionalDistributionEntropy = new double[inputDimensionValCardinal];

        input.forEach(d -> {
            inputDimValCounter[d[conditionDimension]]++;
        });
        for (int i = 0; i < inputDimensionValCardinal; i++) {
            dimPossibility[i] = inputDimValCounter[i] * 1.0 / input.size();
        }

        for (int i = 0; i < inputDimensionValCardinal; i++) {
            List<Integer> filteredByInputDimValOutputList = new ArrayList<>();
            final int expectDimensionVal = i;
            for (int j = 0; j < input.size(); j++) {
                if (input.get(j)[conditionDimension] == expectDimensionVal) {
                    filteredByInputDimValOutputList.add(output[j]);
                }
            }
            int[] conditionOutput = new int[filteredByInputDimValOutputList.size()];
            for (int e = 0; e < filteredByInputDimValOutputList.size(); e++) {
                conditionOutput[e] = filteredByInputDimValOutputList.get(e);
            }
            if (conditionOutput.length > 0) {
                conditionalDistributionEntropy[i] = calEntropy(conditionOutput);
            } else {
                conditionalDistributionEntropy[i] = 0;
            }

        }

        double conditionalEntropy = 0;
        for (int i = 0; i < inputDimensionValCardinal; i++) {
            conditionalEntropy += dimPossibility[i] * conditionalDistributionEntropy[i];
        }
        return conditionalEntropy;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 1, 1, 1, 1, 1, 1};
        int[] a1 = new int[]{1, 1, 1, 1, 12, 2, 2};
        int[] a2 = new int[]{1, 1, 1, 2, 3, 4, 5, 6};
        double pa = calEntropy(a);
        double pa1 = calEntropy(a1);
        double pa2 = calEntropy(a2);
        assert pa < pa1;
        assert pa1 < pa2;
        System.out.println(pa);
        System.out.println(pa1);
        System.out.println(pa2);
        int[] random = new int[100];
        for (int i = 0; i < random.length; i++) {
            random[i] = RandomUtils.nextInt(0, 3);
        }
        System.out.println(calEntropy(random));
    }

}
