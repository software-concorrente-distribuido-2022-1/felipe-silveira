package rmi;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;

public class Servidor implements Deposito {

    public Servidor() {}
    int quantidadeDeposito = 0;
    int quantidadeMaxima = 10;
    public String metodo(String nome, String operacao) {
        if (Objects.equals(operacao, "retirar")) {
            if (quantidadeDeposito > 0) {
                quantidadeDeposito--;
                return ("O " + nome + " retirou uma caixa");
            }
            return ("Deposito vazio não foi possivel retirar");
        } else if (quantidadeDeposito < quantidadeMaxima) {
            quantidadeDeposito++;
            return ("O " + nome + " adicionou uma caixa");
        }
        return ("Deposito cheio não foi possivel adicionar");
    }

    public static void main(String args[]) {

        try {
            Servidor obj = new Servidor();
            Deposito stub = (Deposito) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Deposito", stub);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}