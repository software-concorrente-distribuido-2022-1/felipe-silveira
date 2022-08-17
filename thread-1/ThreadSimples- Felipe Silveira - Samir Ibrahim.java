package aaaaa;

public class ThreadSimples {
	//printa o nome da thread que chamar o método e a mensagem passada como parametro
	static void mensagem(String messagem) {
		//Obtem o nome da thread atual
		String nomeThread = Thread.currentThread().getName();
		//Concatena o nome da thread com a mensagem e printa elas
		System.out.println(nomeThread + " " + messagem);
	}
	//Realiza um loop que imprime as mensagens dentro de info, esperando 4 segundos entre cada mensagem e retornando uma mensagem caso a thread seja interrompida
	private static class Loop implements Runnable {
		public void run() {
			//Instancia o vetor de strings das mensagens a serem printadas
			String info[] = {
					"Java",
					"é uma boa linguagem.",
					"Com threads,",
					"é melhor ainda."
			};
			try {
				//Inicia um loop
				for (int i = 0; i < info.length; i++) {
					//Coloca a thread atual em sleep
					Thread.sleep(4000);
					//Realiza um print do nome da thread e a mensagem do info na posição i
					mensagem(info[i]);
				}
			} catch (InterruptedException e) {
				//Caso a thread seja interrompida ele retorna o nome da thread concatenado com 'Nada Feito'
				mensagem("Nada feito!");
			}
		
		}
	}
	
	//Executa uma thread de loop esperando que ela termine ou interrompendo ela caso ela demore mais que o tempo pre determinado
	public static void main(String args[]) throws InterruptedException {
		//determina como o tempo de espera como 1 hora
		long paciencia = 1000 * 60 * 60;
		if (args.length > 0) {
			try {
				//Utiliza o argumento passado na execução para determinar o numero de segundos a serem esperados
				paciencia = Long.parseLong(args[0]) * 1000;
			} catch (NumberFormatException e) {
				//retorna uma mensagem caso o argumento passado não seja um Long
				System.err.println("Argumento deve ser um inteiro.");
				//encerra a execução
				System.exit(1);
			}
		}
		mensagem("Iniciando a thread Loop");
		//obtem tempo atual
		long inicio = System.currentTimeMillis();
		//cria uma nova instância de thread Loop
		Thread t = new Thread(new Loop());
		//inicia a thread loop
		t.start();
		mensagem("Esperando que a thread Loop termine");
		//realiza um loop enquanto a thread t da classe Loop existir
		while (t.isAlive()) {
			mensagem("Ainda esperando...");
			//espera 1 segundo ou até que t termine
			t.join(1000);
			//entra se tiver passado um tempo maior que paciencia e t ainda estiver operando 
			if (((System.currentTimeMillis() - inicio) > paciencia) && t.isAlive()) {
				mensagem("Cansado de esperar!");
				//interrompe c
				t.interrupt();
				//espera t terminar
				t.join();
			}
		}
		//retorna mensagem de finalização
		mensagem("Finalmente!");
	}
}
