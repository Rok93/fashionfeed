package com.roki.fashionfeed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket apiV1() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("groupName1")
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.roki.fashionfeed.web"))
                .paths(PathSelectors.ant("/api/**")).build();
    }

    @Bean
    public Docket normal() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .groupName("groupName2")
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.roki.fashionfeed.web"))
                .paths(PathSelectors.ant("/feeds/**")).build();
    }
}