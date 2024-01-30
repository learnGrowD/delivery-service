package org.will.delivery.api.cofig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebFluxConfigurer {

    @Bean
    public WebFilter webFilter() {
        return ((exchange, chain) -> {
            System.out.println("Before processing request");

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                System.out.println("After processing request");
            }));
        });
    }
}
