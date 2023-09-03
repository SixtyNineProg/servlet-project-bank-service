package by.clevertec.klimov.cleverbank.configuration;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Configuration {

  private Datasource datasource;

  private Paths paths;

  private int interestRatePercent;

  @Data
  public static class Datasource {

    private String url;

    private String user;

    private String password;

    @SerializedName(value = "driver-class-name")
    private String driverClassName;
  }

  @Data
  public static class Paths {

    private String accounts;

    private String banks;

    private String users;
  }
}
