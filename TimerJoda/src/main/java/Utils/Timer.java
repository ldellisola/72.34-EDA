package Utils;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.Period;

public class Timer {
	
	private DateTime startTimeStamp;
	private DateTime stopTimeStamp = null;
	
	public Timer() {
		this.startTimeStamp = DateTime.now();
	}
	
	public Timer(long initialMS) {
		//this.startTimeStamp = DateTime.;
	}
	
	public void stop() {
		this.stopTimeStamp = DateTime.now();
	}
	public void stop(long finalMS) {
		//this.stopTimeStamp = DateTime.;
	}
	
	@Override
	public String toString() {
		
		if(stopTimeStamp == null)
			throw new TimerNotStoppedException();
		
		Period timePeriod = new Period(startTimeStamp,stopTimeStamp);
	

		if(stopTimeStamp.isBefore(startTimeStamp))
			throw new TimerInvalidException();
		

		StringBuilder bld = new StringBuilder();
					
		bld.append("( ");
		bld.append(timePeriod.toDurationFrom(startTimeStamp).getMillis());
		bld.append(" ms ) ");
		
		int days = timePeriod.getDays();
		timePeriod = timePeriod.minusDays(days);
				
		int hours = timePeriod.getHours();
		timePeriod = timePeriod.minusHours(hours);
		
		int minutes = timePeriod.getMinutes();
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
			super("The timer result is not valid.");
		}
	}
	

}
