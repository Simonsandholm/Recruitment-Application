package se.kth.iv1201.group4.recruitment.recruitmentapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "se.kth.iv1201.group4.Repository")
public class RecruitmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitmentApplication.class, args);
    }
}
