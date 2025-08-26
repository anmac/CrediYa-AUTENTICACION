package co.com.crediya.usecase.usuario;

import co.com.crediya.model.usuario.Usuario;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UsuarioUseCase {
  Mono<Usuario> registrar(Usuario usuario);

  Mono<Usuario> obtenerUsuario(UUID id);

  Flux<Usuario> obtenerUsuarios();
}
