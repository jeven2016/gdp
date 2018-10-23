package zjtech.smf.event;

import zjtech.smf.common.event.IEvent;
import org.springframework.stereotype.Component;

@Component("LogEventPublisher")
public class LocalLogEventPublisher implements IEventPublisher {

  @Override
  public void publishEvent(IEvent event) {
    //记录本地日志

  }

  @Override
  public boolean isEnabled(IEvent event) {
    return false;
  }
}
