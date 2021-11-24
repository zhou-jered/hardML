package me.dev.utils;

import java.util.Arrays;

public class MathUtils {

    public static int dotMultiple(int[][] a, int[][] b) {
        int w = a.length;
        int h = a[0].length;
        int result = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                result += a[i][j] * b[i][j];
            }
        }
        return result;
    }

    public static double sum(double[] eles) {
        return Arrays.stream(eles).sum();
    }

    public static int sum(int[] eles) {
        return Arrays.stream(eles).sum();
    }


}
