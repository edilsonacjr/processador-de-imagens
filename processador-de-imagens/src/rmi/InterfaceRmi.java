package rmi;

import java.awt.Image;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRmi extends Remote {

    public boolean validaUsuario() throws RemoteException;
    public Image averaging(Image image, int noise, String noiseMode, int templateSize, int tamOutput) throws RemoteException;
    public Image fourier(Image image, Boolean lowpass, int radius, int tamOutput) throws RemoteException;
    public Image gaussian(Image image, String noiseMode, int noise, int radius, int sigma, int tamOutput)throws RemoteException;
    public Image median(Image image, int noise, String noiseMode, int templateSize, int tamOutput) throws RemoteException;
    public Image thresholding(Image image, int mode, int threshold, int threshold2, int tamOutput) throws RemoteException;
}