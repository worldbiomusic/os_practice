package semaphore.producer_consumer;

import java.util.concurrent.Semaphore;

public class Buffer {
	int buf[];
	int size, count;
	int in, out;
	Semaphore mutex;
	Semaphore empty, full;
	
	
	Buffer(int size)
	{
		this.size = size;
		buf = new int[size];
		count = in = out = 0;
		
		mutex = new Semaphore(1);
		empty = new Semaphore(size);
		full = new Semaphore(0);
	}
	
	void insert(int item)
	{
		try {
			empty.acquire();
			mutex.acquire();
		} catch (Exception e) {
			e.printStackTrace();
		}
		buf[in] = item;
		in = (in + 1) % size;
		count++;
		
		mutex.release();
		full.release();
	}
	
	int remove()
	{
		// check buffer is empty
		try {
			full.acquire();
			mutex.acquire();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int item = buf[out];
		out = (out + 1) % size;
		count--;
		
		mutex.release();
		empty.release();
		
		return item;
	}
}
