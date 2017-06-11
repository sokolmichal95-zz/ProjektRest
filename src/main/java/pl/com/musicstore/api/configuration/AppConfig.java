package pl.com.musicstore.api.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;
import java.util.logging.Logger;

@EnableJpaRepositories
@Configuration
@ComponentScan(value = "pl.com.musicstore.api.models")
public class AppConfig extends ResourceConfig {
    private static final Logger logger =
            Logger.getLogger(AppConfig.class.getName());

    public AppConfig() {
        //register(AlbumRepository.class);
        //register(InstrumentRepository.class);
        //register(UserRepository.class);
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }
}
