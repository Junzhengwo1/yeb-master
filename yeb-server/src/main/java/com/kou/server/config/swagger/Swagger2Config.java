package com.kou.server.config.swagger;

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
 * @author JIAJUN KOU
 *
 * Swagger2配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kou.server.controller"))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts())
                .securitySchemes(securitySchemes());
    }


    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("云办公接口文档")
                .description("操作手册")
                .contact(new Contact("Joaquin","http:localhost:8081/doc.html","xxx@qq.com"))
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes(){
        //设置请求头信息
        List<ApiKey> result=new ArrayList<>();
        ApiKey apiKey=new ApiKey("Authorization","Authorization","Header");
        result.add(apiKey);
        return result;
    }

    private List<SecurityContext> securityContexts(){
        //设置需要登录认证的路径
        List<SecurityContext> contexts=new ArrayList<>();
        contexts.add(getContextByPath("/hello/.*"));
        return contexts;
    }

    private SecurityContext getContextByPath(String s) {

        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex(s))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> references=new ArrayList<>();
        AuthorizationScope authorizationScope=new AuthorizationScope("global",
                "accessEverything");

        AuthorizationScope[] authorizationScopes=new AuthorizationScope[1];
        authorizationScopes[0]=authorizationScope;
        references.add(new SecurityReference("Authorization",authorizationScopes));
        return references;
    }

}
