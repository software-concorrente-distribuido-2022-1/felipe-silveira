import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Deposito implements OperadorServidorAbstract {
    private int items = 0;
    private List<Endereco> enderecos = new ArrayList<>();

    public int retirar(String nome) {
        synchronized (this) {
            if (items <= 0) {
                System.out.println(nome + " .Sem caixas no stock esperando adicionarem mais caixas");
                return 0;
            }
            items--;
            System.out.println(nome + " .Caixa retirada: Sobram " + items + " caixas");
            return 1;

        }
    }

    public int colocar(String nome) {
        synchronized (this) {
            if (items >= 10) {
                System.out.println(nome + " .Sem espaço no stock esperando retirarem mais caixas");
                return 0;
            }
            items++;
            System.out.println(nome + " .Caixa armazenada: Passaram a ser " + items + " caixas");
            return 1;
        }
    }

    @Override
    public void realizaOperacao(PrintWriter saida, BufferedReader entrada, Socket socketCliente) throws IOException {
        List<String> retorno = new ArrayList<>();
        String linhaResposta = entrada.readLine();
        retorno.add(linhaResposta);
        while (!linhaResposta.equals("fim")) {
            System.out.println(linhaResposta);
            linhaResposta = entrada.readLine();
            if (!linhaResposta.equals("fim")) {
                retorno.add(linhaResposta);
            }
        }
        String mensagem = "";
        String tipo = retorno.get(0);
        String nome = retorno.get(1);
        if (tipo.equals("Retirar")) {
            if (retirar(nome) == 0) {
                mensagem = "Deposito Vazio";
            }
        } else if (tipo.equals("Colocar")) {
            if (colocar(nome) == 0) {
                mensagem = "Deposito Cheio";
            }
        } else if (tipo.equals("Sub")) {
            enderecos.add(new Endereco(socketCliente.getInetAddress(), socketCliente.getPort()));
            saida.println("Adicionado aos incritos");
            return;
        }  else if (tipo.equals("unsub")) {
            enderecos.remove(new Endereco(socketCliente.getInetAddress(), socketCliente.getPort()));
            saida.println("Retirado dos incritos");
            return;
        } else {
            mensagem = "sucesso";
        }
        for (Endereco endereco : enderecos) {
            Socket socket = new Socket(endereco.add, endereco.port);
            PrintWriter saida2 = new PrintWriter(socket.getOutputStream(), true);
            saida2.println(mensagem);
            saida2.println("fim");
            saida2.close();
            socket.close();
        }

        saida.println(mensagem);
    }
}

class Endereco {
    InetAddress add;
    int port;

    public Endereco(InetAddress add, int port) {
        this.add = add;
        this.port = port;
    }
}



