/*
 * Copyright (c) 2015. ZJTech Inc. All Rights Reserved.
 */

package zjtech.bmf.business.global;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import zjtech.bmf.api.business.ICrudService;
import zjtech.bmf.api.dto.PageParamDTO;
import zjtech.bmf.api.dto.PageResultDTO;
import zjtech.bmf.api.result.BmfResult;
import zjtech.bmf.api.result.IBmfResult;
import zjtech.bmf.common.NameConstants;
import zjtech.bmf.components.convert.IBeanConvertor;
import zjtech.common.result.IBaseDTO;
import zjtech.dmf.domain.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 如果BaseCRUDBO没有泛型变量定义，则在BaseBO处定义的变量类型必须为真实的类或接口。意味BaseCRUDBO将使用的类型
 * 如果BaseCRUDBO有泛型变量定义，则在BaseBO处定义的变量类型与BaseCRUDBO保持一直。
 */
public abstract class BaseCrudService<DTO extends IBaseDTO, Entity extends BaseEntity>
        implements ICrudService<Serializable, DTO> {

    //缺省的Bean转换器
    @Autowired
    @Qualifier(NameConstants.COMMON_CONVERTOR)
    private IBeanConvertor beanConvertor;

    @Autowired
    private SystemIdChecker systemIdChecker;

    /**
     * 创建一个默认的Entity对象
     *
     * @return Entity对象
     */
    protected abstract Entity constructEntity();

    /**
     * 创建一个默认的DTO对象
     *
     * @return DTO对象
     */
    protected abstract DTO constructDto();

    /**
     * 获取转换Entity的转换器
     *
     * @return Entity的转换器
     */
    protected IBeanConvertor getEntityConvertor() {
        return beanConvertor;
    }

    /**
     * 获取转换DTO的转换器
     *
     * @return Entity的转换器
     */
    protected IBeanConvertor getDtoConvertor() {
        return beanConvertor;
    }

    /**
     * 获取当前系统的ＩＤ，如果当前是mysql则使用Long类型，如果是其他的则进行对应的转换，但结果都使用Serializable接口返回转换后的值。
     *
     * @param id ID
     * @return 新的ID
     */
    protected Serializable getCurrentID(Serializable id) {
        return systemIdChecker.getCurrentID(id);
    }

    /**
     * 获取JPA　Repository
     *
     * @return JPA　Repository
     */
    protected abstract JpaRepository<Entity, Serializable> getRepository();

    /**
     * 将DTO转换成对应的Entity
     *
     * @param dto DTO
     * @return Entity
     */
    protected Entity getEntity(DTO dto) {
        Entity entity = constructEntity();
        getEntityConvertor().convert(dto, entity);
        return entity;
    }

    /**
     * 将Entity转换成对应的DTO
     *
     * @param entity
     * @return DTO
     */
    protected DTO getDto(Entity entity) {
        DTO dto = constructDto();
        getDtoConvertor().convert(entity, dto);
        return dto;
    }

    @Override
    @Transactional
    public IBmfResult save(DTO dto) {
        Entity entity = getEntity(dto);
        getRepository().save(entity);
        IBmfResult<IBaseDTO> result = new BmfResult();
        return result;
    }

    @Override
    @Transactional
    public IBmfResult update(DTO dto) {
        Entity entity = getEntity(dto);
        entity = getRepository().save(entity);
        IBmfResult<DTO> result = new BmfResult();
        result.setAttachment(getDto(entity));
        return result;
    }

    @Override
    @Transactional
    public IBmfResult delete(Serializable id) {
        getRepository().delete(getCurrentID(id));
        return new BmfResult();
    }

    @Override
    @Transactional
    public IBmfResult delete(DTO dto) {
        return delete(dto.getId());
    }

    @Override
    @Transactional
    public IBmfResult delete(List ids) {
        getRepository().deleteInBatch(ids);
        return new BmfResult();
    }

    @Override
    public IBmfResult findByName(String name) {
        return null;
    }

    @Override
    public IBmfResult findById(Serializable id) {
        Entity entity = getRepository().findOne(getCurrentID(id));
        DTO dto = getDto(entity);
        return new BmfResult(true, dto);
    }

    @Override
    public boolean exists(Serializable serializable) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }

    /**
     * 获取分页查询结果
     *
     * @param pageParamDTO 分页参数设置
     * @return IBmfResult<PageResultDTO>
     */
    @Override
    public IBmfResult<PageResultDTO> findAll(PageParamDTO pageParamDTO) {
        //it should start from 1 even though the spring framework starts from zero
        int page = pageParamDTO.getCurrentPage() - 1 < 0 ? 0 : pageParamDTO.getCurrentPage() - 1;
        int pageSize = pageParamDTO.getPageSize();

        List<Sort.Order> orderList = new ArrayList<>();
        pageParamDTO.iterator().forEachRemaining(order -> {
            PageParamDTO.Direction direction = order.getDirection();
            Sort.Direction spDirection = PageParamDTO.Direction.ASC.equals(direction) ?
                    Sort.Direction.ASC : Sort.Direction.DESC;
            orderList.add(new Sort.Order(spDirection, order.getProperty()));
        });
        Sort sort = null;
        if (!orderList.isEmpty()) {
            sort = new Sort(orderList);
        }
        Pageable pageable = new PageRequest(page, pageSize, sort);

        Page entitiesPage = getRepository().findAll(pageable);

        PageResultDTO<DTO> pageResultDTO = new PageResultDTO<>();
        pageResultDTO.setCurrentPage(entitiesPage.getNumber() + 1);
        pageResultDTO.setPageSize(entitiesPage.getSize());
        pageResultDTO.setTotalElements(entitiesPage.getTotalElements());
        pageResultDTO.setTotalPages(entitiesPage.getTotalPages());

        List<DTO> dtoList = new ArrayList<>();
        Iterator<Entity> iter = entitiesPage.iterator();
        Entity entity;
        DTO dto;
        while (iter.hasNext()) {
            entity = iter.next();
            dto = getDto(entity);
            dtoList.add(dto);
        }
        pageResultDTO.setElements(dtoList);

        return new BmfResult(true, pageResultDTO);
    }

}
