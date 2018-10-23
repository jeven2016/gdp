package zjtech.dmf.repository;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;
import java.io.Serializable;

import static org.springframework.data.querydsl.QueryDslUtils.QUERY_DSL_PRESENT;

/**
 * 自定义的JpaRepositoryFactory
 */
public class MyJpaRepositoryFactory extends JpaRepositoryFactory {

  public MyJpaRepositoryFactory(EntityManager entityManager) {
    super(entityManager);
  }

  @Override
  protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(RepositoryMetadata metadata,
                                                                                       EntityManager entityManager) {

    Class<?> repositoryInterface = metadata.getRepositoryInterface();
    JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());

    SimpleJpaRepository<?, ?> repo = isQueryDslExecutor(repositoryInterface) ? new QueryDslJpaRepository(
            entityInformation, entityManager) : new MyJpaRepository(entityInformation, entityManager);

    return repo;
  }

  @Override
  protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

    if (isQueryDslExecutor(metadata.getRepositoryInterface())) {
      return QueryDslJpaRepository.class;
    } else {
      //使用自定义的类取代默认实现
      return MyJpaRepository.class;
    }
  }

  private boolean isQueryDslExecutor(Class<?> repositoryInterface) {
    return QUERY_DSL_PRESENT && QueryDslPredicateExecutor.class.isAssignableFrom(repositoryInterface);
  }
}
