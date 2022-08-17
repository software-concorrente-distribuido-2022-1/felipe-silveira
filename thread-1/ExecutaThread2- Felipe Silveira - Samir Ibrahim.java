package aaaaa;

public class ExecutaThread2 {
	public static void main(String args[]) {
		 ThreadSimples2 simples = new ThreadSimples2();
		 simples.start();
	}
}

class ThreadSimples2 extends Thread {
	 public void run() {
		 System.out.println("Hello from a thread!");
	 }
}
