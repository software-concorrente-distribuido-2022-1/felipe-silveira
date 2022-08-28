package rmi;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class Server implements Cargo {

    public Server() {}

    public String metodo(String nome, String cargo, BigDecimal salario) {
        if(Objects.equals(cargo, "operador")) {
            salario = salario.multiply(BigDecimal.valueOf(1.2));
        } else {
            salario = salario.multiply(BigDecimal.valueOf(1.18));
        }
        return "Nome: " + nome + "; Salario: " + salario;
    }

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            Cargo stub = (Cargo) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Cargo", stub);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}