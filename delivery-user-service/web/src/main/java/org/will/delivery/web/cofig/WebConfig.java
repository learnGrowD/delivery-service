package org.will.delivery.web.cofig;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.WebExceptionHandler;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Configuration
public class WebConfig {

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
