CREATE DATABASE `lanlinker_job_ver2.0`;

USE `lanlinker_job_ver2.0`;

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `icon` varchar(128) NOT NULL,
  `password` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `attachment`;

CREATE TABLE `attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `education`;

CREATE TABLE `education` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `email`;

CREATE TABLE `email` (
  `id` int(11) NOT NULL,
  `host` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `end` varchar(255) DEFAULT NULL,
  `start` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `job`;

CREATE TABLE `job` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `picture`;

CREATE TABLE `picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `preliminary`;

CREATE TABLE `preliminary` (
  `id` int(11) NOT NULL,
  `grade` float NOT NULL,
  `pass` bit(1) NOT NULL,
  `suggestion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `resume`;

CREATE TABLE `resume` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(32) NOT NULL,
  `card` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  `education` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `job` int(11) NOT NULL,
  `major` varchar(255) NOT NULL,
  `name` varchar(4) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `qq` varchar(255) NOT NULL,
  `register` varchar(32) NOT NULL,
  `remark` varchar(255) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `university` varchar(255) NOT NULL,
  `work_go_date` varchar(255) NOT NULL,
  `work_max_salary` float NOT NULL,
  `work_min_salary` float NOT NULL,
  `work_overtime` bit(1) NOT NULL,
  `work_status` bit(1) NOT NULL,
  `work_trip` bit(1) NOT NULL,
  `work_year` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l041uahe3seqcbc1jak6wakru` (`card`)
) ENGINE=InnoDB AUTO_INCREMENT=247 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `retrial`;

CREATE TABLE `retrial` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `pass` bit(1) NOT NULL,
  `suggestion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
