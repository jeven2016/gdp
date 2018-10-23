package zjtech.dmf.init;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import zjtech.dmf.repository.BaseRepositoryFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

class Parameter {
  public static final String domainDir = "zjtech.dmf.domain";
  public static final String repDir = "zjtech.dmf.repository";
  public static final String dmfDir = "zjtech.dmf";

  public static String[] dirsToScan = {Parameter.domainDir, Parameter.repDir};
}

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(Parameter.domainDir)
//使用自定义的JPA工厂类实现
@EnableJpaRepositories(value = Parameter.dmfDir, repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class DmfConfig implements ApplicationContextAware {
  ApplicationContext ctx;

  @Bean(name = "dataSource")
  @Lazy
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUsername("root");
    dataSource.setPassword("1");
    dataSource.setUrl("jdbc:mysql://db:3306/smf_admin?useUnicode=true&characterEncoding=UTF-8");
//    dataSource.setInitialSize(5);
//    dataSource.setMinIdle(1);
//    dataSource.setMaxActive(10);
    dataSource.setMaxWait(8000);
//    dataSource.setDefaultAutoCommit(true);
    dataSource.setMaxActive(100);
//    dataSource.setMaxIdle(30);    //this setting is deprecated
    dataSource.setMinIdle(20);
    dataSource.setInitialSize(0);
    dataSource.setPoolPreparedStatements(true);
    dataSource.setTestOnBorrow(false);
    dataSource.setTestOnReturn(false);
    dataSource.setMinEvictableIdleTimeMillis(30000); //minEvictableIdleTimeMillis should be greater than 30000
//    dataSource.setMaxWait(5000);
    dataSource.setTestWhileIdle(true);
    dataSource.setTimeBetweenEvictionRunsMillis(30000); //每30秒运行一次空闲连接回收器
    dataSource.setMaxWaitThreadCount(1000);
    // 启用监控统计功能
    try {
      dataSource.setFilters("stat");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    // for mysql
    dataSource.setPoolPreparedStatements(false);

    return dataSource;
  }

  @Bean
  @Lazy
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
//    sessionFactory.setPackagesToScan(Parameter.dmfDir);
    sessionFactory.setHibernateProperties(getHibernateProperties());
    return sessionFactory;
  }

  private Properties getHibernateProperties() {
  /*
    在persistence.xml中若配置了<property name="hibernate.hbm2ddl.auto" value="create" />，那么每次访问数据库都会创建新的表。导致始终只插入最后一条数据。可能值有
    none                   无
    validate               加载hibernate时，验证创建数据库表结构
    create                  每次加载hibernate，重新创建数据库表结构，这就是导致数据库表数据丢失的原因。
    create-drop        加载hibernate时创建，退出是删除表结构
    update                 加载hibernate自动更新数据库结构*/

    Properties props = new Properties();
    props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
    props.getProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
    props.setProperty("hibernate.connection.username", "root");
    props.setProperty("hibernate.connection.password", "1");
//    props.setProperty("hibernate.archive.autodetection", "class"); //会额外触发查询语句
    props.setProperty("hibernate.show_sql", "true");
    props.setProperty("hibernate.use_sql_comments", "true"); //是否打印出注释
    props.setProperty("hibernate.format_sql", "true");
//    props.setProperty("hibernate.generateDdl", "true");      //可以自动生成表
//    props.setProperty("hbm2ddl.auto", "none"); //可以自动生成表

    props.setProperty("Hibernate.default_schema", "smf_admin");
    props.setProperty("hibernate.max_fetch_depth", "3");
    props.setProperty("connection.autocommit", "true");
    props.setProperty("hibernate.globally_quoted_identifiers", "true");
    props.setProperty("hibernate.connection.release_mode", "auto");
    return props;
  }

  @Bean
  @Lazy
  public EntityManagerFactory entityManagerFactory() {
    DataSource ds = dataSource();
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setDatabase(Database.MYSQL);
    vendorAdapter.setGenerateDdl(false); //是否更新DDL
    vendorAdapter.setShowSql(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setJpaProperties(getHibernateProperties());
//    factory.setPackagesToScan(getClass().getPackage().getName());
    factory.setPackagesToScan(Parameter.dmfDir);// this is required indeed
    factory.setDataSource(ds);
    factory.afterPropertiesSet();

//    PersistenceUnitInfo persistenceUnitInfo = factory.getPersistenceUnitInfo();
    return factory.getObject();
  }

  @Bean
  @Lazy
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory());
    txManager.setDataSource(dataSource());
    return txManager;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ctx = applicationContext;
  }
}