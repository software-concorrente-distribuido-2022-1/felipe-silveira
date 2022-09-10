package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Deposito extends Remote {
    String metodo(String nome, String operacao) throws RemoteException;
}