package com.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Rekord {
    String world;
    String nazwaItemuDoTablicy;
    SimpleDateFormat data;
    int cenaSell, cenaBuy;


    public Rekord(String world, String nazwaItemuDoTablicy, SimpleDateFormat data, int cenaSell, int cenaBuy) {
        this.world = world;
        this.nazwaItemuDoTablicy = nazwaItemuDoTablicy;
        this.data = data;
        this.cenaSell = cenaSell;
        this.cenaBuy = cenaBuy;
    }

    public static String przeksztalcArrayWZapytanieBazodanowe(ArrayList<Rekord> lista){
        String koniecLinii = ";\n";
        String kompletneZapytanie = "";
        int licznik = 0;
        for (Rekord iterableRekord: lista){
            String nazwaBezSpacji = iterableRekord.nazwaItemuDoTablicy.replace(' ','_').replace('\'','_');
                    Date nowDate = new Date();//Tu cos jeszcze musze zrobic zeby wywalic sekundy
            String zapytanie = "INSERT INTO " + nazwaBezSpacji + "(czas, cena_sell, cena_buy) VALUES ('" + iterableRekord.data.format(nowDate) + "','"+iterableRekord.cenaSell+"','"+iterableRekord.cenaBuy+"')";
            kompletneZapytanie += zapytanie;
            licznik++;
            if (licznik!=lista.size()){
                kompletneZapytanie += koniecLinii;
            }
        }
//        stmt.executeUpdate("INSERT INTO Tibia_Coins(czas, cena_sell, cena_buy) VALUES ('"+dataDoZapisuwPliku+"','"+cenaSprzadazy+"','"+cenaKupna+"')");
//        System.out.println("Załadowałem dane: CENA ");
        return kompletneZapytanie;
    }


}
