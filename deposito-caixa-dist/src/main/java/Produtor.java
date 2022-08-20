import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class Produtor implements Runnable {
    String nome;
    Cliente cliente;

    Produtor(String nome, Cliente cliente) {
        this.nome = nome;
        this.cliente = cliente;
    }

    public void run() {

        System.out.println("Iniciando produtor " + nome);
        for (int i = 0; i < 20; i++) {
            try {
                List<String> parametros = new ArrayList<>();
                parametros.add("Colocar");
                parametros.add(nome);
                List<String> resposta = cliente.executa(parametros);
                if (Objects.equals(resposta.get(0), "Deposito Cheio")) {
                    System.out.println(nome + " .Deposito cheio, esperando caixas serem removidas");
                }
                Thread.sleep(1000L);
            } catch (Exception ignored) {}
        }
    }
}