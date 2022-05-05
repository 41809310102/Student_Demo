/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.17-log : Database - student_demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`student_demo` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `student_demo`;

/*Table structure for table `t_action` */

DROP TABLE IF EXISTS `t_action`;

CREATE TABLE `t_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `actiontime` varchar(255) DEFAULT NULL,
  `starttime` varchar(255) DEFAULT NULL,
  `endtime` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `jifen` varchar(255) DEFAULT NULL,
  `limitnum` int(11) DEFAULT NULL,
  `actionuser` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_action` */

insert  into `t_action`(`id`,`actiontime`,`starttime`,`endtime`,`title`,`jifen`,`limitnum`,`actionuser`) values (1,'2022-04-28','2022-4-23 10：00','2022-4-23 11：00','三行情书','1',200,'胡俊杰'),(2,'2022-04-28','2022-4-23 10：00','2022-4-23 11：00','怎么才能找到一份好工作','0.5',200,'胡俊杰'),(3,'2022-04-24','2022-4-23 10：00','2022-4-23 11：00','防疫健康知识大讲座','0.5',200,'胡俊杰');

/*Table structure for table `t_apply` */

DROP TABLE IF EXISTS `t_apply`;

CREATE TABLE `t_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_apply` */

insert  into `t_apply`(`id`,`aid`,`uid`,`state`) values (17,1,1,'等待审核'),(18,2,1,'等待审核'),(19,3,3,'等待审核');

/*Table structure for table `t_association` */

DROP TABLE IF EXISTS `t_association`;

CREATE TABLE `t_association` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `createuser` varchar(255) DEFAULT NULL,
  `desction` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_association` */

insert  into `t_association`(`id`,`name`,`createuser`,`desction`) values (14,'ACM社','胡杰','刷算法，冲金牌'),(15,'图灵学社','has','阿斯顿撒'),(16,'书画社团','下下','嘻嘻嘻');

/*Table structure for table `t_award` */

DROP TABLE IF EXISTS `t_award`;

CREATE TABLE `t_award` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `gettime` varchar(255) DEFAULT NULL,
  `pic` varchar(2000) DEFAULT NULL,
  `aname` varchar(255) DEFAULT NULL,
  `getgrade` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `createtime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_award` */

insert  into `t_award`(`id`,`uid`,`gettime`,`pic`,`aname`,`getgrade`,`level`,`createtime`) values (6,1,'2022-3-14','https://tse1-mm.cn.bing.net/th/id/R-C.76cb233036eb1e0c2064db9ac2ebee12?rik=0LfEPkGbHXna4Q&riu=http%3a%2f%2fjwc.wnmc.edu.cn%2f_upload%2farticle%2fimages%2fa7%2f2f%2fdd0e57ee4634a6d18fa79e4393d7%2fa4a7121a-f03c-4b9c-8098-c1fe8f3e3465_d.jpg&ehk=8LCXB1QUozl0KsO8c8QKmy3p%2fQW4e3%2fLwXeJrHBqTUw%3d&risl=&pid=ImgRaw&r=0','计算机设计大赛',NULL,'B类','2022-04-24'),(7,1,'2022-4-23','https://myfacepic.obs.cn-east-3.myhuaweicloud.com:443/14.png?AccessKeyId=4ODQCL8ZFIM1FLCAB8GA&Expires=1650815816&Signature=M%2FiAfAby70Us86qebA45XIaBRH8%3D','数学建模',NULL,'C类','2022-04-24');

/*Table structure for table `t_classfee` */

DROP TABLE IF EXISTS `t_classfee`;

CREATE TABLE `t_classfee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `addusername` varchar(255) DEFAULT NULL COMMENT '记账人',
  `major` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `money` float DEFAULT NULL,
  `bz` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_classfee` */

insert  into `t_classfee`(`id`,`name`,`addusername`,`major`,`time`,`money`,`bz`) values (2,'王小明','胡俊杰','计算机科学与技术1801班','2022-04-26',34.5,NULL),(3,'王小明','胡俊杰','计算机科学与技术1801班','2022-04-26',34.5,NULL),(4,'王小明','胡俊杰','计算机科学与技术1801班','2022-04-26',34.5,NULL),(9,'哈哈哈','胡俊杰','计算机科学与技术1801班','2022-04-29',-23,NULL),(10,'班长','胡俊杰','计算机科学与技术1801班','2022-04-29',-30.2,NULL),(11,'王小明','胡俊杰','计算机科学与技术1801班','2022-05-05',-6,'给同学打应资料');

/*Table structure for table `t_local` */

DROP TABLE IF EXISTS `t_local`;

CREATE TABLE `t_local` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lat2` varchar(255) DEFAULT NULL,
  `lng2` varchar(255) DEFAULT NULL,
  `localname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_local` */

