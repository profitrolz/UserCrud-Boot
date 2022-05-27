package academy.kata;

import academy.kata.configs.Listener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserCrudBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCrudBootApplication.class, args);
    }


    @Bean
    public Listener listener (){
        return new Listener();
    }
}
