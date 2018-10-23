package zjtech.smf.common.log;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * Created by root on 16-2-26.
 */
public class LogInitializer implements ApplicationListener<ContextRefreshedEvent> {

	@Value("${logbackErrorMailPassword}")
	private String logbackErrorMailPassword;

	@Value("${supportEmail}")
	private String supportEmail;

	@Value("${spring.profiles.active}")
	private String env;

	@Value("${log.dir}")
	private String logDir;

	@Value("${log.name}")
	private String logName;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		try {
			configureLogback();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void configureLogback() throws IOException {

		// assume SLF4J is bound to logback in the current environment
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
		try {
			JoranConfigurator jc = new JoranConfigurator();
			jc.setContext(context);
			context.reset(); // override default configuration
			// inject the name of the current application as "application-name"
			// property of the LoggerContext
			context.putProperty("LOG_DIR", logDir);
			context.putProperty("LOG_NAME", logName);

			context.putProperty("ERROR_MAIL_PASSWORD", logbackErrorMailPassword);
			context.putProperty("SUPPORT_EMAIL_ID", supportEmail);
			context.putProperty("ENV", env);
			//jc .doConfigure(servletContext.getRealPath("/WEB-INF/my-logback.xml"));
			jc.doConfigure(new ClassPathResource("my-logback.xml").getInputStream());
		} catch (JoranException je) {
			// StatusPrinter will handle this
		}
		StatusPrinter.printInCaseOfErrorsOrWarnings(context);

	}
}
