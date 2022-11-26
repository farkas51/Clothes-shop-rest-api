package com.yellowhouse.startuppostgressdocker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig2 {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .apiInfo(new ApiInfoBuilder()
                        .title("Yellow House swagger")
                        .description("Описание эндпоинтов API по работе с сущностями")
                        .version("1.0").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yellowhouse.startuppostgressdocker.controller"))
                .paths(PathSelectors.any()).build();
    }
}