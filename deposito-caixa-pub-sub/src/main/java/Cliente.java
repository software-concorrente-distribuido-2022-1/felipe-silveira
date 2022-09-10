import java.io.*;       //Package de classes para manipulacao de E/S
import java.net.*;      //Package de classes para manipulacao de Sockets, IP, etc
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cliente {
    String nomeHost;
    int porta;

    public Cliente(String nomeHost, int porta) {
        this.nomeHost = nomeHost;
        this.porta = porta;
    }

    public List<String> executa(List<String> parametros) throws IOException {
        try {
            Observador observador = new Observador(porta, 5);
            observador.start();
            Socket sock = new Socket(nomeHost, porta);
            PrintWriter saida = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            List<String> retorno = new ArrayList<>();
            for (String linha : parametros) {
                saida.println(linha);
            }
            saida.println("fim");
            String linhaResposta = entrada.readLine();
            retorno.add(linhaResposta);
            while (linhaResposta != null) {
                System.out.println(linhaResposta);
                linhaResposta = entrada.readLine();
                retorno.add(linhaResposta);
            }
            sock.close();
            return retorno;
        } catch (UnknownHostException e) {
            System.err.println("\n\nHost nao encontrado!\n");
            System.out.println("\nUso: ClienteSimples NomeDoHost mensagem [porta]\n\n");
        } catch (IOException e) {
            System.err.println("\n\nConexao com Host nao pode ser estabelecida.\n");
            System.out.println("\nUso: ClienteSimples NomeDoHost mensagem [porta]\n\n");
        }
        return new ArrayList<>();
    }
}

class Observador extends Thread {
    int porta;
    int backlog;

    public Observador(int porta, int backlog) {
        this.porta = porta;
        this.backlog = backlog;
    }

    @Override
    public void run() {
        Socket socketCliente;
        ServerSocket socketServidor;
        Deposito deposito = new Deposito();
        while (true) {
            try {
                socketServidor = new ServerSocket(porta, backlog);
                break;
            } catch (IOException e) {
                porta++;
            }
        }
        System.out.println("\nAguardando atualizações na porta " + porta + "...\n");
        while (true) {
            socketCliente = null;
            try {
                socketCliente = socketServidor.accept();
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                String linhaResposta = null;
                linhaResposta = entrada.readLine();
                List<String> retorno = new ArrayList<>();
                retorno.add(linhaResposta);
                while (!linhaResposta.equals("fim")) {
                    System.out.println(linhaResposta);
                    linhaResposta = entrada.readLine();
                    if (!linhaResposta.equals("fim")) {
                        retorno.add(linhaResposta);
                    }
                }
            } catch (IOException e) {
                System.err.println("Erro de E/S " + e);
                System.exit(1);
            }
        }
    }
}
