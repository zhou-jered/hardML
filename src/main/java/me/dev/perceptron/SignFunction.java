package me.dev.perceptron;

public class SignFunction implements ActiveFunction {
    @Override
    public boolean filter(double val) {
        return val >= 0;
    }
}
