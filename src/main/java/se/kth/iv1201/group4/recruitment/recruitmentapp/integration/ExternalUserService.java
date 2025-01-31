
package se.kth.iv1201.group4.recruitment.recruitmentapp.integration;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;



@Component
public class ExternalUserService {

    private final RestTemplate restTemplate;

    public ExternalUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean verifyEmail(String email) {
        String url = "https://external-email-service.com/verify?email=" + email;
        ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class);
        return response.getBody();
    }
}