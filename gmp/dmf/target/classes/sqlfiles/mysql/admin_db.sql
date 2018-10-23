DROP DATABASE IF EXISTS smf_admin;
CREATE DATABASE smf_admin;
-- GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;
-- flush privileges;

USE smf_admin;

-- 默认全局管理员，在系统初始化时默认可以的登录用户。可以在登录后进行修改。修改后，该用户将从本地数据库删除，并视情况是否迁移至
-- 其他实体数据库（mysql/oracle/ms sql ......）
CREATE TABLE super_admin
(
  id          INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name        VARCHAR(15),
  password    VARCHAR(50),
  description VARCHAR(255),
  pwd_changed BOOLEAN         NOT NULL DEFAULT FALSE,
  last_login  DATETIME,
  deprecated  BOOLEAN         NOT NULL DEFAULT FALSE, -- 布尔类型BOOL/BOOLEAN的功能等同于微整型TINYTINT
  UNIQUE KEY (name)
)
  ENGINE =InnoDB
  CHARSET =utf8;


-- 一些小知识：
-- 日期类型 日期格式、所占存储空间、日期范围 比较。
-- 日期类型        存储空间       日期格式                 日期范围
-- ------------ ---------   --------------------- -----------------------------------------
-- datetime       8 bytes   YYYY-MM-DD HH:MM:SS   1000-01-01 00:00:00 ~ 9999-12-31 23:59:59
-- timestamp      4 bytes   YYYY-MM-DD HH:MM:SS   1970-01-01 00:00:01 ~ 2038
-- date           3 bytes   YYYY-MM-DD            1000-01-01          ~ 9999-12-31
-- year           1 bytes   YYYY                  1901                ~ 2155