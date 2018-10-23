package zjtech.bmf.components.convert;

import net.sf.cglib.core.Converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Field interceptor for converting single specific field
 */
public class FieldInterceptor implements Converter {
  Map<String, Class<?>> innerBeanClassMap;
  IBeanConvertor convertProxy;

  public FieldInterceptor(Map<String, Class<?>> innerBeanClassMap, IBeanConvertor convertProxy) {
    this.innerBeanClassMap = innerBeanClassMap;
    this.convertProxy = convertProxy;
  }


  /**
   * Convert source object to destination object
   *
   * @param value            the field's value
   * @param sourceClass      source class
   * @param setterMethodName the setter method name corresponds to current field
   * @return converted value object
   */
  @Override
  public Object convert(Object value, Class sourceClass, Object setterMethodName) {
    /**
     * 其中instanceof是子-->父   isAssignableFrom是父-->子
     */
    if (Collection.class.isAssignableFrom(sourceClass)) {
      Collection collection = (Collection) value;


      if (collection.isEmpty()) {
        return new ArrayList();
      }
      Collection destCol = new ArrayList();
      Class innerBeanClass = innerBeanClassMap.get(setterMethodName);

      if (innerBeanClass == null) {
        return collection;
      }

      //generate same number of instances according to collection's size
//      Collection instCollection = Stream.generate(() -> newInstance(innerBeanClass)).limit(collection.size())
//              .collect(Collectors.toList());

      //copy inner bean
      collection.forEach(sourceInnerObj -> {
        Object instance = newInstance(innerBeanClass);
        convertProxy.convert(sourceInnerObj, instance);
        destCol.add(instance);
      });
      return destCol;
    }

    return value;
  }

  private Object newInstance(Class innerBeanClass) {
    try {
      Object obj = innerBeanClass.newInstance();
      return obj;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
