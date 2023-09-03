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

  public static final String PATH_FILE_CONFIGURATION = "src/main/resources/application.yml";

  private static Configuration configuration;

  public static Configuration getConfiguration() {
    if (Objects.isNull(configuration)) {
      configuration = loadConfig();
    }
    return configuration;
  }

  private static Configuration loadConfig() {
    Yaml yaml = new Yaml();
    File initialFile = new File(PATH_FILE_CONFIGURATION);
    InputStream inputStream;
    try {
      inputStream = new FileInputStream(initialFile);
    } catch (FileNotFoundException e) {
      throw new ServiceException(e);
    }
    Map<String, Object> data = yaml.load(inputStream);
    Gson gson = new Gson();
    Type typeObject = new TypeToken<HashMap<String, Object>>() {}.getType();
    String gsonData = gson.toJson(data, typeObject);
    return gson.fromJson(gsonData, Configuration.class);
  }
}
