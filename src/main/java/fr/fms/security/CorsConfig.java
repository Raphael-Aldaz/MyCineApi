package fr.fms.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Chemin d'accès de votre API
                .allowedOrigins("*") // Autorise l'origine spécifique
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Autorise les méthodes HTTP spécifiées
                .allowedHeaders("*"); // Autorise tous les en-têtes
        registry.addMapping("/**") // Chemin d'accès de votre API
                .allowedOrigins("*") // Autorise l'origine spécifique
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Autorise les méthodes HTTP spécifiées
                .allowedHeaders("*"); // Autorise tous les en-têtes
    }
}
