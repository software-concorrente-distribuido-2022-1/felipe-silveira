import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class ContadorPrimos {
    public static void main(String args[]) {
        //entre 1.000.000 e 30.000.000
        BuscaListaThread busca = new BuscaListaThread();
        System.out.println("Iniciando 1.000.000 a 30.000.000");
        long tempoInicial1 = System.currentTimeMillis();
        List<Integer> primos1 = contaPrimos(1000000, 30000000);
        long duracao1 = System.currentTimeMillis() - tempoInicial1;
        System.out.println("Tempo de duracao do entre 1.000.000 e 30.000.000: " + duracao1/1000);
        System.out.println(busca.parallelSearch(primos1.get(4), primos1, 100));
        //entre 90.000.000 e 120.000.000
        System.out.println("Iniciando 90.000.000 a 120.000.000");
        long tempoInicial2 = System.currentTimeMillis();
        List<Integer> primos2 = contaPrimos(90000000, 120000000);
        long duracao2 = System.currentTimeMillis() - tempoInicial2;
        System.out.println("Tempo de duracao do entre 1.000.000 e 30.000.000: " + duracao2/1000);
        System.out.println(busca.parallelSearch(primos2.get(11), primos2, 100));
    }

    public static List<Integer> contaPrimos(Integer inicio, Integer fim) {
        List<Integer> numerosPrimos = new ArrayList<>();
        for (Integer numero = inicio; numero <= fim; numero++) {
            if (ePrimo(numero)) {
                numerosPrimos.add(numero);
            }
        }
        return numerosPrimos;
    }

    static boolean ePrimo(Integer numero) {
        for (Integer i = 2; i < numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}

class BuscaListaThread {
    int index;
    public int parallelSearch(int x, List<Integer> A, int numThreads) {
        this.index = -1;
        int tamanhoA = A.size();
        int parcelasAThread = tamanhoA % numThreads;
        for(int i = 0; (i <= numThreads && parcelasAThread * (i) < tamanhoA) ; i++) {
            int inicio = parcelasAThread * i;
            int fim;
            if(i == numThreads && (parcelasAThread * (i + 1)) > tamanhoA) {
                fim = tamanhoA;
            } else {
                fim = parcelasAThread * (i + 1);
            }
            ThreadIdentificaNaLista t = new ThreadIdentificaNaLista(inicio, fim, A, x, this);
            t.start();
        }
        try {
           sleep(10000);
        } catch (Exception e) {}
         return index;
    }

    public synchronized void marcaIndex(int indexMarcar) {
        this.index = indexMarcar;
    }
}

class ThreadIdentificaNaLista extends Thread{
    int inicio;
    int fim;
    List<Integer> lista;
    int buscar;
    BuscaListaThread buscaListaThread;
    public void run() {
        for(int i = inicio; i < fim; i++) {
            if(lista.get(i) == buscar) {
                buscaListaThread.marcaIndex(i);
            }
        }
    }
    public ThreadIdentificaNaLista(int inicio, int fim, List<Integer> lista, int buscar, BuscaListaThread buscaListaThread) {
        this.inicio = inicio;
        this.fim = fim;
        this.lista = lista;
        this.buscar = buscar;
        this.buscaListaThread = buscaListaThread;
    }
}
