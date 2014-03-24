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
    public Imagem averaging(Imagem imagem, int noise, String noiseMode, int templateSize, int tamOutput) throws RemoteException;
    public Imagem fourier(Imagem imagem, Boolean lowpass, int radius, int tamOutput) throws RemoteException;
    public Imagem gaussian(Imagem imagem, String noiseMode, int noise, int radius, int sigma, int tamOutput)throws RemoteException;
    public Imagem median(Imagem imagem, int noise, String noiseMode, int templateSize, int tamOutput) throws RemoteException;
    public Imagem thresholding(Imagem imagem, int mode, int threshold, int threshold2, int tamOutput) throws RemoteException;
    public Imagem nomax(Imagem imagem, int mode, int tamOutput) throws RemoteException;
}