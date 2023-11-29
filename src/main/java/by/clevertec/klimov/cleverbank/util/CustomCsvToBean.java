package by.clevertec.klimov.cleverbank.util;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CustomCsvToBean<T> {

  public List<T> beanBuilder(String filePath, Class<T> requireClass, char separator)
      throws IOException {
    return new CsvToBeanBuilder<T>(new FileReader(filePath))
        .withType(requireClass)
        .withSeparator(separator)
        .withIgnoreEmptyLine(true)
        .build()
        .parse();
  }
}
