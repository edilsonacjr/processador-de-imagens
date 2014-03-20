package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceRmi extends Remote {

    public boolean validaUsuario() throws RemoteException;
}