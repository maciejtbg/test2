package com.company;

public class Punkt {
    int wspX;
    int wspY;

    public Punkt() {
        this.wspX=0;
        this.wspY=0;
    }


    public int getWspX() {
        return wspX;
    }
    public int getWspY() {
        return wspY;
    }
    public void setWspX(int wspX) {
        this.wspX = wspX;
    }
    public void setWspY(int wspY) {
        this.wspY = wspY;
    }
    public Punkt(int wspX, int wspY) {
        this.wspX = wspX;
        this.wspY = wspY;
    }
}
