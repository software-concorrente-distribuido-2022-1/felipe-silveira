// Exercicio 1
// Inicio Exercicio 1
public class exercicio1 {
    public static void main(String[] args) {
        ThreadSimples t01 = new ThreadSimples("Thread01");
        t01.start();
    }
}

class ThreadSimple extends Thread {
    String nome;

    ThreadSimple(String nome) {
        this.nome = nome;
    }

    @Override
    public void run() {
        for(int i = 1; i <= 100; i++) {
            System.out.println(i + " " + nome);
        }
        System.out.println("Programa finalizado");
    }
}
// Fim exercicio 1

// Exercicio 2
//Inicio exercicio 2

public class exercicio2 {
    public static void main(String[] args) throws InterruptedException {
        ThreadSimples t01 = new ThreadSimples("Maca");
        ThreadSimples t02 = new ThreadSimples("Pera");
        ThreadSimples t03 = new ThreadSimples("Uva");
        ThreadSimples t04 = new ThreadSimples("Banana");
        ThreadSimples t05 = new ThreadSimples("Melao");
        t01.start();
        t02.start();
        t03.start();
        t04.start();
        t05.start();
    }
}

class ThreadSimples extends Thread {
    String nome;

    ThreadSimples(String nome) {
        this.nome = nome;
    }

    @Override
    public void run() {
        try {
            System.out.println(nome);
            Thread.sleep(1000);
            backspace(nome.length());
        } catch (Exception e) {

        }
    }

    public static void backspace(int number){
        for(int i=0; i<number; i++){
            System.out.print("\b\b");
        }
    }
}

// Fim exercicio 2

// Exercicio 3
// Inicio Exercicio 3
/*
  O cenário descrito de operações que afetam o saldo ocorrerem ao mesmo tempo é possível de ocorrer
devido ao gerenciamento das operações que estão na CPU ou a forma como está sendo gerida pelo compilador
threads podem começar e parar, deixando abertura para outras threads acessarem e atualizarem as
variáveis comuns entre elas, antes de a outra poder retornar a ativa. Podendo gerar inconsistências
ou causar exceções. No caso para solucionar esse cenário seria necessário tornar as operações de atualizar
e depositar como regiões criticas, podendo apenas uma thread realizar operações que afetem o saldo de
uma conta por vez, isso evitaria que uma alterasse o saldo enquanto outra estava tentando alterar e
com isso gerando inconsistência no valor. Para isso teria de implementar um sistema de semáforo onde
se uma estiver realizando a operação ela sinaliza para outras threads que não podem realizar a operação
até que ela tenha terminado, ao terminar ela notifica aos outros threads e libera elas para realizarem
a operação.
 */