insert  into `t_local`(`id`,`lat2`,`lng2`,`localname`) values (1,'34.380013','108.97340','陕西科技大学');

/*Table structure for table `t_myquestion` */

DROP TABLE IF EXISTS `t_myquestion`;

CREATE TABLE `t_myquestion` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `myname` varchar(255) DEFAULT NULL COMMENT '被问卷人',
  `major` varchar(255) DEFAULT NULL COMMENT '专业班级',
  `asktime` varchar(255) DEFAULT NULL COMMENT '提交时间',
  `value` varchar(255) DEFAULT NULL COMMENT '回答内容',
  `questid` varchar(255) DEFAULT NULL COMMENT '问卷id',
  `createusername` varchar(255) DEFAULT NULL COMMENT '创建者',
  `title` varchar(255) DEFAULT NULL COMMENT '问卷主题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='学生回答问卷记录表';

/*Data for the table `t_myquestion` */

insert  into `t_myquestion`(`id`,`myname`,`major`,`asktime`,`value`,`questid`,`createusername`,`title`) values (8,'胡俊杰','计算机科学与技术1801班','2022-04-27','1.非常的所产生的 2.西安市下撒西安市 ','418987',NULL,'明天会下雨码'),(9,'胡俊杰','计算机科学与技术1801班','2022-05-05','1.从三大城市的从是的 2.发三大城市的从 3.从三大城市的从 ','895606',NULL,'今天你学习了吗');

/*Table structure for table `t_paper` */

DROP TABLE IF EXISTS `t_paper`;

CREATE TABLE `t_paper` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `questid` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_paper` */

insert  into `t_paper`(`id`,`questid`,`major`,`username`,`time`,`title`) values (7,'895606','计算机科学与技术1801班','胡俊杰','2022-04-27','今天你学习了吗'),(8,'418987','计算机科学与技术1801班','胡俊杰','2022-04-27','明天会下雨码');

/*Table structure for table `t_question` */

DROP TABLE IF EXISTS `t_question`;

CREATE TABLE `t_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `questid` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `questionName` varchar(255) DEFAULT NULL,
  `answersopt` blob COMMENT '选项',
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_question` */

insert  into `t_question`(`id`,`questid`,`username`,`major`,`type`,`questionName`,`answersopt`,`title`) values (7,'895606','胡俊杰','计算机科学与技术1801班','0','长袖衬衫的','��\0sr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0com.mySen.demo.model.Answers?$(��}p\0L\0valuet\0Ljava/lang/String;xpt\0从三大城市的从sq\0~\0t\0从是打车市场sq\0~\0t\0四渡赤水大城市sq\0~\0t\0测试的从市场上的x','今天你学习了吗'),(8,'895606','胡俊杰','计算机科学与技术1801班','0','从三大城市的从是','��\0sr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0com.mySen.demo.model.Answers?$(��}p\0L\0valuet\0Ljava/lang/String;xpt\0测试的从三大城市的从sq\0~\0t\0反而份儿饭sq\0~\0t\0发三大城市的从sq\0~\0t\0从三大城市的x','今天你学习了吗'),(9,'895606','胡俊杰','计算机科学与技术1801班','0','的味道外侧','��\0sr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0com.mySen.demo.model.Answers?$(��}p\0L\0valuet\0Ljava/lang/String;xpt\0尾插完成完成sq\0~\0t\0生成的csd才是sq\0~\0t\0从三大城市的从是的x','今天你学习了吗'),(10,'418987','胡俊杰','计算机科学与技术1801班','0','莎车县撒测试','��\0sr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0com.mySen.demo.model.Answers?$(��}p\0L\0valuet\0Ljava/lang/String;xpt\0下西安撒下sq\0~\0t\0西安市下撒西安市sq\0~\0t\0下撒西安市新啊是sq\0~\0t\0西安市西安西安小x','明天会下雨码'),(11,'418987','胡俊杰','计算机科学与技术1801班','0','下撒西安市新啊是小','��\0sr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0com.mySen.demo.model.Answers?$(��}p\0L\0valuet\0Ljava/lang/String;xpt\0下说西安西安撒下sq\0~\0t\0西安撒下洒下sq\0~\0t\0非常的所产生的x','明天会下雨码');

/*Table structure for table `t_sign` */

DROP TABLE IF EXISTS `t_sign`;

CREATE TABLE `t_sign` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `signtime` varchar(255) DEFAULT NULL,
  `local` varchar(255) DEFAULT NULL,
  `uname` varchar(255) DEFAULT NULL,
  `cardid` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `addsigntime` varchar(255) DEFAULT NULL,
  `isdelect` int(11) DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_sign` */

