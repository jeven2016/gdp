package zjtech.smf.service.modules;

import com.thoughtworks.xstream.XStream;
import org.springframework.stereotype.Service;
import zjtech.bmf.api.dto.modules.MenuGroupDTO;
import zjtech.bmf.api.dto.modules.MenuItemDTO;
import zjtech.bmf.api.dto.modules.ModuleDTO;
import zjtech.common.util.DateTimeUtlil;
import zjtech.smf.common.constants.LinkTypeEnum;

import java.util.Date;
import java.util.Set;

/**
 * 系统模块转换类
 */
@Service
public class ModulesConvertSrv  {

  public ModuleDTO convertToVo() {
    Date currentDate = DateTimeUtlil.getCurrentDate();
    XStream xStream = new XStream();
    xStream.alias("Module", ModuleDTO.class);
    xStream.alias("MenuGroup", MenuGroupDTO.class);
    xStream.alias("MenuItem", MenuItemDTO.class);

    //我的收藏
    ModuleDTO moduleDTO = new ModuleDTO();
    moduleDTO.setName("#{modules.my.favorite}");
    moduleDTO.setLink(LinkTypeEnum.external_link.getValue()
            + ":/favorite");
    moduleDTO.setCreateTime(null);
    moduleDTO.setDisabled(false);

    Set<MenuGroupDTO> menuGroupDTOs = moduleDTO.getMenugroups();

    MenuGroupDTO menuGroupDTO = new MenuGroupDTO();
    menuGroupDTO.setName("#{modules.tt}");
    menuGroupDTO.setLink("ilink#/tt/kk?ww=3&rr=kw");
    menuGroupDTO.setDisabled(false);
    menuGroupDTO.setCreateTime(currentDate);
    menuGroupDTOs.add(menuGroupDTO);

    MenuItemDTO itemVO = new MenuItemDTO();
    itemVO.setName("#{module.item}");
    itemVO.setLink("E_Link:http://dskfjls.do?xx=34&dsk=_343_");
    itemVO.setCreateTime(currentDate);
    menuGroupDTO.getMenuItems().add(itemVO);

    Set<MenuGroupDTO> childGroups = menuGroupDTO.getChildMenuGroups();
    menuGroupDTO = new MenuGroupDTO();
    menuGroupDTO.setName("#{modules.tt2}");
    menuGroupDTO.setLink("ilink#/tt2/kk?ww=3&rr=kw");
    menuGroupDTO.setDisabled(false);
    menuGroupDTO.setCreateTime(currentDate);
    menuGroupDTOs.add(menuGroupDTO);
    childGroups.add(menuGroupDTO);

//    System.out.println(xStream.toXML(moduleDTO));


    return moduleDTO;
  }

}
