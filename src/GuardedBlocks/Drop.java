package GuardedBlocks;

public class Drop {
	// Message sent from producer to consumer.
	private String message;
	
	// True if consumer should wait for producer to send message,
	// False if producer should should wait for consumer to retrieve message.
	private boolean empty = true;
	
	public synchronized String take() {
		// wait until message is available
		while (empty) {
			try {
				System.out.println("taking ...");
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		// Toggle status.
		empty = true;
		
		// Notify producer that status has changed.
		notifyAll();
		return message;
	}
	
	public synchronized void put(String message) {
		// Wait until message has been retrieved.
		while (!empty) {
			try {
				System.out.println("puting ..." + empty);
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("waiting ..." + empty);
		// Toggle status
		empty = false;
		
		// Store messsage.
		this.message = message;
		
		// Notify consumer that status has changed.
		notifyAll();
	}
}
