package org.willd.delivery.common.util;

public class EnumUtil {
    public static String enumToString(Enum<?> value) {
        return value.name();
    }

    public static <T extends Enum<T>> T stringToEnum(String value, Class<T> enumType) {
        return Enum.valueOf(enumType, value);
    }
}
