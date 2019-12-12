package nl.vng.realisatie.haalcentraal.web.controller;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.report.LevelResolver;
import com.atlassian.oai.validator.springmvc.OpenApiValidationFilter;
import com.atlassian.oai.validator.springmvc.OpenApiValidationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class OpenApiValidationConfig implements WebMvcConfigurer {

    private static final Boolean VALIDATE_REQUESTS = true;
    private static final Boolean VALIDATE_RESPONSE = false;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ingeschrevenpersonen/**")
                .setCachePeriod(0)
                .setCacheControl(CacheControl.noStore());
    }

    @Bean
    public OpenApiValidationFilter validationFilter() {
        return new OpenApiValidationFilter(VALIDATE_REQUESTS, VALIDATE_RESPONSE) {
        };
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {

        final OpenApiInteractionValidator.Builder openApiInterceptor = OpenApiInteractionValidator.createFor("static/BevragingIngeschrevenPersoon.yaml");

        openApiInterceptor.withLevelResolver(new LevelResolver.Builder()
//                .withLevel("validation.request.parameter.query.unexpected", ValidationReport.Level.IGNORE)
                .build());

        final OpenApiValidationInterceptor validationInterceptor = new OpenApiValidationInterceptor(openApiInterceptor.build());
        registry.addInterceptor(validationInterceptor).addPathPatterns("/ingeschrevenpersonen/**");
    }

}