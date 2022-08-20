import java.io.*;       //Package de classes para manipulacao de E/S
import java.net.*;      //Package de classes para manipulacao de Sockets, IP, etc

public class Cliente {
    String nomeHost;
    int porta;
    OperadorClienteAbstract operadorCliente;
    public Cliente(String nomeHost, OperadorClienteAbstract operadorClienteAbstract, int porta) {
        this.operadorCliente = operadorClienteAbstract;
        this.nomeHost = nomeHost;
        this.porta = porta;
    }

    public static void executa(Map parametros) throws IOException {
        Socket sock = null;
        PrintWriter saida = null;
        BufferedReader entrada = null;
        try {
            sock = new Socket(nomeHost, porta);
            saida = new PrintWriter(sock.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("\n\nHost nao encontrado!\n");
            System.out.println("\nUso: ClienteSimples NomeDoHost mensagem [porta]\n\n");
            return;
        } catch (java.io.IOException e) {
            System.err.println("\n\nConexao com Host nao pode ser estabelecida.\n");
            System.out.println("\nUso: ClienteSimples NomeDoHost mensagem [porta]\n\n");
            return;
        }
        operadorCliente.realizaOperacao(saida, entrada, parametros)
        sock.close();
    }
}
