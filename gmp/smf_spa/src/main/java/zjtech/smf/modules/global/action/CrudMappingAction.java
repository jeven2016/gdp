/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.smf.modules.global.action;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import zjtech.bmf.api.business.ICrudService;
import zjtech.common.result.IBaseDTO;
import zjtech.smf.common.result.ISmfResult;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public abstract class CrudMappingAction<DTO extends IBaseDTO, ID extends Serializable> extends BaseCrudAction<DTO, ID> {

  /* @Autowired
   @Qualifier(IdentifierConstants.BMF_SERVICE_MAP)*/
    //这里如果使用以上spring 原生注解来注入map，会出现无法找到对应资源注入的异常。 故这里使用@Resource
//  In other words, by saying @Autowired @Qualifier("myList") List<String>,
//  you're actually asking for "give me the list of all beans of type java.lang.String that have the qualifier "myList".
//  The solution is mentioned in 3.11.3. Fine-tuning annotation-based autowiring with qualifiers:
/*  @Resource(name = IdentifierConstants.BMF_SERVICE_MAP)
  private Map<String, String> serviceMap;*/

    /**
     * Get the specific service instance that associated with this resource
     *
     * @return ICrudService<ID, DTO>
     */
    protected abstract ICrudService<ID, DTO> getService();


    /**
     * 获取分页查询结果
     *
     * @return SmfResult<List<DTO>>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ISmfResult<List<DTO>>> list(@RequestParam(value = "currentPage", required = false) Integer currentPage,
                                                      @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return super.list(currentPage, pageSize, getService());
    }

    /**
     * 创建或更新一个新的Server
     * /memcached/server -PUT  = create new
     *
     * @return SmfResult
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ISmfResult<DTO> addOrUpdate(@Valid @RequestBody DTO dto,
                                       BindingResult result) {
        if (result.hasErrors()) {
            return createResult(result);
        }
        return super.addOrUpdate(dto, getService());
    }


    /**
     * 获取对象
     *
     * @param id 对象ID
     * @return SmfResult
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ISmfResult<DTO>> get(@PathVariable("id") ID id) {
        return super.get(id, getService());
    }

    /**
     * 获取一个存在的对象
     *
     * @param id 对象ID
     * @return SmfResult
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ISmfResult delete(@PathVariable("id") ID id) {
        return super.delete(id, getService());
    }

}
