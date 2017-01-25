import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Time {

	public static void main(String[] args) {
		
//		Runnable helloRunnable = new Runnable() {
//			
//			@Override
//			public void run() {
//				System.out.println("Hello World!");
//				
//			}
//		};
//		
//		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//		executor.scheduleAtFixedRate(helloRunnable, 0, 3, TimeUnit.SECONDS);
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
		    @Override
		    public void run() {
		       System.out.println("Hello World");
		    }
		}, 0, 5000);
	}

}
