
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
  `person_id` INT NOT NULL,
  PRIMARY KEY (`id`, `person_id`),
  INDEX `fk_Address_Person1_idx` (`person_id` ASC),
  CONSTRAINT `fk_Address_Person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `smf_admin`.`person` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
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
  `country_id` INT NOT NULL,
  `Person_id` INT NOT NULL,
  PRIMARY KEY (`country_id`, `Person_id`),
  INDEX `fk_country_has_Person_Person1_idx` (`Person_id` ASC),
  INDEX `fk_country_has_Person_country1_idx` (`country_id` ASC),
  CONSTRAINT `fk_country_has_Person_country1`
    FOREIGN KEY (`country_id`)
    REFERENCES `smf_admin`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_country_has_Person_Person1`
    FOREIGN KEY (`Person_id`)
    REFERENCES `smf_admin`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `smf_admin`.`log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `smf_admin`.`log` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `time` TIMESTAMP NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;