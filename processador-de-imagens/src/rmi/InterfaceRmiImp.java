package rmi;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.ImageIcon;
import operacoes.AverageFilter;
import operacoes.CondimentNoise;
import operacoes.FFT;
import operacoes.FreqFilter;
import operacoes.GaussianFilter;
import operacoes.GaussianNoise;
import operacoes.HystThresh;
import operacoes.InverseFFT;
import operacoes.MedianFilter;
import operacoes.Sobel;
import operacoes.TwoDArray;

/**
 *
 * @author Edilson Anselmo Corrêa Júnior
 */
public class InterfaceRmiImp implements InterfaceRmi {

    @Override
    public boolean validaUsuario() throws RemoteException {
        return true;
    }

    public Image averaging(Image image, int noise, String noiseMode, int templateSize, int tamOutput) {
        int orig[] = null;
        int width = 0, height = 0;
        width = image.getWidth(null);
        height = image.getHeight(null);

        orig = new int[width * height];
        PixelGrabber grabber = new PixelGrabber(image, 0, 0, width, height, orig, 0, width);
        try {
            grabber.grabPixels();
        } catch (InterruptedException e2) {
            System.out.println("error: " + e2);
        }
        GaussianNoise gNoise = new GaussianNoise();
        CondimentNoise cNoise = new CondimentNoise();
        AverageFilter filter = new AverageFilter();

        gNoise.init(orig, width, height, (float) noise);
        cNoise.init(orig, width, height, (float) noise / 100);
        if (noiseMode == "Gaussian") {
            orig = gNoise.process();
        }
        if (noiseMode == "Condiment") {
            orig = cNoise.process();
        }

        filter.init(orig, width, height, templateSize, tamOutput);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image output = tk.createImage(new MemoryImageSource(width, height, filter.process(), 0, width)).getScaledInstance(tamOutput, height/(width/tamOutput), Image.SCALE_SMOOTH);

        return output;
    }

    public Image fourier(Image image, Boolean lowpass, int radius, int tamOutput) {
        int orig[] = null;
        float origFloat[] = null;
        FFT fft = new FFT();
        InverseFFT inverse = new InverseFFT();
        Toolkit tk = Toolkit.getDefaultToolkit();
        fft.progress = 0;

        int width = 0, height = 0;
        width = image.getWidth(null);
        height = image.getHeight(null);

        fft.progress = 0;

        inverse.progress = 0;

        orig = new int[width * height];
        PixelGrabber grabber = new PixelGrabber(image, 0, 0, width, height, orig, 0, width);
        try {
            grabber.grabPixels();
        } catch (InterruptedException e2) {
            System.out.println("error: " + e2);
        }

        origFloat = new float[width * height];

        for (int i = 0; i < width * height; i++) {

            origFloat[i] = (float) orig[i];
        }

        fft = new FFT(orig, width, height);

        fft.intermediate = FreqFilter.filter(fft.intermediate, lowpass, radius);
        TwoDArray output = inverse.transform(fft.intermediate);
        Image invFourier = tk.createImage(new MemoryImageSource(width, height, inverse.getPixels(output), 0, output.width)).getScaledInstance(tamOutput, height/(width/tamOutput), Image.SCALE_SMOOTH);

        return invFourier;
    }

    public Image gaussian(Image image, String noiseMode, int noise, int radius, int sigma, int tamOutput) {
        int orig[] = null;
        int width = image.getWidth(null);
        int height = image.getHeight(null);

        orig = new int[width * height];
        PixelGrabber grabber = new PixelGrabber(image, 0, 0, width, height, orig, 0, width);
        try {
            grabber.grabPixels();
        } catch (InterruptedException e2) {
            System.out.println("error: " + e2);
        }

        GaussianNoise gNoise = new GaussianNoise();
        CondimentNoise cNoise = new CondimentNoise();

        gNoise.init(orig, width, height, (float) noise);
        cNoise.init(orig, width, height, (float) noise / 100);
        if (noiseMode == "Gaussian") {
            orig = gNoise.process();
        }
        if (noiseMode == "Condiment") {
            orig = cNoise.process();
        }
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image output;
        if (noiseMode.equals("Gaussian")) {
            output = tk.createImage(new MemoryImageSource(width, height, gNoise.process(), 0, width)).getScaledInstance(tamOutput, height/(width/tamOutput), Image.SCALE_SMOOTH);
        } else {
            output = tk.createImage(new MemoryImageSource(width, height, cNoise.process(), 0, width)).getScaledInstance(tamOutput, height/(width/tamOutput), Image.SCALE_SMOOTH);
        }

        return output;
    }

    public Image median(Image image, int noise, String noiseMode, int templateSize, int tamOutput) {
        int orig[] = null;
        int width = 0, height = 0;
        width = image.getWidth(null);
        height = image.getHeight(null);

        orig = new int[width * height];
        PixelGrabber grabber = new PixelGrabber(image, 0, 0, width, height, orig, 0, width);
        try {
            grabber.grabPixels();
        } catch (InterruptedException e2) {
            System.out.println("error: " + e2);
        }
        GaussianNoise gNoise = new GaussianNoise();
        CondimentNoise cNoise = new CondimentNoise();
        MedianFilter filter = new MedianFilter();

        gNoise.init(orig, width, height, (float) noise);
        cNoise.init(orig, width, height, (float) noise / 100);
        if (noiseMode == "Gaussian") {
            orig = gNoise.process();
        }
        if (noiseMode == "Condiment") {
            orig = cNoise.process();
        }

        filter.init(orig, width, height, templateSize, tamOutput);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image output = tk.createImage(new MemoryImageSource(width, height, filter.process(), 0, width)).getScaledInstance(tamOutput, height/(width/tamOutput), Image.SCALE_SMOOTH);

        return output;
    }

    private int[] threshold(int[] original, int value) {
        for (int x = 0; x < original.length; x++) {
            if ((original[x] & 0xff) >= value) {
                original[x] = 0xffffffff;
            } else {
                original[x] = 0xff000000;
            }
        }
        return original;
    }

    public Image thresholding(Image image, int mode, int threshold, int threshold2, int tamOutput) {
        int orig[] = null;
        int width = 0, height = 0;
        width = image.getWidth(null);
        height = image.getHeight(null);
        HystThresh hystThreshObject;
        orig = new int[width * height];
        PixelGrabber grabber = new PixelGrabber(image, 0, 0, width, height, orig, 0, width);
        Sobel edgedetector = new Sobel();
        hystThreshObject = new HystThresh();
        try {
            grabber.grabPixels();
        } catch (InterruptedException e2) {
            System.out.println("error: " + e2);
        }
        edgedetector.init(orig, width, height);
        orig = edgedetector.process();

        //edges = createImage(new MemoryImageSource(width, height, orig, 0, width));
        hystThreshObject.init(orig, width, height, threshold, threshold2);

        if (mode == 1) {
            orig = threshold(orig, threshold);
        } else {
            orig = hystThreshObject.process();
        }
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image output = tk.createImage(new MemoryImageSource(width, height, orig, 0, width));
        output = output.getScaledInstance(tamOutput, height/(width/tamOutput), Image.SCALE_SMOOTH);
        //Image output = tk.createImage(new MemoryImageSource(tamOutput, height / (width / tamOutput), edgedetector.process(), 0, width));
        return output;
    }
}
