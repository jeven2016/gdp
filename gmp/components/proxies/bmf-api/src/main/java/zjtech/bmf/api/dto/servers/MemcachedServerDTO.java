package zjtech.bmf.api.dto.servers;

import zjtech.bmf.api.validation.*;
import zjtech.common.result.BaseDTO;
import zjtech.common.util.JsonUtil;

import javax.validation.constraints.NotNull;

/**
 * Memcached Server VO
 */
public class MemcachedServerDTO extends BaseDTO {
    @ValidateName
    private String name;

    @NotBlankValue
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
