package co.com.crediya.usecase.usuario;

import co.com.crediya.model.usuario.Usuario;
import reactor.core.publisher.Mono;

public interface RegistrarUsuarioUseCase {
  Mono<Usuario> registrar(Usuario usuario);
}
