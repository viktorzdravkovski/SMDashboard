package com.viktor.ttt.configuration;

import com.fasterxml.classmate.TypeResolver;
import com.sun.corba.se.spi.ior.ObjectId;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  TypeResolver typeResolver = new TypeResolver();

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .alternateTypeRules(new AlternateTypeRule(typeResolver.resolve(ObjectId.class),typeResolver.resolve(String.class)))
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.viktor.ttt.controller"))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("TaskTrackingTool")
        .description("Task Tracking Tool")
        .termsOfServiceUrl("")
        .version("1.0")
        .build();
  }

}