package presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"presentation", "application", "infrastructure", "domain",})
public class RaceKatteKlubbenApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaceKatteKlubbenApplication.class, args);
    }

}
