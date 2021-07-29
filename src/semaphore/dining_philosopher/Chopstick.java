package semaphore.dining_philosopher;

import java.util.concurrent.Semaphore;

@SuppressWarnings("serial")
class Chopstick extends Semaphore{

	Chopstick(int arg0) {
		super(arg0);
	}
	
	void holdChopstick()
	{
		try {
			this.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void putDownChopstick()
	{
		this.release();
	}
}
