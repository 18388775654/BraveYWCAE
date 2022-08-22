package com.yang.cae.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@EnableKnife4j
@EnableSwagger2WebMvc
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("BraveYwCAE")
                .apiInfo(apiInfo())
                .host("localhost:8001")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo(){
        Contact contact = new Contact("杨伟","123","brave.yw.gm@Gmail.com");
        return new ApiInfoBuilder()
                .title("CAE咨询系统API文档")
                .description("接口文档只对于开发测试环节开放")
                .version("v1.0")
                .contact(contact)
                .build();
    }
}
