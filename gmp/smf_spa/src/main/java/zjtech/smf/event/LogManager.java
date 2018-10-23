package zjtech.smf.event;

import zjtech.smf.common.event.IEvent;
import zjtech.smf.common.event.LogLevel;
import org.springframework.stereotype.Component;

@Component
public class LogManager {

  public void log(IEvent event){
    //选择本地log或远程log对象
    //记录日志
  }

  public boolean isEnabled(LogLevel level){
    return true;
  }
}
