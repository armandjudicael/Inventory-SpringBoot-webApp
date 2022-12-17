package mg.imwa.admin.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import javax.persistence.*;

@Entity
@Data
public class CompanyDataSourceConfig{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String driverClassName;
    private String host;
    private String port;

    @Enumerated(EnumType.ORDINAL)
    private DatabaseType databaseType;

    private String databaseName;

    @Transient
    @JsonIgnore
    public HikariDataSource initDatasource(){

      String  url = "jdbc:"+databaseType.dbType2String()+"://"+host+":"+port+"/"+databaseName.toLowerCase();

      DataSourceProperties dsp = new DataSourceProperties();

      dsp.setUrl(url);

      dsp.setUsername(username);

      dsp.setPassword(password);

      dsp.setDriverClassName(driverClassName);

      return dsp.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
}
