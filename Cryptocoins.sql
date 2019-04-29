CREATE DATABASE IF NOT EXISTS `mydb`;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Cryptocoin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Cryptocoin`;
CREATE TABLE IF NOT EXISTS `Cryptocoin`  (
  `testid`INT AUTO_INCREMENT NOT NULL,
  `a` VARCHAR(30) NOT NULL,
  `b` VARCHAR(30) NOT NULL,
  `c` VARCHAR(30) NOT NULL,
  `d` INT NOT NULL,
/*  `price_usd` DOUBLE,
  `price_btc` DOUBLE,
  `24h_volume_usd` DOUBLE,
  `market_cap_usd` DOUBLE,
  `available_supply` DOUBLE,
  `total_supply` DOUBLE,
  `max_supply` DOUBLE,
  `percent_change_1h` DOUBLE,
  `percent_change_24h` DOUBLE,
  `percent_change_7d` DOUBLE,
  */PRIMARY KEY (`testid`))
ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET utf8;

