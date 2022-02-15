package com.company;

import org.opencv.core.Core;

import java.awt.*;
import java.io.IOException;

import static org.opencv.imgproc.Imgproc.TM_SQDIFF_NORMED;

public class Proba {

    public static void main(String[] args) throws Exception {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        Wyszukiwarka.zaznacz("D://screenDoSzukania.PNG","D://belka.PNG","D://wynikBelki.png",TM_SQDIFF_NORMED);
//        System.out.println("Zaczynam...");
        Robot robot = new Robot();

        Przyciskacz przyciskacz = new Przyciskacz();
//        Punkt punktPoczatkowy;
//        punktPoczatkowy = Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia("D://belka.png");
//        Punkt punktKoncowy;
//        punktKoncowy = new Punkt(punktPoczatkowy.getWspX(),585);
//        przyciskacz.przeciagnijLewym(punktPoczatkowy,punktKoncowy);




        System.out.println("Zaczynam...");
        robot.delay(2000);
        przyciskacz.Prawo();
        robot.delay(500);
        przyciskacz.Lewo();
//        int stoper=0;
//        while(Wyszukiwarka.sprawdzCzyJestObiektNaEkranie("D://lost_connection.png") == false){
//            robot.delay(1000);
//            stoper++;
//        }
//        System.out.println("Czas potrzebny na wylogwanie to: "+stoper+" sekund, czyli "+stoper/60+" minut.");
//
//
//        przyciskacz.przyciskajSlowo("alehandro'alehandro");

    }
}
