package me.dev.common;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImgReader {

    public static MyImage read(File file) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(file);
        System.out.println(bufferedImage.getData().getNumBands());
        System.out.println(bufferedImage.getColorModel());
        System.out.println(bufferedImage.getData().getBounds());
        return null;
    }

    public static void main(String[] args) {
        File file = new File("images/ChengHua.png");
        System.out.println(file.exists());
        try {
            read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
