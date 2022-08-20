import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Deposito implements OperadorServidorAbstract {
    private int items = 0;

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
    public void realizaOperacao(PrintWriter saida, BufferedReader entrada) throws IOException {
        List<String> retorno = new ArrayList<>();
        String linhaResposta = entrada.readLine();
        retorno.add(linhaResposta);
        while (!linhaResposta.equals("fim")) {
            System.out.println(linhaResposta);
            linhaResposta = entrada.readLine();
            if(!linhaResposta.equals("fim")) {
                retorno.add(linhaResposta);
            }
        }
        String tipo = retorno.get(0);
        String nome = retorno.get(1);
        if (tipo.equals("Retirar")) {
            if(retirar(nome) == 0) {
                saida.println("Deposito Vazio");
                return;
            }
        } else {
            if(colocar(nome) == 0) {
                saida.println("Deposito Cheio");
                return;
            }
        }
        saida.println("Sucesso");
    }
}



