//Usando Thread para contador
public class TesteContador {
	public static void main(String args[]) {
		Contador contador = new Contador();
		contador.start();
	}
}

class Contador extends Thread {
	public void run() {
		for(int i = 0; i <= 10; i++) {
			System.out.printlin(i);
		}
	}
}

//Usando Runnable para contador
public class TesteContador {
	public static void main(String args[]) {
		Contador contador = new Thread(new Contador());
		contador.start();
	}
}

class Contador implements Runnable {
	public void run() {
		for(int i = 0; i <= 10; i++) {
			System.out.printlin(i);
		}
	}
}

//Mais de uma thread
//Usando Thread para contador
public class TesteContador {
	public static void main(String args[]) {
		Contador contador1 = new Thread(new Contador());
		Contador contador2 = new Thread(new Contador());
		Contador contador3 = new Thread(new Contador());
		Contador contador4 = new Thread(new Contador());
		contador1.start();
		contador2.start();
		contador3.start();
		contador4.start();
	}
}

class Contador extends Runnable {
	public void run() {
		for(int i = 0; i <= 10; i++) {
			System.out.printlin(i);
		}
	}
}