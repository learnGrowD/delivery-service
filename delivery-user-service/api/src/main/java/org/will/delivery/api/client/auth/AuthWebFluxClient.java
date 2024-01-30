package org.will.delivery.api.client.auth;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.will.delivery.api.client.WebFluxClientIfs;
import org.willd.delivery.common.api.Api;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AuthWebFluxClient implements WebFluxClientIfs {
    private final String baseUrl = "http://localhost:8082";
    private final WebClient webClient = WebClient.create(baseUrl);

    @Override
    public <R, B> Mono<Api<B>> postToMono(String uri, R request) {
        return webClient.post()
                .uri(uri)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});
    }

    @Override
    public <R, B> Flux<Api<B>> postToFlux(String uri, R request) {
        return webClient.post()
                .uri(uri)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<Api<B>>() {});
    }

    @Override
    public <B> Mono<Api<B>> getToMono(String uri, MultiValueMap<String, String> requestMap) {
        return webClient.get()
                .uri(uriBuilder -> {
                    return uriBuilder
                            .path(uri)
                            .queryParams(requestMap)
                            .build();
                })
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Api<B>>() {});
    }
    @Override
    public <B> Flux<Api<B>> getToFlux(String uri, MultiValueMap<String, String> requestMap) {
        return webClient.get()
                .uri(uriBuilder -> {
                    return uriBuilder
                            .path(uri)
                            .queryParams(requestMap)
                            .build();
                })
                .retrieve()
                .bodyToFlux(new ParameterizedTypeReference<Api<B>>() {});
    }
}
