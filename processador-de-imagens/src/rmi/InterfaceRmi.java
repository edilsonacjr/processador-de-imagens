package rmi;

import entidades.Imagem;
import entidades.Usuario;
import java.awt.Image;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface InterfaceRmi extends Remote {

    public boolean validaUsuario(String username, String senha) throws RemoteException;
    public Usuario getUsuario(String username, String senha) throws RemoteException;
    public Imagem getImagem(int i) throws RemoteException;
    public void inserirImagem(Imagem i) throws RemoteException;
    public void removerImagem(int i) throws RemoteException;
    public ArrayList<Imagem> getImagens(int i) throws RemoteException;
    public Image averaging(Image image, int noise, String noiseMode, int templateSize, int tamOutput) throws RemoteException;
    public Image fourier(Image image, Boolean lowpass, int radius, int tamOutput) throws RemoteException;
    public Image gaussian(Image image, String noiseMode, int noise, int radius, int sigma, int tamOutput)throws RemoteException;
    public Image median(Image image, int noise, String noiseMode, int templateSize, int tamOutput) throws RemoteException;
    public Image thresholding(Image image, int mode, int threshold, int threshold2, int tamOutput) throws RemoteException;
    public Image nomax(Image image, int mode, int tamOutput) throws RemoteException;
}