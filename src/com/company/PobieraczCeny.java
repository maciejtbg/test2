package com.company;

import com.sun.jdi.IntegerValue;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class PobieraczCeny {
    public static int zrobZdjecieCeny(boolean czySprzedaz, String nazwaItemu) throws Exception {


        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
//        //Tworzy nazwę pliku na podstawie daty.
        Date nowDate = new Date();
        String nazwaPlikuDoRozpoznania = sdf1.format(nowDate);
//        System.out.println("Plik bedzie posiadal nazwe w formacie [yyyy-MM-dd hh-mm]: " + nazwaPlikuDoRozpoznania);
        int poczatekY, koniecY;
        System.out.print("Dla "+nazwaItemu+ " cena ");
        String nazwaTransakcji;

        if (czySprzedaz == true) {
            nazwaTransakcji = "sprzedazy";
            poczatekY = 191;
            koniecY = 206;
        } else {
            nazwaTransakcji = "kupna";
            poczatekY = 372;
            koniecY = 387;
        }
        System.out.print(nazwaTransakcji + " to: ");
        ScreenShotTaker screenShotTaker = new ScreenShotTaker(685, poczatekY, 767, koniecY);
        screenShotTaker.zrobZdjecieiZapisz(nazwaPlikuDoRozpoznania);
        Zwiekszacz zwiekszacz = new Zwiekszacz();
        String nazwaPlikuRozpoznanego = nazwaPlikuDoRozpoznania + "_sizeUp";
        zwiekszacz.zwieksz(nazwaPlikuDoRozpoznania, nazwaPlikuRozpoznanego);
        RecognizeText recognizeText = new RecognizeText();
        String cenaString = recognizeText.Rozpoznanie(nazwaPlikuRozpoznanego);
//        System.out.println("Test-test, cenaString to:"+cenaString);
        String cenaBezKropki = cenaString.replace(".", "");
//        System.out.println("Test-test, cenaBezKropki to:"+cenaBezKropki);
//
//        System.out.print("Cena "+nazwaTransakcji+" to....."+cenaBezKropki+".....");
        int cenaInt = 0;
        if (cenaBezKropki != "") {
            char tablica[] = new char[cenaBezKropki.length()];
            String cenaCzysta = "";

            for (int i = 0; i < tablica.length; i++) {
                tablica[i] = cenaBezKropki.charAt(i);
                if (tablica[i] == '0' ||
                        tablica[i] == '1' ||
                        tablica[i] == '2' ||
                        tablica[i] == '3' ||
                        tablica[i] == '4' ||
                        tablica[i] == '5' ||
                        tablica[i] == '6' ||
                        tablica[i] == '7' ||
                        tablica[i] == '8' ||
                        tablica[i] == '9') {
                    cenaCzysta = cenaCzysta + tablica[i];
                }
//                System.out.println("Tworze czysta cene "+nazwaTransakcji+": "+cenaCzysta);
//                System.out.println(i+">>>>>"+Character.getNumericValue(tablica[i]));
                cenaInt = Integer.parseInt(cenaCzysta);
            }
        } else cenaInt = Integer.valueOf(cenaInt);


//        System.out.println("Cena "+nazwaTransakcji+" przerobiona na Integer: "+cenaBezKropki);
        System.out.println(cenaInt+".");

        return cenaInt;
    }

    public static void zapiszDaneDoPlikuTXT(String nazwaItemu, Integer cenaSell, Integer cenaBuy) throws IOException {
        File plik = new File("D://Thyria/" + nazwaItemu + ".txt");
        if (plik.exists() == false) {
            plik.createNewFile();
        }
        Date nowDate = new Date();
        FileWriter zapisywacz = new FileWriter(plik, true);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dataDoZapisuwPliku = sdf2.format(nowDate);
//        System.out.println("Zapisuje kolejny rekord w pliku pod data: "+dataDoZapisuwPliku+" sell,buy.");
        BufferedWriter out = new BufferedWriter(zapisywacz);
        out.write(dataDoZapisuwPliku + "," + cenaSell + "," + cenaBuy + ",DATA/SELL/BUY");
        out.newLine();
        out.close();
    }

    public static void zapiszDaneDoPlikuCSV(String nazwaItemu, Integer cenaSell, Integer cenaBuy) throws IOException {
        File plik = new File("D://Thyria/" + nazwaItemu + ".csv");
        if (plik.exists() == false) {
            plik.createNewFile();
        }
        Date nowDate = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dataDoZapisuwPliku = sdf2.format(nowDate);
        String out = dataDoZapisuwPliku + "," + cenaSell + "," + cenaBuy + "\n";
        FileWriter pw = new FileWriter(plik, true);
        pw.write(out);
        pw.close();
    }

    public static int sprawdzMaxCenezCSV(String nazwaItemu) throws FileNotFoundException {
        File plik = new File("D://Thyria/" + nazwaItemu + ".csv");
        Scanner fr = new Scanner(plik);
        String ostatniaLinia = null;
        while (fr.hasNextLine()) {
            ostatniaLinia = fr.nextLine();
        }
        int numerPrzecinka = ostatniaLinia.indexOf(',');
        String prawieOstatniString = ostatniaLinia.substring(numerPrzecinka+2);
        int numerOstatniegoPrzecinka = prawieOstatniString.indexOf(',');
        String ostatniaLiczba = ostatniaLinia.substring(ostatniaLinia.length()-numerOstatniegoPrzecinka-1);
        int ostatniaLiczbaInt= Integer.parseInt(ostatniaLiczba);

        return ostatniaLiczbaInt;
    }



    }

