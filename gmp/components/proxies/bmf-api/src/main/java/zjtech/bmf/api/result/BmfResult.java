package zjtech.bmf.api.result;

import zjtech.common.result.ErrorCode;
import zjtech.common.result.IBaseDTO;

import java.util.Map;

/**
 * BMF Result
 */
public class BmfResult<DTO extends IBaseDTO> implements IBmfResult<DTO> {
    private Integer errorCode = ErrorCode.SUCCESS;

    private DTO attachmentDTO;

    private String description;

    //用来存放多个返回对象的map，自主实现
    private Map attributeMap;

    public BmfResult() {

    }

    public BmfResult(boolean success) {
        errorCode = success ? ErrorCode.SUCCESS : ErrorCode.FAILED;
    }

    public BmfResult(int errorCode) {
        this.errorCode = errorCode;
    }

    public BmfResult(Integer errorCode, DTO attachmentDTO, String description) {
        this.errorCode = errorCode;
        this.attachmentDTO = attachmentDTO;
        this.description = description;
    }

    public BmfResult(boolean success, DTO attachmentDTO, String description) {
        this.errorCode = success ? ErrorCode.SUCCESS : ErrorCode.FAILED;
        this.attachmentDTO = attachmentDTO;
        this.description = description;
    }

    public BmfResult(Integer errorCode, DTO attachmentDTO) {
        this.errorCode = errorCode;
        this.attachmentDTO = attachmentDTO;
    }

    public BmfResult(boolean success, DTO attachmentDTO) {
        this.errorCode = success ? ErrorCode.SUCCESS : ErrorCode.FAILED;
        this.attachmentDTO = attachmentDTO;
    }

    public boolean isOk() {
        return errorCode == ErrorCode.SUCCESS;
    }

    public boolean isOk(int errorCodeForCompare) {
        return errorCode == errorCodeForCompare;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public DTO getAttachment() {
        return attachmentDTO;
    }

    public void setAttachment(DTO attachment) {
        this.attachmentDTO = attachment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public <K, V> Map<K, V> getAttributesMap() {
        return attributeMap;
    }

    @Override
    public <K, V> void setAttributesMap(Map<K, V> map) {
        this.attributeMap = map;
    }

}
