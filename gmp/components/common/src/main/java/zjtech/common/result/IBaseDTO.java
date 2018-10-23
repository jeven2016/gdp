package zjtech.common.result;

import java.io.Serializable;

public interface IBaseDTO<ID extends Serializable> extends Serializable {
    ID getId();

    void setId(ID id);

    default public String toJsonString() {
        return "{id: \"" + getId().toString() + "\"}";
    }
}