insert  into `t_sign`(`id`,`uid`,`signtime`,`local`,`uname`,`cardid`,`major`,`addsigntime`,`isdelect`,`aid`) values (10,1,'2022-4-29 15时39分25秒','西安工程大学(临潼校区)','胡俊杰','418010112','计算机科学与技术1801班','2022-4-29 15时39分25秒',0,2),(16,1,'2022-4-29 16时31分26秒','西安工程大学(临潼校区)','胡俊杰','418010112','计算机科学与技术1801班','2022-4-29 16时31分26秒',0,1);

/*Table structure for table `t_subject` */

DROP TABLE IF EXISTS `t_subject`;

CREATE TABLE `t_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `subjectname` varchar(255) DEFAULT NULL,
  `subjecttype` varchar(255) DEFAULT NULL,
  `xuefen` varchar(255) DEFAULT NULL,
  `jidian` varchar(255) DEFAULT NULL,
  `bz` varchar(255) DEFAULT NULL,
  `ispass` varchar(255) DEFAULT NULL,
  `school_group` varchar(255) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `teacherid` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `xid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_subject` */

insert  into `t_subject`(`id`,`uid`,`subjectname`,`subjecttype`,`xuefen`,`jidian`,`bz`,`ispass`,`school_group`,`grade`,`teacherid`,`type`,`xid`) values (1,1,'计算机网络原理','必修课','5','4.5','无','优秀','计算机科学学院',95,1,'大四学年','第一学期'),(2,1,'计算机网络原理','必修课','5','4.5','无','优秀','计算机科学学院',95,1,'大一学年','第一学期'),(3,1,'计算机网络原理','必修课','5','4.5','无','优秀','计算机科学学院',95,1,'大一学年','第一学期'),(4,1,'计算机网络原理','必修课','5','4.5','无','优秀','计算机科学学院',95,1,'大三学年','第二学期'),(5,1,'计算机网络原理','必修课','5','4.5','无','优秀','计算机科学学院',95,1,'大一学年','第一学期'),(6,1,'计算机网络原理','必修课','5','4.5','无','优秀','计算机科学学院',95,1,'大一学年','第一学期'),(7,1,'计算机网络原理','必修课','5','4.5','无','优秀','计算机科学学院',95,1,'大二学年','第一学期'),(8,1,'计算机网络原理','必修课','5','4.5','无','优秀','计算机科学学院',95,1,'大一学年','第二学期'),(9,1,'计算机网络原理','必修课','5.0','3.0',NULL,'优秀','计算机科学学院',95,1,'大四学年','第一学期'),(10,1,'计算机网络原理','必修课','5.0','3.0',NULL,'优秀','计算机科学学院',95,1,'大四学年','第一学期'),(11,1,'计算机网络原理','必修课','5.0','3.0',NULL,'优秀','计算机科学学院',95,1,'大四学年','第一学期'),(12,1,'计算机网络原理','必修课','5.0','3.0',NULL,'优秀','计算机科学学院',95,1,'大四学年','第一学期');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vid` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `shool_group` varchar(255) DEFAULT NULL,
  `cardid` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `grade` float DEFAULT NULL,
  `hobby` varchar(255) DEFAULT NULL,
  `association` varchar(255) DEFAULT NULL,
  `isdelect` int(11) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `desction` varchar(255) DEFAULT NULL,
  `pic` varchar(1000) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`vid`,`username`,`password`,`major`,`shool_group`,`cardid`,`sex`,`age`,`grade`,`hobby`,`association`,`isdelect`,`role`,`desction`,`pic`,`phone`) values (1,NULL,'胡俊杰','123456','计算机科学与技术1801班','材料工程学院','418010112','男','22',9.2,'计算机','ACM社',0,'普通学生','爱好学习，学习使我快乐','https://myfacepic.obs.cn-east-3.myhuaweicloud.com:443/hjjcs6.jpg.jpg?AccessKeyId=4ODQCL8ZFIM1FLCAB8GA&Expires=1651210608&Signature=KkBZv4pPaF5By10f4BR7rraTDMY%3D','18891636452'),(2,NULL,'秀色可餐','123456','计算机科学与技术1801班','材料工程学院','418010112',NULL,NULL,10,NULL,NULL,0,'班干部',NULL,NULL,NULL),(3,NULL,'吃撒判断','123456','计算机科学与技术1801班','计算机科学学院','41809310101','男','24',0.2,'打篮球',NULL,0,'教师','快快乐乐，开开心心，考试不挂',NULL,NULL);

