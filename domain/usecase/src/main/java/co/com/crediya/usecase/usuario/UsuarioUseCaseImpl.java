package co.com.crediya.usecase.usuario;

import co.com.crediya.model.role.Roles;
import co.com.crediya.model.usuario.Usuario;
import co.com.crediya.model.usuario.gateways.UsuarioRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UsuarioUseCaseImpl implements UsuarioUseCase {

  private final UsuarioRepository usuarioRepository;

  @Override
  public Mono<Usuario> registrar(Usuario usuario) {
    if (usuario.getIdRol() == null) {
      usuario.setIdRol(Roles.CLIENT.getId());
    }

    return validarDatos(usuario).then(usuarioRepository.save(usuario));
  }

  @Override
  public Mono<Usuario> obtenerUsuario(UUID id) {
    return usuarioRepository.findById(id);
  }

  @Override
  public Flux<Usuario> obtenerUsuarios() {
    return usuarioRepository.findAll();
  }

  private Mono<Void> validarDatos(Usuario usuario) {
    Mono<Void> emailCheck =
        usuarioRepository
            .findByEmail(String.valueOf(usuario.getCorreoElectronico()))
            .flatMap(
                existing ->
                    Mono.<Usuario>error(
                        new IllegalArgumentException("El correo electrónico ya está registrado")))
            .then();

    Mono<Void> documentIdCheck =
        usuarioRepository
            .findByDocument(usuario.getDocumentoIdentidad())
            .flatMap(
                existing ->
                    Mono.<Usuario>error(
                        new IllegalArgumentException("El documento ya está registrado")))
            .then();

    return Mono.when(emailCheck, documentIdCheck);
  }
}
