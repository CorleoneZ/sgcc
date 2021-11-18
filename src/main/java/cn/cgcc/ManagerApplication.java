package cn.cgcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ManagerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ManagerApplication.class, args);
        System.out.println("| ---------------------------------------------------------------------------------- |");
        System.out.println("|                                    Started Success                                 |");
        System.out.println("| ---------------------------------------------------------------------------------- |");
    }
}
