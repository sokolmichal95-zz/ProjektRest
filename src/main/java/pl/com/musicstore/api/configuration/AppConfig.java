package pl.com.musicstore.api.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import pl.com.musicstore.api.resources.AlbumRepository;
import pl.com.musicstore.api.resources.InstrumentRepository;
import pl.com.musicstore.api.resources.UserRepository;

@Configuration
public class AppConfig extends ResourceConfig {
    public AppConfig() {
        register(AlbumRepository.class);
        register(InstrumentRepository.class);
        register(UserRepository.class);
    }
}
