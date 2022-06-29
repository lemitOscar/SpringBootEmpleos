package net.kranki.udemyApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 * Clase para indicar donde se guardan las fotos
 * de los registros que se van guardando
 * @author Oscar Rodriguez
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // registry.addResourceHandler("/logos/**").addResourceLocations("file:/empleos/img-vacantes/");
        registry.addResourceHandler("/logos/**").addResourceLocations("file:c:/empleos/img-vacantes/"); // Windows
    }
}
