package com.retail.demo;

import java.util.Collection;
import java.util.HashSet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class DemoApplication {

    public static void main(String ... args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(generateApiInfo());
    }

    private ApiInfo generateApiInfo() {
        @SuppressWarnings("rawtypes")
        Collection<VendorExtension> extensions = new HashSet<>();
        extensions.add(new StringVendorExtension("vendor", "retail-demo"));
        
        return new ApiInfo("Retail Demo REST API",
                           "REST API documentaiton for the Retail Demo",
                           "Version 1.0",
                           "urn:tos",
                           new Contact("Retail Coding Services", "https://retail.demo.com/", "hello@retail.demo.com"),
                           "Unlicense",
                           "https://tldrlegal.com/license/unlicense",
                           extensions);
    }
}

