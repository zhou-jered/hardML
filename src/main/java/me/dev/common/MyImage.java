package me.dev.common;

import lombok.Data;

@Data
public class MyImage {
    private ImageFormat format;
    private int width;
    private int height;
    private int depth;
    private byte[][][] pixels; //width, height, depth
}
