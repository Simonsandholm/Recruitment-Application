package se.kth.iv1201.group4.recruitment.recruitmentapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean  
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}