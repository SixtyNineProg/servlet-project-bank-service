package by.clevertec.klimov.cleverbank.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtil {

  public static String getFileContentAsStringFromResources(String filePath) throws IOException {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    try (InputStream inputStream = classloader.getResourceAsStream(filePath)) {
      return new BufferedReader(
              new InputStreamReader(Objects.requireNonNull(inputStream), StandardCharsets.UTF_8))
          .lines()
          .collect(Collectors.joining("\n"));
    }
  }
}
