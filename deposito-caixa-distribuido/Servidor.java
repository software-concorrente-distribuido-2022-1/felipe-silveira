import java.net.*;
import java.io.*;

public class Servidor {
    OperadorServidorAbstract operadorServidor;
    public Servidor(OperadorServidorAbstract operadorServidor) {
        this.operadorServidor = operadorServidor;
    }
    public static void executa(Map parametros) throws IOException {
        int porta = 8080;
        int backlog = 5;
        Socket socketCliente = null;
        ServerSocket socketServidor = null;
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
            new Conexao(socketCliente, operadorServidor, parametros).start();
        }
    }
}

class Conexao extends Thread {

    final String msgBadRqt = "400 Bad Request";
    OperadorServidorAbstract operadorServidor;
    Socket socketCliente;
    Map parametros;

    Conexao(Socket aSocketCliente, OperadorServidorAbstract operadorServidor, Map parametros) throws IOException {
        this.socketCliente = aSocketCliente;
        this.operadorServidor = operadorServidor;
        this.parametros = parametros;
    }

    public void run() {
        PrintWriter saida = null;
        BufferedReader entrada = null;
        InetAddress endCliente = this.socketCliente.getInetAddress();
        String mensagem = null;
        String linhaArq = null;
        try {
            saida = new PrintWriter(this.socketCliente.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(this.socketCliente.getInputStream()));
            operadorServidor.realizaOperacao(saida, entrada, parametros);
            socketCliente.close();
            saida.close();
            entrada.close();
        } catch (IOException e) {
            System.out.println("Erro E/S " + e);
        }
    }
}
