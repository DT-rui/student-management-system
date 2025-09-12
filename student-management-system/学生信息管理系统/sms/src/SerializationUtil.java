import java.io.*;
import java.util.List;

public class SerializationUtil {

    /**
     * 将对象序列化到文件
     * @param obj 要序列化的对象
     * @param filePath 目标文件路径
     * @return 序列化是否成功
     */
    public static boolean serialize(Object obj, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(obj);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从文件反序列化对象
     * @param filePath 包含序列化对象的文件路径
     * @return 反序列化得到的对象
     */
    public static Object deserialize(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 序列化学生列表（重载方法，方便调用）
     * @param students 学生列表
     * @param filePath 目标文件路径
     * @return 序列化是否成功
     */
    public static boolean serialize(List<Student> students, String filePath) {
        return serialize((Object) students, filePath);
    }
}
