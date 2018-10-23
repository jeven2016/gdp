package zjtech.dmf.domain;

import java.io.Serializable;

/**
 * 领域实体接口
 *
 * @param <ID> ID  对象
 */
public interface IDEntity<ID extends Serializable> {

    ID getId();

    void setId(Serializable id);


}
