package co.com.crediya.api.config;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LogginWebFilter implements WebFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    ServerHttpRequest request = exchange.getRequest();
    ServerHttpResponse response = exchange.getResponse();

    String traceId = UUID.randomUUID().toString();
    long startTime = System.currentTimeMillis();

    log.info("[{}] Incoming request: {} {}", traceId, request.getMethod(), request.getURI());

    return chain
        .filter(exchange)
        .doOnSuccess(
            done -> {
              long duration = System.currentTimeMillis() - startTime;
              log.info("[{}] Response: {} ({} ms)", traceId, response.getStatusCode(), duration);
            })
        .doOnError(
            error -> {
              long duration = System.currentTimeMillis() - startTime;
              log.error(
                  "[{}] Error processing request ({} ms): {}",
                  traceId,
                  duration,
                  error.getMessage());
            });
  }
}
