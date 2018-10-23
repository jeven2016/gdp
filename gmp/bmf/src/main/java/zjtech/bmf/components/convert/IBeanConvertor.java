package zjtech.bmf.components.convert;

/**
 * Bean converter interface
 */
public interface IBeanConvertor {

  /**
   * 将原对象的属性转换变拷贝到目标对象中
   *
   * @param destination 目标对象
   * @param source      源拷贝对象
   * @return void
   */
  <Source, Destination> void convert(Source source, Destination destination);
}

