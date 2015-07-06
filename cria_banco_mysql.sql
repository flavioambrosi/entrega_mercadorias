-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`VERTICE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`VERTICE` (
  `ID` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `DESCRICAO` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '',
  UNIQUE INDEX `DESCRICAO_UNIQUE` (`DESCRICAO` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ARESTA`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ARESTA` (
  `ID` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '',
  `DISTANCIA` INT NOT NULL COMMENT '',
  `FK_VERTICE_ORIGEM` BIGINT(20) NOT NULL COMMENT '',
  `FK_VERTICE_DESTINO` BIGINT(20) NOT NULL COMMENT '',
  PRIMARY KEY (`ID`)  COMMENT '',
  INDEX `fk_ARESTA_VERTICE_idx` (`FK_VERTICE_ORIGEM` ASC)  COMMENT '',
  INDEX `fk_ARESTA_VERTICE1_idx` (`FK_VERTICE_DESTINO` ASC)  COMMENT '',
  CONSTRAINT `fk_ARESTA_VERTICE`
    FOREIGN KEY (`FK_VERTICE_ORIGEM`)
    REFERENCES `mydb`.`VERTICE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ARESTA_VERTICE1`
    FOREIGN KEY (`FK_VERTICE_DESTINO`)
    REFERENCES `mydb`.`VERTICE` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
