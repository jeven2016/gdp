package zjtech.smf.modules.system;


import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;
import zjtech.smf.modules.global.action.BaseAction;
import zjtech.smf.common.result.SmfResult;
import zjtech.smf.common.constants.LanguageEnum;
import zjtech.smf.common.constants.UrlMapping;

import javax.servlet.http.HttpSession;
import java.util.Locale;

@Controller
public class LanguageAction extends BaseAction {

    @RequestMapping(UrlMapping.SWITCH_LANGUAGE)
    @ResponseBody
    public SmfResult switchLanguage(@PathVariable("language") String language) {
        if (StringUtils.isEmpty(language)) {
            return new SmfResult().setOk(false).addGlobalError("error.illegal.parameter", "'language'");
        }
        setLanguage(language);
        return new SmfResult();
    }


    //switch language
    private void setLanguage(String lan) {
        boolean isValidLanguage = !StringUtils.isEmpty(lan) && LanguageEnum.acceptable(lan);
        HttpSession session = getSession();

        if (isValidLanguage) {
            Locale sessionLocale = getLocaleFromSession();
            LanguageEnum languageEnum = LanguageEnum.valueOf(lan);
            Locale newLocale = languageEnum.getLocaleInfo();

            //如果未设置语言或需要修改语言设置
            if (sessionLocale == null || !sessionLocale.equals(newLocale)) {
                //session中存储当前国际化设置
                WebUtils.setSessionAttribute(getRequest(), SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, newLocale);
            }

        }
    }
}
