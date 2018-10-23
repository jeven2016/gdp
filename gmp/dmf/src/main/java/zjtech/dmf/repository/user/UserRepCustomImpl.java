package zjtech.dmf.repository.user;

import zjtech.dmf.domain.adminuser.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 系统管理员Repository类
 * 实现方式：
 * 1. 接口使用@NoRepositoryBean,不生成类，然后实现自定义接口的类标注@Repository
 * 1. 接口不使用@NoRepositoryBean,会生成代理类，然后实现自定义接口的类不标注@Repository
 */
public class UserRepCustomImpl implements UserRepCustom<UserEntity, Long> {
  @PersistenceContext
  private EntityManager entityManager;

}
