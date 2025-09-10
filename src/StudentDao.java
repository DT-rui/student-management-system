import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    // 初始化学生表
    public static void initTable() {
        String sql = "CREATE TABLE IF NOT EXISTS student (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "studentId VARCHAR(20) UNIQUE NOT NULL," +
                "name VARCHAR(50) NOT NULL," +
                "gender VARCHAR(10) NOT NULL," +
                "birthDate DATE," +
                "major VARCHAR(50)," +
                "score DOUBLE)";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取学生数量
    public static int getStudentCount() {
        String sql = "SELECT COUNT(*) AS count FROM student";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 添加学生
    public static boolean addStudent(Student student) {
        String sql = "INSERT INTO student (studentId, name, gender, birthDate, major, score) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getStudentId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getGender());
            pstmt.setDate(4, new java.sql.Date(student.getBirthDate().getTime()));
            pstmt.setString(5, student.getMajor());
            pstmt.setDouble(6, student.getScore());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 根据学号查询学生
    public static Student queryStudentByStudentId(String studentId) {
        String sql = "SELECT * FROM student WHERE studentId = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setStudentId(rs.getString("studentId"));
                    student.setName(rs.getString("name"));
                    student.setGender(rs.getString("gender"));
                    student.setBirthDate(rs.getDate("birthDate"));
                    student.setMajor(rs.getString("major"));
                    student.setScore(rs.getDouble("score"));
                    return student;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 查询所有学生
    public static List<Student> queryAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM student ORDER BY studentId";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setStudentId(rs.getString("studentId"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setBirthDate(rs.getDate("birthDate"));
                student.setMajor(rs.getString("major"));
                student.setScore(rs.getDouble("score"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // 更新学生信息
    public static boolean updateStudent(Student student) {
        String sql = "UPDATE student SET name = ?, gender = ?, birthDate = ?, major = ?, score = ? " +
                "WHERE studentId = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGender());
            pstmt.setDate(3, new java.sql.Date(student.getBirthDate().getTime()));
            pstmt.setString(4, student.getMajor());
            pstmt.setDouble(5, student.getScore());
            pstmt.setString(6, student.getStudentId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 删除学生
    public static boolean deleteStudent(String studentId) {
        String sql = "DELETE FROM student WHERE studentId = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
