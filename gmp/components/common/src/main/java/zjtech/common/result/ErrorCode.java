package zjtech.common.result;

/**
 * 公共错误码
 */
public interface ErrorCode {
  /**
   * 公共错误吗
   */
  int SUCCESS = 1000;
  /**
   * 操作成功
   */
  int FAILED = 1001;
  /**
   * 操作失败*
   */
  int INTER_EXCEPTION = 1002; /**内部异常*/

}
