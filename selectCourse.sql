/*
Navicat MySQL Data Transfer

Source Server         : goldlone
Source Server Version : 50720
Source Host           : goldlone.cn:3306
Source Database       : selectCourse

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-03-15 21:37:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `Admin`
-- ----------------------------
DROP TABLE IF EXISTS `Admin`;
CREATE TABLE `Admin` (
  `no` varchar(24) NOT NULL,
  `name` varchar(30) NOT NULL,
  `schoolNo` int(11) DEFAULT NULL,
  `password` varchar(64) NOT NULL,
  `power` tinyint(4) NOT NULL,
  PRIMARY KEY (`no`),
  KEY `power` (`power`),
  KEY `schoolNo` (`schoolNo`),
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`power`) REFERENCES `Powers` (`no`),
  CONSTRAINT `admin_ibfk_2` FOREIGN KEY (`schoolNo`) REFERENCES `Schools` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Admin
-- ----------------------------
INSERT INTO `Admin` VALUES ('123', '我的管理员', '39', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', '1');
INSERT INTO `Admin` VALUES ('456', '超级管理员', '38', '123', '0');
INSERT INTO `Admin` VALUES ('789', '文学院管理员', '39', '789', '1');

-- ----------------------------
-- Table structure for `Course`
-- ----------------------------
DROP TABLE IF EXISTS `Course`;
CREATE TABLE `Course` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `time` tinyint(4) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Course
-- ----------------------------
INSERT INTO `Course` VALUES ('1', '操作系统', '3');
INSERT INTO `Course` VALUES ('2', '操作系统', '3');
INSERT INTO `Course` VALUES ('3', '操作系统', '3');
INSERT INTO `Course` VALUES ('4', '数据结构', '2');
INSERT INTO `Course` VALUES ('5', '数据结构', '5');
INSERT INTO `Course` VALUES ('6', '数据结构', '8');
INSERT INTO `Course` VALUES ('8', '操作系统', '3');
INSERT INTO `Course` VALUES ('9', '计算机足彩', '10');
INSERT INTO `Course` VALUES ('10', '测试课程1', '2');
INSERT INTO `Course` VALUES ('11', '操作系统', '3');
INSERT INTO `Course` VALUES ('12', '操作系统', '3');
INSERT INTO `Course` VALUES ('13', '操作系统', '3');
INSERT INTO `Course` VALUES ('14', '操作系统', '3');
INSERT INTO `Course` VALUES ('15', '操作系统', '3');
INSERT INTO `Course` VALUES ('16', '测试课程2', '2');

-- ----------------------------
-- Table structure for `CoursePlus`
-- ----------------------------
DROP TABLE IF EXISTS `CoursePlus`;
CREATE TABLE `CoursePlus` (
  `courseNo` int(11) NOT NULL,
  `stage` tinyint(4) NOT NULL,
  `classroom` varchar(50) NOT NULL,
  `teacher` varchar(30) DEFAULT NULL,
  `startDateTime` datetime NOT NULL,
  `endDateTime` datetime NOT NULL,
  PRIMARY KEY (`courseNo`,`stage`),
  CONSTRAINT `courseplus_ibfk_1` FOREIGN KEY (`courseNo`) REFERENCES `Course` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CoursePlus
-- ----------------------------
INSERT INTO `CoursePlus` VALUES ('1', '1', '文科三层报告厅', '李老师', '2018-02-14 14:00:00', '2018-02-14 16:00:00');
INSERT INTO `CoursePlus` VALUES ('1', '2', '文科三层报告厅', '孙老师', '2018-02-15 14:00:00', '2018-02-15 16:00:00');
INSERT INTO `CoursePlus` VALUES ('1', '3', '文科三层报告厅', '白老师', '2018-02-16 14:00:00', '2018-02-16 16:00:00');
INSERT INTO `CoursePlus` VALUES ('2', '1', '文科三层报告厅', '李老师', '2018-02-14 14:00:00', '2018-02-14 16:00:00');
INSERT INTO `CoursePlus` VALUES ('2', '2', '文科三层报告厅', '孙老师', '2018-02-15 14:00:00', '2018-02-15 16:00:00');
INSERT INTO `CoursePlus` VALUES ('2', '3', '文科三层报告厅', '白老师', '2018-02-16 14:00:00', '2018-02-16 16:00:00');
INSERT INTO `CoursePlus` VALUES ('3', '1', '文科三层报告厅', '李老师', '2018-02-14 14:00:00', '2018-02-14 16:00:00');
INSERT INTO `CoursePlus` VALUES ('3', '2', '文科三层报告厅', '孙老师', '2018-02-15 14:00:00', '2018-02-15 16:00:00');
INSERT INTO `CoursePlus` VALUES ('3', '3', '文科三层报告厅', '白老师', '2018-02-16 14:00:00', '2018-02-16 16:00:00');
INSERT INTO `CoursePlus` VALUES ('3', '4', '文科三层报告厅', '白老师', '2018-02-18 14:00:00', '2018-02-18 16:00:00');
INSERT INTO `CoursePlus` VALUES ('4', '1', '文科楼报告厅', '李雪莲', '2018-02-01 00:00:00', '2018-03-28 00:00:00');
INSERT INTO `CoursePlus` VALUES ('5', '1', '文科楼', '1234', '2018-02-01 00:00:00', '2018-03-28 00:00:00');
INSERT INTO `CoursePlus` VALUES ('6', '1', '文科楼大厅', '李雪莲', '2018-02-01 00:00:00', '2018-03-31 00:00:00');
INSERT INTO `CoursePlus` VALUES ('6', '2', '文科楼大厅', '丁兆辉', '2018-02-14 00:00:00', '2018-03-31 00:00:00');
INSERT INTO `CoursePlus` VALUES ('8', '1', '文科三层报告厅', '李老师', '2018-02-14 14:00:00', '2018-02-14 16:00:00');
INSERT INTO `CoursePlus` VALUES ('8', '2', '文科三层报告厅', '孙老师', '2018-02-15 14:00:00', '2018-02-15 16:00:00');
INSERT INTO `CoursePlus` VALUES ('8', '3', '文科三层报告厅', '白老师', '2018-02-16 14:00:00', '2018-02-16 16:00:00');
INSERT INTO `CoursePlus` VALUES ('9', '1', '文科楼三层报告厅', '李梅', '2018-02-01 00:00:00', '2018-03-31 00:00:00');
INSERT INTO `CoursePlus` VALUES ('9', '2', '文科楼三层报告厅', '李梅', '2018-03-01 00:00:00', '2018-04-30 00:00:00');
INSERT INTO `CoursePlus` VALUES ('9', '3', '文科楼三层报告厅', '李梅', '2018-02-28 00:00:00', '2018-03-30 00:00:00');
INSERT INTO `CoursePlus` VALUES ('10', '1', '文科三层报告厅', '王', '2018-02-27 14:00:00', '2018-02-27 16:00:00');
INSERT INTO `CoursePlus` VALUES ('10', '2', '文科三层报告厅', '白老师', '2018-02-28 14:00:00', '2018-02-28 16:00:00');
INSERT INTO `CoursePlus` VALUES ('10', '3', '文三', '', '2018-03-12 08:00:00', '2018-03-12 10:00:00');
INSERT INTO `CoursePlus` VALUES ('11', '1', '文科三层报告厅', '李老师', '2018-02-14 14:00:00', '2018-02-14 16:00:00');
INSERT INTO `CoursePlus` VALUES ('11', '2', '文科三层报告厅', '孙老师', '2018-02-15 14:00:00', '2018-02-15 16:00:00');
INSERT INTO `CoursePlus` VALUES ('11', '3', '文科三层报告厅', '白老师', '2018-02-16 14:00:00', '2018-02-16 16:00:00');
INSERT INTO `CoursePlus` VALUES ('12', '1', '文科三层报告厅', '李老师', '2018-02-14 14:00:00', '2018-02-14 16:00:00');
INSERT INTO `CoursePlus` VALUES ('12', '2', '文科三层报告厅', '孙老师', '2018-02-15 14:00:00', '2018-02-15 16:00:00');
INSERT INTO `CoursePlus` VALUES ('12', '3', '文科三层报告厅', '白老师', '2018-02-16 14:00:00', '2018-02-16 16:00:00');
INSERT INTO `CoursePlus` VALUES ('13', '1', '文科三层报告厅', '李老师', '2018-02-14 14:00:00', '2018-02-14 16:00:00');
INSERT INTO `CoursePlus` VALUES ('13', '2', '文科三层报告厅', '孙老师', '2018-02-15 14:00:00', '2018-02-15 16:00:00');
INSERT INTO `CoursePlus` VALUES ('13', '3', '文科三层报告厅', '白老师', '2018-02-16 14:00:00', '2018-02-16 16:00:00');
INSERT INTO `CoursePlus` VALUES ('14', '1', '文科三层报告厅', '李老师', '2018-02-14 14:00:00', '2018-02-14 16:00:00');
INSERT INTO `CoursePlus` VALUES ('14', '2', '文科三层报告厅', '孙老师', '2018-02-15 14:00:00', '2018-02-15 16:00:00');
INSERT INTO `CoursePlus` VALUES ('14', '3', '文科三层报告厅', '白老师', '2018-02-16 14:00:00', '2018-02-16 16:00:00');
INSERT INTO `CoursePlus` VALUES ('15', '1', '文科三层报告厅', '李老师', '2018-02-14 14:00:00', '2018-02-14 16:00:00');
INSERT INTO `CoursePlus` VALUES ('15', '2', '文科三层报告厅', '孙老师', '2018-02-15 14:00:00', '2018-02-15 16:00:00');
INSERT INTO `CoursePlus` VALUES ('15', '3', '文科三层报告厅', '白老师', '2018-02-16 14:00:00', '2018-02-16 16:00:00');
INSERT INTO `CoursePlus` VALUES ('16', '1', '文三', '', '2018-02-28 14:00:00', '2018-02-28 16:00:00');
INSERT INTO `CoursePlus` VALUES ('16', '2', '文三', '王老师', '2018-03-14 02:00:00', '2018-03-14 04:00:00');

-- ----------------------------
-- Table structure for `CoursePower`
-- ----------------------------
DROP TABLE IF EXISTS `CoursePower`;
CREATE TABLE `CoursePower` (
  `courseNo` int(11) NOT NULL,
  `power` tinyint(4) NOT NULL,
  PRIMARY KEY (`courseNo`,`power`),
  KEY `power` (`power`),
  CONSTRAINT `coursepower_ibfk_1` FOREIGN KEY (`courseNo`) REFERENCES `Course` (`no`),
  CONSTRAINT `coursepower_ibfk_2` FOREIGN KEY (`power`) REFERENCES `Powers` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of CoursePower
-- ----------------------------
INSERT INTO `CoursePower` VALUES ('3', '2');
INSERT INTO `CoursePower` VALUES ('4', '2');
INSERT INTO `CoursePower` VALUES ('5', '2');
INSERT INTO `CoursePower` VALUES ('6', '2');
INSERT INTO `CoursePower` VALUES ('9', '2');
INSERT INTO `CoursePower` VALUES ('10', '2');
INSERT INTO `CoursePower` VALUES ('16', '2');
INSERT INTO `CoursePower` VALUES ('1', '3');
INSERT INTO `CoursePower` VALUES ('2', '3');
INSERT INTO `CoursePower` VALUES ('4', '3');
INSERT INTO `CoursePower` VALUES ('5', '3');
INSERT INTO `CoursePower` VALUES ('6', '3');
INSERT INTO `CoursePower` VALUES ('8', '3');
INSERT INTO `CoursePower` VALUES ('9', '3');
INSERT INTO `CoursePower` VALUES ('10', '3');
INSERT INTO `CoursePower` VALUES ('11', '3');
INSERT INTO `CoursePower` VALUES ('12', '3');
INSERT INTO `CoursePower` VALUES ('13', '3');
INSERT INTO `CoursePower` VALUES ('14', '3');
INSERT INTO `CoursePower` VALUES ('15', '3');
INSERT INTO `CoursePower` VALUES ('16', '3');
INSERT INTO `CoursePower` VALUES ('1', '4');
INSERT INTO `CoursePower` VALUES ('2', '4');
INSERT INTO `CoursePower` VALUES ('4', '4');
INSERT INTO `CoursePower` VALUES ('5', '4');
INSERT INTO `CoursePower` VALUES ('6', '4');
INSERT INTO `CoursePower` VALUES ('8', '4');
INSERT INTO `CoursePower` VALUES ('9', '4');
INSERT INTO `CoursePower` VALUES ('10', '4');
INSERT INTO `CoursePower` VALUES ('11', '4');
INSERT INTO `CoursePower` VALUES ('12', '4');
INSERT INTO `CoursePower` VALUES ('13', '4');
INSERT INTO `CoursePower` VALUES ('14', '4');
INSERT INTO `CoursePower` VALUES ('15', '4');
INSERT INTO `CoursePower` VALUES ('16', '4');
INSERT INTO `CoursePower` VALUES ('1', '5');
INSERT INTO `CoursePower` VALUES ('2', '5');
INSERT INTO `CoursePower` VALUES ('3', '5');
INSERT INTO `CoursePower` VALUES ('4', '5');
INSERT INTO `CoursePower` VALUES ('5', '5');
INSERT INTO `CoursePower` VALUES ('6', '5');
INSERT INTO `CoursePower` VALUES ('8', '5');
INSERT INTO `CoursePower` VALUES ('9', '5');
INSERT INTO `CoursePower` VALUES ('11', '5');
INSERT INTO `CoursePower` VALUES ('12', '5');
INSERT INTO `CoursePower` VALUES ('13', '5');
INSERT INTO `CoursePower` VALUES ('14', '5');
INSERT INTO `CoursePower` VALUES ('15', '5');

-- ----------------------------
-- Table structure for `Powers`
-- ----------------------------
DROP TABLE IF EXISTS `Powers`;
CREATE TABLE `Powers` (
  `no` tinyint(4) NOT NULL,
  `identity` varchar(20) NOT NULL,
  PRIMARY KEY (`no`),
  UNIQUE KEY `identity` (`identity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Powers
-- ----------------------------
INSERT INTO `Powers` VALUES ('2', '党员');
INSERT INTO `Powers` VALUES ('4', '入党积极分子');
INSERT INTO `Powers` VALUES ('5', '发展对象');
INSERT INTO `Powers` VALUES ('1', '基层党委组织管理员');
INSERT INTO `Powers` VALUES ('0', '超级管理管理员');
INSERT INTO `Powers` VALUES ('3', '预备党员');

-- ----------------------------
-- Table structure for `Schools`
-- ----------------------------
DROP TABLE IF EXISTS `Schools`;
CREATE TABLE `Schools` (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Schools
-- ----------------------------
INSERT INTO `Schools` VALUES ('38', '山西大学党委');
INSERT INTO `Schools` VALUES ('39', '文学院党委');
INSERT INTO `Schools` VALUES ('40', '历史文化学院党委');
INSERT INTO `Schools` VALUES ('41', '哲学社会学学院党委');
INSERT INTO `Schools` VALUES ('42', '政治与公共管理学院党委');
INSERT INTO `Schools` VALUES ('43', '外国语学院党委');
INSERT INTO `Schools` VALUES ('44', '教育科学学院党委');
INSERT INTO `Schools` VALUES ('45', '经济与管理学院党委');
INSERT INTO `Schools` VALUES ('46', '法学院党委');
INSERT INTO `Schools` VALUES ('47', '马克思主义学院党委');
INSERT INTO `Schools` VALUES ('48', '新闻学院党委');
INSERT INTO `Schools` VALUES ('49', '数学科学学院党委');
INSERT INTO `Schools` VALUES ('50', '计算机与信息技术学院党委（大数据学院党委）');
INSERT INTO `Schools` VALUES ('51', '物理电子工程学院党委');
INSERT INTO `Schools` VALUES ('52', '化学化工学院党委');
INSERT INTO `Schools` VALUES ('53', '生命科学学院党委');
INSERT INTO `Schools` VALUES ('54', '环境与资源学院党委');
INSERT INTO `Schools` VALUES ('55', '体育学院党委');
INSERT INTO `Schools` VALUES ('56', '音乐学院党委');
INSERT INTO `Schools` VALUES ('57', '美术学院党委');
INSERT INTO `Schools` VALUES ('58', '继续教育学院党委');
INSERT INTO `Schools` VALUES ('59', '研究生院党委');
INSERT INTO `Schools` VALUES ('60', '国际教育交流学院党委');
INSERT INTO `Schools` VALUES ('61', '现代教育技术学院党委');
INSERT INTO `Schools` VALUES ('62', '机关党委');
INSERT INTO `Schools` VALUES ('63', '教辅单位党委');
INSERT INTO `Schools` VALUES ('64', '离退休党委');
INSERT INTO `Schools` VALUES ('65', '附中党委');
INSERT INTO `Schools` VALUES ('66', '大东关校区党委');
INSERT INTO `Schools` VALUES ('67', '软件学院党委');
INSERT INTO `Schools` VALUES ('68', '电力工程系党总支');
INSERT INTO `Schools` VALUES ('69', '动力工程系党总支');
INSERT INTO `Schools` VALUES ('70', '自动化系党总支');
INSERT INTO `Schools` VALUES ('71', '土木工程系党总支');
INSERT INTO `Schools` VALUES ('72', '电子信息工程系党总支');
INSERT INTO `Schools` VALUES ('73', '环境工程系党总支');
INSERT INTO `Schools` VALUES ('74', '工程管理系党总支');

-- ----------------------------
-- Table structure for `SelectCourse`
-- ----------------------------
DROP TABLE IF EXISTS `SelectCourse`;
CREATE TABLE `SelectCourse` (
  `stuNo` varchar(30) NOT NULL,
  `courseNo` int(11) NOT NULL,
  `stage` tinyint(4) NOT NULL,
  `seatNo` int(11) NOT NULL,
  `acquireTime` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`stuNo`,`courseNo`),
  UNIQUE KEY `uq_select_seat` (`courseNo`,`seatNo`,`stage`),
  CONSTRAINT `selectcourse_ibfk_1` FOREIGN KEY (`stuNo`) REFERENCES `Student` (`no`),
  CONSTRAINT `selectcourse_ibfk_2` FOREIGN KEY (`courseNo`) REFERENCES `Course` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SelectCourse
-- ----------------------------
INSERT INTO `SelectCourse` VALUES ('123', '9', '2', '118', '0');
INSERT INTO `SelectCourse` VALUES ('123', '16', '1', '56', '0');

-- ----------------------------
-- Table structure for `Student`
-- ----------------------------
DROP TABLE IF EXISTS `Student`;
CREATE TABLE `Student` (
  `name` varchar(30) NOT NULL,
  `no` varchar(24) NOT NULL,
  `schoolNo` int(11) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `nation` varchar(20) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `applyDate` date DEFAULT NULL,
  `beActivistDate` date DEFAULT NULL,
  `beDevelopDate` date DEFAULT NULL,
  `power` tinyint(4) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`no`),
  KEY `power` (`power`),
  KEY `schoolNo` (`schoolNo`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`power`) REFERENCES `Powers` (`no`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`schoolNo`) REFERENCES `Schools` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Student
-- ----------------------------
INSERT INTO `Student` VALUES ('徐1', '123', '39', '男', '汉', '2010-01-05', '本科生', '2015', '无', null, null, null, '2', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3');
INSERT INTO `Student` VALUES ('kfos', '12334239898', '39', '男', '汗', '2018-02-01', '本科生', '2011', '无', '2018-02-27', '2018-02-27', '2018-02-27', '2', 'fb79c2df83c79f3dff54410f25883a11f0375ae12c8992ad43cffeda40906f61');
INSERT INTO `Student` VALUES ('次年会', '1234564789', '41', '男', '汉', '2018-02-08', '本科生', '2016', '无', '2018-02-01', '2018-02-01', '2018-02-01', '5', '9ef39f0c31556593b2c14167fea21678adb328365aae4c46b1b0a3d529dbfa8c');
INSERT INTO `Student` VALUES ('李梅', '123456789', '39', '男', '汗', '1900-01-01', '本科生', '2016', '无', '1980-01-01', '1980-01-01', '1980-01-01', '2', '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225');
INSERT INTO `Student` VALUES ('徐1', '147852', '39', '男', '汉', null, '本科生', '2015', '无', null, null, null, '2', '0729563253bc11cb72714d61132adfe7ba2346b581b02546c9ac4a65fc0c02d8');
INSERT INTO `Student` VALUES ('许愿2', '456', '40', '女', '汉', '2018-02-22', '研究生', '2016', '无', '2018-02-01', '2018-02-27', '2018-02-27', '3', 'b3a8e0e1f9ab1bfe3a36f231f676f78bb30a519d2b21e6c530c0eee8ebb4a5d0');
DROP TRIGGER IF EXISTS `del_course`;
DELIMITER ;;
CREATE TRIGGER `del_course` BEFORE DELETE ON `Course` FOR EACH ROW BEGIN
  DELETE FROM SelectCourse WHERE SelectCourse.courseNo=OLD.no;
  DELETE FROM CoursePower WHERE CoursePower.courseNo=OLD.no;
  DELETE FROM CoursePlus WHERE CoursePlus.courseNo=OLD.no;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `del_stage_course`;
DELIMITER ;;
CREATE TRIGGER `del_stage_course` BEFORE DELETE ON `CoursePlus` FOR EACH ROW BEGIN
  DELETE FROM SelectCourse WHERE SelectCourse.courseNo=OLD.courseNo AND SelectCourse.stage=OLD.stage;
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `del_stu`;
DELIMITER ;;
CREATE TRIGGER `del_stu` BEFORE DELETE ON `Student` FOR EACH ROW BEGIN
  DELETE FROM SelectCourse WHERE OLD.no;
END
;;
DELIMITER ;
