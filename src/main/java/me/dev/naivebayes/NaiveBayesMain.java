package me.dev.naivebayes;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class NaiveBayesMain {

    public static void main(String[] args) {
        final int n = 5;
        final int k = 10;
        final int sz = 100;

        //prepare the data
        List<int[]> learningInput = randomInput(n, sz);
        int[] y = new int[sz];
        for (int i = 0; i < sz; i++) {
            y[i] = RandomUtils.nextInt(0, k);
        }

        NaiveBayes naiveBayes = new NaiveBayes(n, k);
        naiveBayes.learn(learningInput, y);
        System.out.println("start predict:");
        int right = 0;
        int failed = 0;
        for (int i = 0; i < sz; i++) {
            PredictResult pr = naiveBayes.predict(learningInput.get(i));
            System.out.println((pr.getCategory() == y[i]) + " : " + pr.getPossibility() + " " + diffInfoIfNecessary(pr, y[i]));
            if (pr.getCategory() == y[i]) {
                right++;
            } else {
                failed++;
            }
        }

        System.out.println("Accuracy:" + (right * 1.0 / sz));
    }

    private static List<int[]> randomInput(int n, int size) {
        List<int[]> randomData = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int[] d = new int[n];
            for (int j = 0; j < n; j++) {
                d[j] // if I am a DJ, would you love me ?
                        = RandomUtils.nextInt(0, 50);
            }
            randomData.add(d);
        }
        return randomData;
    }


    private static String diffInfoIfNecessary(PredictResult predictResult, int expect) {
        if (expect == predictResult.getCategory()) {
            return "";
        } else {
            return "expect:" + expect + ", but " + predictResult.getCategory() + " with possibility:" + predictResult.getPossibility();
        }

    }


}
