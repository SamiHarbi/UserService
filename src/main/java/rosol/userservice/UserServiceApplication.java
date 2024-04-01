package rosol.userservice;

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

    public static void main(String[] args){
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner initData(UserCommandService userCommandService){
        return args -> {
            LocalDate date = LocalDate.of(1991, 10, 5);
            AppUser user1 = userCommandService.createActiveUser("Daniel", "Lozano", date, 'M', "Spain",
                    "659988745", "dani@mail.com", "daniuser", "danipass", UUID.randomUUID().toString(), 12345,
                    "User", 2, UUID.randomUUID().toString());
            date = LocalDate.of(1994, 5, 20);
            AppUser user2 = userCommandService.createActiveUser("Erik", "Vila", date, 'M', "Spain",
                    "632256353", "erik@mail.com", "erikuser", "erikpass", UUID.randomUUID().toString(), 34567,
                    "User", 3, UUID.randomUUID().toString());
            date = LocalDate.of(1996, 10, 1);
            AppUser user3 = userCommandService.createActiveUser("Pablo", "Arjona", date, 'M', "Spain",
                    "659936662", "pablo@mail.com", "pablouser", "pablopass", UUID.randomUUID().toString(), 56789,
                    "Admin", 4, UUID.randomUUID().toString());
        };
    }
}
