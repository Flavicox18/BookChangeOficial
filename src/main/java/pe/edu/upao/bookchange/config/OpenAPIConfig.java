package pe.edu.upao.bookchange.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.List;


public class OpenAPIConfig {

    @Value("${cashflow.openapi.dev-url}")
    private String devUrl;

    @Value("${cashflow.openapi.prod-url")
    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI(){
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("BookChange@gmail.com");
        contact.setName("BookChange");
        contact.setUrl("https://www.bookchange.com");

        Info info = new Info()
                .title("BookChange Managment API")
                .version("1.0")
                .contact(contact)
                .description("Este API expone los endpoints del proyecto BookChange");

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }

}
