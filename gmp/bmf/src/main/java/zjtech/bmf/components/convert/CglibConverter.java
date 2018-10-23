package zjtech.bmf.components.convert;


import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import org.springframework.stereotype.Component;
import zjtech.bmf.api.dto.ConvertParam;
import zjtech.bmf.common.NameConstants;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Component(NameConstants.COMMON_CONVERTOR)
public class CglibConverter implements IBeanConvertor {
  private HashMap<String, ConvertParamter> beanCopierMap = new HashMap();

  private HashMap<String/*Source+Destination*/, Map<String/*destinationSetter*/, Class<?>/*innerBeanClass*/>>
          convertParamMap = new HashMap<>();

  @Override
  public <Source, Destination> void convert(Source source, Destination destination) {
    String outerMapKey = getOuterMapKey(source, destination);
    ConvertParamter convertParamter = beanCopierMap.get(outerMapKey);
    BeanCopier copier;
    Converter converter = null;

    if (convertParamter == null) {
      synchronized (source.getClass()) {
        convertParamter = beanCopierMap.get(outerMapKey);
        if (convertParamter == null) {
          boolean needConvert = false;
          Map<String, Class<?>> innerBeanClassMap = null;

          Field[] fields = destination.getClass().getDeclaredFields();
          for (Field field : fields) {
            if (field.isAnnotationPresent(ConvertParam.class)) {

              //init a innerBeanClass map and register into convertParamMap
              if (innerBeanClassMap == null) {
                innerBeanClassMap = new HashMap<>();
                convertParamMap.put(outerMapKey, innerBeanClassMap);
              }

              needConvert = true;
              ConvertParam param = field.getDeclaredAnnotation(ConvertParam.class);

              //key : setter name ,value : innerBeanClass
              String setter = param.sourceBeanSetter();
              innerBeanClassMap.put(setter, param.innerBeanClass());
            }
          }

          if (needConvert) {
            copier = BeanCopier.create(source.getClass(), destination.getClass(), true);
            converter = new FieldInterceptor(innerBeanClassMap, this);
          } else {
            copier = BeanCopier.create(source.getClass(), destination.getClass(), false);
          }
          convertParamter = new ConvertParamter(copier, converter);
          beanCopierMap.put(outerMapKey, convertParamter);
        }
      }
    }

    copier = convertParamter.copier;
    converter = convertParamter.converter;

    copier.copy(source, destination, converter);
  }

  private <Source, Destination> String getOuterMapKey(Source source, Destination destination) {
    return source.getClass().getName() + "#" + destination.getClass().getName();
  }

  private static class ConvertParamter {
    BeanCopier copier;
    Converter converter;

    public ConvertParamter(BeanCopier copier, Converter converter) {
      this.copier = copier;
      this.converter = converter;
    }
  }

}
