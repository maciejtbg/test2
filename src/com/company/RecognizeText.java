package com.company;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import javax.imageio.ImageIO;

import static org.opencv.imgproc.Imgproc.THRESH_BINARY_INV;

public class RecognizeText {
    Tesseract ts;

//    public BufferedImage getImage(String imgPath) throws IOException {
//        Mat mat = Imgcodecs.imread(imgPath);
//        Mat gray = new Mat();
//        Imgproc.cvtColor(mat,gray,Imgproc.COLOR_BGR2GRAY);
//        Mat resized = new Mat();
//        Size size = new Size(mat.width()*1.9f,mat.height()*1.9f);
//        Imgproc.resize(gray,resized,size);
//        MatOfByte mof = new MatOfByte();
//        byte imageByte[];
//        Imgcodecs.imencode(".2",resized,mof);
//        imageByte=mof.toArray();
//        return ImageIO.read(new ByteArrayInputStream(imageByte));
//    }
    public String Rozpoznanie(String nazwaPliku) throws Exception {
        ts = new Tesseract();
        ts.setDatapath("");
        ts.setLanguage("eng");
        String text;
        ts.setTessVariable("tessedit_char_whitelist", ",0123456789");
//        String nazwaPlikuDoRozpoznania = BlackWhite.Treshold(nazwaPliku);
//        try {
//            String text = ts.doOCR(getImage("D://nazwa.png"));
//            System.out.println(text);
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        String file ="D://"+nazwaPliku+".png";
        Mat src = Imgcodecs.imread(file,0);
        Mat dst = new Mat();
        Imgproc.threshold(src, dst, 100, 255, THRESH_BINARY_INV);




            MatOfByte mob=new MatOfByte();
            Imgcodecs.imencode(".png", dst, mob);
            byte ba[]=mob.toArray();

            BufferedImage bi=ImageIO.read(new ByteArrayInputStream(ba));

        File outputfile = new File("D://"+nazwaPliku+".png");
        ImageIO.write(bi, ".png", outputfile);

//        Imgcodecs.imwrite(nazwaPliku, dst);

        try {
            text = ts.doOCR(new File("D://"+nazwaPliku+".png"));
//            System.out.print(text);
        } catch (TesseractException e) {
            e.printStackTrace();
            text = "Blad rozpoznania";
//            System.out.print(text);
        }

        return text;
    }
}
