package by.clevertec.klimov.cleverbank.utils;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CustomCsvToBean<T> {
  public List<T> beanBuilder(String fileName, Class<T> requireClass, char separator)
      throws IOException {
    return new CsvToBeanBuilder<T>(
            new FileReader("src/main/resources/" + fileName))
        .withType(requireClass)
        .withSeparator(separator)
        .build()
        .parse();
  }
}
