package se.kth.iv1201.group4.recruitment.recruitmentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * Main entry point for the recruitment application.
 * Excludes default Spring Security auto-configuration.
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class RecruitmentApplication {

    /**
     * Starts the Spring Boot application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(RecruitmentApplication.class, args);
    }
}
