package zjtech.bmf.api.constant;

/**
 * 值常量定义类
 */
public interface ValueConstants {
  /**
   * 名称允许输入的最多字符数
   **/
  int MAX_LENGTH_OF_NAME = 20;

  /**
   * 名称允许输入的最少字符数
   **/
  int MIN_LENGTH_OF_NAME = 1;

  /**
   * 描述允许输入的最多字符数
   **/
  int MAX_LENGTH_OF_DESC = 250;

  /**
   * 描述允许输入的最少字符数
   **/
  int MIN_LENGTH_OF_DESC = 0;


  int DEFAULT_PAGE = 0; //默认显示第一页
  int DEFAULT_PAGE_SIZE = 10;//每页显示的条数

}
