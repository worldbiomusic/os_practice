package monitor.dining_philosopher;

public class Test {
	public static void main(String[] args) {
		
		int num = 5;
		
		// make chopstick
		Chopstick[] chopsticks = new Chopstick[num];
		
		for(int i = 0; i < num; i++)
		{
			chopsticks[i] = new Chopstick();
		}
		
		
		// make philosopher
		Philosopher[] philosophers = new Philosopher[num];
		
		for (int i = 0; i < num; i++) 
		{
			philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % num]);
		}
		
		
		// start
		for(Philosopher p : philosophers)
		{
			p.start();
		}
		
	}
}
