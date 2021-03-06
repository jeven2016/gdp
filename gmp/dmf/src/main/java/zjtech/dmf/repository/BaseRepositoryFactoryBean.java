package zjtech.dmf.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * 自定义的 RepositoryFactoryBean
 */
public class BaseRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
        extends JpaRepositoryFactoryBean<T, S, ID> {
  @Override
  protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
    return new MyJpaRepositoryFactory(entityManager);
  }
}
