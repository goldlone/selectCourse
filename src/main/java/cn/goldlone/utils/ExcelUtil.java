package cn.goldlone.utils;

import cn.goldlone.model.Result;
import cn.goldlone.model.Student;
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

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
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
        String[] titles = {"学号/工号", "姓名", "基层党组织名称",
                "年龄", "性别", "身份"};
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
        Result result = null;
        List<Student> list = new ArrayList<>();
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheet(0);
        Student stu = null;
        for(int i=1; i<sheet.getRows(); i++) {
            String no = sheet.getCell(0, i).getContents();
            String name = sheet.getCell(1, i).getContents();
            String schoolName = sheet.getCell(2, i).getContents();
            int age = Integer.parseInt(sheet.getCell(3, i).getContents());
            String gender = sheet.getCell(4, i).getContents();
            String identity = sheet.getCell(5, i).getContents();

            stu = new Student();
            stu.setNo(no);
            stu.setName(name);
            stu.setSchool(schoolName);
            stu.setGender(gender);
            stu.setIdentity(identity);
            stu.setPassword(DigestUtils.sha256Hex(no));
            list.add(stu);
        }
        return list;
    }


}
