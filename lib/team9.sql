/*
SQLyog Enterprise - MySQL GUI v6.14
MySQL - 5.7.18-log : Database - team9
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `team9`;

USE `team9`;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*Table structure for table `class_course` */

DROP TABLE IF EXISTS `class_course`;

CREATE TABLE `class_course` (
  `semester` varchar(20) DEFAULT NULL,
  `classId` varchar(20) NOT NULL,
  `couId` varchar(20) NOT NULL,
  `teaId` varchar(20) DEFAULT NULL,
  `examType` varchar(20) NOT NULL,
  `examDate` varchar(20) NOT NULL,
  `examTime` varchar(20) NOT NULL,
  `examAddr` varchar(20) NOT NULL,
  KEY `FK_Reference_4` (`classId`),
  KEY `FK_Reference_5` (`couId`),
  KEY `FK_Reference_7` (`teaId`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`classId`) REFERENCES `class_info` (`id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`couId`) REFERENCES `course_info` (`id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`teaId`) REFERENCES `teacher_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `class_course` */

insert  into `class_course`(`semester`,`classId`,`couId`,`teaId`,`examType`,`examDate`,`examTime`,`examAddr`) values ('第一学期','2016010301','A00001','040714','结课考试','2018-12-17','10:20','A203'),('第一学期','2016010301','A00002','040714','','','',''),('第一学期','2016010301','B00001','040714','','','',''),('第一学期','2016010302','A00003','040712','','','','');

/*Table structure for table `class_info` */

DROP TABLE IF EXISTS `class_info`;

CREATE TABLE `class_info` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `number` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `class_info` */

insert  into `class_info`(`id`,`name`,`number`) values ('2016010301','数学1601','29'),('2016010302','数学1602','0');

/*Table structure for table `class_schedule_info` */

DROP TABLE IF EXISTS `class_schedule_info`;

CREATE TABLE `class_schedule_info` (
  `classId` varchar(20) NOT NULL,
  `semester` varchar(20) NOT NULL,
  `monNo1` varchar(20) DEFAULT NULL,
  `monNo2` varchar(20) DEFAULT NULL,
  `monNo3` varchar(20) DEFAULT NULL,
  `monNo4` varchar(20) DEFAULT NULL,
  `tueNo1` varchar(20) DEFAULT NULL,
  `tueNo2` varchar(20) DEFAULT NULL,
  `tueNo3` varchar(20) DEFAULT NULL,
  `tueNo4` varchar(20) DEFAULT NULL,
  `wedNo1` varchar(20) DEFAULT NULL,
  `wedNo2` varchar(20) DEFAULT NULL,
  `wedNo3` varchar(20) DEFAULT NULL,
  `wedNo4` varchar(20) DEFAULT NULL,
  `thursNo1` varchar(20) DEFAULT NULL,
  `thursNo2` varchar(20) DEFAULT NULL,
  `thursNo3` varchar(20) DEFAULT NULL,
  `thursNo4` varchar(20) DEFAULT NULL,
  `friNo1` varchar(20) DEFAULT NULL,
  `friNo2` varchar(20) DEFAULT NULL,
  `friNo3` varchar(20) DEFAULT NULL,
  `friNo4` varchar(20) DEFAULT NULL,
  KEY `FK_Reference_10` (`classId`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`classId`) REFERENCES `class_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `class_schedule_info` */

insert  into `class_schedule_info`(`classId`,`semester`,`monNo1`,`monNo2`,`monNo3`,`monNo4`,`tueNo1`,`tueNo2`,`tueNo3`,`tueNo4`,`wedNo1`,`wedNo2`,`wedNo3`,`wedNo4`,`thursNo1`,`thursNo2`,`thursNo3`,`thursNo4`,`friNo1`,`friNo2`,`friNo3`,`friNo4`) values ('2016010301','第一学期','高等数学','','大学英语','高等数学','','高等数学','高等数学','','高等数学','','大学英语','','','大学英语','','','','','',''),('2016010302','第一学期','','','','','','','','','','','','','','','','','','','',''),('2016010301','第二学期','大学英语','','','','','','','','','大学英语','','','','','','','','','','');

/*Table structure for table `course_info` */

DROP TABLE IF EXISTS `course_info`;

CREATE TABLE `course_info` (
  `id` varchar(20) NOT NULL,
  `couName` varchar(20) NOT NULL,
  `couType` varchar(20) NOT NULL,
  `creditHour` varchar(20) NOT NULL,
  `couFaculty` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `course_info` */

insert  into `course_info`(`id`,`couName`,`couType`,`creditHour`,`couFaculty`) values ('A00001','高等数学','通识教育课','5.5','理学院'),('A00002','线性代数','通识教育课','3.5','理学院'),('A00003','复变函数','专业课','4.5','理学院'),('B00001','大学英语','通识教育课','5','外国语学院');

/*Table structure for table `result_info` */

DROP TABLE IF EXISTS `result_info`;

CREATE TABLE `result_info` (
  `semester` varchar(20) NOT NULL,
  `id` varchar(20) NOT NULL,
  `couId` varchar(20) NOT NULL,
  `result` varchar(20) NOT NULL,
  KEY `FK_Reference_8` (`id`),
  KEY `FK_Reference_9` (`couId`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`id`) REFERENCES `student_info` (`id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`couId`) REFERENCES `course_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `result_info` */

insert  into `result_info`(`semester`,`id`,`couId`,`result`) values ('第一学期','201601030118','A00001','92'),('第一学期','201601030118','A00002','88'),('第一学期','201601030116','A00001','80'),('第一学期','201601030117','A00001','95');

/*Table structure for table `student_info` */

DROP TABLE IF EXISTS `student_info`;

CREATE TABLE `student_info` (
  `id` varchar(20) NOT NULL,
  `stuName` varchar(20) NOT NULL,
  `enName` varchar(20) NOT NULL,
  `sex` varchar(20) NOT NULL,
  `grade` varchar(20) NOT NULL,
  `eduSys` varchar(20) NOT NULL,
  `faculty` varchar(20) NOT NULL,
  `major` varchar(20) NOT NULL,
  `stuMode` varchar(20) NOT NULL,
  `education` varchar(20) NOT NULL,
  `campus` varchar(20) NOT NULL,
  `classId` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_student_class` (`classId`),
  CONSTRAINT `FK_student_class` FOREIGN KEY (`classId`) REFERENCES `class_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student_info` */

insert  into `student_info`(`id`,`stuName`,`enName`,`sex`,`grade`,`eduSys`,`faculty`,`major`,`stuMode`,`education`,`campus`,`classId`) values ('201601030115','陈圆圆','Chen Yuanyuan','男','2016','4','理学院','数学与应用数学','普通全日制','本科','鄠邑校区','2016010301'),('201601030116','王康民','Wang Kangmin','男','2016','4','理学院','数学与应用数学专业','普通全日制','本科','鄠邑校区','2016010301'),('201601030117','许金珍','Xu Jinzhen','女','2016','4','理学院','数学与应用数学','普通全日制','本科','鄠邑校区','2016010302'),('201601030118','孙云杰','Sun Yunjie','男','2016','4','理学院','数学与应用数学','普通全日制','本科','鄠邑校区','2016010301'),('201601030119','高燕','Gao Yan','女','2016','4','理学院','数学与应用数学','普通全日制','本科','鄠邑校区','2016010301');

/*Table structure for table `teacher_info` */

DROP TABLE IF EXISTS `teacher_info`;

CREATE TABLE `teacher_info` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teacher_info` */

insert  into `teacher_info`(`id`,`name`) values ('040711','折延宏'),('040712','周新怡'),('040713','吕伟'),('040714','高楠');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userName` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `roleInfo` varchar(20) NOT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userName`,`password`,`roleInfo`) values ('040711','78a11ab79e85dfad564821ab9de1ae9a','teacher'),('040712','c3274adcd912b19d1f4b2fa06fd3e910','teacher'),('040713','75bb480860ba524e8e47c87d4174e497','teacher'),('040714','51daab0fba244408c38bfca0ffefb891','teacher'),('201601030115','1f79f9e60ea9a4e2fee6597863485636','student'),('201601030116','06c31fbcf6535b7ef4bf3b35c3550f28','student'),('201601030117','3db11bac2cc51d30adc7b7e95874116f','student'),('201601030118','2da776068fe7258813647bc04fecdd30','student'),('201601030119','b0c4e790901adc3acdc0ca18f5fb64af','student'),('admi','60eb0f73e33ce3ffd4e51d974447db53','manager'),('asdf','912ec803b2ce49e4a541068d495ab570','manager'),('edcx','e4ac41ecc25972c614c8e756466333b1','manager'),('poiu','6ca29d9bb530402bd09fe026ee838148','manager'),('qazx','5a90d2dde2355447e668cdc722cad314','manager'),('qwas','ce827a5f1df5b82656d6ecebf1cd20ab','manager'),('qwer','962012d09b8170d912f0669f6d7d9d07','manager'),('rfvc','4b99b66b9cfd1cf49a75f8cd84039938','manager'),('root','63a9f0ea7bb98050796b649e85481845','manager'),('sdfg','e332a76c29654fcb7f6e6b31ced090c7','manager'),('tgbv','d9c148d1e8ad0c232ce4912160037621','manager'),('wert','843f257a9b35eff7032b03bce281feb1','manager'),('yhnb','d26a02c897e5b4a6b8f3d086d22c9ff0','manager'),('zxcv','fd2cc6c54239c40495a0d3a93b6380eb','manager'),('zxsd','c8b9d0b8425aa946aa4d4eb80af67060','manager');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
