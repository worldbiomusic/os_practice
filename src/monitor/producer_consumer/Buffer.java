package monitor.producer_consumer;

public class Buffer {
	int buf[];
	int size, count;
	int in, out;
	
	
	Buffer(int size)
	{
		this.size = size;
		buf = new int[size];
		count = in = out = 0;
	}
	
	synchronized void insert(int item)
	{
		if(count >= size)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		buf[in] = item;
		in = (in + 1) % size;
		count++;
		
		notify();
	}
	
	synchronized int remove()
	{
		if(count <= 0)
		{
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int item = buf[out];
		out = (out + 1) % size;
		count--;
		
		notify();
		
		return item;
	}
}
