package academy.kata;

import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserCrudBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCrudBootApplication.class, args);
    }


}
