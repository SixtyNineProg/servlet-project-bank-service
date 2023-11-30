package by.clevertec.klimov.cleverbank.util;

import by.clevertec.klimov.cleverbank.exception.SerializationException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReflectionUtil {

  @SuppressWarnings("java:S3011")
  public static <T> Map<String, Object> mapObjectToMap(T object) {
    Map<String, Object> map = new HashMap<>();
    Field[] fields = object.getClass().getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      try {
        map.put(field.getName(), field.get(object));
      } catch (IllegalAccessException e) {
        throw new SerializationException(e);
      }
    }
    return map;
  }
}
