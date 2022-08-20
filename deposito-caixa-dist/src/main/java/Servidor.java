import java.net.*;
import java.io.*;
import java.util.List;
import java.util.Map;

public class Servidor {
    public static void main(String[] args) throws IOException {
        int porta = 8080;
        int backlog = 5;
        Socket socketCliente;
        ServerSocket socketServidor;
        while (true) {
            try {
                socketServidor = new ServerSocket(porta, backlog);
                break;
            } catch (IOException e) {
                porta++;
            }
        }
        System.out.println("\nServidor ativado. Aguardando Cliente na porta " + porta + "...\n");
        while (true) {
            socketCliente = null;
            try {
                socketCliente = socketServidor.accept();
            } catch (IOException e) {
                System.err.println("Erro de E/S " + e);
                System.exit(1);
            }
            new Conexao(socketCliente, new Deposito()).start();
        }
    }
}

class Conexao extends Thread {
    OperadorServidorAbstract operadorServidor;
    Socket socketCliente;

    Conexao(Socket aSocketCliente, OperadorServidorAbstract operadorServidor) throws IOException {
        this.socketCliente = aSocketCliente;
        this.operadorServidor = operadorServidor;
    }

    public void run() {
        try {
            PrintWriter saida = new PrintWriter(this.socketCliente.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));
            operadorServidor.realizaOperacao(saida, entrada);
            socketCliente.close();
            saida.close();
            entrada.close();
        } catch (IOException e) {
            System.out.println("Erro E/S " + e);
        }
    }
}
