package com.company;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;


public interface User32 extends StdCallLibrary {
    final Main.User32 instance = (Main.User32) Native.loadLibrary ("user32", Main.User32.class);
    WinDef.HWND FindWindowA(String winClass, String title);
    boolean ShowWindow(WinDef.HWND hWnd, int nCmdShow);
    boolean SetForegroundWindow(WinDef.HWND hWnd);
}