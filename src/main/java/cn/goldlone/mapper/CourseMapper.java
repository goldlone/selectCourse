package cn.goldlone.mapper;

import cn.goldlone.model.Course;
import cn.goldlone.model.CourseSeat;
import cn.goldlone.po.DBCourse;
import cn.goldlone.po.DBCoursePlus;
import cn.goldlone.po.DBCoursePower;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by CN on 2017/12/17.
 */
@Mapper
public interface CourseMapper {

    /****  发布一个课程  ****/
    /**
     * 添加课程基本信息
     * @param course
     * @return
     */
    @Insert("INSERT " +
            "INTO Course(name, time, startSelectDateTime, endSelectDateTime) " +
            "VALUES(#{name}, #{time}, #{startSelectDateTime}, #{endSelectDateTime});")
    @Options(useGeneratedKeys=true, keyProperty="no", keyColumn="no")
    public Integer addCourse(DBCourse course);
    /**
     * 添加课程权限
     * @param coursePower
     * @return
     */
    @Insert("INSERT " +
            "INTO CoursePower(courseNo, power) " +
            "VALUES(#{courseNo}, #{power});")
    public Integer addCoursePower(DBCoursePower coursePower);
    /**
     * 添加课程其余期的信息
     * @param coursePlus
     * @return
     */
    @Insert("INSERT " +
            "INTO CoursePlus(courseNo, stage, classroom, " +
                "   teacher, startDateTime, endDateTime) " +
            "VALUES(#{courseNo}, #{stage}, #{classroom}, " +
            "   #{teacher}, #{startDateTime}, #{endDateTime});")
    public Integer addCoursePlus(DBCoursePlus coursePlus);


    /**
     * 获取全部的课程信息
     * @return
     */
    @Select("SELECT c1.no,c1.name,c2.classroom,c2.teacher,c1.time," +
            "   c2.stage,c2.startDateTime,c2.endDateTime, " +
            "   c1.startSelectDateTime, c1.endSelectDateTime " +
            "FROM Course c1, CoursePlus c2 " +
            "WHERE c1.no=c1.no AND " +
            "   c1.no=c2.courseNo " +
            "ORDER BY c2.startDateTime DESC;")
    public List<Course> getAllCourseInfo();


    /**
     * 获取某门课程的基本信息
     * @param courseNo
     * @return
     */
    @Select("SELECT c1.no,c1.name,c2.classroom,c2.teacher,c1.time, " +
            "   c2.stage,c2.startDateTime,c2.endDateTime, " +
            "   c1.startSelectDateTime, c1.endSelectDateTime " +
            "FROM Course c1, CoursePlus c2 " +
            "WHERE c1.no=#{courseNo} AND " +
            "   c2.stage=#{stage} AND " +
            "   c1.no=c2.courseNo AND " +
            "   c1.no=c2.courseNo;")
    public Course getCourseInfo(@Param("courseNo") int courseNo,
                                @Param("stage") int stage);


    /**
     * 获取该课程的选课权限
     * @param courseNo
     * @return
     */
    @Select("SELECT power " +
            "FROM CoursePower " +
            "WHERE courseNo=#{courseNo};")
    public List<Integer> getCoursePowers(int courseNo);

    /**
     * 查询某课程详情
     * @param courseNo
     * @return
     */
    @Select("SELECT * " +
            "FROM CoursePlus " +
            "WHERE courseNo=#{courseNo}")
    public List<DBCoursePlus> getCoursePlus(int courseNo);

    /**
     * 修改课程基本信息(课程名，学分)
     * @param course
     * @return
     */
    @Update("UPDATE Course " +
            "SET name=#{name}, " +
            "   time=#{time}, " +
            "   startSelectDateTime=#{startSelectDateTime}, " +
            "   endSelectDateTime=#{endSelectDateTime} " +
            "WHERE no=#{no}")
    public Integer updateBaseCourse(DBCourse course);


