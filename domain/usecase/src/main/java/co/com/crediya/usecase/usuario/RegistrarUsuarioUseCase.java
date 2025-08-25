package co.com.crediya.usecase.usuario;

import co.com.crediya.model.usuario.Usuario;
import co.com.crediya.model.usuario.gateways.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RegistrarUsuarioUseCase {

  private final UsuarioRepository usuarioRepository;

  public Mono<Usuario> registrar(Usuario usuario) {
    return null;
  }

  private Mono<Void> validarDatos(Usuario usuario) {
    return null;
  }
}
