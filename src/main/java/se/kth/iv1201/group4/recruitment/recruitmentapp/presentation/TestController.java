package se.kth.iv1201.group4.recruitment.recruitmentapp.presentation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/people")
    @Transactional
    public List<Object[]> getPeopleDirectly() {
        return entityManager.createNativeQuery("SELECT * FROM person").getResultList();
    }
}

