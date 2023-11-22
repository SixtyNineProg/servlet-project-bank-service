package by.clevertec.klimov.cleverbank.configuration;

import by.clevertec.klimov.cleverbank.exception.ServiceException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.Yaml;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfigurationLoader {

  public static final String CONFIGURATION_FILE_NAME = "application.yml";
  private static Configuration configuration;

  public static Configuration getConfiguration() {
    if (Objects.isNull(configuration)) {
      configuration = loadConfig();
    }
    return configuration;
  }

  private static Configuration loadConfig() {
    Yaml yaml = new Yaml();
    InputStream inputStream;
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    inputStream = classloader.getResourceAsStream(CONFIGURATION_FILE_NAME);
    Map<String, Object> data = yaml.load(inputStream);
    Gson gson = new Gson();
    Type typeObject = new TypeToken<HashMap<String, Object>>() {}.getType();
    String gsonData = gson.toJson(data, typeObject);
    return gson.fromJson(gsonData, Configuration.class);
  }
}
