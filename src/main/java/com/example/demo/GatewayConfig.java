package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.gateway.mvc.config.ProxyExchangeArgumentResolver;
import org.springframework.cloud.gateway.mvc.config.ProxyProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
  private final RestTemplateBuilder restTemplateBuilder;

  @Bean
  public ProxyExchangeArgumentResolver proxyExchangeArgumentResolver(ProxyProperties proxy) {
    ProxyExchangeArgumentResolver resolver = new ProxyExchangeArgumentResolver(proxyRestTemplate());
    resolver.setHeaders(proxy.convertHeaders());
    resolver.setAutoForwardedHeaders(proxy.getAutoForward());
    resolver.setSensitive(proxy.getSensitive());
    return resolver;
  }

  @Bean
  protected RestTemplate proxyRestTemplate() {
    return restTemplateBuilder
        .additionalMessageConverters(new FormHttpMessageConverter())
        .build();
  }
}
