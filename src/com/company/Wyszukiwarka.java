package com.company;

import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class Wyszukiwarka {
    private static BufferedImage convertTo3ByteBGRType(BufferedImage image) {
        BufferedImage convertedImage = new BufferedImage(image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
        convertedImage.getGraphics().drawImage(image, 0, 0, null);
        return convertedImage;
    }

    public static Mat matify(BufferedImage image) {
        image = convertTo3ByteBGRType(image);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        mat.put(0, 0, data);
        return mat;
    }

    public static boolean sprawdzCzyJestObiektNaEkranie(String templateFile) throws AWTException {
        boolean czyJest=false;
//        System.out.println("Rozpoczynam szukanie itemu.");
        ScreenShotTaker screenShotTaker = new ScreenShotTaker(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        BufferedImage zdjecieWPamiec = null;
        zdjecieWPamiec = screenShotTaker.make();
//        System.out.println("Rozpoczynam konwersję z BufferedImage do Mat.");
        Mat img = matify(zdjecieWPamiec);
//        System.out.println("Konwersja udana.");
        int match_method = Imgproc.TM_CCOEFF_NORMED;
        Mat templ = Imgcodecs.imread(templateFile);

        // / Create the result matrix
        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);


        // / Do the Matching and Normalize
        Imgproc.matchTemplate(img, templ, result, match_method);
        //Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        int licznik = 0;

        while (true) {
            // / Localizing the best match with minMaxLoc
            Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

            Point matchLoc;
            if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
                matchLoc = mmr.minLoc;
            } else {
                matchLoc = mmr.maxLoc;
            }


            if (mmr.maxVal >= 0.97) {
                // / Show me what you got
//                Imgproc.rectangle(img, matchLoc,
//                        new Point(matchLoc.x + templ.cols(), matchLoc.y + templ.rows()),
//                        new Scalar(0, 255, 0), 2);
                //Imgproc.putText(img, "Edited by me",
                //  new Point(matchLoc.x + templ.cols(),matchLoc.y + templ.rows()),
                //Core.FONT_HERSHEY_PLAIN, 1.0 ,new  Scalar(0,255,255));
                Imgproc.rectangle(result, matchLoc,
                        new Point(matchLoc.x + templ.cols(), matchLoc.y + templ.rows()),
                        new Scalar(0, 255, 0), -1);//Niewiem dlacze go ta linijka jest konieczna ale bez niej pentla sie nie konczy
                licznik++;
//                System.out.println("Znalazlem " + licznik + " dopasowanie: X: " + matchLoc.x + " Y: " + matchLoc.y);

            } else {
                break; //No more results within tolerance, break search
            }

        }
        if (licznik == 0) {
//            System.out.println("Nie znalazlem zadnych dopasowan.");
        } else {
            czyJest=true;
        }
        return czyJest;
    }


    public static Punkt zrobScreeniZwrocPunktDoKlikniecia(String templateFile) throws AWTException, IOException {
//        System.out.println("\nUruchamiam znajdowanie itemu "+templateFile);
        ScreenShotTaker screenShotTaker = new ScreenShotTaker(0,0,(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
//        String tempImage= "tymczasowyScreen";
        BufferedImage zdjecieWPamiec = null;
        zdjecieWPamiec = screenShotTaker.make();
//        System.out.println("Rozpoczynam konwersję z BufferedImage do Mat.");
        Mat img = matify(zdjecieWPamiec);
//        System.out.println("Konwersja udana.");

//        screenShotTaker.zrobZdjecieiZapisz(tempImage);
//        String inFile = screenShotTaker.RESOURCES_DIR+tempImage+".png";
        int match_method = Imgproc.TM_CCOEFF_NORMED;


//        Mat img = Imgcodecs.imread(inFile);
        Mat templ = Imgcodecs.imread(templateFile);

        // / Create the result matrix
        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

        // / Do the Matching and Normalize
        Imgproc.matchTemplate(img, templ, result, match_method);
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        Punkt punkt = null;
        int licznik = 0;
        while(true)
        {
            // / Localizing the best match with minMaxLoc
            Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

            Point matchLoc;
            if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
                matchLoc = mmr.minLoc;
            } else {
                matchLoc = mmr.maxLoc;
            }


            if(mmr.maxVal >=0.97)
            {
                licznik++;
//                System.out.println("Do itemu "+templateFile+" znalazlem dopasowanie nr "+licznik+": X: "+matchLoc.x+" Y: "+matchLoc.y);
                int miejceDoKliknieciaX = (int) (matchLoc.x+templ.cols()/2);
                int miejceDoKliknieciaY = (int) (matchLoc.y+templ.rows()/2);
//                System.out.println("Miejsce do kliniecia:\nX: "+miejceDoKliknieciaX+" Y: "+miejceDoKliknieciaY);
                punkt = new Punkt(miejceDoKliknieciaX,miejceDoKliknieciaY);
                return punkt;
            }
            else
            {
                System.out.println("Znalazlem "+licznik+" wyszukiwanych itemow.");
                break; //No more results within tolerance, break search
            }
        }
        return punkt;
    }
    public static void zaznacz(String inFile, String templateFile, String outFile, int match_method) {
        System.out.println("\nRunning Template Matching");


        Mat img = Imgcodecs.imread(inFile);
        Mat templ = Imgcodecs.imread(templateFile);
        System.out.println("Załadowałem obrazy.");

        // / Create the result matrix
        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

        // / Do the Matching and Normalize

        Imgproc.matchTemplate(img, templ, result, match_method);
        //Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        int licznik = 0;
        Point tempMatchLoc = null;

        while(true)
        {
            // / Localizing the best match with minMaxLoc
            Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

            Point matchLoc;
            if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
                matchLoc = mmr.minLoc;
            } else {
                matchLoc = mmr.maxLoc;
            }




            if(mmr.maxVal >= 1)
            {
                // / Show me what you got
                if (matchLoc.equals(tempMatchLoc)){
                    break;
                }
                tempMatchLoc=matchLoc;
                Imgproc.rectangle(img, matchLoc,
                        new Point(matchLoc.x + templ.cols(),matchLoc.y + templ.rows()),
                        new Scalar(0,255,0),2);
                //Imgproc.putText(img, "Edited by me",
                //  new Point(matchLoc.x + templ.cols(),matchLoc.y + templ.rows()),
                //Core.FONT_HERSHEY_PLAIN, 1.0 ,new  Scalar(0,255,255));
                Imgproc.rectangle(result, matchLoc,
                        new Point(matchLoc.x + templ.cols(),matchLoc.y + templ.rows()),
                        new    Scalar(0,255,0),-1);
                licznik++;
                System.out.println("Znalazlem "+licznik+" dopasowanie: X: "+matchLoc.x+" Y: "+matchLoc.y);

            }
            else
            {
                break; //No more results within tolerance, break search
            }

        }
        if (licznik==0){
            System.out.println("Nie znalazlem zadnych dopasowan.");
        }

        // / Show me what you got
        //Imgproc.rectangle(img, matchLoc, new Point(matchLoc.x + templ.cols(),
        //matchLoc.y + templ.rows()), new Scalar(0, 255, 0),2);

        // Save the visualized detection.
        System.out.println("Writing "+ outFile);
        Imgcodecs.imwrite(outFile, img);

    }
}