    /**
     * 删除课程权限
     * @param courseNo
     * @return
     */
    @Delete("DELETE " +
            "FROM CoursePower " +
            "WHERE courseNo=#{courseNo};")
    public Integer deleteCoursePower(int courseNo);


    /**
     * 修改某期的课程信息
     * @param coursePlus
     * @return
     */
    @Update("UPDATE CoursePlus " +
            "SET classroom = #{classroom}," +
            "   teacher=#{teacher}," +
            "   startDateTime=#{startDateTime}," +
            "   endDateTime=#{endDateTime} " +
            "WHERE courseNo=#{courseNo} AND " +
            "   stage=#{stage};")
    public Integer updateCoursePlus(DBCoursePlus coursePlus);

    /**
     * 删除多余的课程-期
     * @param courseNo
     * @param maxStage
     * @return
     */
    @Delete("DELETE " +
            "FROM CoursePlus " +
            "WHERE courseNo=#{courseNo} AND " +
            "   stage>#{maxStage};")
    public Integer deleteMoreCoursePlus(@Param("courseNo") int courseNo,
                                        @Param("maxStage") int maxStage);

    /**
     * 删除课程
     * @param courseNo
     * @return
     */
    @Delete("DELETE FROM Course WHERE no=#{courseNo};")
    public Integer deleteCourse(int courseNo);

    /**
     * 根据权限获取可选课程列表
     * @param power
     * @return
     */
    @Select("SELECT c1.no, c1.name, c2.classroom, c2.teacher, c1.time, " +
            "   c2.stage, c2.startDateTime, c2.endDateTime, " +
            "   c1.startSelectDateTime, c1.endSelectDateTime " +
            "FROM Course c1, CoursePlus c2, CoursePower c3 " +
            "WHERE c3.power=#{power} AND " +
            "   c3.courseNo=c1.no AND " +
            "   c3.courseNo=c2.courseNo AND " +
            "   now()>c1.startSelectDateTime AND " +
            "   now()<c1.endSelectDateTime AND " +
            "   s.no=#{no} AND " +
            "   s.batch == 0 AND " +
            "      NOT exists( " +
            "          SELECT * " +
            "          FROM SelectCourse sc " +
            "          WHERE stuNo=#{no} AND " +
            "                sc.courseNo=c1.no)")
    public List<Course> getCourseList(@Param("power") int power,
                                      @Param("no") String no);



    /**
     * 查询某期课程座位选择状况
     * @param courseNo
     * @param stage
     * @return
     */
    @Select("SELECT seatNo " +
            "FROM SelectCourse " +
            "WHERE courseNo=#{courseNo} AND " +
            "      stage=#{stage} AND " +
            "      batch=0;")
    public List<Integer> getSeatStatus(@Param("courseNo") int courseNo,
                                       @Param("stage") int stage);

    /**
     * 学生选课选座
     * @param stuNo
     * @param courseNo
     * @param stage
     * @param seatNo
     * @return
     */
    @Insert("INSERT " +
            "INTO SelectCourse(stuNo, courseNo, stage, seatNo) " +
            "VALUES(#{stuNo}, #{courseNo}, #{stage}, #{seatNo});")
    public Integer selectCourse(@Param("stuNo") String stuNo,
                                @Param("courseNo") int courseNo,
                                @Param("stage") int stage,
                                @Param("seatNo") int seatNo);

    /**
     * 取消选课
     * @param courseNo
     * @param stuNo
     * @return
     */
    @Delete("DELETE " +
            "FROM SelectCourse " +
            "WHERE courseNo=#{courseNo} AND " +
            "   stuNo=#{stuNo} ;")
    public Integer cancelSelectCourse(@Param("courseNo") int courseNo,
                                      @Param("stuNo") String stuNo);

