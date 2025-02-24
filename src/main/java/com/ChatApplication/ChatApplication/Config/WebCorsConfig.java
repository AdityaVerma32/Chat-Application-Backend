package com.ChatApplication.ChatApplication.Config; // Package declaration for configuration files

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebCorsConfig is a configuration class that sets up CORS (Cross-Origin Resource Sharing)
 * settings for the application. CORS allows resources from different origins to be accessed
 * by a web application running in a browser.
 */
@Configuration // Marks this class as a Spring configuration class
public class WebCorsConfig {

    /**
     * Defines a bean for WebMvcConfigurer that customizes CORS settings.
     * This allows frontend applications running on a different origin to
     * access the backend API while maintaining security constraints.
     *
     * @return WebMvcConfigurer instance with customized CORS mappings.
     */
    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allows CORS for all endpoints in the application
                        .allowedOrigins("http://localhost:5173/") // Restricts access to this specific frontend origin
                        .allowedMethods(
                                HttpMethod.POST.name(), // Allows HTTP POST requests
                                HttpMethod.GET.name(),  // Allows HTTP GET requests
                                HttpMethod.PUT.name()   // Allows HTTP PUT requests
                        )
                        .allowedHeaders("*") // Allows all headers in requests
                        .allowCredentials(true); // Enables sending credentials (e.g., cookies, authentication headers)
            }
        };
    }
}
