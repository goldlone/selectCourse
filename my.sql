# 权限信息表
DROP TABLE IF EXISTS Powers;
CREATE TABLE Powers(
  no TINYINT,
  identity VARCHAR(20) NOT NULL UNIQUE,
  PRIMARY KEY (no)
)DEFAULT CHARSET=utf8;
INSERT INTO Powers(no, identity) VALUES(0, '超级管理管理员');
INSERT INTO Powers(no, identity) VALUES(1, '基层党委组织管理员');
INSERT INTO Powers(no, identity) VALUES(2, '党员');
INSERT INTO Powers(no, identity) VALUES(3, '预备党员');
INSERT INTO Powers(no, identity) VALUES(4, '入党积极分子');
INSERT INTO Powers(no, identity) VALUES(5, '发展对象');

# 学院信息表
DROP TABLE IF EXISTS Schools;
CREATE TABLE Schools (
  no INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  PRIMARY KEY (no)
) DEFAULT CHARSET=utf8;
INSERT INTO Schools(name) VALUES ('文学院党委');
INSERT INTO Schools(name) VALUES ('历史文化学院党委');
INSERT INTO Schools(name) VALUES ('哲学社会学学院党委');
INSERT INTO Schools(name) VALUES ('政治与公共管理学院党委');
INSERT INTO Schools(name) VALUES ('外国语学院党委');
INSERT INTO Schools(name) VALUES ('教育科学学院党委');
INSERT INTO Schools(name) VALUES ('经济与管理学院党委');
INSERT INTO Schools(name) VALUES ('法学院党委');
INSERT INTO Schools(name) VALUES ('马克思主义学院党委');
INSERT INTO Schools(name) VALUES ('新闻学院党委');
INSERT INTO Schools(name) VALUES ('数学科学学院党委');
INSERT INTO Schools(name) VALUES ('计算机与信息技术学院党委（大数据学院党委）');
INSERT INTO Schools(name) VALUES ('物理电子工程学院党委');
INSERT INTO Schools(name) VALUES ('化学化工学院党委');
INSERT INTO Schools(name) VALUES ('生命科学学院党委');
INSERT INTO Schools(name) VALUES ('环境与资源学院党委');
INSERT INTO Schools(name) VALUES ('体育学院党委');
INSERT INTO Schools(name) VALUES ('音乐学院党委');
INSERT INTO Schools(name) VALUES ('美术学院党委');
INSERT INTO Schools(name) VALUES ('继续教育学院党委');
INSERT INTO Schools(name) VALUES ('研究生院党委');
INSERT INTO Schools(name) VALUES ('国际教育交流学院党委');
INSERT INTO Schools(name) VALUES ('现代教育技术学院党委');
INSERT INTO Schools(name) VALUES ('机关党委');
INSERT INTO Schools(name) VALUES ('教辅单位党委');
INSERT INTO Schools(name) VALUES ('离退休党委');
INSERT INTO Schools(name) VALUES ('附中党委');
INSERT INTO Schools(name) VALUES ('大东关校区党委');
INSERT INTO Schools(name) VALUES ('软件学院党委');
INSERT INTO Schools(name) VALUES ('电力工程系党总支');
INSERT INTO Schools(name) VALUES ('动力工程系党总支');
INSERT INTO Schools(name) VALUES ('自动化系党总支');
INSERT INTO Schools(name) VALUES ('土木工程系党总支');
INSERT INTO Schools(name) VALUES ('电子信息工程系党总支');
INSERT INTO Schools(name) VALUES ('环境工程系党总支');
INSERT INTO Schools(name) VALUES ('工程管理系党总支');

# 管理员信息表
DROP TABLE IF EXISTS Admin;
CREATE TABLE Admin (
  no VARCHAR(24) NOT NULL,
  name VARCHAR(30) NOT NULL,
  schoolNo INT,
  password VARCHAR(64) NOT NULL,
  power TINYINT NOT NULL,
  PRIMARY KEY (no),
  FOREIGN KEY (power) REFERENCES Powers(no),
  FOREIGN KEY (schoolNo) REFERENCES Schools(no)
) DEFAULT CHARSET utf8;

