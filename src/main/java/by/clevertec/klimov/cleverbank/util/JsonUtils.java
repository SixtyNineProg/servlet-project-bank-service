package by.clevertec.klimov.cleverbank.util;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {

  public static final Gson GSON = new Gson();

  public static <T> T jsonToObject(String jsonObject, Class<T> model) {
    return GSON.fromJson(jsonObject, model);
  }

  public static <T> String objToJson(T object) {
    return GSON.toJson(object);
  }
}
