package Utils;
import java.time.*;


public class Timer {
	
	private Instant startTimeStamp;
	private Instant stopTimeStamp = null;
	
	public Timer() {
		this.startTimeStamp = Instant.now();
	}
	
	public Timer(long initialMS) {
		this.startTimeStamp = Instant.now().plusMillis(initialMS);
	}
	
	public void stop() {
		this.stopTimeStamp = Instant.now();
	}
	
	public void stop(long finalMS) {
		Instant now = Instant.now();
		this.stopTimeStamp = startTimeStamp.plusMillis(finalMS);
		
		
		if(stopTimeStamp.isBefore(now)) {
			throw new TimerInvalidException();
		}

	}
	
    public long getElapsedTime() {
    	if(stopTimeStamp == null)
			throw new TimerNotStoppedException();
		
		if(stopTimeStamp.isBefore(startTimeStamp))
			throw new TimerInvalidException();
		
		Duration timePeriod = Duration.between(startTimeStamp, stopTimeStamp);
		return timePeriod.toMillis();
    }
	
	@Override
	public String toString() {
		
		if(stopTimeStamp == null)
			throw new TimerNotStoppedException();
		
		if(stopTimeStamp.isBefore(startTimeStamp))
			throw new TimerInvalidException();
		
		Duration timePeriod = Duration.between(startTimeStamp, stopTimeStamp);

		
		

		StringBuilder bld = new StringBuilder();
					
		bld.append("( ");
		bld.append(timePeriod.toMillis());
		bld.append(" ms ) ");
		
		long days = timePeriod.toDays();
		timePeriod = timePeriod.minusDays(days);
				
		long hours = timePeriod.toHours();
		timePeriod = timePeriod.minusHours(hours);
		
		long minutes = timePeriod.toMinutes();
		timePeriod = timePeriod.minusMinutes(minutes);
		
		long seconds = timePeriod.getSeconds();
		
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
			super("The timer result is not valid");
		}
	}
	

}
