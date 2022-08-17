package AAAAAA;

interface Buffer {
	public void set(int value); // place value into Buffer

	public int get(); // return value from Buffer
}

class Producer extends Thread {
	private Buffer sharedLocation;

	public Producer(Buffer shared) {
		super("Producer");
		sharedLocation = shared;
	}

	public void run() {
		for (int count = 1; count <= 4; count++) {

			try {
				Thread.sleep((int) (Math.random() * 3001));
				sharedLocation.set(count);
			}

			catch (InterruptedException exception) {
				exception.printStackTrace();
			}

		}

		System.err.println(getName() + " done producing." + "\nTerminating " + getName() + ".");

	}

}

class Consumer extends Thread {
	private Buffer sharedLocation;

	public Consumer(Buffer shared) {
		super("Consumer");
		sharedLocation = shared;
	}

	public void run() {
		int sum = 0;

		for (int count = 1; count <= 4; count++) {

			try {
				Thread.sleep((int) (Math.random() * 3001));
				sum += sharedLocation.get();
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}

		System.err.println(getName() + " read values totaling: " + sum + ".\nTerminating " + getName() + ".");

	}

}

class UnsynchronizedBuffer implements Buffer {
	private int buffer = -1;

	public void set(int value) {
		System.err.println(Thread.currentThread().getName() + " writes " + value);

		buffer = value;
	}

	public int get() {
		System.err.println(Thread.currentThread().getName() + " reads " + buffer);

		return buffer;
	}

}

public class aula2exercicio2unsyncronized {
	public static void main(String[] args) {

		Buffer sharedLocation = new UnsynchronizedBuffer();

		Producer producer = new Producer(sharedLocation);
		Consumer consumer = new Consumer(sharedLocation);

		producer.start();
		consumer.start();

	}

}