package com.lightside.fg.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Lists;
import com.lightside.fg.web.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.WildcardType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author Ummers
 */

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = "com.lightside.fg.web.controller")
@Slf4j
public class SwaggerConfig extends WebMvcConfigurerAdapter {
    static final String title = "FG Cart Service Rest APIs";
    static final String description = "APIs providing all features of the application via REST endpoints.";

    @Autowired
    private Environment environment;

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    UiConfiguration uiConfig() {
        return UiConfiguration.DEFAULT;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/swagger-ui.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    @Bean
    public Docket fgCartApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("fgcartservice")
                .apiInfo(getApiInfo())
                .select()
                .paths(regex("/api/v1.*"))
                .build()
//                .globalOperationParameters(commonHeaderParameters())
                .alternateTypeRules(getAlternateRule())
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .additionalModels(typeResolver.resolve(ErrorResponse.class))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, Lists.newArrayList(
                        getDefaultResponseMessages()))
                .enableUrlTemplating(false);
    }

    private AlternateTypeRule getAlternateRule() {
        return new AlternateTypeRule(
                typeResolver.resolve(DeferredResult.class,
                        typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                typeResolver.resolve(WildcardType.class));
    }

    private List<Parameter> commonHeaderParameters() {

        List<Parameter> headerParamList = new ArrayList<>();
        headerParamList.add(new ParameterBuilder().name("apiKey").description("Api Key").modelRef(
                new ModelRef("string")).parameterType("header").required(true).build());
        return headerParamList;
    }

    private List<ResponseMessage> getDefaultResponseMessages() {
        List<ResponseMessage> defaultResponseMessages = Lists.newArrayList();
        defaultResponseMessages.add(new ResponseMessageBuilder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .responseModel(new ModelRef("ErrorResponse")).build());
        defaultResponseMessages.add(new ResponseMessageBuilder()
                .code(HttpStatus.NOT_FOUND.value())
                .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .responseModel(new ModelRef("ErrorResponse")).build());
        defaultResponseMessages.add(new ResponseMessageBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .responseModel(new ModelRef("ErrorResponse")).build());
        return defaultResponseMessages;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .version(getApiVersion())
                .build();
    }

    private String getHost() {
        String[] hosts = environment.getProperty("vcap.application.application_uris", String[].class);
        return isEmpty(hosts) ? "localhost" : hosts[0];
    }

    private String getApiVersion() {
        return environment.getProperty("service.api.version", "1.0");
    }
}
