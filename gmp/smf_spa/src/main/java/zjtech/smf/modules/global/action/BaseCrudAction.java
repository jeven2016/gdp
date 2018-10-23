package zjtech.smf.modules.global.action;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import zjtech.bmf.api.business.ICrudService;
import zjtech.bmf.api.dto.PageParamDTO;
import zjtech.bmf.api.dto.PageResultDTO;
import zjtech.bmf.api.result.IBmfResult;
import zjtech.common.result.IBaseDTO;
import zjtech.smf.common.constants.SmfValueConstants;
import zjtech.smf.common.log.LoggerProxy;
import zjtech.smf.common.result.ISmfResult;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseCrudAction<DTO extends IBaseDTO, ID extends Serializable> extends BaseAction {

    //logger
    private static final Logger logger = new LoggerProxy(BaseCrudAction.class);

    protected Logger getLogger() {
        return logger;
    }

    /**
     * 获取分页查询结果
     *
     * @return SmfResult<List<DTO>>
     */
    public ResponseEntity<ISmfResult<List<DTO>>> list(@RequestParam(value = "currentPage", required = false) Integer currentPage,
                                                      @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                      ICrudService<ID, DTO> service) {

        ISmfResult<List<DTO>> smfResult = null;
        try {
            //设置分页参数
            currentPage = ensureValidValue(currentPage, 1, Integer.MAX_VALUE);
            pageSize = ensureValidValue(pageSize, SmfValueConstants.DEFAULT_NUMBER_OF_ELEMENTS, SmfValueConstants.MAX_NUMBER_OF_ELEMENTS);
            PageParamDTO pageParamDTO = new PageParamDTO(currentPage, pageSize);
            pageParamDTO.setCurrentPage(currentPage);
            pageParamDTO.setPageSize(pageSize);

            IBmfResult bmfResult = service.findAll(pageParamDTO);
            PageResultDTO<DTO> resultVO = (PageResultDTO<DTO>) bmfResult.getAttachment();
            List<DTO> list = resultVO.getElements();

            if (list == null) {
                list = new ArrayList<>();
            }

            smfResult = retrieveSmfResult(bmfResult, smfResult, list);
        } catch (Exception e) {
            smfResult = retrieveSmfResult(e);
            smfResult.setAttachedObj(new ArrayList<>());
            String msg = String.format("Failed to get a list of objects. (current=%s, pageSize=%s, serviceClass=%s)",
                    currentPage, pageSize, service.getClass().toGenericString());
            getLogger().warn(msg, e);
        }
        return new ResponseEntity<>(smfResult, HttpStatus.OK);
    }

    /**
     * 创建或更新一个新的Server
     * /memcached/server -PUT  = create new
     *
     * @return SmfResult
     */
    public ISmfResult<DTO> addOrUpdate(DTO dto, ICrudService<ID, DTO> service) {
        ISmfResult smfResult;
        try {
            IBmfResult<DTO> bmfResult;
            if (dto.getId() == null) {
                //to add
                dto.setId(null);
                bmfResult = service.save(dto);
            } else {
                //to update
                bmfResult = service.update(dto);
            }

            smfResult = retrieveSmfResult(bmfResult);
        } catch (Exception e) {
            smfResult = retrieveSmfResult(e);
            String msg = String.format("Failed to create or update an object in service(%s), the json data is: \n %s",
                    service.getClass().toString(), dto.toJsonString());
            getLogger().warn(msg, e);
        }
        return smfResult;
    }


    /**
     * 获取对象
     *
     * @param id 对象ID
     * @return SmfResult
     */
    public ResponseEntity<ISmfResult<DTO>> get(ID id, ICrudService<ID, DTO> service) {
        ISmfResult<DTO> smfResult = null;
        try {
            if (id == null) {
                smfResult.setOk(false).addGlobalError("illegalId", "error.illegal.parameter");
                return new ResponseEntity(smfResult, HttpStatus.OK);
            }
            IBmfResult<DTO> bmfResult = service.findById(id);
            smfResult = retrieveSmfResult(bmfResult);
        } catch (Exception e) {
            smfResult = retrieveSmfResult(e);
            String msg = String.format("Failed to retrieve an object by ID(%s) in service(%s)",
                    id, service.getClass().toString());
            getLogger().warn(msg, e);
        }
        return new ResponseEntity(smfResult, HttpStatus.OK);
    }

    /**
     * 获取一个存在的对象
     *
     * @param id 对象ID
     * @return SmfResult
     */
    public ISmfResult delete(ID id, ICrudService<ID, DTO> service) {
        ISmfResult smfResult;
        try {
      /*if (!NumberUtils.isDigits(id)) {
        smfResult = createResult();
        smfResult.setOk(false).addGlobalError("illegalId", "error.illegal.parameter");
        //记录日志
        return smfResult;
      }*/

            IBmfResult bmfResult = service.delete(id);
            smfResult = retrieveSmfResult(bmfResult);
        } catch (Exception e) {
            String msg = String.format("Failed to delete it by ID(%s) in service(%s)",
                    id, service.getClass().toString());
            getLogger().warn(msg, e);
            smfResult = retrieveSmfResult(e);
        }
        return smfResult;
    }

    protected Integer ensureValidValue(Integer currentPage, Integer defaultValue, Integer maxValue) {
        if (currentPage == null || currentPage.intValue() < defaultValue.intValue()) {
            currentPage = defaultValue;
        }
        if (currentPage.intValue() > maxValue.intValue()) {
            currentPage = maxValue;
        }
        return currentPage;
    }

}
