package co.com.crediya.api;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import co.com.crediya.api.config.UsuarioPath;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RouterRest {

  private final UsuarioPath usuarioPath;
  private final Handler usuarioHandler;

  @Bean
  public RouterFunction<ServerResponse> routerFunction(Handler handler) {
    return route(GET(usuarioPath.getUsuarios()), usuarioHandler::listenGetAllUsuarios)
        .andRoute(GET(usuarioPath.getUsuarioById()), usuarioHandler::listenGetUsuario)
        .andRoute(POST(usuarioPath.getUsuarios()), usuarioHandler::listenSaveUsuario)
        .andRoute(PUT(usuarioPath.getUsuarios()), usuarioHandler::listenUpdateUsuario)
        .andRoute(DELETE(usuarioPath.getUsuarios()), usuarioHandler::listenSaveUsuarios);
  }
}
