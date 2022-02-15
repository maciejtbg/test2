package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Items {
    public ArrayList<String> items = new ArrayList<String>();
    FileReader plik = new FileReader("D://Items.txt");
    BufferedReader in = new BufferedReader(plik);

    public Items() throws IOException {
        String linijka;
        while ((linijka = in.readLine()) != null) {
            items.add(linijka);
        }
        in.close();

    }
//    public static void main(String[] args) throws IOException {
//        Items itemy = new Items();
//
//        System.out.println(itemy.items.toString());
//    }

}
