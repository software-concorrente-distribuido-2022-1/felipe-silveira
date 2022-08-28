package rmi;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cargo extends Remote {
    String metodo(String nome, String cargo, BigDecimal Salario) throws RemoteException;
}