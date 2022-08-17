import java.util.DoubleSummaryStatistics;

public class Deposito {
    private int items = 0;
    private final int capacidade = 10;

    public int retirar(String nome) {
        synchronized (this) {
            if (items <= 0) {
                System.out.println(nome + " .Sem caixas no stock esperando adicionarem mais caixas");
                try {
                    this.wait(1000);
                    System.out.println(nome + " .Caixas adicionadas tentando retirar uma");
                } catch (InterruptedException e) {}
            }
            if (items > 0) {
                items--;
                System.out.println(nome + " .Caixa retirada: Sobram " + items + " caixas");
                this.notifyAll();
                return 1;
            }
            return 0;
        }
    }

    public int colocar(String nome) {
        synchronized (this) {
            if (items >= capacidade) {
                System.out.println(nome + " .Sem espaço no stock esperando retirarem mais caixas");
                try {
                    this.wait(1000);
                    System.out.println(nome + " .Espaço liberado tentando adicionar mais uma");
                } catch (InterruptedException e) {}
            }
            if (items < capacidade) {
                items++;
                System.out.println(nome + " .Caixa armazenada: Passaram a ser " + items + " caixas");
                this.notifyAll();
                return 1;
            }
            return 0;
        }
    }

    public static void main(String[] args) {
        Deposito dep = new Deposito();
        Produtor p = new Produtor(dep, 1, "Thread-0");
        Consumidor c = new Consumidor(dep, 1, "Thread-1");
        Consumidor c2 = new Consumidor(dep, 1, "Thread-2");
        Consumidor c3 = new Consumidor(dep, 1, "Thread-3");
        //inicia o produtor
        Thread tp = new Thread(p);
        tp.start();
        //inicia o consumidor
        Thread tc = new Thread(c);
        Thread tc2 = new Thread(c2);
        Thread tc3 = new Thread(c3);
        tc.start();
        tc2.start();
        tc3.start();
        System.out.println("Execução do main da classe Deposito terminada!");
    }
}

class Produtor implements Runnable {
    Deposito deposito;
    int tempo;
    String nome;

    Produtor(Deposito deposito, int tempo, String nome) {
        this.deposito = deposito;
        this.tempo = tempo;
        this.nome = nome;
    }

    public void run() {

        System.out.println("Iniciando produtor " + nome);
        for (int i = 0; i < 20; i++) {
            try {
                int depositado = deposito.colocar(nome);
                if (depositado == 0) {
                    System.out.println(nome + " .Deposito cheio, esperando caixas serem removidas");
                }

                Thread.sleep(tempo * 1000L);
            } catch (InterruptedException e) {
            }
        }

    }
}

class Consumidor implements Runnable {
    Deposito deposito;
    int tempo;
    String nome;

    Consumidor(Deposito deposito, int tempo, String nome) {
        this.deposito = deposito;
        this.tempo = tempo;
        this.nome = nome;
    }

    public void run() {

        System.out.println("Iniciando Consumidor " + nome);
        for (int i = 0; i < 10; i++) {
            try {
                int retirado = deposito.retirar(nome);
                if (retirado == 0) {
                    System.out.println(nome + " .Deposito vazio, esperando caixas serem colocadas");
                }

                Thread.sleep(tempo * 1000L);
            } catch (InterruptedException e) {
            }
        }

    }
}