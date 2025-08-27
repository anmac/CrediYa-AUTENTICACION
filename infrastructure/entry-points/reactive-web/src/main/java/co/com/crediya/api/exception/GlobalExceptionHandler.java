package co.com.crediya.api.exception;

import java.util.Map;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

@Component
@Order(-2) // prioridad antes del handler default
public class GlobalExceptionHandler extends AbstractErrorWebExceptionHandler {

  public GlobalExceptionHandler(
      CustomErrorAttributes errorAttributes,
      WebProperties webProperties,
      ApplicationContext applicationContext,
      ServerCodecConfigurer codecConfigurer) {
    super(errorAttributes, webProperties.getResources(), applicationContext);
    this.setMessageReaders(codecConfigurer.getReaders());
    this.setMessageWriters(codecConfigurer.getWriters());
  }

  @Override
  protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
    return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
  }

  private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
    Map<String, Object> errorProps = getErrorAttributes(request, ErrorAttributeOptions.defaults());
    int status = (int) errorProps.get("status");

    return ServerResponse.status(status)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(errorProps);
  }
}
