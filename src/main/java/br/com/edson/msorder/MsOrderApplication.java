package br.com.edson.msorder;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;


@SpringBootApplication
@EnableSwagger2
public class MsOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsOrderApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.basePackage("br.com.edson.msorder"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {

        return new ApiInfo(
                "Orders API REST",
                "API REST de cadastro de ordens.",
                "1.0",
                "Terms of Service",
                new Contact("Edson Friedrich", " ", ""),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<>()
        );
    }

}
