-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema StudyingPlatform
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `StudyingPlatform` ;

-- -----------------------------------------------------
-- Schema StudyingPlatform
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `StudyingPlatform` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema platforma de studiu
-- -----------------------------------------------------
USE `StudyingPlatform` ;

-- -----------------------------------------------------
-- Table `StudyingPlatform`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudyingPlatform`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(32) NOT NULL DEFAULT '\"default username\"',
  `password` VARCHAR(32) NOT NULL DEFAULT '\"default password\"',
  `role` ENUM('STUDENT', 'PROFESSOR') NULL DEFAULT 'STUDENT',
  `CNP` VARCHAR(20) NULL,
  `first_name` VARCHAR(45) NULL DEFAULT '\"default first name\"',
  `last_name` VARCHAR(45) NULL DEFAULT '\"default last name\"',
  `phone` VARCHAR(15) NULL,
  `email` VARCHAR(45) NULL,
  `iban` VARCHAR(45) NULL,
  `contract_number` VARCHAR(45) NULL,
  `is_admin` TINYINT NULL,
  `is_super_admin` TINYINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `StudyingPlatform`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudyingPlatform`.`student` (
  `id` INT NOT NULL,
  `year` INT NULL,
  `min_studying_hours` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_student_to_user`
    FOREIGN KEY (`id`)
    REFERENCES `StudyingPlatform`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudyingPlatform`.`professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudyingPlatform`.`professor` (
  `id` INT NOT NULL,
  `min_teaching_hours` INT NULL,
  `max_teaching_hours` INT NULL,
  `department` VARCHAR(60) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_professor_to_user`
    FOREIGN KEY (`id`)
    REFERENCES `StudyingPlatform`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudyingPlatform`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudyingPlatform`.`address` (
  `id` INT NOT NULL,
  `country` VARCHAR(45) NULL,
  `region` VARCHAR(45) NULL,
  `town` VARCHAR(45) NULL,
  `street_address` VARCHAR(45) CHARACTER SET 'armscii8' NULL,
  `postal_code` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_address_user1`
    FOREIGN KEY (`id`)
    REFERENCES `StudyingPlatform`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudyingPlatform`.`subject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudyingPlatform`.`subject` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(200) NULL,
  `has_lecture` TINYINT NULL,
  `has_seminar` TINYINT NULL,
  `has_lab` TINYINT NULL,
  `date_start` DATE NULL,
  `date_end` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudyingPlatform`.`studying`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudyingPlatform`.`studying` (
  `sudent_id` INT NOT NULL,
  `subject_id` INT NOT NULL,
  `grade_lecture` INT NULL DEFAULT 0,
  `grade_seminar` INT NULL DEFAULT 0,
  `grade_lab` INT NULL DEFAULT 0,
  `professor_id` INT NOT NULL,
  PRIMARY KEY (`sudent_id`, `subject_id`, `professor_id`),
  INDEX `fk_sudent_has_subject_subject1_idx` (`subject_id` ASC) VISIBLE,
  INDEX `fk_sudent_has_subject_sudent1_idx` (`sudent_id` ASC) VISIBLE,
  INDEX `fk_studying_professor1_idx` (`professor_id` ASC) VISIBLE,
  CONSTRAINT `fk_sudent_has_subject_sudent1`
    FOREIGN KEY (`sudent_id`)
    REFERENCES `StudyingPlatform`.`student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sudent_has_subject_subject1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `StudyingPlatform`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_studying_professor1`
    FOREIGN KEY (`professor_id`)
    REFERENCES `StudyingPlatform`.`professor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `StudyingPlatform`.`teaching`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `StudyingPlatform`.`teaching` (
  `subject_id` INT NOT NULL,
  `professor_id` INT NOT NULL,
  `students_capacity` INT NULL,
  `lecture_weight` FLOAT NULL,
  `seminar_weight` FLOAT NULL,
  `lab_weight` FLOAT NULL,
  `schedule_seminar_day` ENUM('SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY') NULL,
  `schedule_lecture_day` ENUM('SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY') NULL,
  `schedule_lab_day` ENUM('SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY') NULL,
  `schedule_seminar_hour` INT NULL,
  `schedule_lecture_hour` INT NULL,
  `schedule_lab_hour` INT NULL,
  PRIMARY KEY (`subject_id`, `professor_id`),
  INDEX `fk_subject_has_professor_professor1_idx` (`professor_id` ASC) VISIBLE,
  INDEX `fk_subject_has_professor_subject1_idx` (`subject_id` ASC) VISIBLE,
  CONSTRAINT `fk_subject_has_professor_subject1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `StudyingPlatform`.`subject` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_subject_has_professor_professor1`
    FOREIGN KEY (`professor_id`)
    REFERENCES `StudyingPlatform`.`professor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `StudyingPlatform` ;

-- -----------------------------------------------------
-- procedure get_user_by_id
-- -----------------------------------------------------

DELIMITER $$
USE `StudyingPlatform`$$
CREATE PROCEDURE get_user_by_id(given_id INT)
BEGIN
DECLARE vrole ENUM('STUDENT', 'PROFESSOR') DEFAULT null;
SET vrole = (SELECT role FROM user WHERE id = given_id);
IF vrole = 'STUDENT' THEN
	SELECT al1.id, username, password, role, CNP, first_name, last_name, phone, email, iban, contract_number, is_admin, is_super_admin, year, min_studying_hours, country, region, town, street_address, postal_code 
    FROM ((select * from user u where u.id = given_id) as al1 
		 left join student s on s.id= given_id 
         left join address a on a.id = given_id);
ELSEIF vrole = 'PROFESSOR' THEN
	SELECT al1.id, username, password, role, CNP, first_name, last_name, phone, email, iban, contract_number, is_admin, is_super_admin, min_teaching_hours, max_teaching_hours, department, country, region, town, street_address, postal_code 
    FROM ((select * from user u where u.id = given_id) as al1 
		 left join professor p on p.id = given_id 
         left join address a on a.id = given_id);
ELSE 
	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'User not found';
END IF;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insert_student
-- -----------------------------------------------------

DELIMITER $$
USE `StudyingPlatform`$$
CREATE PROCEDURE insert_student(pusername VARCHAR(32), ppassword VARCHAR(32),pCNP VARCHAR(20),pfirst_name VARCHAR(45), plast_name VARCHAR(45), pcountry VARCHAR(45), pregion VARCHAR(45), ptown VARCHAR(45), pstreet_address VARCHAR(45), ppostal_code VARCHAR(45), pphone VARCHAR(15), pemail VARCHAR(45), piban VARCHAR(45), pcontract_number VARCHAR(45), pis_admin TINYINT, pis_super_admin TINYINT, pyear INT, pmin_studying_hours INT) 
BEGIN
DECLARE current_id INT;
DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  -- rollback any changes made in the transaction
        RESIGNAL;  -- raise again the sql exception to the caller
    END;
START TRANSACTION;
INSERT INTO user(username, password,role, CNP, first_name, last_name, phone, email, iban, contract_number, is_admin, is_super_admin) 
VALUES(pusername,ppassword, 'STUDENT', pCNP, pfirst_name, plast_name, pphone, pemail, piban, pcontract_number, pis_admin, pis_super_admin);

SET current_id = (SELECT LAST_INSERT_ID());

INSERT INTO address(id,country,region,town,street_address,postal_code)
VALUES(current_id,pcountry,pregion,ptown,pstreet_address,ppostal_code);

INSERT INTO student(id,year,min_studying_hours)
VALUES (current_id,pyear,pmin_studying_hours);

COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insert_professor
-- -----------------------------------------------------

DELIMITER $$
USE `StudyingPlatform`$$
CREATE PROCEDURE insert_professor(pusername VARCHAR(32), ppassword VARCHAR(32),pCNP VARCHAR(20),pfirst_name VARCHAR(45), plast_name VARCHAR(45), pcountry VARCHAR(45), pregion VARCHAR(45), ptown VARCHAR(45), pstreet_address VARCHAR(45), ppostal_code VARCHAR(45), pphone VARCHAR(15), pemail VARCHAR(45), piban VARCHAR(45), pcontract_number VARCHAR(45), pis_admin TINYINT, pis_super_admin TINYINT, pmin_teaching_hours INT, pmax_teaching_hours INT, pdepartment VARCHAR(60)) 
BEGIN
DECLARE current_id INT;
DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  -- rollback any changes made in the transaction
        RESIGNAL;  -- raise again the sql exception to the caller
    END;
START TRANSACTION;
INSERT INTO user(username,password,role,CNP,first_name,last_name,phone,email,iban,contract_number,is_admin,is_super_admin) 
VALUES(pusername,ppassword,'PROFESSOR',pCNP,pfirst_name,plast_name,pphone,pemail,piban,pcontract_number,pis_admin,pis_super_admin);

SET current_id = (SELECT LAST_INSERT_ID());

INSERT INTO address(id,country,region,town,street_address,postal_code)
VALUES(current_id,pcountry,pregion,ptown,pstreet_address,ppostal_code);

INSERT INTO professor(id,min_teaching_hours,max_teaching_hours,department)
VALUES (current_id,pmin_teaching_hours,pmax_teaching_hours,pdepartment);
COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure try_log_in
-- -----------------------------------------------------

DELIMITER $$
USE `StudyingPlatform`$$
CREATE PROCEDURE try_log_in(pusername VARCHAR(32),ppassword VARCHAR(32))
BEGIN
DECLARE log_in_status varchar(45);
DECLARE user_id int default null;
DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  -- rollback any changes made in the transaction
        RESIGNAL;  -- raise again the sql exception to the caller
    END;
START TRANSACTION;
	set user_id = (select id from user where username=pusername and password = ppassword);
	IF (exists(select id from user where username=pusername and password = ppassword)) then
		set log_in_status = "successful";
	ELSEIF(exists(select id from user where username = pusername)) then 
		set log_in_status = "wrong password";
	ELSE
		set log_in_status = "username not found";
	END IF;
    SELECT log_in_status,user_id;
COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure get_role_by_id
-- -----------------------------------------------------

DELIMITER $$
USE `StudyingPlatform`$$
CREATE PROCEDURE get_role_by_id(given_id INT)
BEGIN
DECLARE role ENUM('STUDENT','PROFESSOR') default null;
DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        ROLLBACK;  -- rollback any changes made in the transaction
        RESIGNAL;  -- raise again the sql exception to the caller
    END;
START TRANSACTION;
	set role = (SELECT u.role from user u where u.id = given_id);
    select role;
COMMIT;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- Data for table `StudyingPlatform`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `StudyingPlatform`;
INSERT INTO `StudyingPlatform`.`user` (`id`, `username`, `password`, `role`, `CNP`, `first_name`, `last_name`, `phone`, `email`, `iban`, `contract_number`, `is_admin`, `is_super_admin`) VALUES (1, 'CezarZbughin', 'buna', 'STUDENT', '5010901070000', 'Cezar', 'Zbughin', '0740159698', 'cezarzbughin3@gmail.com', 'RO1234432112', '#50103', 0, 0);
INSERT INTO `StudyingPlatform`.`user` (`id`, `username`, `password`, `role`, `CNP`, `first_name`, `last_name`, `phone`, `email`, `iban`, `contract_number`, `is_admin`, `is_super_admin`) VALUES (2, 'RobertVarga', 'Diana<3', 'PROFESSOR', '5140294060000', 'Robert', 'Varga', '0744499000', 'robertvarga93@gmail.com', 'RO1323243232', '#70707', 0, 0);
INSERT INTO `StudyingPlatform`.`user` (`id`, `username`, `password`, `role`, `CNP`, `first_name`, `last_name`, `phone`, `email`, `iban`, `contract_number`, `is_admin`, `is_super_admin`) VALUES (3, 'MarianPolman', 'golf', 'STUDENT', '5081200074064', 'Marian', 'Polman', '0744154400', 'marianpolman@gmail.com', 'RO2341242144', '#53521', 0, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `StudyingPlatform`.`student`
-- -----------------------------------------------------
START TRANSACTION;
USE `StudyingPlatform`;
INSERT INTO `StudyingPlatform`.`student` (`id`, `year`, `min_studying_hours`) VALUES (1, 2, 40);
INSERT INTO `StudyingPlatform`.`student` (`id`, `year`, `min_studying_hours`) VALUES (3, 2, 55);

COMMIT;


-- -----------------------------------------------------
-- Data for table `StudyingPlatform`.`professor`
-- -----------------------------------------------------
START TRANSACTION;
USE `StudyingPlatform`;
INSERT INTO `StudyingPlatform`.`professor` (`id`, `min_teaching_hours`, `max_teaching_hours`, `department`) VALUES (2, 10, 55, 'Programare');

COMMIT;


-- -----------------------------------------------------
-- Data for table `StudyingPlatform`.`address`
-- -----------------------------------------------------
START TRANSACTION;
USE `StudyingPlatform`;
INSERT INTO `StudyingPlatform`.`address` (`id`, `country`, `region`, `town`, `street_address`, `postal_code`) VALUES (1, 'Romania', 'Botosani', 'Botosani', 'Ale.Scolii 4 sc.A', '714145');
INSERT INTO `StudyingPlatform`.`address` (`id`, `country`, `region`, `town`, `street_address`, `postal_code`) VALUES (2, 'Romania', 'Cluj', 'Cluj-Napoca', 'Ale.varga 3 sc B', '714232');
INSERT INTO `StudyingPlatform`.`address` (`id`, `country`, `region`, `town`, `street_address`, `postal_code`) VALUES (3, 'Romania', 'Bistrita-Nasaud', 'Beclean', 'Str.Radu Afrim 14 sc.A ap 3', '715231');

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
