package com.example.E_commerce_v2;

import com.example.E_commerce_v2.config.security.SessionUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing
public class ECommerceV2Application {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceV2Application.class, args);
    }

    @Bean
    public AuditorAware<Integer> getAuditor(SessionUser sessionUser) {
        return new AuditorAware<>() {
            @Override
            public Optional<Integer> getCurrentAuditor() {
                return Optional.of(sessionUser.getId());
            }
        };
    }
}
