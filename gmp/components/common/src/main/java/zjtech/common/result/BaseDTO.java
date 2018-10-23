package zjtech.common.result;

import zjtech.common.util.JsonUtil;

import java.io.Serializable;

public class BaseDTO implements IBaseDTO<Serializable> {

    private Serializable id;

    @Override
    public Serializable getId() {
        return id;
    }

    @Override
    public void setId(Serializable id) {
        this.id = id;
    }


    @Override
    public String toJsonString() {
        return JsonUtil.toJsonString(this);
    }
}
