package com.dieudonne.supa_menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.dieudonne.supa_menu.repository")
public class SupaMenuApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupaMenuApplication.class, args);
    }

}
