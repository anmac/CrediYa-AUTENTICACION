package co.com.crediya.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {
  // private  final UseCase useCase;

  public Mono<ServerResponse> listenGetAllUsuarios(ServerRequest serverRequest) {
    // useCase.logic();
    return ServerResponse.ok().bodyValue("Some Usuarios Problematicas");
  }

  public Mono<ServerResponse> listenGetUsuario(ServerRequest serverRequest) {
    // useCase.logic();
    return ServerResponse.ok().bodyValue("");
  }

  public Mono<ServerResponse> listenSaveUsuario(ServerRequest serverRequest) {
    // useCase2.logic();
    return ServerResponse.ok().bodyValue("");
  }

  public Mono<ServerResponse> listenUpdateUsuario(ServerRequest serverRequest) {
    // useCase.logic();
    return ServerResponse.ok().bodyValue("");
  }

  public Mono<ServerResponse> listenSaveUsuarios(ServerRequest serverRequest) {
    return ServerResponse.ok().bodyValue("");
  }
}
