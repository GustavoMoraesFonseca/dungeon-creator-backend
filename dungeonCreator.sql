CREATE SCHEMA `rpg_war` DEFAULT CHARACTER SET latin1;

CREATE USER 'war-user'@'%' IDENTIFIED BY 'War@User';
GRANT SELECT, DELETE, INSERT, UPDATE  ON *.* TO 'war-user'@'%';
FLUSH PRIVILEGES;
	
CREATE TABLE `rpg_war`.`tab_biome` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255),
  `dthr_criacao` DATETIME NOT NULL,
  `dthr_atualizacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name` (`name` ASC) VISIBLE);

CREATE TABLE `rpg_war`.`tab_book` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `dthr_criacao` DATETIME NOT NULL,
  `dthr_atualizacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name` (`name` ASC) VISIBLE);

CREATE TABLE `rpg_war`.`tab_monster_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200),
  `dthr_criacao` DATETIME NOT NULL,
  `dthr_atualizacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name` (`name` ASC) VISIBLE);

CREATE TABLE `rpg_war`.`tab_monster` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `biome_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  `monster_type_id` INT NOT NULL,
  `page` INT NOT NULL,
  `level` DOUBLE NOT NULL,
  `exists_south` TINYINT NOT NULL,
  `exists_north` TINYINT NOT NULL,
  `exists_center` TINYINT NOT NULL,
  `exists_west` TINYINT NOT NULL,
  `exists_east` TINYINT NOT NULL,
  `dthr_criacao` DATETIME NOT NULL,
  `dthr_atualizacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name` (`name` ASC) VISIBLE,
  INDEX `FK_MONSTER_TO_BIOME` (`biome_id` ASC) VISIBLE,
  CONSTRAINT `FK_MONSTER_TO_BIOME`
    FOREIGN KEY (`biome_id`)
    REFERENCES `rpg_war`.`tab_biome` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `FK_MONSTER_TO_BOOK` (`book_id` ASC) VISIBLE,
  CONSTRAINT `FK_MONSTER_TO_BOOK`
    FOREIGN KEY (`book_id`)
    REFERENCES `rpg_war`.`tab_book` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  INDEX `FK_MONSTER_TO_MONSTER_TYPE` (`monster_type_id` ASC) VISIBLE,
  CONSTRAINT `FK_MONSTER_TO_MONSTER_TYPE`
    FOREIGN KEY (`monster_type_id`)
    REFERENCES `rpg_war`.`tab_monster_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);