package zjtech.smf.modules.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zjtech.bmf.api.dto.modules.ModuleDTO;
import zjtech.smf.common.result.SmfResult;
import zjtech.smf.service.modules.IModulesConvertSrv;

import java.util.ArrayList;
import java.util.List;

/**
 * 模块Action.
 */
@Controller
public class ModuleAction {
/*
  @Autowired
  private IModulesConvertSrv modulesConvertService;*/

  @RequestMapping(value = "/module/all", method = RequestMethod.GET)
  @ResponseBody
  public SmfResult<List<ModuleDTO>> queryModules() {
    SmfResult<List<ModuleDTO>> result = new SmfResult();
    List<ModuleDTO> modules = new ArrayList<>();
   /* modules.add(modulesConvertService.convertToVo());
    result.setAttachedObj(modules);*/
    return result;
  }

}
