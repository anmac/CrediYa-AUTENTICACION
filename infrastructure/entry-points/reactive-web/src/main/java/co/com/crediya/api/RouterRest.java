package co.com.crediya.api;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import co.com.crediya.api.config.UsuarioPath;
import co.com.crediya.api.dto.RegistrarUsuarioDTO;
import co.com.crediya.api.dto.UsuarioResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RouterRest {

  private final UsuarioPath usuarioPath;
  private final Handler usuarioHandler;

  @Bean
  @RouterOperations(
      value = {
        @RouterOperation(
            path = "/api/v1/usuarios",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            beanClass = Handler.class,
            beanMethod = "listenSaveUsuario",
            operation =
                @Operation(
                    operationId = "registrarUsuario",
                    summary = "Registrar un usuario",
                    requestBody =
                        @RequestBody(
                            required = true,
                            content =
                                @Content(
                                    schema = @Schema(implementation = RegistrarUsuarioDTO.class))),
                    responses = {
                      @ApiResponse(
                          responseCode = "201",
                          description = "Usuario creado",
                          content =
                              @Content(
                                  schema = @Schema(implementation = UsuarioResponseDTO.class))),
                      @ApiResponse(responseCode = "400", description = "Datos inválidos")
                    })),
        @RouterOperation(
            path = "/api/v1/usuarios/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            beanClass = Handler.class,
            beanMethod = "listenGetUsuarioById",
            operation =
                @Operation(
                    operationId = "getUsuarioById",
                    summary = "Obtener un usuario por ID",
                    parameters = {
                      @Parameter(
                          in = ParameterIn.PATH,
                          name = "id",
                          required = true,
                          description = "UUID del usuario")
                    },
                    responses = {
                      @ApiResponse(
                          responseCode = "200",
                          description = "Usuario encontrado",
                          content =
                              @Content(
                                  schema = @Schema(implementation = UsuarioResponseDTO.class))),
                      @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
                    })),
        @RouterOperation(
            path = "/api/v1/usuarios",
            beanClass = Handler.class,
            beanMethod = "listenGetAllUsuarios")
      })
  public RouterFunction<ServerResponse> routerFunction(Handler handler) {
    return route(POST(usuarioPath.getUsuarios()), usuarioHandler::listenSaveUsuario)
        .andRoute(GET(usuarioPath.getUsuarioById()), usuarioHandler::listenGetUsuarioById)
        .andRoute(GET(usuarioPath.getUsuarios()), usuarioHandler::listenGetAllUsuarios);
    //        .andRoute(PUT(usuarioPath.getUsuarios()), usuarioHandler::listenUpdateUsuario);
  }
}