    /**
     * 查询某位学员的选课状况
     * @param stuNo
     * @return
     */
    @Select("SELECT c2.stuNo,s.name stuName,ss.name school,c1.no courseNo,c1.name courseName,c3.stage,startDateTime,endDateTime,classroom,teacher,time,seatNo,acquireTime " +
            "FROM Course c1,SelectCourse c2,CoursePlus c3,Student s,Schools ss\n" +
            "WHERE s.no = #{stuNo} AND " +
            "  c2.batch=0 AND " +
            "  c2.stuNo=s.no AND " +
            "  c2.batch=s.batch AND " +
            "  s.schoolNo=ss.no AND " +
            "  c2.courseNo=c1.no AND " +
            "  c2.courseNo=c3.courseNo AND " +
            "  c2.stage=c3.stage " +
            "ORDER BY startDateTime DESC;")
    public List<CourseSeat> getMyCourseSeat(String stuNo);

    /**
     * 管理员查询某节某期课程的选座状况
     * @param courseNo
     * @param stage
     * @return
     */
    @Select("SELECT c2.stuNo,s.name stuName,ss.name school,c1.no courseNo,c1.name courseName,c3.stage,startDateTime,endDateTime,classroom,teacher,time,seatNo,acquireTime " +
            "FROM Course c1,SelectCourse c2,CoursePlus c3,Student s,Schools ss " +
            "WHERE c2.courseNo = #{courseNo} AND " +
            "      c2.stage = #{stage} AND " +
            "      c2.batch=0 AND " +
            "      c2.stuNo=s.no AND " +
            "      c2.batch=s.batch AND " +
            "      s.schoolNo=ss.no AND " +
            "      c2.courseNo=c1.no AND " +
            "      c2.courseNo=c3.courseNo AND " +
            "      c2.stage=c3.stage " +
            "ORDER BY startDateTime DESC;")
    public List<CourseSeat> getSelectCourseInfoByMaster(@Param("courseNo") int courseNo,
                                                        @Param("stage") int stage);


//    /**
//     * 查看某个学员已选课程
//     * @param stuNo
//     * @return
//     */
//    @Select("SELECT c2.stuNo,s.name stuName,ss.name school," +
//            "   c1.no courseNo,c1.name courseName,c3.stage,startDateTime," +
//            "   endDateTime,classroom,teacher,time,seatNo,acquireTime " +
//            "FROM Course c1,SelectCourse c2,CoursePlus c3,Student s,Schools ss " +
//            "WHERE c2.stuNo=#{stuNo} AND " +
//            "   c2.stuNo=s.no AND " +
//            "   s.schoolNo=ss.no AND " +
//            "   c2.courseNo=c1.no AND " +
//            "   c2.courseNo=c3.courseNo AND " +
//            "   c2.stage=c3.stage " +
//            "ORDER BY startDateTime DESC;")
//    public List<CourseSeat> getSelectCourse(String stuNo);


    /**
     * 获取某个学生的已获得学时总额
     * @param stuNo
     * @return
     */
    @Select("SELECT SUM(acquireTime) " +
            "FROM SelectCourse " +
            "WHERE stuNo=#{stuNo} and sc.batch=#{batch};")
    public Integer getAcquireTime(@Param("stuNo") String stuNo, @Param("batch") int batch);

    /**
     * 获取已结束的课程信息
     * @return
     */
    @Select("SELECT c1.no,c1.name,c2.classroom,c2.teacher,c1.time," +
            "   c2.stage,c2.startDateTime,c2.endDateTime, " +
            "   c1.startSelectDateTime, c1.endSelectDateTime  " +
            "FROM Course c1, CoursePlus c2 " +
            "WHERE c2.endDateTime<now() AND " +
            "   c1.no=c1.no AND " +
            "   c1.no=c2.courseNo " +
            "ORDER BY c2.startDateTime DESC;")
    public List<Course> getCanFeedbackCourse();

    /**
     * 反馈某期课程到课人员信息（单个人的信息）
     * @param stuNo
     * @param courseNo
     * @return
     */
    @Update("UPDATE SelectCourse " +
            "SET acquireTime = (SELECT time FROM Course WHERE no=#{courseNo}) " +
            "WHERE stuNo = #{stuNo} and batch=0;")
    public Integer feedbackCome(@Param("stuNo") String stuNo,
                                @Param("courseNo") int courseNo);
}
