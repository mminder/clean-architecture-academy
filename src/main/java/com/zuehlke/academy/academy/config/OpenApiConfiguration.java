package com.zuehlke.academy.academy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI academyOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Academy API")
                        .description("Clean Architecture Demo Project")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Academy Team")
                                .email("academy@foo.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
