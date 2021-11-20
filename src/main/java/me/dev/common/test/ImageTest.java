package me.dev.common.test;

import me.dev.common.ImageShower;
import me.dev.common.Rectangle;
import me.dev.utils.MathUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageTest {

    static void seeMode() {
        File dir = new File("images-u");
        File[] files = dir.listFiles();
        for (File f : files) {
            try {
                BufferedImage bi = ImageIO.read(f);
                System.out.println(f.getName() + ":" + bi.getType());
                System.out.println(bi.getColorModel());
                System.out.println();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        seeMode();

        File file = new File("images/CoffeeBean.png");
        if (file.exists()) {
            return;
        }
        BufferedImage bufferedImage = ImageIO.read(file);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();

        BufferedImage newImage = new BufferedImage(width - 2, height - 2, bufferedImage.getType());

        Rectangle rectangle = new Rectangle(200, 40, 120, 250);
        int[][] kernelR = new int[][]{{0, 0, 1}, {0, 2, 0}, {1, 0, 0}};
        int[][] kernelG = new int[][]{{1, 1, 1}, {0, 0, 0}, {-1, -1, -1}};
        int[][] kernelB = new int[][]{{1, 1, 1}, {0, 0, 0}, {-1, -1, -1}};

        int readMask = 0xff0000;
        int writeMask = 0x00ffff;
        int shift = 16;

        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                int newColor = 0;
                for (int colorIdx = 0; colorIdx < 3; colorIdx++) {
                    int[][] colorMatrix = new int[3][3];
                    for (int ox = -1; ox <= 1; ox++) {
                        for (int oy = -1; oy <= 1; oy++) {
                            int nx = i + ox;
                            int ny = j + oy;
                            int color = bufferedImage.getRGB(nx, ny);
                            color &= (0xff << ((2 - colorIdx) * 8));
                            colorMatrix[ox + 1][oy + 1] = color >> ((2 - colorIdx) * 8);
                        }
                    }
                    int channelPixel = MathUtils.dotMultiple(colorMatrix, kernelR);
                    newColor <<= 8;
                    newColor |= channelPixel;
                }
                newImage.setRGB(i - 1, j - 1, newColor);
            }
        }
        ImageShower.showImage(newImage);
    }


}
