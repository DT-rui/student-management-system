import java.util.List;
import java.util.Scanner;

public class test1 {
    /**
     * 恢复数据
     */
    @SuppressWarnings("unchecked")
    public void restoreData(Scanner scanner) {
        System.out.println("\n===== 恢复数据 =====");
        System.out.print("输入要恢复的备份文件名(默认为students_backup.dat): ");
        String fileName = scanner.nextLine().trim();
        if (fileName.isEmpty()) {
            fileName = "students_backup.dat";
        }

        Object obj = SerializationUtil.deserialize(fileName);
        if (obj == null || !(obj instanceof List)) {
            System.out.println("恢复失败，备份文件不存在或格式不正确!");
            return;
        }

        List<Student> students = (List<Student>) obj;
        System.out.println("发现备份数据，共 " + students.size() + " 条记录");
        System.out.print("恢复将覆盖现有数据，是否继续? (y/n): ");
        String confirm = scanner.nextLine().trim();

        if (!"y".equalsIgnoreCase(confirm)) {
            System.out.println("已取消恢复操作!");
            return;
        }

        // 先清空现有数据（这里简化处理，实际应用中可能需要更复杂的逻辑）
        int successCount = 0;
        for (Student student : students) {
            if (StudentDao.queryStudentByStudentId(student.getStudentId()) != null) {
                // 如果学生已存在，则更新信息
                if (StudentDao.updateStudent(student)) {
                    successCount++;
                }
            } else {
                // 如果学生不存在，则添加
                if (StudentDao.addStudent(student)) {
                    successCount++;
                }
            }
        }

        System.out.println("数据恢复完成，成功恢复 " + successCount + " 条记录!");
    }
}
