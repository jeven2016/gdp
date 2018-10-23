package zjtech.bmf.api.business;

import zjtech.bmf.api.exception.BmfException;
import zjtech.bmf.api.result.IBmfResult;
import zjtech.bmf.api.dto.PageParamDTO;
import zjtech.bmf.api.dto.PageResultDTO;
import zjtech.common.result.IBaseDTO;

import java.io.Serializable;
import java.util.List;

public interface ICrudService<ID extends Serializable, VO extends IBaseDTO> extends IBaseService {
  /**
   * 保存单个实体
   *
   * @param vo 实体
   * @return 返回保存的实体
   */
  default IBmfResult<VO> save(VO vo) {
    throw new BmfException("This method should be implemented in subclass.");
  }

  /**
   * 更新单个实体
   *
   * @param vo 实体
   * @return 返回更新的实体
   */
  default IBmfResult<VO> update(VO vo) {
    throw new BmfException("This method should be implemented in subclass.");
  }

  /**
   * 根据主键删除相应实体
   *
   * @param id 主键
   */
  default IBmfResult<VO> delete(ID id) {
    throw new BmfException("This method should be implemented in subclass.");
  }

  /**
   * 删除实体
   *
   * @param vo 实体
   */
  default IBmfResult<VO> delete(VO vo) {
    throw new BmfException("This method should be implemented in subclass.");
  }

  /**
   * 根据主键删除相应实体
   *
   * @param ids 实体
   */
  default IBmfResult<VO> delete(List<ID> ids) {
    throw new BmfException("This method should be implemented in subclass.");
  }

  /**
   * 按照名称查询
   *
   * @param name 你改成
   * @return 返回id对应的实体
   */
  default IBmfResult<VO> findByName(String name) {
    throw new BmfException("This method should be implemented in subclass.");
  }

  /**
   * 按照主键查询
   *
   * @param id 主键
   * @return 返回id对应的实体
   */
  default IBmfResult<VO> findById(ID id) {
    throw new BmfException("This method should be implemented in subclass.");
  }

  /**
   * 实体是否存在
   *
   * @param id 主键
   * @return 存在 返回true，否则false
   */
  default boolean exists(ID id) {
    throw new BmfException("This method should be implemented in subclass.");
  }

  /**
   * 统计实体总数
   *
   * @return 实体总数
   */
  default long count() {
    throw new BmfException("This method should be implemented in subclass.");
  }

  /**
   * 分页及排序查询实体
   *
   * @param pageParam 分页及排序参数
   * @return
   */
  default IBmfResult<PageResultDTO> findAll(PageParamDTO pageParam) {
    throw new BmfException("This method should be implemented in subclass.");
  }

  /**
   * 按条件分页并排序查询实体
   *
   * @param searchable 条件
   * @return
  /*   */
  /*Page<M> findAll(Searchable searchable) {
      return baseRepository.findAll(searchable);
  }*/

  /**
   * 按条件不分页不排序查询实体
   *
   * @param searchable 条件
   * @return
   */
/*   List<M> findAllWithNoPageNoSort(Searchable searchable) {
    searchable.removePageable();
    searchable.removeSort();
    return Lists.newArrayList(baseRepository.findAll(searchable).getContent());
  }*/

  /**
   * 按条件排序查询实体(不分页)
   *
   * @param searchable 条件
   * @return
   */
/*
   List<M> findAllWithSort(Searchable searchable) {
    searchable.removePageable();
    return Lists.newArrayList(baseRepository.findAll(searchable).getContent());
  }
*/


  /**
   * 按条件分页并排序统计实体数量
   *
   * @param searchable 条件
   * @return
   */
/*   Long count(Searchable searchable) {
    return baseRepository.count(searchable);
  }*/
}
