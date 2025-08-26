package co.com.crediya.api;

import co.com.crediya.api.dto.RegistrarUsuarioDTO;
import co.com.crediya.usecase.usuario.RegistrarUsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

  private final RegistrarUsuarioUseCase registrarUsuarioUseCase;

  public Mono<ServerResponse> listenGetUsuario(ServerRequest serverRequest) {
    // useCase.logic();
    return ServerResponse.ok().bodyValue("Sam Alderson");
  }

  public Mono<ServerResponse> listenGetAllUsuarios(ServerRequest serverRequest) {
    // useCase.logic();
    return ServerResponse.ok().bodyValue("");
  }

  public Mono<ServerResponse> listenSaveUsuario(ServerRequest serverRequest) {
    return serverRequest
        .bodyToMono(RegistrarUsuarioDTO.class)
        .doOnError(Throwable::printStackTrace)
        .flatMap(dto -> registrarUsuarioUseCase.registrar(dto.toDomain()))
        .flatMap(
            usuarioRegistrado ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(usuarioRegistrado));

    //    return serverRequest
    //        .bodyToMono(String.class)
    //        .doOnNext(body -> System.out.println("DEBUG >>> JSON recibido: " + body))
    //        .doOnError(Throwable::printStackTrace)
    //        .flatMap(
    //            usuarioRegistrado ->
    //                ServerResponse.ok()
    //                    .contentType(MediaType.APPLICATION_JSON) // mejor JSON, no EVENT_STREAM
    //                    .bodyValue(usuarioRegistrado));

    //    return serverRequest
    //        .bodyToMono(RegistrarUsuarioDTO.class)
    //        .flatMap(dto -> registrarUsuarioUseCase.registrar(dto.toDomain()))
    //        .flatMap(usuario -> ServerResponse.ok().bodyValue(usuario))
    //        .onErrorResume(e -> ServerResponse.badRequest().bodyValue(e.getMessage()));
  }

  public Mono<ServerResponse> listenUpdateUsuario(ServerRequest serverRequest) {
    // useCase.logic();
    return ServerResponse.ok().bodyValue("");
  }

  public Mono<ServerResponse> listenSaveUsuarios(ServerRequest serverRequest) {
    return ServerResponse.ok().bodyValue("");
  }
}
