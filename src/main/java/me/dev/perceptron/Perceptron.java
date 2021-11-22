package me.dev.perceptron;

public class Perceptron {

    private double[] weight;
    private double learningRate;
    private ActiveFunction activeFunction;

    public Perceptron(double learningRate, int size, ActiveFunction activeFunction) {
        this.learningRate = learningRate;
        weight = new double[size + 1]; // the last one is the bias
        this.activeFunction = activeFunction;
    }

    public void learn(double[] input, boolean output) {
        assert input.length == weight.length - 1;
        double result = 0;
        for (int i = 0; i < input.length; i++) {
            result += input[i] * weight[i];
        }
        result += weight[input.length];
        boolean r = activeFunction.filter(result);
        if (r != output) {
            for (int i = 0; i < input.length; i++) {
                weight[i] += learningRate * input[i] * (output ? 1 : -1);
                weight[weight.length - 1] += learningRate * (output ? 1 : -1);
            }
        }
    }

    public boolean predicate(double[] input) {
        assert input.length == weight.length - 1;
        double result = 0;
        for (int i = 0; i < input.length; i++) {
            result += weight[i] * input[i];
        }
        result += weight[weight.length - 1];
        return activeFunction.filter(result);
    }

    public double[] getWeight() {
        double[] w = new double[weight.length - 1];
        System.arraycopy(weight, 0, w, 0, weight.length - 1);
        return w;
    }

    public double getBias() {
        return weight[weight.length - 1];
    }

}