# 学生信息表
DROP TABLE IF EXISTS Student;
CREATE TABLE Student(
  name VARCHAR(30) NOT NULL,
  no VARCHAR(24) NOT NULL,
  schoolNo INT DEFAULT NULL,
  gender VARCHAR(2),
  nation VARCHAR(20),
  birth DATE,
  type VARCHAR(10),
  grade INT,
  position VARCHAR(20),
  applyDate DATE,
  beActivistDate DATE,
  beDevelopDate DATE,
  power TINYINT NOT NULL,
  password VARCHAR(64) NOT NULL,
  PRIMARY KEY (no),
  FOREIGN KEY (power) REFERENCES Powers(no),
  FOREIGN KEY (schoolNo) REFERENCES Schools(no)
)DEFAULT CHARSET=utf8;
ALTER TABLE Student ADD CONSTRAINT check_gender CHECK (gender IN ('男','女'));
ALTER TABLE Student ADD CONSTRAINT check_age CHECK (gender BETWEEN 10 AND 150);
ALTER TABLE Student ADD CONSTRAINT check_grade CHECK (gender BETWEEN 1902 AND 3000);
ALTER TABLE Student ADD CONSTRAINT low_power CHECK(power>1);

# 课程信息表
DROP TABLE IF EXISTS Course;
CREATE TABLE Course(
  no INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  time TINYINT NOT NULL,
  PRIMARY KEY (no)
)DEFAULT CHARSET=utf8;
# ALTER TABLE Course ADD CONSTRAINT uq_name UNIQUE(name);

