package org.will.delivery.api.client;

import org.springframework.util.MultiValueMap;
import org.willd.delivery.common.api.Api;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WebFluxClientIfs {
    <R, B> Mono<Api<B>> postToMono(String uri, R request);

    <R, B> Flux<Api<B>> postToFlux(String uri, R request);

    <B> Mono<Api<B>> getToMono(String uri, MultiValueMap<String, String> requestMap);

    <B> Flux<Api<B>> getToFlux(String uri, MultiValueMap<String, String> requestMap);
}
