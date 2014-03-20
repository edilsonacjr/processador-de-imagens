package servidor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import rmi.InterfaceRmi;
import rmi.InterfaceRmiImp;

/**
 *
 * @author Edilson Anselmo Corrêa Júnior
 */
public class Servidor {
    public static void main(String args[]) {

        try {
            InterfaceRmi servico = new InterfaceRmiImp();
            Registry registry = LocateRegistry.createRegistry(1100);
            registry.bind("rmi", servico);
            System.out.println("Servidor iniciado.");

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
