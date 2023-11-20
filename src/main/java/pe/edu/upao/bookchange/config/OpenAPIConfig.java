package pe.edu.upao.bookchange.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI myOpenAPI(){

        Contact contact = new Contact();
        contact.setEmail("bookchange@upao.edu.pe");
        contact.setName("BookChange");
        contact.setUrl("https://flavicox18.github.io/BookChangeOficial/#equipoDeTrabajo");

        License mitLicense = new License().name("MIT License")
                .url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("BookChange Managment API")
                .version("1.0")
                .contact(contact)
                .description("Esta API expone puntos de conexión para administrar tutoriales.")
                .termsOfService("hhttps://flavicox18.github.io/BookChangeOficial/#equipoDeTrabajo");

        return new OpenAPI().info(info);


    }
}