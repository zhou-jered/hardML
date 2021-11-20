package me.dev.utils;

import javax.imageio.ImageIO;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ColorModel;
import java.io.File;

public class ImageUtils {

    private static int UNIFORM_IMAGE_TYPE = BufferedImage.TYPE_3BYTE_BGR;

    public static BufferedImage toUniformImageType(BufferedImage bufferedImage) {
        return transformType(bufferedImage, UNIFORM_IMAGE_TYPE);
    }

    public static BufferedImage transformType(BufferedImage originImage, int type) {
        if (originImage.getType() == type) {
//            return originImage;
        }
        BufferedImage newImage = new BufferedImage(originImage.getWidth(), originImage.getHeight(), UNIFORM_IMAGE_TYPE);
        ColorConvertOp coo = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_LINEAR_RGB), null);
        coo.filter(originImage, newImage);
        return newImage;
    }

    public static void main(String[] args) throws Exception{
        File dir = new File("images");
        File[] imgs = dir.listFiles();
        for(File f : imgs) {
            BufferedImage img = ImageIO.read(f);
            System.out.println(f.getName());
            BufferedImage uniformed = toUniformImageType(img);
            String originfn = f.getName().split("\\.")[0];
            ImageIO.write(uniformed,"jpg", new File("images-u/"+originfn+"_unifom.jpg"));
            System.out.println("Done:"+originfn);
        }
    }

}
