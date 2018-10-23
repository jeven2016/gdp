package zjtech.smf.modules.global.service;

import zjtech.common.result.IBaseDTO;

import java.io.Serializable;

public interface IBaseSrv<DTO extends IBaseDTO, ID extends Serializable> {
	String getRemoteServiceName();

	void setRemoteServiceName(String serviceName);
}
