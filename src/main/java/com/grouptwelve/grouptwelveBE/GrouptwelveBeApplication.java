package com.grouptwelve.grouptwelveBE;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrouptwelveBeApplication {

    public static void main(String[] args) {
        // Load .env file and set environment variables
        Dotenv dotenv = Dotenv.configure()
                .directory("./")  // Look for .env in project root
                .ignoreIfMissing() // Don't fail if .env is missing (for Heroku deployment)
                .load();
        
        // Set each variable from .env as a system property so Spring can access them
        dotenv.entries().forEach(entry -> 
            System.setProperty(entry.getKey(), entry.getValue())
        );
        
        SpringApplication.run(GrouptwelveBeApplication.class, args);
    }

}