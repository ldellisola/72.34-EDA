
import java.util.concurrent.TimeUnit;


public class Timer {
	
	private long  startTimeStamp;
	private long stopTimeStamp = -1;
	
	public Timer() {
		this.startTimeStamp = System.currentTimeMillis();
	}
	
	public Timer(long initialMS) {
		this.startTimeStamp = initialMS;
	}
	
	public void stop() {
		this.stopTimeStamp = System.currentTimeMillis();
	}
	public void stop(long finalMS) {
		this.stopTimeStamp = finalMS;
	}
	
	@Override
	public String toString() {
		
		if(stopTimeStamp == -1)
			throw new TimerNotStoppedException();
		
		long diff = this.stopTimeStamp - this.startTimeStamp;

		if(diff < 0)
			throw new TimerInvalidException();
		

		StringBuilder bld = new StringBuilder();		
			
		bld.append("( ");
		bld.append(diff);
		bld.append(" ms ) ");
		
		long days = TimeUnit.MILLISECONDS.toDays(diff); 
		diff -= TimeUnit.DAYS.toMillis(days);
		
		long hours = TimeUnit.MILLISECONDS.toHours(diff);
		diff -= TimeUnit.HOURS.toMillis(hours);
		
		long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
		diff -= TimeUnit.MINUTES.toMillis(minutes);
		
		long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
		
		bld.append(days);
		bld.append(" days ");
		
		bld.append(hours);
		bld.append(" hs ");
		bld.append(minutes);
		bld.append(" min ");
		
		bld.append(seconds);
		bld.append(" seconds ");
		
		
		return bld.toString();
	}
	
	@SuppressWarnings("serial")
	class TimerNotStoppedException extends RuntimeException{
		
		public TimerNotStoppedException() {
			super("The timer must be stopped before printing its result");
		}
	}
	
	@SuppressWarnings("serial")
	class TimerInvalidException extends RuntimeException{
		
		public TimerInvalidException() {
			super("The timer result is not valid.");
		}
	}
	

}
