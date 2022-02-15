package com.company;

import com.sun.jna.platform.win32.WinDef;


public class Maksymalizator {
    public void Maksymalizuj(String nazwaOkna){
        WinDef.HWND hwnd = Main.User32.instance.FindWindowA(null, nazwaOkna);
        Main.User32.instance.ShowWindow(hwnd, 9);
        Main.User32.instance.SetForegroundWindow(hwnd);
    }
}
