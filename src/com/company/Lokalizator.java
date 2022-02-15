package com.company;

import java.awt.*;

public class Lokalizator {


    public static void cls(){
        try{
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void start(){


        int x,y;
        while (true){
            x= MouseInfo.getPointerInfo().getLocation().x;
            y= MouseInfo.getPointerInfo().getLocation().y;
            System.out.println("X : "+x+" Y: "+y);
            cls();
        }
    }
    public static void main(String[] args){
        new Lokalizator().start();
    }
}
