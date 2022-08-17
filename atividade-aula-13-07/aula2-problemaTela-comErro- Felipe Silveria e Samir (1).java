package AAAAAA;

class Tela2 {
	String texto;

	public void setTexto(String s) {
		texto = s;
	}

	public void mostraTexto(String nomeThread) {
		System.out.println(nomeThread + "printando: " + texto);
	}
}

class UserSemControle extends Thread {
	private Tela2 recurso;
	private String nomeThread;

	public UserSemControle(String str, Tela2 r) {
		recurso = r;
		nomeThread = str;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			recurso.setTexto(nomeThread);
			try {
				sleep(30);
			} catch (Exception e) {
			}
			recurso.mostraTexto(nomeThread);
		}
	}
}

public class aula2erro {
	public static void main(String[] args) {

		Tela2 recurso = new Tela2();
		for (int i = 0; i < 1000; i++) {
			UserSemControle us = new UserSemControle("Usuario " + i, recurso);
			us.start();
		}
	}
}