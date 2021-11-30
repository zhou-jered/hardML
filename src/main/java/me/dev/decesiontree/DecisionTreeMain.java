package me.dev.decesiontree;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class DecisionTreeMain {

    public static void main(String[] args) {
        final int n = 10;
        final int sz = 1000;
        final int inputValCardinal = 20;
        final int outputValCardinal = 50;
        List<int[]> input = new ArrayList<>();
        int[] output = new int[sz];
        for (int i = 0; i < sz; i++) {
            int[] singleInput = new int[n];
            for (int j = 0; j < n; j++) {
                singleInput[j] = RandomUtils.nextInt(0, inputValCardinal);
            }
            input.add(singleInput);
            output[i] = RandomUtils.nextInt(0, outputValCardinal);
        }
        DecisionTreeID3 decisionTreeID3 = new DecisionTreeID3(n);
        decisionTreeID3.learn(input, output);

        final int testCounter = 1000;
        int rightCnt = 0;
        int errCnt = 0;
        for (int i = 0; i < testCounter; i++) {
            final int testDataIdx = RandomUtils.nextInt(0, sz);
            int predictOutput = decisionTreeID3.predict(input.get(testDataIdx));
            if (predictOutput == output[testDataIdx]) {
                rightCnt++;
            } else {
                errCnt++;
            }
        }

        System.out.format("right: %s, err:%s, accuracy: %.2f\n", rightCnt, errCnt, (rightCnt * 1.0 / (rightCnt + errCnt)));

    }

}
