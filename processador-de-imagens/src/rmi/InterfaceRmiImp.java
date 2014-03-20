package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Edilson Anselmo Corrêa Júnior
 */
public class InterfaceRmiImp implements InterfaceRmi {

    @Override
    public boolean validaUsuario() throws RemoteException {
        return true;
    }
}
