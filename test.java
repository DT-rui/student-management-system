import java.util.List;
import java.util.Scanner;

public class test {
    /**
     * 备份数据
     */
    public void backupData() {
        System.out.println("\n===== 备份数据 =====");
        List<Student> students = StudentDao.queryAllStudents();

        if (students.isEmpty()) {
            System.out.println("没有数据需要备份!");
            return;
        }

        if (SerializationUtil.serialize(students, "students_backup.dat")) {
            System.out.println("数据备份成功，共备份 " + students.size() + " 条记录!");
        } else {
            System.out.println("数据备份失败!");
        }
    }

    
}
