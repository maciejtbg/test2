package com.company;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.opencv.imgproc.Imgproc.THRESH_BINARY;
import static org.opencv.imgproc.Imgproc.THRESH_BINARY_INV;

public class BlackWhite {
    public static String Treshold (String plik) throws Exception {

//        ScreenShotTaker screenShotTaker = new ScreenShotTaker(0, 0, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
//        BufferedImage zdjecieWPamiec = null;
//        zdjecieWPamiec = screenShotTaker.make();
//        Mat mat = Wyszukiwarka.matify(zdjecieWPamiec);

        // Loading the OpenCV core library
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

        // Reading the Image from the file and storing it in to a Matrix object
        String file ="D://"+plik+".png";

        // Reading the image
        Mat src = Imgcodecs.imread(file,0);

        // Creating an empty matrix to store the result
        Mat dst = new Mat();
        Mat binarized;
        Imgproc.threshold(src, dst, 100, 255, THRESH_BINARY_INV);

        //Imgproc.adaptiveThreshold(src, dst, 125, Imgproc.ADAPTIVE_THRESH_MEAN_C, THRESH_BINARY, 11, 12);

        // Writing the image
        Imgcodecs.imwrite(plik, dst);


//        System.out.println("Image Processed");


        return plik;
    }
}