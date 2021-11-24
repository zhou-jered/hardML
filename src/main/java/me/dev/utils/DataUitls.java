package me.dev.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataUitls {

    public static List<int[]> filterInputByOutput(List<int[]> input, int[] output, int filterOutputVal) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            if (output[i] == filterOutputVal) {
                result.add(input.get(i));
            }
        }
        return result;
    }


    /**
     * vertical filter
     *
     * @param input
     * @param dimension
     * @param dimensionVal
     * @return
     */
    public static List<int[]> filterInputByDimensionValue(List<int[]> input, int dimension, int dimensionVal) {
        List<int[]> result = new ArrayList<>();
        input.forEach(ip -> {
            if (ip[dimension] == dimensionVal) {
                result.add(ip);
            }
        });
        return result;
    }

    public static int countDimensionCardinalNumber(int[] data) {
        return Arrays.stream(data).max().getAsInt() + 1;
    }

    public static int counterDimensionCardinalNumber(List<int[]> input, int dimension) {
        int val = 0;
        for (int i = 0; i < input.size(); i++) {
            val = Math.max(input.get(i)[dimension], val);
        }
        return val + 1;
    }

}