# 课程期数表
DROP TABLE IF EXISTS CoursePlus;
CREATE TABLE CoursePlus (
  courseNo INT NOT NULL,
  stage TINYINT NOT NULL,
  classroom VARCHAR(50) NOT NULL,
  teacher VARCHAR(30),
  startDateTime DATETIME NOT NULL,
  endDateTime DATETIME NOT NULL,
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
ALTER TABLE SelectCourse ADD CONSTRAINT uq_select_seat UNIQUE(courseNo, seatNo, stage);









# 获取学员密码
SELECT no, password, schoolNo, power FROM Student WHERE no = '201502401086';

# 获取基层党组织管理员密码
SELECT no, name, schoolNo, power FROM Admin WHERE no=#{no};

# 修改学员登录密码
UPDATE Student SET password=#{newPassword} WHERE no=#{stuNo} AND password=#{password};

# 修改管理员登录密码
UPDATE Admin SET password=#{newPassword} WHERE no=#{no} AND password=#{password};

#
INSERT
INTO student (no, name, grade, schoolNo, age, gender, power, password)
VALUES (#{no}, #{name}, #{grade}, #{schoolNo}, #{age}, #{gender}, #{power}, #{password});

SELECT Student.no,Student.name,grade,Schools.name school,age,gender,power,identity
FROM Powers,Student,Schools
WHERE Student.power=Powers.no AND Student.schoolNo=Schools.no;


SELECT Student.no, Student.name, grade, Schools.name school, age, gender, power, identity
FROM Powers,Student,Schools
WHERE Schools.name='计算机与信息技术学院党委（大数据学院党委）' AND Student.schoolNo=Schools.no AND Student.power=Powers.no;


SELECT Student.no, Student.name, grade, Schools.name school, age, gender, power, identity
FROM Powers,Student,Schools
WHERE Student.schoolNo=17 AND Student.schoolNo=Schools.no AND Student.power=Powers.no;

SELECT Student.no, Student.name, grade, Schools.name school, age, gender, power, identity
FROM Powers,Student,Schools
WHERE Student.no='201502401086' AND Student.schoolNo=Schools.no AND Student.power=Powers.no;

UPDATE Student
SET name=#{name},grade=#{grade},schoolNo=#{schoolNo},age=#{age},gender=#{gender},power=#{power}
WHERE no = #{no};

INSERT
INTO Course(no, name, time)
VALUES(#{no}, #{name}, #{time});

INSERT
INTO CoursePower(courseNo, power)
VALUES(#{courseNo}, #{power});

INSERT
INTO CoursePlus(courseNo, stage, classroom, teacher, startDateTime, endDateTime)
VALUES(#{courseNo}, #{stage}, #{classroom}, #{teacher}, #{startDateTime}, #{endDateTime});

SELECT c1.no,c1.name,c2.classroom,c2.teacher,c1.time,c2.stage,c2.startDateTime,c2.endDateTime
FROM Course c1, CoursePlus c2
WHERE c1.no=c1.no AND
      c1.no=c2.courseNo;


SELECT c1.no,c1.name,c2.classroom,c2.teacher,c1.time,c2.stage,c2.startDateTime,c2.endDateTime
FROM Course c1, CoursePlus c2
WHERE c1.no=1 AND c1.no=c1.no AND c1.no=c2.courseNo;

SELECT power
FROM CoursePower
WHERE courseNo=#{courseNo};

SELECT power
FROM CoursePower
WHERE courseNo=#{courseNo};

UPDATE course
SET name=#{name},
  time=#{time}
WHERE no=#{no};

DELETE FROM CoursePower WHERE courseNo=#{courseNo};

UPDATE CoursePlus
SET classroom = #{classroom},teacher=#{teacher},startDateTime=#{startDateTime},endDateTime=#{endDateTime}
WHERE courseNo=#{courseNo} AND stage=#{stage};


SELECT c1.no, c1.name, c2.classroom, c2.teacher, c1.time, c2.stage, c2.startDateTime, c2.endDateTime
FROM Course c1, CoursePlus c2, CoursePower c3
WHERE c3.power=#{power} AND
      c3.courseNo=c1.no AND
      c3.courseNo=c2.courseNo AND
      now()<c2.startDateTime;

INSERT
INTO SelectCourse(stuNo, courseNo, stage, seatNo)
VALUES(#{stuNo}, #{courseNo}, #{stage}, #{seatNo});

DELETE
FROM SelectCourse s
WHERE s.courseNo=#{courseNo} AND
      s.stuNo=#{stuNo} AND (SELECT 1);
      (SELECT now()<c.startDateTime
       FROM CoursePlus c
       WHERE c.courseNo=s.courseNo AND
             c.stage=#{stage});

SELECT now()<startDateTime
FROM CoursePlus
WHERE courseNo=#{courseNo} AND
      stage=#{stage};

SELECT 1;

SELECT seatNo
FROM SelectCourse
WHERE courseNo=#{courseNo} AND stage=#{stage};

SELECT c2.stuNo,s.name stuName,ss.name school,c1.no courseNo,c1.name courseName,c3.stage,startDateTime,endDateTime,classroom,teacher,time,seatNo,acquireTime
FROM Course c1,SelectCourse c2,CoursePlus c3,Student s,Schools ss
WHERE c2.courseNo = #{courseNo} AND
      c2.stage = #{stage} AND
      c2.stuNo=s.no AND
      s.schoolNo=ss.no AND
      c2.courseNo=c1.no AND
      c2.courseNo=c3.courseNo AND
      c2.stage=c3.stage;

SELECT c2.stuNo,s.name stuName,s.grade,ss.name school,c1.no courseNo,c1.name courseName,c3.stage,startDateTime,endDateTime,classroom,teacher,time,seatNo,acquireTime
FROM Course c1,SelectCourse c2,CoursePlus c3,Student s,Schools ss
WHERE c2.stuNo=#{stuNo} AND
  c2.stuNo=s.no AND
  s.schoolNo=ss.no AND
  c2.courseNo=c1.no AND
  c2.courseNo=c3.courseNo AND
  c2.stage=c3.stage;

SELECT SUM(acquireTime)
FROM SelectCourse
WHERE stuNo=#{stuNo};

UPDATE SelectCourse
SET acquireTime = (SELECT time FROM Course WHERE no=#{courseNo})
WHERE stuNo = #{stuNo};

SELECT * FROM Schools;

SELECT no FROM Schools WHERE name=#{name};

INSERT
INTO Student(no, name, schoolNo, age, gender, power, password)
VALUES(#{no}, #{name}, (SELECT no FROM Schools WHERE Schools.name=#{school}), #{age}, #{gender}, (SELECT no FROM Powers WHERE identity=#{identity}), '123');

SELECT * FROM Powers;


SELECT c2.stuNo,s.name stuName,ss.name school,c1.no courseNo,c1.name courseName,c3.stage,startDateTime,endDateTime,classroom,teacher,time,seatNo,acquireTime
FROM Course c1,SelectCourse c2,CoursePlus c3,Student s,Schools ss
WHERE s.no = #{stuNo} AND
      c2.stuNo=s.no AND
      s.schoolNo=ss.no AND
      c2.courseNo=c1.no AND
      c2.courseNo=c3.courseNo AND
      c2.stage=c3.stage;

SELECT c1.no,c1.name,c2.classroom,c2.teacher,c1.time,
   c2.stage,c2.startDateTime,c2.endDateTime
FROM Course c1, CoursePlus c2
WHERE c1.no=#{courseNo} AND c2.stage=#{stage} AND c1.no=c2.courseNo AND c1.no=c2.courseNo;


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
