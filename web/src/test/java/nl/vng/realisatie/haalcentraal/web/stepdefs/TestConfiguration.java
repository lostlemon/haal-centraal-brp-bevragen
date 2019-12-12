package nl.vng.realisatie.haalcentraal.web.stepdefs;

import lombok.extern.slf4j.Slf4j;
import nl.vng.realisatie.haalcentraal.restclient.generated.api.ApiClient;
import nl.vng.realisatie.haalcentraal.restclient.generated.api.bip.IngeschrevenpersonenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * TestConfiguration
 */
@Configuration
@ComponentScan(basePackageClasses = {IngeschrevenpersonenApi.class, ApiClient.class})
@Slf4j
public class TestConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder(new RestTemplateCustomizer() {

            @Override
            public void customize(final RestTemplate restTemplate) {

                restTemplate.setInterceptors(Arrays.asList(new ClientHttpRequestInterceptor() {
                    @Override
                    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {

                        final ArrayList<MediaType> header = new ArrayList<>(request.getHeaders().getAccept());

                        header.add(MediaType.APPLICATION_JSON);
                        header.add(MediaType.APPLICATION_PROBLEM_JSON);

                        request.getHeaders().setAccept(header.stream().distinct().collect(Collectors.toList()));

                        return execution.execute(request, body);
                    }
                }));
            }
        }).build();
    }

    @Autowired
    public ApiClient apiClient;

    @Bean
    @Scope("cucumber-glue")
    public World world() {
        return new World();
    }

    @PostConstruct
    public void postConstruct() {
        log.info("Configuring basepath apiClient");
        apiClient.setBasePath(Defaults.BASE + Defaults.port);
    }

}
