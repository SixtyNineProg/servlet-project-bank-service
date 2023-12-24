package by.clevertec.klimov.cleverbank.configuration;

import by.clevertec.klimov.cleverbank.cache.CacheType;
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
  private Cache cache;
  private Pagination pagination;

  @Data
  public static class Datasource {

    private String url;
    private String user;
    private String password;

    @SerializedName(value = "driver-class-name")
    private String driverClassName;
    private Boolean isInitDb;
  }

  @Data
  public static class Paths {

    private String accounts;
    private String banks;
    private String users;
  }

  @Data
  public static class Cache {

    private CacheType type;
    private int size;
  }

  @Data
  public static class Pagination {

    private int pageSize;
  }
}
