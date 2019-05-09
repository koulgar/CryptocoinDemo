-- MySQL Workbench Forward Engineering
-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`cryptocoin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`cryptocoin` ;

CREATE TABLE IF NOT EXISTS `mydb`.`cryptocoin` (
  `id` VARCHAR(30) NOT NULL,
  `name` VARCHAR(30) NOT NULL,
  `symbol` VARCHAR(30) NOT NULL,
  `coin_rank` INT (11) NOT NULL,
  `price_usd` DOUBLE ,
  `price_btc` DOUBLE,
  `24h_volume_usd` DOUBLE,
  `market_cap_usd` DOUBLE,
  `available_supply` DOUBLE,
  `total_supply` DOUBLE,
  `max_supply` DOUBLE,
  `percent_change_1h` DOUBLE,
  `percent_change_24h` DOUBLE,
  `percent_change_7d` DOUBLE,
  PRIMARY KEY (`coin_rank`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`role` ;

CREATE TABLE IF NOT EXISTS `mydb`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` CHAR(80) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  -- `usercol` VARCHAR(45) NOT NULL, 
  PRIMARY KEY (`id`))
ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user_role` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user_role` (
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX `fk_user_has_role_role1` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `mydb`.`role` (`id`),
  CONSTRAINT `fk_user_has_role_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user_has_cryptocoin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user_has_cryptocoin` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user_has_cryptocoin` (
  `user_id` INT(11) NOT NULL,
  `cryptocoin_coin_rank` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`, `cryptocoin_coin_rank`),
  INDEX `fk_user_has_cryptocoin_cryptocoin1_idx` (`cryptocoin_coin_rank` ASC) VISIBLE,
  INDEX `fk_user_has_cryptocoin_user1_idx` (`user_id` ASC) VISIBLE,
  
  CONSTRAINT `fk_user_has_cryptocoin_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `mydb`.`user` (`id`),
    
  CONSTRAINT `fk_user_has_cryptocoin_cryptocoin1`
    FOREIGN KEY (`cryptocoin_coin_rank`)
    REFERENCES `mydb`.`cryptocoin` (`coin_rank`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `user` (id,username,password,first_name,last_name,email)
VALUES 
(1,'koulgar','$2a$10$DfWAvErJOthRETiqhXM60OJ2bRv.4GnBlhB1D7mYLDp.6dANvvf5m','Doğucan','Durmuşlar','koulgar@gmail.com');

INSERT INTO `role` (id,name)
VALUES 
('1','USER'),('2','ADMIN');

INSERT INTO `user_role` (user_id,role_id)
VALUES 
(1, 1),
(1, 2);
