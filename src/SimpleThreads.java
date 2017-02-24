/**
 * 
 */

/**
 * @author vchar
 *
 */
public class SimpleThreads {
	
	// Display a message, preceded by the name of the current thread
	static void threadMessage(String message) {
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, message);
	}

	private static class MessageLoop implements Runnable {
		public void run() {
			String importantInfo[] = {
					"Mares eat oats",
					"Dogs eat oats",
					"LIttle lambs eat ivy",
					"A kid will eat ivy too"
			};
			
			try {
				for (int i = 0; i < importantInfo.length; i++) {
					// pause for 4 seconds
					Thread.sleep(4000);
					
					// print a message
					threadMessage(importantInfo[i]);
				}
			} catch (InterruptedException e) {
					threadMessage("I wasn't done!");
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws InterruptedException {
		// Delay, in milliseconds before we interrupt
		// messageLoop thread (default one hour).
		long patience = 100*60*60;
		
		// if command line argument present, gives patience in seconds.
		if (args.length > 0) {
			try {
				patience = Long.parseLong(args[0]) * 1000;
			} catch (NumberFormatException e) {
				System.err.println("Argument must be an integer.");
				System.exit(1);
			}
		}
		
		threadMessage("Starting messageloop thread");
		long startTime = System.currentTimeMillis();
		Thread t = new Thread(new MessageLoop());
		t.start();

		threadMessage("Waiting for messageLoop thread to finish");
		
		// loop until MessageLoop thread exists
		while (t.isAlive()) {
			threadMessage("Still waiting ...");
			// Wait maximum of 1 second for MessageLoop thread to finish
//			t. join(1000);
			if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
				threadMessage("Tired of waiting");
				t.interrupt();
				// shouldn't be too long now -- wait indefinitely
//				t.join();
			}
		}
		
		threadMessage("Finally");
	}

}
