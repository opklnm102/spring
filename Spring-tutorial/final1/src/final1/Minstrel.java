package final1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class Minstrel {

	public void singBeforeQuest(JoinPoint jp) throws Throwable {
		
		System.out.println("singBeforeQuest");
	}
	
	public void singAfterQuest(JoinPoint pjp) throws Throwable {

		System.out.println("singAfterQuest");
	}	
}
