package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerUiConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("测试环境")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.business"))
                .paths(PathSelectors.any())
                .build();
    }

//    @Bean
//    public Docket createMonitorRestApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .groupName("人员管理")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.example.demo.business.user"))
//                .paths(PathSelectors.any())
//                .build();
//    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试API环境搭建")
                .description("为了平常做一些测试联系用处")
                .termsOfServiceUrl("http://www.test.com/")
                .contact("shj_no_bug@163.com")
                .version("1.0")
                .build();
    }
}
