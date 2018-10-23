package zjtech.dmf.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import zjtech.dmf.domain.adminuser.UserEntity;

import java.util.Date;

/**
 * 系统管理员Repository类
 */
public interface UserRep extends JpaRepository<UserEntity, Long>,
        UserRepCustom<UserEntity, Integer> {

  @Query("select e from UserEntity e where e.name= ?1")
  UserEntity findByName(String name);

  /**
   * 更新最后登录时间
   *
   * @param id   id
   * @param time 时间
   */
  @Query("update UserEntity e set e.lastLogin=:time where e.id=:id")
  @Modifying
  void updateLastLoginTime(@Param("id") Long id, @Param("time") Date time);
}
