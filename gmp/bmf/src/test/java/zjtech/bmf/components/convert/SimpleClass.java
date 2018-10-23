package zjtech.bmf.components.convert;


import net.sf.cglib.beans.BeanCopier;
import org.junit.Test;
import zjtech.bmf.api.validation.ValidateDescription;
import zjtech.bmf.api.validation.ValidateName;
import zjtech.bmf.api.validation.ValidateNetAddress;
import zjtech.bmf.api.validation.ValidatePort;
import zjtech.common.result.IBaseDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SimpleClass {

    @Test
    public void selfCopy() {
        BeanCopier.create(MsDTO.class, MsEntity.class, false);
    }
}

interface IBsDTO<ID extends Serializable> {
    ID getId();

    void setId(ID id);
}

abstract class BsDTO<ID extends Serializable> implements IBaseDTO<ID>, Serializable {

    private ID id;

    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(ID id) {
        this.id = id;
    }
}

/**
 * Memcached Server VO
 */
class MsDTO extends BsDTO<Long> {
    @ValidateName
    private String name;

    @NotNull
    @ValidateNetAddress
    private String address;

    @ValidatePort
    private int port = 11211;

    private boolean enable = true;

    @ValidateDescription
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

//========================================================================================================
interface IdEntity<ID extends Serializable> {

    ID getId();

    void setId(ID id);


}


@MappedSuperclass
abstract class BsEntity<ID extends Serializable> implements IdEntity<ID> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}

@Entity
@Table(name = "memcached_server", schema = "", catalog = "smf_admin")
class MsEntity extends BsEntity<Long> {
    private String name;
    private String address;
    private int port;
    private boolean enable = true;
    private String description;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "port")
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Basic
    @Column(name = "enable")
    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
