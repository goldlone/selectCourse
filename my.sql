# 权限信息表
DROP TABLE IF EXISTS Powers;
CREATE TABLE Powers(
  no TINYINT,
  identity VARCHAR(10) NOT NULL UNIQUE,
  PRIMARY KEY (no)
)DEFAULT CHARSET=utf8;
INSERT INTO Powers(no, identity) VALUES(0, '超级管理管理员');
INSERT INTO Powers(no, identity) VALUES(1, '学生信息管理员');
INSERT INTO Powers(no, identity) VALUES(2, '课程管理员');
INSERT INTO Powers(no, identity) VALUES(3, '党员');
INSERT INTO Powers(no, identity) VALUES(4, '预备党员');
INSERT INTO Powers(no, identity) VALUES(5, '入党积极分子');
INSERT INTO Powers(no, identity) VALUES(6, '发展对象');
-- INSERT INTO Powers(no, identity) VALUES(7, '团员');
-- INSERT INTO Powers(no, identity) VALUES(8, '群众');

# 学院信息表
DROP TABLE IF EXISTS Schools;
CREATE TABLE Schools (
  no INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  PRIMARY KEY (no)
) DEFAULT CHARSET=utf8;
INSERT INTO Schools(name) VALUES ('文学院');
INSERT INTO Schools(name) VALUES ('历史文化学院');
INSERT INTO Schools(name) VALUES ('哲学社会学学院');
INSERT INTO Schools(name) VALUES ('外国语学院');
INSERT INTO Schools(name) VALUES ('音乐学院');
INSERT INTO Schools(name) VALUES ('美术学院');
INSERT INTO Schools(name) VALUES ('政治与公共管理学院');
INSERT INTO Schools(name) VALUES ('教育科学学院');
INSERT INTO Schools(name) VALUES ('经济与管理学院');
INSERT INTO Schools(name) VALUES ('法学院');
INSERT INTO Schools(name) VALUES ('体育学院');
INSERT INTO Schools(name) VALUES ('数学科学学院');
INSERT INTO Schools(name) VALUES ('物理电子工程学院');
INSERT INTO Schools(name) VALUES ('化学化工学院');
INSERT INTO Schools(name) VALUES ('生命科学学院');
INSERT INTO Schools(name) VALUES ('环境与资源学院');
INSERT INTO Schools(name) VALUES ('计算机与信息技术学院');
INSERT INTO Schools(name) VALUES ('软件学院');
INSERT INTO Schools(name) VALUES ('电力工程系');
INSERT INTO Schools(name) VALUES ('动力工程系');
INSERT INTO Schools(name) VALUES ('自动化系');
INSERT INTO Schools(name) VALUES ('土木工程系');
INSERT INTO Schools(name) VALUES ('电子信息工程系');
INSERT INTO Schools(name) VALUES ('环境工程系');
INSERT INTO Schools(name) VALUES ('工程管理系');
INSERT INTO Schools(name) VALUES ('初民学院');
INSERT INTO Schools(name) VALUES ('国际教育交流学院');
INSERT INTO Schools(name) VALUES ('继续教育学院');
INSERT INTO Schools(name) VALUES ('商务学院');

# 管理员信息表
DROP TABLE IF EXISTS Admin;
CREATE TABLE Admin (
  no VARCHAR(24) NOT NULL,
  name VARCHAR(30) NOT NULL,
  schoolNo INT NOT NULL,
  password VARCHAR(64) NOT NULL,
  power TINYINT NOT NULL,
  PRIMARY KEY (no),
  FOREIGN KEY (power) REFERENCES Powers(no),
  FOREIGN KEY (schoolNo) REFERENCES Schools(no)
) DEFAULT CHARSET utf8;

# 学生信息表
DROP TABLE IF EXISTS Student;
CREATE TABLE Student(
  no VARCHAR(24) NOT NULL,
  name VARCHAR(30) NOT NULL,
  grade INT DEFAULT 0,
  schoolNo INT DEFAULT NULL,
  major VARCHAR(30) DEFAULT '',
  age TINYINT DEFAULT 18,
  gender VARCHAR(1) DEFAULT '',
  power TINYINT NOT NULL,
  password VARCHAR(64) NOT NULL,
  PRIMARY KEY (no),
  FOREIGN KEY (power) REFERENCES Powers(no),
  FOREIGN KEY (schoolNo) REFERENCES Schools(no)
)DEFAULT CHARSET=utf8;
ALTER TABLE Student ADD CONSTRAINT check_gender CHECK (gender IN ('男','女'));
ALTER TABLE Student ADD CONSTRAINT check_age CHECK (gender BETWEEN 10 AND 150);
ALTER TABLE Student ADD CONSTRAINT check_grade CHECK (gender BETWEEN 1902 AND 3000);

