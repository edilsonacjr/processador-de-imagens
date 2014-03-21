package rmi;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import opercoes.AverageFilter;
import opercoes.CondimentNoise;
import opercoes.FFT;
import opercoes.FreqFilter;
import opercoes.GaussianNoise;
import opercoes.InverseFFT;
import opercoes.TwoDArray;

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
        Image output = tk.createImage(new MemoryImageSource(tamOutput, height / (width / tamOutput), filter.process(), 0, width));

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
        Image invFourier = tk.createImage(new MemoryImageSource(tamOutput, height / (width / tamOutput), inverse.getPixels(output), 0, output.width));

        return invFourier;
    }
}
