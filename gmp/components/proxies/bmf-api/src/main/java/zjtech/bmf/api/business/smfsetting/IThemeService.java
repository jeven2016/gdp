package zjtech.bmf.api.business.smfsetting;

import zjtech.bmf.api.business.IBaseService;
import zjtech.bmf.api.result.IBmfResult;


public interface IThemeService extends IBaseService {
  /**
   * 获取主题相关的设置
   *
   * @return
   */
  IBmfResult getThemeSetting();
}
