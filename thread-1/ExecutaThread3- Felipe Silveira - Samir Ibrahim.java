package aaaaa;

public class ExecutaThread3 {
	public static void main(String args[]) {
		 ThreadSimples3 simples = new ThreadSimples3();
		 System.out.println(Thread.currentThread().getName());
		 simples.start();
	}
}

class ThreadSimples3 extends Thread {
	public void run() {
		 String info[] = {
				 "Java",
				 "é uma boa linguagem.",
				 "Com threads",
				 "é melhor ainda."
		 };
		 for (int i = 0; i < info.length; i++) {
			 System.out.println(Thread.currentThread().getName());
			 try {
			 Thread.sleep(4000);
			 } catch (InterruptedException e) {}
			 System.out.println(info[i]);
		 }
	 }
}
