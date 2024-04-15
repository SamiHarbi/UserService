package rosol.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import rosol.userservice.model.AppUser;
import rosol.userservice.service.UserCommandService;
import java.time.LocalDate;
import java.util.UUID;

/**
 * User Service Springboot starting point
 */
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Autowired
    UserCommandService userCommandService;
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    @Bean
    public CommandLineRunner startup() {
        return args -> {
            LocalDate date = LocalDate.of(1991, 10, 5);
            AppUser user1 = userCommandService.createActiveUser("Daniel", "Lozano", date, 'M', "Spain",
                    "659988745", "dani@mail.com", "daniuser", "danipass", UUID.randomUUID().toString(), 12345,
                    "User", 2, UUID.randomUUID().toString());

            System.out.println("Database initialized!");

        };

    }

}
