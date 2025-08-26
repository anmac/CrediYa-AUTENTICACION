package co.com.crediya.api;

import co.com.crediya.api.dto.RegistrarUsuarioDTO;
import co.com.crediya.api.dto.UsuarioResponseDTO;
import co.com.crediya.usecase.usuario.UsuarioUseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Handler {

  private final UsuarioUseCase usuarioUseCase;

  private final Logger log = LoggerFactory.getLogger(Handler.class);

  public Mono<ServerResponse> listenGetUsuarioById(ServerRequest serverRequest) {
    String id = serverRequest.pathVariable("id");

    return usuarioUseCase
        .obtenerUsuario(UUID.fromString(id))
        .flatMap(
            usuario ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(UsuarioResponseDTO.fromDomain(usuario)))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  public Mono<ServerResponse> listenGetAllUsuarios(ServerRequest serverRequest) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(
            usuarioUseCase.obtenerUsuarios().map(UsuarioResponseDTO::fromDomain),
            UsuarioResponseDTO.class);
  }

  public Mono<ServerResponse> listenSaveUsuario(ServerRequest serverRequest) {
    return serverRequest
        .bodyToMono(RegistrarUsuarioDTO.class)
        .doOnError(Throwable::printStackTrace)
        .flatMap(dto -> usuarioUseCase.registrar(dto.toDomain()))
        .flatMap(
            usuarioRegistrado ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(UsuarioResponseDTO.fromDomain(usuarioRegistrado)));
  }

  //  public Mono<ServerResponse> listenUpdateUsuario(ServerRequest serverRequest) {
  //    return ServerResponse.ok().bodyValue("");
  //  }
  //
  //  public Mono<ServerResponse> listenSaveUsuarios(ServerRequest serverRequest) {
  //    return ServerResponse.ok().bodyValue("");
  //  }
}
