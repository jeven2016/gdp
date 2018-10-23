package zjtech.smf.event;

import zjtech.smf.common.event.IEvent;

/**
 * Created by root on 14-12-24.
 */
public interface IEventPublisher<Event extends IEvent> {

  /**
   * 触发一个事件
   */
  default public void publishEvent(Event event) {
  }

  /**
   * 是否启用
   * @param event
   * @return
   */
  default public boolean isEnabled(Event event) {
    return false;
  }

}
