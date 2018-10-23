package zjtech.bmf.api.result;

import zjtech.common.result.IResult;
import zjtech.common.result.IBaseDTO;

import java.util.Map;

public interface IBmfResult<DTO extends IBaseDTO> extends IResult<DTO> {

    boolean isOk();

    boolean isOk(int errorCodeForCompare);

    Integer getErrorCode();

    void setErrorCode(Integer errorCode);

    DTO getAttachment();

    void setAttachment(DTO attachment);

    String getDescription();

    void setDescription(String description);

    <K, V> Map<K, V> getAttributesMap();

    <K, V> void setAttributesMap(Map<K, V> map);
}
