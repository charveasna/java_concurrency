
public class SleepMessages {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		String importantInfo[] = {
				"Mares eat oats",
				"Dogs eat oats",
				"LIttle lambs eat ivy",
				"A kid will eat ivy too"
		};
			
		for (int i = 0; i < importantInfo.length; i++) {
			// Pause for 4 seconds
			// Thread.sleep(4000);
			
			// To support its own interruptions, sleep has to be in as the following
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// We've been interrupted
				return;
			}
			
			// Print a message
			System.out.println(importantInfo[i]);
		}
	}
}
