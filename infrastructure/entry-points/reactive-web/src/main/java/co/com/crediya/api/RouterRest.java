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
    return route(POST(usuarioPath.getUsuarios()), usuarioHandler::listenSaveUsuario)
        .andRoute(GET(usuarioPath.getUsuarioById()), usuarioHandler::listenGetUsuarioById)
        .andRoute(GET(usuarioPath.getUsuarios()), usuarioHandler::listenGetAllUsuarios);
    //        .andRoute(PUT(usuarioPath.getUsuarios()), usuarioHandler::listenUpdateUsuario);
  }
}
