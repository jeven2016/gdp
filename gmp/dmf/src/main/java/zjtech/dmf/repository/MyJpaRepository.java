package zjtech.dmf.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 自定义的JpaRepository, 对SimpleJpaRepository中的方法进行修改覆盖
 */
public class MyJpaRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {
  public MyJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
  }

  public MyJpaRepository(Class<T> domainClass, EntityManager em) {
    super(domainClass, em);
  }

  @Override
  @Transactional
  public void delete(ID id) {
    T entity = findOne(id);

    //当删除的是一个不存在的对象时，直接提交事物并返回成功
    if (entity != null) {
      delete(entity);
    }
  }
}
