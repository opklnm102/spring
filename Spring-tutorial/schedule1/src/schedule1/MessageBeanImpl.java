package schedule1;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MessageBeanImpl extends QuartzJobBean implements MessageBean {

	private Log log = LogFactory.getLog(getClass());

	private String name;

	private String greeting;

	public void setName(String name) {
		this.name = name;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}

	public void sayHello() {
		String message = greeting + name + "!";
		log.info("스레드ID=" + Thread.currentThread().getId() + ":" + message);
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		sayHello();
	}

}