package rosol.userservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration bean required by the UserDTOConverter class
 */
@Configuration
public class MyConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}