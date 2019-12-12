package nl.vng.realisatie.haalcentraal.web;

import lombok.extern.slf4j.Slf4j;
import nl.vng.realisatie.haalcentraal.core.config.CoreConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Import({CoreConfig.class})
@SpringBootApplication
public class HaalCentraal extends SpringBootServletInitializer {

    @Bean
    public HandlerInterceptorAdapter redirectInterceptor() {

        return new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
                log.debug("request: " + request.getRequestURI());
                log.debug("request: " + request.getQueryString());
                return super.preHandle(request, response, handler);
            }

            @Override
            public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final Exception ex) throws Exception {
                log.debug("request2: " + request.getRequestURI());
                log.debug("request:2 " + request.getQueryString());
                super.afterCompletion(request, response, handler, ex);
            }
        };
    }

    @Bean
    @Profile("production")
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setAfterMessagePrefix("Request: ");
        return filter;
    }

    public static void main(String[] args) {
        SpringApplication.run(HaalCentraal.class, args);
    }

}