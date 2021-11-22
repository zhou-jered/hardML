package me.dev.perceptron;

import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PerceptronMain {

    public static void main(String[] args) {
        Perceptron perceptron = new Perceptron(0.05, 2, new SignFunction());
        List<double[]> positive = new ArrayList<>();
        List<double[]> negative = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            double x = Math.random() * 100;
            double y = Math.random() * 100;
            positive.add(new double[]{x, y});
            negative.add(new double[]{-x, -y});
        }
        System.out.println("start training");
        for (int i = 0; i < 1000; i++) {
            double[] randomInput = positive.get(RandomUtils.nextInt(0, positive.size()));
            perceptron.learn(randomInput, true);

            double[] randomInputNegative = positive.get(RandomUtils.nextInt(0, positive.size()));
            perceptron.learn(randomInputNegative, false);
        }
        System.out.println("traing completed...");
        System.out.println("Weight: " + Arrays.toString(perceptron.getWeight()));
        System.out.println("Bias:" + perceptron.getBias());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("input test point[x,y]:");
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            double[] testPoint = new double[]{x, y};
            System.out.println("predict:" + perceptron.predicate(testPoint));
        }
    }

}
