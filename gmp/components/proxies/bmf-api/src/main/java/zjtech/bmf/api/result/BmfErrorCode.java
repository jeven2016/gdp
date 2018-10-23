package zjtech.bmf.api.result;

import zjtech.common.result.ErrorCode;

public interface BmfErrorCode extends ErrorCode {

  /**
   * 内部异常
   */
  int INTERNAL_EXCEPTION = 2000;

  /**
   * 用户不存在
   */
  int USER_NOT_EXIST = 2001;

  /**
   * 用户名或密码错误
   */
  int INVALID_CREDENTIAL = 2002;

  /**
   * 账号已冻结
   */
  int ACCOUNT_LOCKED = 2003;

  interface Parameter {
    //start from 3000
    int SPECIAL_CHARACTER_EXIST = 3001;//存在特殊字符
    int INVALID_LENGTH = 3002;//长度不符合规定
    int CANNOT_NULL = 3003;//不能为null
    int INVALID_ADDRESS = 3004;//无效的地址
    int INVALID_PORT = 3005;//端口无效，不在值范围内
  }

  String notNull = "validation.field.notNull"; //不能为空
  String notBlank = "validation.field.notBlank"; //值不能为空
  String invalidCharacter = "validation.field.invalidCharacter";//非法字符
  String invalidLength = "validation.field.invalidLength";//字符不在合法范围内
  String invalidAddress = "validation.field.invalidAddress";//无效的地址
  String lessThanMinValue = "validation.field.lessThanMinValue";//小于最小值
  String greatThanMaxValue = "validation.field.greatThanMaxValue";//大于最大值


}
