package semaphore.dining_philosopher;

public class Philosopher extends Thread{

	int id;
	Chopstick lstick, rstick;
	
	Philosopher(int id, Chopstick lstick, Chopstick rstick)
	{
		this.id = id;
		this.lstick = lstick;
		this.rstick = rstick;
	}
	
	@Override
	public void run()
	{
		try 
		{
			while(true)
			{
				// prepare dining
				lstick.holdChopstick();
				rstick.holdChopstick();
				
				// dining
				dining();
				
				// prepare think
				lstick.putDownChopstick();
				rstick.putDownChopstick();
				
				// think
				think();
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	void dining()
	{
		System.out.println(String.format("[%d] philosopher is dining", id));
	}
	
	void think()
	{
		System.out.println(String.format("[%d] philosopher is thinking", id));
	}
	
}
