package zjtech.bmf.init;

import zjtech.bmf.api.business.smfsetting.IThemeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Configuration
@ComponentScan(value = {"zjtech.bmf"})
public class BmfConfig {

  public static void main(String[] args) {
    //test
    ApplicationContext ctx = new ClassPathXmlApplicationContext("/spring/sp-bmf.xml");
    IThemeService srv = (IThemeService) ctx.getBean(IThemeService.class);
    System.out.println(srv.getThemeSetting());
  }
}
