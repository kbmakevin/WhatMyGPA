DROP SCHEMA IF EXISTS `what_my_gpa_db`;

CREATE SCHEMA `what_my_gpa_db` ;

CREATE TABLE `what_my_gpa_db`.`courses` (
  `code` varchar(45) NOT NULL,
  `credits` int(11) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `code_UNIQUE` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `what_my_gpa_db`.`users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('admin','user') NOT NULL DEFAULT 'user',
  `name` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `gpa` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `what_my_gpa_db`.`course_enrollments` (
  `course_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `earnedGPA` double NOT NULL,
  PRIMARY KEY (`course_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `what_my_gpa_db`.`users` (`type`, `name`, `username`, `password`) VALUES ('admin', 'admin', 'admin', 'password');
