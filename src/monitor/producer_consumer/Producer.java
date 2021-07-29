package monitor.producer_consumer;

public class Producer extends Thread{
	Buffer b;
	int N;
	
	Producer(Buffer b, int N)
	{
		this.b = b;
		this.N = N;
	}
	
	@Override
	public void run()
	{
		for(int i = 0; i < N; i++)
		{
			b.insert(i);
		}
	}
}
