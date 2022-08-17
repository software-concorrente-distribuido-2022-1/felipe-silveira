public class Coelhos {
    public static void main(String args[]) {
        Vencedor vencedor = new Vencedor();
        for(int i = 0; i < 5; i ++) {
            ThreadCoelho t = new ThreadCoelho(Integer.toString(i), vencedor );
            t.start();
        }
        while(vencedor.vencedor.equals("")) {}
        System.out.println("Vencedor:" + vencedor.vencedor);
    }
}

class Vencedor {
    String vencedor = "";
    public synchronized void marcaVencedor(String vencedor) {
        if(this.vencedor.equals("")) {
            this.vencedor = vencedor;
        }
    }
}

class ThreadCoelho extends Thread {
    long posicao = 0;
    String nome;
    Vencedor vencedor;

    public ThreadCoelho(String nome, Vencedor vencedor) {
        this.nome = nome;
        this.vencedor = vencedor;
    }
    public void run() {
        while(posicao < 20) {
            salto();
            try {
                this.wait(10);
            } catch (Exception e) {}
        }
        vencedor.marcaVencedor(nome);
    }

    public void salto() {
        long saltoAtual = Math.round(Math.random() % 2) + 1;
        posicao = posicao + saltoAtual;
        System.out.println(nome + " pulou : " + saltoAtual);
    }
}
