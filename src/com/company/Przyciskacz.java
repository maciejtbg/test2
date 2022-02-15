package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.event.KeyEvent.*;

public class Przyciskacz {
    Robot robot = new Robot();
    public Map<Character, Integer> mapaznakow = new HashMap();



    public Przyciskacz() throws AWTException {
        mapaznakow.put('0',48);
        mapaznakow.put('1',49);
        mapaznakow.put('2',50);
        mapaznakow.put('3',51);
        mapaznakow.put('4',52);
        mapaznakow.put('5',53);
        mapaznakow.put('6',54);
        mapaznakow.put('7',55);
        mapaznakow.put('8',56);
        mapaznakow.put('9',57);
        mapaznakow.put('a',VK_A);
        mapaznakow.put('b',VK_B);
        mapaznakow.put('c',VK_C);
        mapaznakow.put('d',VK_D);
        mapaznakow.put('e',VK_E);
        mapaznakow.put('f',VK_F);
        mapaznakow.put('g',VK_G);
        mapaznakow.put('h',VK_H);
        mapaznakow.put('i',VK_I);
        mapaznakow.put('j',VK_J);
        mapaznakow.put('k',VK_K);
        mapaznakow.put('l',VK_L);
        mapaznakow.put('m',VK_M);
        mapaznakow.put('n',VK_N);
        mapaznakow.put('o',VK_O);
        mapaznakow.put('p',VK_P);
        mapaznakow.put('q',VK_Q);
        mapaznakow.put('r',VK_R);
        mapaznakow.put('s',VK_S);
        mapaznakow.put('t',VK_T);
        mapaznakow.put('u',VK_U);
        mapaznakow.put('v',VK_V);
        mapaznakow.put('w',VK_W);
        mapaznakow.put('x',VK_X);
        mapaznakow.put('y',VK_Y);
        mapaznakow.put('z',VK_Z);
        mapaznakow.put('@',VK_AT);
        mapaznakow.put('_',VK_UNDERSCORE);
        mapaznakow.put('.',VK_PERIOD);
        mapaznakow.put(' ',VK_SPACE);
        mapaznakow.put('\'',VK_QUOTE);
//        mapaznakow.put('Enter',VK_ENTER);
//        mapaznakow.put('prawo',VK_RIGHT);
//        mapaznakow.put('lewo',VK_LEFT);
//        mapaznakow.put('gora',VK_UP);
//        mapaznakow.put('dol',VK_DOWN);

    }
    public void Enter() {
        robot.keyPress(VK_ENTER);
        robot.keyRelease(VK_ENTER);
    }
    public void Prawo() {
        robot.keyPress(VK_RIGHT);
        robot.keyRelease(VK_RIGHT);
    }
    public void Lewo() {
        robot.keyPress(VK_LEFT);
        robot.keyRelease(VK_LEFT);
    }
    public void Dol() {
        robot.keyPress(VK_DOWN);
        robot.keyRelease(VK_DOWN);
    }

    public void skierujPostacwDol (){
        robot.keyPress(VK_CONTROL);
        robot.delay(20);
        Dol();
        robot.keyRelease(VK_CONTROL);
    }

    public void przyciskajSlowo (String slowo) throws Exception{
        Robot robot = new Robot();
//        System.out.println("Czekam 2 sekundy i zaczynam pisać...");
        robot.delay(20);
        char[] tablicaZnakow = slowo.toCharArray();
        for (int i=0; i<tablicaZnakow.length; i++){
            Character tymczasowyZnak = tablicaZnakow[i];

            robot.keyPress(mapaznakow.get(tymczasowyZnak));
            robot.delay(50);
            robot.keyRelease(mapaznakow.get(tymczasowyZnak));
            //System.out.print(tablicaZnakow[i]+" ");

        }
//        System.out.println("Zakonczylem pisanie.");

    }

    public void maksymalizujAktualneOkno() throws AWTException{
        Robot robot = new Robot();
        robot.keyPress(VK_WINDOWS);
        robot.keyPress(VK_UP);
        robot.keyRelease(VK_UP);
        robot.keyRelease(VK_WINDOWS);
    }
    public void enter() throws  AWTException{
        Robot robot = new Robot();
        robot.keyPress(VK_ENTER);
        robot.keyRelease(VK_ENTER);
    }
    public void kliknijPrawym(int x, int y) throws  AWTException{
        Robot robot = new Robot();
        robot.mouseMove(x,y);
        robot.mousePress(BUTTON3_MASK);
        robot.mouseRelease(BUTTON3_MASK);
    }
    public void kliknijPrawym(Punkt punkt) throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove(punkt.getWspX(), punkt.getWspY());
        robot.mousePress(BUTTON3_MASK);
        robot.mouseRelease(BUTTON3_MASK);
    }
    public void kliknijLewym(int x, int y) throws  AWTException{
        Robot robot = new Robot();
        robot.mouseMove(x,y);
        robot.mousePress(BUTTON1_MASK);
        robot.mouseRelease(BUTTON1_MASK);
    }
    public void kliknijLewym(Punkt punkt) throws  AWTException{
        robot.mouseMove(punkt.getWspX(), punkt.getWspY());
        robot.mousePress(BUTTON1_MASK);
        robot.mouseRelease(BUTTON1_MASK);
    }
    public void przeciagnijLewym(Punkt punktPocz, Punkt punktKonc) throws AWTException {
        Robot robot = new Robot();
        robot.mouseMove(punktPocz.getWspX(),punktPocz.getWspY());
        robot.delay(1000);
        robot.mousePress(BUTTON1_MASK);
        robot.delay(1000);
        for (int i = punktPocz.getWspY();i<=punktKonc.getWspY();i++){
            robot.mouseMove(punktKonc.getWspX(),i);
            robot.delay(20);
        }
//        robot.mouseMove(punktKonc.getWspX(), punktKonc.getWspY());
        robot.delay(1000);
        robot.mouseRelease(BUTTON1_MASK);

    }
}
