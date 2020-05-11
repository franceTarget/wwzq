package com.ren.wwzq.common.config;

import com.ren.wwzq.models.response.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: target
 * @date: 2020/5/11 9:59
 * @description:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String TOKEN = "token";
    private static final String HEADER = "header";
    private static final String PACKAGE = "com.ren.wwzq.controller";

    @Bean
    public Docket restApi() {

        return new Docket(DocumentationType.SWAGGER_2)
                //是否开启api  可以配置文件中
                .enable(true)
                .groupName("1rest")
                .genericModelSubstitutes(Response.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .apiInfo(restApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey(TOKEN, TOKEN, HEADER));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$")).build());
        return securityContexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference(TOKEN, authorizationScopes));
        return securityReferences;
    }

    private ApiInfo restApiInfo() {
        StringBuilder descBuilder = new StringBuilder();
        descBuilder.append("<h4><strong>婉兮的api:</strong></h4>");

        return new ApiInfoBuilder()
                .title("接口文档")
                .description(descBuilder.toString())
                .contact(new Contact("rest", "", ""))
                .version("1.0")
                .build();
    }
}
