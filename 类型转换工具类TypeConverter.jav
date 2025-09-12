public class TypeConverter {

    /**
     * 将字符串安全转换为int
     * @param str 要转换的字符串
     * @param defaultValue 转换失败时的默认值
     * @return 转换后的int值
     */
    public static int toInt(String str, int defaultValue) {
        if (str == null || str.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * 将字符串安全转换为double
     * @param str 要转换的字符串
     * @param defaultValue 转换失败时的默认值
     * @return 转换后的double值
     */
    public static double toDouble(String str, double defaultValue) {
        if (str == null || str.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(str.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