/*Table structure for table `t_vote` */

DROP TABLE IF EXISTS `t_vote`;

CREATE TABLE `t_vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL,
  `options` varchar(255) DEFAULT NULL,
  `a` int(11) DEFAULT NULL,
  `b` int(11) DEFAULT NULL,
  `c` int(11) DEFAULT NULL,
  `d` int(11) DEFAULT NULL,
  `endtime` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_vote` */

insert  into `t_vote`(`id`,`question`,`options`,`a`,`b`,`c`,`d`,`endtime`,`name`,`major`) values (1,'明天聚会吃什么？','火锅',0,0,0,0,'2022-4-25','胡俊杰','计算机科学与技术1802班'),(2,'明天要做核酸检测，有想要做志愿者的吗？','A:想-B:不想-C:无所谓',1,4,4,0,'2022-04-26T16:00:00.000Z','胡俊杰','计算机科学与技术1801班'),(3,'明天班级聚餐吃什么？','A:火锅-B:烧烤-C:麻辣烫-D:其他',23,32,61,13,'2022-04-26T16:00:00.000Z','胡俊杰','计算机科学与技术1801班'),(4,'按四川省','A:按时吃三数-B:阿砂石厂-C:撒擦擦-D:阿擦擦',2,0,0,0,'2022-04-16T16:00:00.000Z','胡俊杰','计算机科学与技术1803班'),(5,'这是一个测试','A:哈哈哈-B:急急急-C:是啊是啊',1,0,1,0,'2022-04-25T16:00:00.000Z','胡俊杰','计算机科学与技术1801班');

/*Table structure for table `t_votelog` */

DROP TABLE IF EXISTS `t_votelog`;

CREATE TABLE `t_votelog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_votelog` */

insert  into `t_votelog`(`id`,`vid`,`uid`) values (1,3,1),(2,5,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
