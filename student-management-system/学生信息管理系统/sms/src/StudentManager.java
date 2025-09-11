import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public StudentManager() {
        // 初始化学生表
        StudentDao.initTable();
        // 初始化测试数据
        DataInitializer.initTestData();
    }

    /**
     * 添加学生
     */
    public void addStudent(Scanner scanner) {
        try {
            System.out.println("\n===== 添加学生 =====");

            System.out.print("请输入学号: ");
            String studentId = scanner.nextLine().trim();

            // 检查学号是否已存在
            if (StudentDao.queryStudentByStudentId(studentId) != null) {
                System.out.println("错误: 该学号已存在!");
                return;
            }

            System.out.print("请输入姓名: ");
            String name = scanner.nextLine().trim();

            System.out.print("请输入性别(男/女): ");
            String gender = scanner.nextLine().trim();
            if (!"男".equals(gender) && !"女".equals(gender)) {
                System.out.println("错误: 性别只能是'男'或'女'!");
                return;
            }

            System.out.print("请输入出生日期(yyyy-MM-dd): ");
            String birthDateStr = scanner.nextLine().trim();
            Date birthDate = sdf.parse(birthDateStr);

            System.out.print("请输入专业: ");
            String major = scanner.nextLine().trim();

            System.out.print("请输入成绩: ");
            double score = Double.parseDouble(scanner.nextLine().trim());
            if (score < 0 || score > 100) {
                System.out.println("错误: 成绩必须在0-100之间!");
                return;
            }

            // 创建学生对象并保存
            Student student = new Student(studentId, name, gender, birthDate, major, score);
            if (StudentDao.addStudent(student)) {
                System.out.println("添加成功!");
            } else {
                System.out.println("添加失败!");
            }
        } catch (ParseException e) {
            System.out.println("错误: 日期格式不正确，请使用yyyy-MM-dd格式!");
        } catch (NumberFormatException e) {
            System.out.println("错误: 成绩格式不正确，请输入数字!");
        }
    }

    /**
     * 查询学生
     */
    public void queryStudent(Scanner scanner) {
        System.out.println("\n===== 查询学生 =====");
        System.out.print("请输入要查询的学号: ");
        String studentId = scanner.nextLine().trim();

        Student student = StudentDao.queryStudentByStudentId(studentId);
        if (student != null) {
            System.out.println("查询结果:");
            System.out.println(student);
        } else {
            System.out.println("没有找到学号为 " + studentId + " 的学生!");
        }
    }

    /**
     * 查看所有学生
     */
    public void queryAllStudents() {
        System.out.println("\n===== 所有学生信息 =====");
        List<Student> students = StudentDao.queryAllStudents();

        if (students.isEmpty()) {
            System.out.println("没有学生信息!");
            return;
        }

        // 按学号排序
        Collections.sort(students, Comparator.comparing(Student::getStudentId));

        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("共 " + students.size() + " 名学生");
    }

    /**
     * 更新学生信息
     */
    public void updateStudent(Scanner scanner) {
        try {
            System.out.println("\n===== 更新学生信息 =====");
            System.out.print("请输入要更新的学生学号: ");
            String studentId = scanner.nextLine().trim();

            Student student = StudentDao.queryStudentByStudentId(studentId);
            if (student == null) {
                System.out.println("没有找到学号为 " + studentId + " 的学生!");
                return;
            }

            System.out.println("当前信息: " + student);
            System.out.println("请输入新的信息(直接回车表示不修改):");

            System.out.print("姓名(" + student.getName() + "): ");
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                student.setName(name);
            }

            System.out.print("性别(" + student.getGender() + "): ");
            String gender = scanner.nextLine().trim();
            if (!gender.isEmpty()) {
                if ("男".equals(gender) || "女".equals(gender)) {
                    student.setGender(gender);
                } else {
                    System.out.println("错误: 性别只能是'男'或'女'，已保留原性别!");
                }
            }

            System.out.print("出生日期(" + sdf.format(student.getBirthDate()) + "): ");
            String birthDateStr = scanner.nextLine().trim();
            if (!birthDateStr.isEmpty()) {
                student.setBirthDate(sdf.parse(birthDateStr));
            }

            System.out.print("专业(" + student.getMajor() + "): ");
            String major = scanner.nextLine().trim();
            if (!major.isEmpty()) {
                student.setMajor(major);
            }

            System.out.print("成绩(" + student.getScore() + "): ");
            String scoreStr = scanner.nextLine().trim();
            if (!scoreStr.isEmpty()) {
                double score = Double.parseDouble(scoreStr);
                if (score >= 0 && score <= 100) {
                    student.setScore(score);
                } else {
                    System.out.println("错误: 成绩必须在0-100之间，已保留原成绩!");
                }
            }

            if (StudentDao.updateStudent(student)) {
                System.out.println("更新成功!");
            } else {
                System.out.println("更新失败!");
            }
        } catch (ParseException e) {
            System.out.println("错误: 日期格式不正确，请使用yyyy-MM-dd格式!");
        } catch (NumberFormatException e) {
            System.out.println("错误: 成绩格式不正确，请输入数字!");
        }
    }

    /**
     * 删除学生
     */
    public void deleteStudent(Scanner scanner) {
        System.out.println("\n===== 删除学生 =====");
        System.out.print("请输入要删除的学生学号: ");
        String studentId = scanner.nextLine().trim();

        Student student = StudentDao.queryStudentByStudentId(studentId);
        if (student == null) {
            System.out.println("没有找到学号为 " + studentId + " 的学生!");
            return;
        }

        System.out.println("确认要删除以下学生信息吗?");
        System.out.println(student);
        System.out.print("输入 y 确认删除，其他键取消: ");
        String confirm = scanner.nextLine().trim();

        if ("y".equalsIgnoreCase(confirm)) {
            if (StudentDao.deleteStudent(studentId)) {
                System.out.println("删除成功!");
            } else {
                System.out.println("删除失败!");
            }
        } else {
            System.out.println("已取消删除操作!");
        }
    }

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
        // 为了简单起见，我们不删除现有数据，而是直接添加备份数据（可能会有重复）
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
