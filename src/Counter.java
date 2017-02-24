
public class Counter {
	private static int c = 0;
	
	// object for synchronized statement
	private static Object object = new Object();	
	
	// use synchronized keyword to create a synchronised method
//	public static void increment() {
//	public static synchronized void increment() {
//		c++;
//	}

	// alternatively, use synchronized statement of which objects 
	// need to be specified as an argument.
	public static  void increment() {
		synchronized(object) {
			c++;
		}
	}
	public static void decrement() {
		c--;
	}
	
	public static int value() {
		return c;
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i< 10000; i++) {
					increment();
				}
			}
		});
		
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0; i< 10000; i++) {
					increment();
				}
			}
		});
	t1.start();
	t2.start();
	try {
		t1.join();
		t2.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("value of c is : " + c);
	}
}
