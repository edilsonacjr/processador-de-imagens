/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opercoes;

/**
 *
 * @author josepedro
 */
import java.awt.*;
import java.awt.image.*;
import java.applet.*;
import java.net.*;
import java.io.*;
import java.lang.Math;
import java.util.*;

 // additional stuff for swing
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JApplet;
import javax.imageio.*;
import java.util.Arrays;

public class AverageFilter {

    int[] input;
    int[] output;
    float[] template;
    int progress;
    int templateSize;
    int width;
    int height;
    int tamOutput;

    public void averageFilter() {
        progress = 0;
    }

    public void init(int[] original, int widthIn, int heightIn, int templatesizein, int tamOutputIn) {
        templateSize = templatesizein;
        width = widthIn;
        height = heightIn;
        input = new int[width * height];
        output = new int[width * height];
        template = new float[templateSize * templateSize];
        input = original;
        tamOutput = tamOutputIn;
        
    }

    public int[] process() {
        float sum;
        progress = 0;
        int val = 0; // value of all the pixels in the kernel area
        int count;
        int outputsmaller[] = new int[(width - (templateSize - 1)) * (height - (templateSize - 1))];

        for (int x = (templateSize - 1) / 2; x < width - (templateSize + 1) / 2; x++) {
            progress++;
            for (int y = (templateSize - 1) / 2; y < height - (templateSize + 1) / 2; y++) {

                count = 0;
                val = 0;
                for (int x1 = x - ((templateSize - 1) / 2); x1 < x + ((templateSize + 1) / 2); x1++) {
                    for (int y1 = y - ((templateSize - 1) / 2); y1 < y + ((templateSize + 1) / 2); y1++) {
                        val += ((input[y1 * width + x1] & 0xff) / (templateSize * templateSize));
                        count++;
                    }
                }
                outputsmaller[(y - (templateSize - 1) / 2) * (width - (templateSize - 1)) + (x - (templateSize - 1) / 2)] = 0xff000000 | ((int) (val) << 16 | (int) (val) << 8 | (int) (val));
            }
        }

        Toolkit tk = Toolkit.getDefaultToolkit();

        Image tempImage = tk.createImage(new MemoryImageSource((width - (templateSize - 1)), (height - (templateSize - 1)), outputsmaller, 0, (width - (templateSize - 1)))).getScaledInstance(tamOutput, height/(width/tamOutput), Image.SCALE_SMOOTH);
        PixelGrabber grabber = new PixelGrabber(tempImage, 0, 0, width, height, output, 0, width);
        try {
            grabber.grabPixels();
        } catch (InterruptedException e2) {
            System.out.println("error: " + e2);
        }
        progress = width;

        return output;
    }

    public int getProgress() {
        return progress;
    }

}
