import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Consumidor implements Runnable {
    String nome;
    Cliente cliente;

    Consumidor(String nome, Cliente cliente) {
        this.cliente = cliente;
        this.nome = nome;
    }

    public void run() {

        System.out.println("Iniciando Consumidor " + nome);
        for (int i = 0; i < 10; i++) {
            try {
                List<String> parametros = new ArrayList<>();
                parametros.add("Retirar");
                parametros.add(nome);
                List<String> resposta = cliente.executa(parametros);
                if (Objects.equals(resposta.get(0), "Deposito Vazio")) {
                    System.out.println(nome + " .Deposito vazio, esperando caixas serem colocadas");
                }
                Thread.sleep(1000L);

            } catch (Exception ignored) {}
        }

    }
}