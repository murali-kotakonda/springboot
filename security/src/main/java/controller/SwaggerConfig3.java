package controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import  springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig3 {
    @Bean
    public Docket docket() {
      return new Docket(DocumentationType.SWAGGER_2)
          .forCodeGeneration(true)
          .globalOperationParameters(globalParameterList())
          .select()
         // .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
         // .paths(PathSelectors.any())
          .apis(RequestHandlerSelectors.basePackage("controller"))
          .build();
    }
    
    private List<Parameter> globalParameterList() {
          Parameter authTokenHeader  = new ParameterBuilder()
                .name("authentication") // name of the header
                .modelRef(new ModelRef("string")) // data-type of the header
                .required(true) // required/optional
                .parameterType("header") // for query-param, this value can be 'query'
                .description("JWT Auth Token")
                .build();

        return Arrays.asList(authTokenHeader);
    }
    
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot REST API",
                "Spring Boot REST API for Product Store",
                "3.0",
                "Terms of service",
                new Contact("John Thompson", "https://springframework.guru/about/", "john@springfrmework.guru"),
               "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",null);
        return apiInfo;
    }
}