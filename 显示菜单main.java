import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentManager studentManager = new StudentManager();

    public static void main(String[] args) {
        System.out.println("===== 学生信息管理系统 =====");
        showMenu();

        while (true) {
            System.out.print("\n请输入操作编号: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    studentManager.addStudent(scanner);
                    break;
                case "2":
                    studentManager.queryStudent(scanner);
                    break;
                case "3":
                    studentManager.queryAllStudents();
                    break;
                case "4":
                    studentManager.updateStudent(scanner);
                    break;
                case "5":
                    studentManager.deleteStudent(scanner);
                    break;
                case "6":
                    studentManager.backupData();
                    break;
                case "7":
                    studentManager.restoreData(scanner);
                    break;
                case "0":
                    System.out.println("感谢使用，再见!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效的操作编号，请重新输入");
            }
        }
    }

    /**
     * 显示系统菜单
     */
    private static void showMenu() {
        System.out.println("操作菜单:");
        System.out.println("1. 添加学生");
        System.out.println("2. 查询学生");
        System.out.println("3. 查看所有学生");
        System.out.println("4. 更新学生信息");
        System.out.println("5. 删除学生");
        System.out.println("6. 备份数据");
        System.out.println("7. 恢复数据");
        System.out.println("0. 退出系统");
    }
}
