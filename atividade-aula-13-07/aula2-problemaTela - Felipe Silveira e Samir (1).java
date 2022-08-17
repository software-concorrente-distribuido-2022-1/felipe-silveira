package AAAAAA;

class Tela {
	String texto;

	public void setTexto(String s) {
		texto = s;
	}

	public void mostraTexto() {
		System.out.println(texto);
	}
}

class Usuario extends Thread {
	private ControlaAcesso monitor;
	private String nomeThread;

	public Usuario(String str, ControlaAcesso m) {
		monitor = m;
		nomeThread = str;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			monitor.request();
			monitor.setRecurso(nomeThread);
			try {
				sleep(30);
			} catch (Exception e) {
			}
			monitor.usaRecurso();
			monitor.release();
		}
	}
}

public class aula2 {
	public static void main(String[] args) {

		Tela recurso = new Tela();

		ControlaAcesso monitor = new ControlaAcesso(recurso);
		Usuario us01 = new Usuario("Usuario 01", monitor);
		Usuario us02 = new Usuario("Usuario 02", monitor);
		Usuario us03 = new Usuario("Usuario 03", monitor);
		Usuario us04 = new Usuario("Usuario 04", monitor);

		us02.start();
		us01.start();
		us04.start();
		us03.start();
	}
}

class ControlaAcesso {
	private boolean ocupado = false;
	private Tela recurso;

	public ControlaAcesso(Tela r) {
		recurso = r;
	}

	public synchronized void release() {
		ocupado = false;
		notifyAll();
	}

	public synchronized void request() {
		while (ocupado) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		ocupado = true;
	}

	public void setRecurso(String s) {
		recurso.setTexto(s);
	}

	public void usaRecurso() {
		recurso.mostraTexto();
	}
}
