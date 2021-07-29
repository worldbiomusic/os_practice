package monitor.dining_philosopher;


class Chopstick{

	boolean inUse = false;
	
	synchronized void holdChopstick()
	{
		if(inUse)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		inUse = true;
	}
	
	synchronized void putDownChopstick()
	{
		inUse = false;
		notify();
	}
}
