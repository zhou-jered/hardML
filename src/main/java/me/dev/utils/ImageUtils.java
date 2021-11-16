package me.dev.utils;

import java.awt.image.BufferedImage;

public class ImageUtils {

    public static BufferedImage transformType(BufferedImage originImage, int type) {
        if (originImage.getType() == type) {
            return originImage;
        }
        switch (originImage.getType()) {

        }
        return originImage;
    }

}
