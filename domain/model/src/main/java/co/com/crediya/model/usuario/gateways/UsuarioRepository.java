package co.com.crediya.model.usuario.gateways;

import co.com.crediya.model.usuario.Usuario;
import java.util.UUID;
import reactor.core.publisher.Mono;

public interface UsuarioRepository {

  Mono<Usuario> save(Usuario usuario);

  Mono<Usuario> findById(UUID id);

  Mono<Usuario> findByEmail(String email);

  Mono<Usuario> findByDocument(String document);
}
