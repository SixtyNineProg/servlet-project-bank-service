package by.clevertec.klimov.cleverbank.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@FieldNameConstants
public class BankDto {

  @CsvBindByName(column = "id")
  private long id;

  @CsvBindByName(column = "name")
  private String name;
}
