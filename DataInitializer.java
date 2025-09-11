import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataInitializer {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 初始化测试数据，如果数据库中没有数据则添加
     */
    public static void initTestData() {
        // 检查是否已有数据
        if (StudentDao.getStudentCount() > 0) {
            return;
        }

        System.out.println("正在初始化测试数据...");

        List<Student> students = new ArrayList<>();
        try {
            students.add(new Student("2023001", "张三", "男", sdf.parse("2005-03-15"), "计算机科学", 88.5));
            students.add(new Student("2023002", "李四", "男", sdf.parse("2004-11-22"), "软件工程", 92.0));
            students.add(new Student("2023003", "王五", "女", sdf.parse("2005-05-08"), "人工智能", 79.5));
            students.add(new Student("2023004", "赵六", "男", sdf.parse("2004-09-30"), "数据科学", 85.0));
            students.add(new Student("2023005", "钱七", "女", sdf.parse("2005-01-17"), "计算机科学", 90.5));
            students.add(new Student("2023006", "孙八", "男", sdf.parse("2004-07-05"), "软件工程", 76.0));
            students.add(new Student("2023007", "周九", "女", sdf.parse("2005-04-23"), "人工智能", 89.0));
            students.add(new Student("2023008", "吴十", "男", sdf.parse("2004-12-10"), "数据科学", 82.5));
            students.add(new Student("2023009", "郑十一", "女", sdf.parse("2005-02-19"), "计算机科学", 94.0));
            students.add(new Student("2023010", "王十二", "男", sdf.parse("2004-08-28"), "软件工程", 78.5));
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        // 添加到数据库
        int count = 0;
        for (Student student : students) {
            if (StudentDao.addStudent(student)) {
                count++;
            }
        }

        System.out.println("测试数据初始化完成，共添加 " + count + " 条学生信息");
    }
}
