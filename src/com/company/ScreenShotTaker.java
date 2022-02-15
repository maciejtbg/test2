package com.company;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

public class ScreenShotTaker {
    public static String RESOURCES_DIR = "D:/";
    private Robot robot;
    private Rectangle screenRct;
    public int poczX,poczY,konX,konY;

    public ScreenShotTaker(int poczX, int poczY, int konX, int konY) throws AWTException {
        robot = new Robot();
        this.poczX = poczX;
        this.poczY = poczY;
        this.konX = konX;
        this.konY = konY;
        //width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        //height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        int szerokosc = konX - poczX;
        int wysokosc = konY - poczY;
//        System.out.println("Prostokat [Wysokosc: "+wysokosc+" Szerokosc: "+szerokosc+"]");
        screenRct = new Rectangle(poczX,poczY, szerokosc, wysokosc);
    }
    public void zrobZdjecieiZapisz(String nazwaPliku) throws AWTException, IOException{
        if(!new File(RESOURCES_DIR).exists()){
            Logger.getAnonymousLogger().warning("Katalog docelowy nie istnieje! Tworzę nowy katalog!");
            new File(RESOURCES_DIR).mkdirs();
        }
        (new ScreenShotTaker(poczX,poczY,konX,konY)).makeAndSave(new File(RESOURCES_DIR + "/"+ nazwaPliku+".png"));
//        System.out.println("Zrobilem zrzut ekranu: "+RESOURCES_DIR+"/"+nazwaPliku+".png");
    }

    public void makeAndSave(File target) throws IOException {
        save(target, make());
    }

    private void save(File target, BufferedImage image) throws IOException {
        ImageIO.write(image, "png", target);
        
    }

    public BufferedImage make() {
        if (robot != null) {
//            System.out.println("Wykonalem screenshot: "+screenRct.toString());
            return robot.createScreenCapture(screenRct);
        }
        return null;
    }
}
