package me.dev.common;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

public class ImageShower {
    public static void showImage(BufferedImage bufferedImage) {
        String tempFn = "/tmp/Hardml/" + UUID.randomUUID().toString() + ".jpg";
        if (!new File(tempFn).getParentFile().exists()) {
            new File(tempFn).getParentFile().mkdirs();
        }
        try {
            ImageIO.write(bufferedImage, "png", new File(tempFn));
            showImage(tempFn);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void showImage(String fullPath) {
        try {
            Runtime.getRuntime().exec("open " + fullPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showImage(File file) {
        showImage(file.getAbsolutePath());
    }


}
