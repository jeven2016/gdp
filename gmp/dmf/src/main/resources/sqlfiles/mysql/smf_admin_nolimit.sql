
-- -----------------------------------------------------
-- Schema smf_admin
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema smf_admin
-- -----------------------------------------------------
drop database if EXISTS `smf_admin`;
CREATE SCHEMA IF NOT EXISTS `smf_admin` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `smf_admin` ;

-- -----------------------------------------------------
-- Table `smf_admin`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `smf_admin`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NULL,
  `desct` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `smf_admin`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `smf_admin`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NULL,
  `dest` VARCHAR(45) NULL,
  `person_id` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `smf_admin`.`country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `smf_admin`.`country` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `smf_admin`.`country_Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `smf_admin`.`country_Person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `country_id` INT NOT NULL,
  `Person_id` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `smf_admin`.`log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `smf_admin`.`log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `time` TIMESTAMP NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
