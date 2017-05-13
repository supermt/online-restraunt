/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: SwaggerConfig.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.config 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月10日 下午3:25:50 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfig
 * @Description: TODO
 * @author: MT
 * @date: 2017年5月10日 下午3:25:50
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .any())
                .paths(PathSelectors.any()).build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("全景餐厅").description("详细设计文档——可视化接口文档")
                .termsOfServiceUrl("http://localhost:8080/").version("1.0")
                .build();
    }
}
