package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Zwiekszacz {
    private static BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT) {



        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }
    public void zwieksz(String nazwaStaregoPliku, String nazwaNowegoPliku) {
        try {

//            System.out.println("Przyjmuje plik do powiekszenia o nazwie: "+nazwaStaregoPliku);
            BufferedImage originalImage = ImageIO.read(new File("D://"+nazwaStaregoPliku+".png"));//change path to where file is located
            int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
//            System.out.println("Przyjalem do powiekszenia plik o nazwie: "+nazwaStaregoPliku);

            BufferedImage resizeImageJpg = resizeImage(originalImage, type, originalImage.getWidth()*2, originalImage.getHeight()*2);
            ImageIO.write(resizeImageJpg, "png", new File("D://"+nazwaNowegoPliku+".png")); //change path where you want it saved
//            System.out.println("Zmienilem rozmiar obrazu i napisalem pod nazwa: "+nazwaNowegoPliku);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}