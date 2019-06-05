package com.retail.store;

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
public class RetailStoreApplication {

    public static void main(String... args) {
        SpringApplication.run(RetailStoreApplication.class, args);
    }

    @Bean
    public Docket docket() {
        //@formatter:off

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(generateApiInfo());

        //@formatter:on
    }

    private ApiInfo generateApiInfo() {
        @SuppressWarnings("rawtypes")
        Collection<VendorExtension> extensions = new HashSet<>();
        extensions.add(new StringVendorExtension("vendor", "retail-store"));

        //@formatter:off

        return new ApiInfo("Retail Store REST API",
                           "REST API documentaiton for the Retail STore",
                           "Version 1.0",
                           "urn:tos",
                           new Contact("Retail Support", "https://retail.store.com/", "hello@retail.store.com"),
                           "Unlicense",
                           "https://tldrlegal.com/license/unlicense",
                           extensions);
        //@formatter:on
    }
}
