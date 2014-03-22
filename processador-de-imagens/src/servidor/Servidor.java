package servidor;

import rmi.InterfaceRmiImp;
import rmi.InterfaceRmi;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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
