package com.company;

import com.sun.jna.Library;
import com.sun.jna.win32.StdCallLibrary;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.*;
import net.sourceforge.tess4j.Tesseract;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.StdCallLibrary;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Logger;

import static com.company.Wyszukiwarka.zaznacz;
import static com.company.Wyszukiwarka.zaznacz;
import static com.sun.jna.Native.loadLibrary;
import static com.sun.jna.platform.win32.WinUser.SW_RESTORE;
import static java.awt.SystemColor.window;
import static java.awt.event.KeyEvent.*;
import static javax.swing.text.StyleConstants.Size;

public class Main {


    public interface User32 extends StdCallLibrary {
        final User32 instance = (User32) Native.loadLibrary("user32", User32.class);

        HWND FindWindowA(String winClass, String title);

        boolean ShowWindow(HWND hWnd, int nCmdShow);

        boolean SetForegroundWindow(HWND hWnd);


    }

//    Tesseract ts;
//    public BufferedImage getImage(String imgPath) throws IOException {
//        Mat mat = Imgcodecs.imread(imgPath);
//        Mat gray = new Mat();
//        Imgproc.cvtColor(mat,gray,Imgproc.COLOR_BGR2GRAY);
//        Mat resized = new Mat();
//        Size size = new Size(mat.width()*1.9f,mat.height()*1.9f);
//        Imgproc.resize(gray,resized,size);
//        MatOfByte mof = new MatOfByte();
//        byte imageByte[];
//        Imgcodecs.imencode(".png",resized,mof);
//        imageByte=mof.toArray();
//        return ImageIO.read(new ByteArrayInputStream(imageByte));
//    }
//    public Main(){
//        ts = new Tesseract();
//        ts.setDatapath("");
//        ts.setLanguage("eng");
//        try{
//        String text = ts.doOCR(getImage("D://nazwa.png"));
//    } catch (TesseractException e){
//            e.printStackTrace();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//
//    }


    public static void main(String[] args) throws Exception, IOException {

        Robot robot = new Robot();
        Przyciskacz przyciskacz = new Przyciskacz();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Zaczynam...");
        robot.delay(2000);
        System.out.println("Sprawdzam czy postac jest zalogowana.");
        boolean czyZalogowaloPostac = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie("D://localChat.png");
        if (czyZalogowaloPostac == false) {
            boolean result = CzyJestUruchomiony.isRunning("client_launcher.exe");
            if (result) {
                System.out.println("Tibia jest uruchomiona.");
            } else {
                Uruchamiacz.uruchomProgram("C://Users/PC/AppData/Local/Tibia/packages/Tibia/bin/client_launcher.exe");
                System.out.println("Uruchamiam Tibie...");
            }
            System.out.println("Zaczynam sprawdzac czy Tibia sie wlaczyla.");


            //Wyszukiwarka.zaznacz("D://screenDoSzukania2.png","D://oknoLogowania.png","D://output2.png",Imgproc.TM_CCOEFF_NORMED);

//        int czasNaUruchomienie=45000;
//        System.out.print("Odliczam "+czasNaUruchomienie/1000+" sekund na uruchomienie programu... ");
//        for (int i=0; i<czasNaUruchomienie; i+=1000){
//            System.out.print((i/1000+1)+" ");
//            robot.delay(1000 );
//        }
//        System.out.println();
//
//        //Teraz trzeba poczekać aż się włączy...
            System.out.println("Sprawdzam, czy Tibia wymaga UPDATE'u.");

            System.out.print("Oczekuje na uruchomienie Tibii.");
            boolean czyUruchomiona = false;
            boolean czyWymagaUpdate;
            boolean czyUpdateWykonany = false;
            while (czyUruchomiona == false) {
                robot.delay(500);
                czyWymagaUpdate = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie("D://update.png");
                if (czyWymagaUpdate == true) {
                    System.out.println("Tibia wymaga aktualizacji.");
                    robot.delay(500);
                    System.out.print("Rozpoczynam aktualizację.");
                    przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia("D://update.png"));
                    while (czyUpdateWykonany == false) {
                        przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia("D://updated.png"));
                        System.out.print(".");
                        czyUpdateWykonany = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie("D://updated.png");
                    }
                }
                czyUruchomiona = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie("D://oknoLogowania.png");
                System.out.print(".");


            }
            System.out.println("\nTibia uruchomiona.");


            Maksymalizator maksymalizator = new Maksymalizator();
            maksymalizator.Maksymalizuj("Tibia");

