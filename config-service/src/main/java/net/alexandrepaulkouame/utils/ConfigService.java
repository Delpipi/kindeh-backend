package net.alexandrepaulkouame.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class ConfigService {

    @Value("${spring.cloud.config.server.git.password}")
    private String githubToken;

    public ConfigService() {
        System.out.println("ConfigService Bean Created!");
    }

    @PostConstruct
    public void checkGitHubToken() {
        System.out.println("GitHub Token: " + githubToken); // This will print the token in the logs
    }
}