INSERT INTO Student VALUES ('201502401086', '程宁', 2015, 17, '计算机科学与技术', 21, '男', 0, '201502401086');

# 课程信息表
DROP TABLE IF EXISTS Course;
CREATE TABLE Course(
  no INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  classroom VARCHAR(50) NOT NULL,
  teacher VARCHAR(30) NOT NULL,
  time TINYINT NOT NULL,
  PRIMARY KEY (no)
)DEFAULT CHARSET=utf8;
# ALTER TABLE Course ADD CONSTRAINT uq_name UNIQUE(name);

# 课程期数表
CREATE TABLE CoursePlus (
  courseNo INT NOT NULL,
  stage TINYINT NOT NULL,
  startSelectDate DATETIME NOT NULL,
  endSelectDate DATETIME NOT NULL,
  PRIMARY KEY (courseNo, stage),
  FOREIGN KEY (courseNo) REFERENCES Course(no)
)DEFAULT CHARSET=utf8;

# 课程权限控制表
DROP TABLE IF EXISTS CoursePower;
CREATE TABLE CoursePower (
  courseNo INT,
  power TINYINT NOT NULL,
  PRIMARY KEY(courseNo, power),
  FOREIGN KEY(courseNo) REFERENCES Course(no),
  FOREIGN KEY(power) REFERENCES Powers(no)
)DEFAULT CHARSET=utf8;

# 选课信息表
DROP TABLE IF EXISTS SelectCourse;
CREATE TABLE SelectCourse(
  stuNo VARCHAR(30),
  courseNo INT,
  stage TINYINT NOT NULL,
  seatNo INT NOT NULL,
  acquireTime TINYINT DEFAULT 0,
  PRIMARY KEY (stuNo, courseNo),
  FOREIGN KEY (stuNo) REFERENCES Student(no),
  FOREIGN KEY (courseNo) REFERENCES Course(no)
)DEFAULT CHARSET=utf8;
ALTER TABLE SelectCourse ADD CONSTRAINT uq_selcetSeat UNIQUE(courseNo, seatNo, stage);














SELECT * FROM Student WHERE 1;

SELECT no, password, power FROM Student WHERE no = '201502401086';

