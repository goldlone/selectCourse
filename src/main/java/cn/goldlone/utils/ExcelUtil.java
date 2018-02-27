package cn.goldlone.utils;

import cn.goldlone.model.CourseSeat;
import cn.goldlone.model.Result;
import cn.goldlone.model.Student;
import jxl.Cell;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by CN on 2018/1/31.
 */
public class ExcelUtil {
    /**
     * 导出录入信息模板
     * @return
     */
    public static File exportStuModel() {
        String[] titles = {"姓名", "学号/工号", "基层党组织名称", "性别", "民族",
                "出生日期(1900/01/01)", "类别", "年级", "职务", "申请入党时间",
                "定为积极分子时间", "定为发展对象初步人选时间",
                "身份(党员/预备党员/积极分子/发展对象)"};
        File file = new File("./stuModel.xls");
        try {
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet("学员信息", 0);
            Label label = null;
            for(int i=0; i<titles.length; i++)
                sheet.addCell(new Label(i, 0, titles[i]));
            workbook.write();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 录入学员信息
     * @param file
     * @return
     */
    public static List importStuInfo(MultipartFile file) throws IOException, BiffException {
        List<Student> list = new ArrayList<>();
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheet(0);
        Student stu = null;
        Cell cell = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        for(int i=1; i<sheet.getRows(); i++) {
            String name = sheet.getCell(0, i).getContents();
            String no = sheet.getCell(1, i).getContents();
            String schoolName = sheet.getCell(2, i).getContents();
            String gender = sheet.getCell(3, i).getContents();
            String nation = sheet.getCell(4, i).getContents();
            Date birth = null;
//            cell = sheet.getCell(5, i);
//            if(cell.getContents()!=null && !cell.getContents().equals("")) {
//                birth = ((DateCell) sheet.getCell(5, i)).getDate();
//            }
            String birthStr = sheet.getCell(5, i).getContents();
//            System.out.println(birthStr);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String type = sheet.getCell(6, i).getContents();
            int grade = Integer.parseInt(sheet.getCell(7, i).getContents());
            String position = sheet.getCell(8, i).getContents();
            String applyDateStr = sheet.getCell(9, i).getContents();
            String beActivistDateStr = sheet.getCell(10, i).getContents();
            String beDevelopDateStr = sheet.getCell(11, i).getContents();
            Date applyDate = null;
            Date beActivistDate = null;
            Date beDevelopDate = null;

//            cell = sheet.getCell(9, i);
//            if(cell.getContents()!=null && !cell.getContents().equals("")) {
//                applyDate = ((DateCell) sheet.getCell(9, i)).getDate();
//            }
//            cell = sheet.getCell(10, i);
//            if(cell.getContents()!=null && !cell.getContents().equals("")) {
//                beActivistDate = ((DateCell) sheet.getCell(10, i)).getDate();
//            }
//            cell = sheet.getCell(11, i);
//            if(cell.getContents()!=null && !cell.getContents().equals("")) {
//                beDevelopDate = ((DateCell) sheet.getCell(11, i)).getDate();
//            }

            String identity = sheet.getCell(12, i).getContents();


            try {
                birth = simpleDateFormat.parse(birthStr);
                applyDate = simpleDateFormat.parse(applyDateStr);
                beActivistDate = simpleDateFormat.parse(beActivistDateStr);
                beDevelopDate = simpleDateFormat.parse(beDevelopDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

//            try {
//                birth = simpleDateFormat.parse(birthStr);
//                applyDate =simpleDateFormat.parse(applyDateStr);
//                beActivistDate =simpleDateFormat.parse(beActivistDateStr);
//                beDevelopDate =simpleDateFormat.parse(beDevelopDateStr);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }

            stu = new Student();
            stu.setNo(no);
            stu.setName(name);
            stu.setSchool(schoolName);
            stu.setGender(gender);
            stu.setNation(nation);
            stu.setBirth(birth);
            stu.setType(type);
            stu.setGrade(grade);
            stu.setPosition(position);
            stu.setApplyDate(applyDate);
            stu.setBeActivistDate(beActivistDate);
            stu.setBeDevelopDate(beDevelopDate);
            stu.setIdentity(identity);
            stu.setPassword(DigestUtils.sha256Hex(no));
            list.add(stu);
        }
        return list;
    }

    /**
     * 导出某课程某期的选座状况
     * @param list
     * @return
     *
     */
    public static File exportSelectSeatStatus(List<CourseSeat> list) {
        File file = new File("./selectSeatStatus.xls");

        try {
            WritableWorkbook workbook = Workbook.createWorkbook(file);
            WritableSheet sheet = workbook.createSheet("选座信息", 0);
            Label label = null;

            CourseSeat cs = null;
            for(int i=0; i<list.size(); i++) {
                cs = list.get(i);
                sheet.addCell(new Label(0, i, ""+cs.getSeatNo()));
                sheet.addCell(new Label(1, i, cs.getStuNo()));
                sheet.addCell(new Label(2, i, cs.getStuName()));
            }

            workbook.write();
            workbook.close();
        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }
        return file;
    }




}
