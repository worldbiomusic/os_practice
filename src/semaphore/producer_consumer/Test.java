package semaphore.producer_consumer;

public class Test {
	public static void main(String args[])
	{
		System.out.println("start");
		
		Buffer buf = new Buffer(100);
		Producer p = new Producer(buf, 1000);
		Consumer c = new Consumer(buf, 1000);
		
		p.start();
		c.start();
		
		try {
			p.join();
			c.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("count: " + buf.count);
	}
}
