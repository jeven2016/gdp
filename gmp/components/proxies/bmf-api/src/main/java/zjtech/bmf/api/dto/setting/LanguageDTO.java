package zjtech.bmf.api.dto.setting;

import zjtech.common.result.BaseDTO;

public class LanguageDTO extends BaseDTO {
    private String name;
    private String resourceKey;
    private String label;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceKey() {
        return resourceKey;
    }

    public void setResourceKey(String resourceKey) {
        this.resourceKey = resourceKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
