import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {
    /**
     * 使用反射获取对象的所有属性值
     */
    public static String getObjectFields(Object obj) {
        if (obj == null) {
            return "对象为空";
        }

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder sb = new StringBuilder();

        sb.append(clazz.getSimpleName()).append(" 属性:\n");
        for (Field field : fields) {
            // 跳过serialVersionUID
            if (field.getName().equals("serialVersionUID")) {
                continue;
            }

            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                sb.append(fieldName).append(": ").append(value).append("\n");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }

    /**
     * 使用反射调用对象的setter方法设置属性值
     */
    public static boolean setFieldValue(Object obj, String fieldName, Object value) {
        try {
            Class<?> clazz = obj.getClass();
            // 构造setter方法名
            String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

            // 获取字段类型
            Field field = clazz.getDeclaredField(fieldName);
            Class<?> fieldType = field.getType();

            // 获取setter方法并调用
            Method method = clazz.getMethod(methodName, fieldType);
            method.invoke(obj, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
