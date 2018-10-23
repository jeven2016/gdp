/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf.business.theme;


import org.springframework.stereotype.Service;
import zjtech.bmf.api.business.smfsetting.IThemeService;
import zjtech.bmf.api.result.BmfResult;
import zjtech.bmf.api.result.IBmfResult;
import zjtech.bmf.api.dto.ThemeSettingDTO;
import zjtech.bmf.api.dto.ThemeDTO;

@Service
public class ThemeService implements IThemeService {

  /**
   * 获取主题相关的设置
   *
   * @return IBmfResult
   */
  public IBmfResult getThemeSetting() {
    ThemeSettingDTO vo = new ThemeSettingDTO();
    IBmfResult result = new BmfResult();
    result.setAttachment(vo);

    ThemeDTO themeDTO = new ThemeDTO();
    themeDTO.setName("Blue");
    themeDTO.setDisplayName("{header.theme.blue}");
    themeDTO.setEnabled(true);
    themeDTO.setLink("bootstrap-cerulean.min.css");
    themeDTO.setId(1l);
    vo.getThemesList().add(themeDTO);
    vo.setDefaultTheme(themeDTO);//默认

    themeDTO = new ThemeDTO();
    themeDTO.setName("Black");
    themeDTO.setDisplayName("{header.theme.Black}");
    themeDTO.setEnabled(true);
    themeDTO.setLink("bootstrap-cyborg.min.css");
    themeDTO.setId(2l);
    vo.getThemesList().add(themeDTO);

    themeDTO = new ThemeDTO();
    themeDTO.setName("Hoary");
    themeDTO.setDisplayName("{header.theme.Hoary}");
    themeDTO.setEnabled(true);
    themeDTO.setLink("bootstrap-simplex.min.css");
    themeDTO.setId(3l);
    vo.getThemesList().add(themeDTO);

    themeDTO = new ThemeDTO();
    themeDTO.setName("DarkBlue");
    themeDTO.setDisplayName("{header.theme.DarkBlue}");
    themeDTO.setEnabled(true);
    themeDTO.setLink("bootstrap-darkly.min.css");
    themeDTO.setId(4l);
    vo.getThemesList().add(themeDTO);

    themeDTO = new ThemeDTO();
    themeDTO.setName("Frenchgrey");
    themeDTO.setDisplayName("{header.theme.Frenchgrey}");
    themeDTO.setEnabled(true);
    themeDTO.setLink("bootstrap-lumen.min.css");
    themeDTO.setId(5l);
    vo.getThemesList().add(themeDTO);

    themeDTO = new ThemeDTO();
    themeDTO.setName("Darkgrey");
    themeDTO.setDisplayName("{header.theme.Darkgrey}");
    themeDTO.setEnabled(true);
    themeDTO.setLink("bootstrap-slate.min.css");
    themeDTO.setId(5l);
    vo.getThemesList().add(themeDTO);

    themeDTO = new ThemeDTO();
    themeDTO.setName("Gray2");
    themeDTO.setDisplayName("{header.theme.Gray2}");
    themeDTO.setEnabled(true);
    themeDTO.setLink("bootstrap-spacelab.min.css");
    themeDTO.setId(5l);
    vo.getThemesList().add(themeDTO);

    themeDTO = new ThemeDTO();
    themeDTO.setName("Orange");
    themeDTO.setDisplayName("{header.theme.Orange}");
    themeDTO.setEnabled(true);
    themeDTO.setLink("bootstrap-united.min.css");
    themeDTO.setId(5l);
    vo.getThemesList().add(themeDTO);

    return result;
  }
}
