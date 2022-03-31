package com.sptech.applicationws.infra.configurations.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sptech.applicationws.controllers"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
                .apiInfo(apiInfo());
    }

//    private List<ResponseMessage> responseMessageForGET() {
//        ArrayList<ResponseMessage> responseMessageList = new ArrayList<>();
//
//        responseMessageList.add(new ResponseMessageBuilder()
//                .code(403)
//                .message("Forbidden.")
//                .build());
//
//        return responseMessageList;
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("application-ws")
                .description("T-Veste REST API for SPTech project.")
                .version("1.0.0")
                .contact(
                        new Contact(
                                "Matheus Noschese Codello",
                                "https://github.com/matheusncodello",
                                "matheus.ncodello@gmail.com"
                        )
                )
                .build();
    }
}
