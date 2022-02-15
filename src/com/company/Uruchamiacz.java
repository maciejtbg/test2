package com.company;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;

public class Uruchamiacz {
    public static void uruchomProgram(String sciezka) {
        try {
            Runtime runTime = Runtime.getRuntime();
            Process process2 = runTime.exec(sciezka);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("Closing Tibia");
            //process2.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zamknijProgram() {
        try {

            Runtime.getRuntime().exec("taskkill /F /IM client.exe");

//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            //System.out.println("Closing Tibia");
            //process2.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


