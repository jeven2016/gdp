-- 参考知识点
-- 1. SET sql_mode='NO_AUTO_VALUE_ON_ZERO'; 可以设置自动增长的步长从0开始，默认从1开始
-- 2. truncate table : 直接清空不考虑数据备份即历史记录

-- 3. 关于时间的一些小知识：
-- 日期类型 日期格式、所占存储空间、日期范围 比较。
-- 日期类型        存储空间       日期格式                 日期范围
-- ------------ ---------   --------------------- -----------------------------------------
-- datetime       8 bytes   YYYY-MM-DD HH:MM:SS   1000-01-01 00:00:00 ~ 9999-12-31 23:59:59
-- timestamp      4 bytes   YYYY-MM-DD HH:MM:SS   1970-01-01 00:00:01 ~ 2038
-- date           3 bytes   YYYY-MM-DD            1000-01-01          ~ 9999-12-31
-- year           1 bytes   YYYY                  1901                ~ 2155

DROP SCHEMA IF EXISTS smf_admin;
CREATE SCHEMA IF NOT EXISTS smf_admin
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;
-- GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;
-- flush privileges;

USE smf_admin;

-- 默认全局管理员，在系统初始化时默认可以的登录用户。可以在登录后进行修改。修改后，该用户将从本地数据库删除，并视情况是否迁移至
-- 其他实体数据库（mysql/oracle/ms sql ......）
CREATE TABLE user
(
  id          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name        VARCHAR(20)        NOT NULL,
  password    VARCHAR(50),
  description VARCHAR(255),
  pwd_changed BOOLEAN            NOT NULL DEFAULT FALSE,
  last_login  DATETIME,
  locked      BOOLEAN            NOT NULL DEFAULT FALSE -- 布尔类型BOOL/BOOLEAN的功能等同于微整型TINYTINT
)
  ENGINE = InnoDB
  -- AUTO_INCREMENT=100    -- 起始ID，低于起始值的记录为保留数据，不允许编辑删除
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------
-- Table smf_admin.modules
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS smf_admin.module (
  id               BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name             VARCHAR(20)        NULL,
  link             VARCHAR(100)       NULL,
  enable           TINYINT(1)         NULL,
  description      VARCHAR(250)       NULL,
  create_time      TIMESTAMP          NULL,
  last_update_time TIMESTAMP          NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


-- -----------------------------------------------------
-- Table smf_admin.menu_group
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS smf_admin.menu_group (
  id               BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  module_id        INT                NULL,
  parent_id        INT                NULL,
  name             VARCHAR(20)        NULL,
  link             VARCHAR(100)       NULL,
  enable           TINYINT(1)         NULL,
  description      VARCHAR(250)       NULL,
  create_time      TIMESTAMP          NULL,
  last_update_time TIMESTAMP          NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


-- -----------------------------------------------------
-- Table smf_admin.menu_item
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS smf_admin.menu_item (
  id               BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  group_id         INT                NULL,
  name             VARCHAR(20)        NULL,
  link             VARCHAR(100)       NULL,
  enable           TINYINT(1)         NULL,
  description      VARCHAR(250)       NULL,
  create_time      TIMESTAMP          NULL,
  last_update_time TIMESTAMP          NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------
-- Table smf_admin.cluster_memcached
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS smf_admin.memcached_cluster (
  id          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name        VARCHAR(20)        NOT NULL,
  enable      BOOLEAN            NOT NULL DEFAULT TRUE,
  description VARCHAR(250)       NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------
-- Table smf_admin.cluster_server
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS smf_admin.memcached_server (
  id          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name        VARCHAR(20)        NOT NULL,
  address     VARCHAR(50)        NOT NULL,
  port        INT                NOT NULL,
  enable      BOOLEAN            NOT NULL DEFAULT TRUE,
  description VARCHAR(250)       NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- -----------------------------------------------------
-- Table smf_admin.memcached_cluster_server
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS smf_admin.memcached_cluster_server (
  id        BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  clusterid BIGINT             NOT NULL,
  serverid  BIGINT             NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
