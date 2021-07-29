package semaphore.producer_consumer;

public class Consumer extends Thread{
	Buffer b;
	int N;
	
	Consumer(Buffer b, int N)
	{
		this.b = b;
		this.N = N;
	}
	
	@Override
	public void run()
	{
		for(int i = 0; i < N; i++)
		{
			b.remove();
		}
	}
}