UPDATE Student SET password=#{password} WHERE no=#{stuNo};
INSERT
INTO student (no, name, grade, schoolNo, major,age, gender, power, password)
VALUES (#{no}, #{name}, #{grade}, #{schoolNo}, #{major},#{age}, #{gender}, #{power}, #{password});

SELECT Student.no,Student.name,grade,Schools.name school,major,age,gender,power,identity
FROM Powers,Student,Schools
WHERE Student.power=Powers.no AND Student.schoolNo=Schools.no;


SELECT Student.no,Student.name,grade,Schools.name school,major,age,gender,power,identity
FROM Powers,Student,Schools
WHERE Schools.name='计算机与信息技术学院' AND Student.schoolNo=Schools.no AND Student.power=Powers.no;

SELECT Student.no, Student.name, grade, Schools.name school, major, age, gender, power, identity
FROM Powers,Student,Schools
WHERE Student.schoolNo=17 AND Student.schoolNo=Schools.no AND Student.power=Powers.no;

SELECT Student.no, Student.name, grade, Schools.name school, major, age, gender, power, identity
FROM Powers,Student,Schools
WHERE Student.no='201502401086' AND Student.schoolNo=Schools.no AND Student.power=Powers.no;

UPDATE Student
SET name=#{name},grade=#{grade},schoolNo=#{schoolNo},major=#{major},age=#{age},gender=#{gender},power=#{power}
WHERE no = #{no}

# SELECT Student.no,name,grade,school,major,age,gender,power,identity
# FROM Powers,Student
# WHERE Student.power=Powers.no;
#
#
# SELECT c1.no,c1.name,c1.classroom,c1.teacher,c1.time,c2.stage,c2.startSelectDate,c2.endSelectDate
# FROM Course c1, CoursePlus c2, CoursePower c3
# WHERE c3.power=#{power} AND
#       c3.courseNo=c1.no AND
#       c3.courseNo=c2.courseNo AND
#       now()<c2.endSelectDate;
#
# SELECT SC.seatNo,SC.stuNo,Student.name,Student.grade,SC.courseNo,Course.name
# FROM SelectCourse SC,Student,Course
# WHERE SC.courseNo=1 AND
#       stage=1 AND
#       SC.stuNo=Student.no AND
#       SC.stuNo=Course.no;
#
# SELECT seatNo
# FROM SelectCourse
# WHERE courseNo = 1 AND
#       stage = 1;
#
# SELECT c2.stuNo,s.name,s.grade,s.school,c1.no,c1.name,c3.stage,startSelectDate,endSelectDate,classroom,teacher,time,seatNo,acquireTime
# FROM Course c1,SelectCourse c2,CoursePlus c3,Student s
# WHERE c2.courseNo = ? AND
#       c2.stage=? AND
#       c2.stuNo=s.no AND
#       c2.courseNo=c1.no AND
#       c2.courseNo=c3.courseNo AND
#       c2.stage=c3.stage;
#
# SELECT c1.no,c1.name,c1.classroom,c1.teacher,c1.time,c2.stage,c2.startSelectDate,c2.endSelectDate
# FROM Course c1, CoursePlus c2
# WHERE c1.no=2 AND
#       c1.no=c1.no AND
#       c1.no=c2.courseNo;
#
#
#
# UPDATE SelectCourse
# SET acquireTime = (SELECT time FROM Course WHERE no=#{courseNo})
# WHERE stuNo = #{stuNo};
#
# UPDATE SelectCourse
# SET acquireTime = (SELECT time FROM Course WHERE no=2)
# WHERE stuNo = '123';

# SELECT count(*) FROM Course WHERE no=0;
#
# SELECT no,name,startSelectDate,endSelectDate,classroom,teacher,teacher,time,seatNo,grade
# FROM Course,SC
# WHERE Sno='201502401086' AND Cno=no;
#
# DELETE FROM Student WHERE 1;
# DELETE FROM SC WHERE 1;
# DELETE FROM CoursePower WHERE 1;
# DELETE FROM CoursePlus WHERE 1;
# DELETE FROM Course WHERE 1;
#
# SELECT c1.no,c1.name,c1.classroom,c1.teacher,c1.time,c2.stage,c2.startSelectDate,c2.endSelectDate
# FROM Course c1, CoursePlus c2, CoursePower c3
# WHERE c3.Cno NOT IN (SELECT Cno FROM SC WHERE Sno='123') AND c3.Cpower=0 AND c3.Cno=c1.no AND c3.Cno=c2.Cno AND now()<c2.endSelectDate;
#
# SELECT c1.no,c1.name,c1.classroom,c1.teacher,c1.time,c2.stage,c2.startSelectDate,c2.endSelectDate
# FROM Course c1, CoursePlus c2, CoursePower c3
# WHERE c3.Cpower=0 AND c3.Cno=c1.no AND c3.Cno=c2.Cno AND now()<c2.endSelectDate;
#
# SELECT Sno,no,name,c3.stage,startSelectDate,endSelectDate,classroom,teacher,time,seatNo,acquireTime
# FROM Course c1,SC c2,CoursePlus c3
# WHERE c2.Sno='123' AND c2.Cno=c1.no AND c2.Cno=c3.Cno AND c2.stage=c3.stage;
#
#
# SELECT seatNo
# FROM SC
# WHERE Cno=2 AND stage=1;
#
# SELECT SUM(acquireTime)
# FROM SC
# WHERE Sno='123';
#
# UPDATE SC SET acquireTime = (SELECT time FROM Course WHERE no=?) WHERE Sno = ?;
#
# SELECT SC.seatNo,SC.Sno,Student.name,Student.grade,SC.Cno,Course.name
# FROM SC,Student,Course
# WHERE Cno=1 AND
#       stage=1 AND
#       SC.Sno=Student.no AND
#       SC.Cno=Course.no;
