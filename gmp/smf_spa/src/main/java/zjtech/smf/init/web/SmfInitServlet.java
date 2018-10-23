package zjtech.smf.init.web;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import zjtech.smf.common.SmfWebUtils;
import zjtech.smf.common.constants.AttributesConstants;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(
        name = "SmfInitServlet",
        loadOnStartup = 2,
        description = "SMF web启动时执行系统的初始化工作，在Spring MVC的DispatcherServlet之后启动"
)
public class SmfInitServlet extends HttpServlet {
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.config = config;
        try {
            initAfterStartup();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initAfterStartup() {
        ServletContext context = config.getServletContext();

        try {
            //servlet获取由Spring容器管理的service对象
            WebApplicationContext webApplicationContext = SmfWebUtils.getWebAppCtxFromServletContext(context);
         /*   smfWebSettingSrv = (SmfWebSettingSrv) webApplicationContext.getBean(IdentifierConstants.SmfWebSettingSrv);
            themeSettingSrv = (ThemeSettingSrv) webApplicationContext.getBean(IdentifierConstants.ThemeSettingSrv);*/

            //设置页面资源所需的默认参数
//            SmfWebSettingDTO smfWebSettingDTO = smfWebSettingSrv.getSmfWebSetting();
            context.setAttribute(AttributesConstants.WEB_CONTEXT_PATH, this.config.getServletContext().getContextPath());
//      context.setAttribute(AttributesConstants.STATIC_RESOURCE_URL, smfWebSettingDTO.getStaticResourceUrl());
//      context.setAttribute(AttributesConstants.JSP_ROOT_PATH, smfWebSettingDTO.getJspRootPath());
//            context.setAttribute(AttributesConstants.ENABLE_VERIFYCODE_FORADMINLOGIN, smfWebSettingDTO.isEnableVerifyCodeForAdminLogin());

            //设置主题配置类
           /* ThemeSettingDTO themeSettingVO = (ThemeSettingDTO) themeSettingSrv.getById(null).getAttachment();
            themeSettingSrv = (ThemeSettingSrv) webApplicationContext.getBean(IdentifierConstants.ThemeSettingSrv);
            context.setAttribute(AttributesConstants.THEME_SETTING, themeSettingVO);
            //设置默认主题
            context.setAttribute(AttributesConstants.DEFAULT_THEME, themeSettingVO.getDefaultTheme());*/
        } catch (BeansException e) {
            e.printStackTrace();
        }

    }


}
