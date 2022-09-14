package af2parte1;
// Feito por Felipe Silveira e Samir Monteiro
class Mailbox {
	String mensagem = null;

	public synchronized void storeMessage(String mensagemGuardar) {
		while (mensagem != null) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}
		if (mensagem == null) {
			mensagem = mensagemGuardar;
			System.out.println(mensagem + " - Está guardando uma mensagem");
			notifyAll();
		}

	}

	public synchronized void retrieveMessage(String nomeConsumidor) {
		while (mensagem == null) {
			try {
				wait();
			} catch (InterruptedException e) {

			}
		}
		if (mensagem != null) {
			System.out.println(nomeConsumidor + " - Obtendo conteudo do mailbox: " + mensagem);
			mensagem = null;
			notifyAll();
		}
	}
}

class Producer extends Thread {
	Mailbox mailbox;
	String nome;
	
	public Producer(Mailbox mailbox, String nome) {
		this.mailbox = mailbox;
		this.nome = nome;
	}
	
	public void run() {
		for(int i = 0; i < 100; i++) {
			mailbox.storeMessage(nome);
		}
	}
}

class Consumer extends Thread {
	Mailbox mailbox;
	String nome;
	
	public Consumer(Mailbox mailbox, String nome) {
		this.mailbox = mailbox;
		this.nome = nome;
	}
	
	public void run() {
		for(int i = 0; i < 100; i++) {
			mailbox.retrieveMessage(nome);
		}
	}
}

public class Parte1 {
	public static void main(String args[]) {
		Mailbox mailbox = new Mailbox();
		String produtor = "Produtor ";
		String consumidor = "Consumidor ";
		Producer p = new Producer(mailbox, produtor + "00");
		Consumer c = new Consumer(mailbox, consumidor + "00");
		p.start();
		c.start();
		for(int numeroThreads = 1; numeroThreads <= 100; numeroThreads++) {
			Producer pIteravel = new Producer(mailbox, produtor + numeroThreads);
			Consumer cIteravel = new Consumer(mailbox, consumidor + numeroThreads);
			pIteravel.start();
			cIteravel.start();
		}
	}
}
