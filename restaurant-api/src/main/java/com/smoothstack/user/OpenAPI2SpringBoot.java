package com.smoothstack.user;

import com.database.security.SecurityConfig;
import com.fasterxml.jackson.databind.Module;
import org.modelmapper.ModelMapper;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EntityScan("com.database.ormlibrary")
@Import(SecurityConfig.class)
@EnableJpaRepositories(basePackages = {"com.database.security", "com.smoothstack.user.repo"})
public class OpenAPI2SpringBoot implements CommandLineRunner {

    public static void main(String[] args) {
        new SpringApplication(OpenAPI2SpringBoot.class).run(args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public WebMvcConfigurer webConfigurer() {
        return new WebMvcConfigurer() {

        };
    }

    @Bean
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }

    static class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }

}
