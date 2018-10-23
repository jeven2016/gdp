package zjtech.bmf.api.facade;

import zjtech.bmf.api.result.IBmfResult;

/**
 * SMF Web 本地设置管理门面类
 */
public interface ISmfWebMgntFacade extends IBmfFacade {

  /**
   * 获取当前的SMF缺省配置
   *
   * @return
   */
  IBmfResult getSmfWebSetting();

  /**
   * 获取主题相关的设置
   *
   * @return
   */
  IBmfResult getThemeSetting();
}