            przyciskacz.maksymalizujAktualneOkno();
            robot.delay(1000);
            przyciskacz.przyciskajSlowo("tibia123");
            przyciskacz.enter();
//        //tu trzeba sprawdzić czy się zalogowało
            System.out.print("Oczekuje na zalogowanie konta.");
            boolean czyZalogowalo = false;
            while (czyZalogowalo == false) {
                robot.delay(500);
                czyZalogowalo = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie("D://wyborPostaci.png");
                System.out.print(".");
            }
            System.out.println("\nKonto zalogowane.");
//        int czasNaZalogowanie=5000;
//        System.out.print("Odliczam "+czasNaZalogowanie/1000+" sekund na zalogowanie... ");
//        for (int i=0; i<czasNaZalogowanie; i+=1000){
//            System.out.print((i/1000+1)+" ");
//            robot.delay(1000 );
//        }
//        System.out.println("Zakonczylem odliczanie. Loguje postac.");
            przyciskacz.enter();
            System.out.println("Oczekuje na zalogowanie postaci.");
            while (czyZalogowaloPostac == false) {
                robot.delay(500);
                czyZalogowaloPostac = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie("D://localChat.png");
                System.out.print(".");
            }
            System.out.println("\nPostac zalogowana.");
            System.out.println("Oczekuje na znikniecie napisow powitalnych. 20 seknund.");
            robot.delay(20000);
        } else System.out.println("Postac jest zalogowana.");
        //tutaj dodać serwerlog 3 rozne kolorki, zmienic wyszukiwanie na boolean false/true
        robot.delay(1000);
        boolean czyServerLogJestWlaczony=Wyszukiwarka.sprawdzCzyJestObiektNaEkranie("D://ServerLog_WHITEON.PNG");
        if (czyServerLogJestWlaczony==false){
            System.out.println("ServerLog nie jest włączony. Włączam.");
            przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia("D://ServerLog_RED.png"));
        }
        System.out.println("SerwerLog jest włączony");
        System.out.println("Rozszerzam widok.");
        //Rozszerzenie widoku
        // tu trzeba przelaczyc na serwer czat
        Punkt punktPoczatkowy;
        punktPoczatkowy = Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia("D://belka.png");
        Punkt punktKoncowy;
        punktKoncowy = new Punkt(punktPoczatkowy.getWspX(), 585);
        przyciskacz.przeciagnijLewym(punktPoczatkowy, punktKoncowy);


        System.out.println("Przystepuje do otwierania Depo.");
        robot.delay(2000);
        przyciskacz.skierujPostacwDol();
        robot.delay(500);
        przyciskacz.kliknijPrawym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia("D://depo.png"));
        System.out.println("Wlaczylem depo. Przystepuje do otwierania marketu.");
//        //przyciskacz.kliknijPrawym(551,317);
        robot.delay(2000);
        przyciskacz.kliknijPrawym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia("D://market.png"));
//        //przyciskacz.kliknijPrawym(1329,488);
        //tutaj trzeba zaczac pentle po tablicy itemow

//        SprawdzanieDis sprawdzanieDis = new SprawdzanieDis();
//        sprawdzanieDis.start();
//        Browser browser = new Browser();
//        browser.start();

//        System.out.println(Core.VERSION);

//        System.loadLibrary("opencv_java310");
//        zaznacz("D://screenDoSzukania.png", "D://plecak.png",  "D://output.png", Imgproc.TM_CCOEFF_NORMED);
        Items itemki = new Items();
        ArrayList<Rekord> Rekordy = new ArrayList<>();
        Date nowDate = new Date();
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        robot.delay(2000);
        boolean czyPojawiloSieOstrzezenie = false;
        for (int iterator = 0; iterator < itemki.items.size(); iterator++) {
            przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia("D://searchBar.PNG"));
            robot.delay(1);
            String nazwaItemu = itemki.items.get(iterator);
            String slowoDoSzukania = itemki.items.get(iterator).toLowerCase(Locale.ROOT);
            przyciskacz.przyciskajSlowo(slowoDoSzukania);
//            System.out.println("Poprostu wciskam pierwszy rekord na liscie.");
            robot.delay(1);
            przyciskacz.kliknijLewym(400, 300);
            robot.delay(500);
            int cenaSprzedazy = 0;
            cenaSprzedazy = PobieraczCeny.zrobZdjecieCeny(true, nazwaItemu);
            int cenaKupna = 0;
            cenaKupna = PobieraczCeny.zrobZdjecieCeny(false, nazwaItemu);
            Rekord rekord = new Rekord("Thyria",nazwaItemu,sdf3,cenaSprzedazy,cenaKupna);
            Rekordy.add(rekord);
            PobieraczCeny.zapiszDaneDoPlikuTXT(nazwaItemu, cenaSprzedazy, cenaKupna);
            PobieraczCeny.zapiszDaneDoPlikuCSV(nazwaItemu, cenaSprzedazy, cenaKupna);
//            BazaDanych.zaladujDaneDoBazy(nazwaItemu,cenaSprzedazy,cenaKupna);
            przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia("D://erase.PNG"));
            robot.delay(1);

            czyPojawiloSieOstrzezenie = Wyszukiwarka.sprawdzCzyJestObiektNaEkranie("D://logoutWarning.png");

            if (czyPojawiloSieOstrzezenie == true) {


                przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia("D://closeMarket.png"));
                robot.delay(500);
                przyciskacz.kliknijLewym(Wyszukiwarka.zrobScreeniZwrocPunktDoKlikniecia("D://market.png"));
                robot.delay(500);

            }
            czyPojawiloSieOstrzezenie=false;

        }
        System.out.println("Zakończono pobieranie danych.");
        System.out.println("Wygenerowanie kompletne zapytanie bazodanowe:");
        System.out.println(Rekord.przeksztalcArrayWZapytanieBazodanowe(Rekordy));
        System.out.println("Laduje dane do bazy...");
//        BazaDanych.zaladujPrzygotowaneZapytanieDoBazy(Rekord.przeksztalcArrayWZapytanieBazodanowe(Rekordy));
        System.out.println("Dane zaladowane. Koniec programu.");
        Uruchamiacz.zamknijProgram();

    }
}



















