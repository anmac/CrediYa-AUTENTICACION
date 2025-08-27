package co.com.crediya.usecase.usuario;

import co.com.crediya.model.exception.BusinessException;
import co.com.crediya.model.exception.NotFoundException;
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
    return usuarioRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new NotFoundException("Usuario no encontrado con id: " + id)));
  }

  @Override
  public Flux<Usuario> obtenerUsuarios() {
    return usuarioRepository.findAll();
  }

  private Mono<Void> validarDatos(Usuario usuario) {
    Mono<Void> emailCheck =
        usuarioRepository
            .findByEmail(usuario.getCorreoElectronico().value())
            .flatMap(
                existing ->
                    Mono.<Usuario>error(
                        new BusinessException("El correo electrónico ya está registrado")))
            .then();

    Mono<Void> documentIdCheck =
        usuarioRepository
            .findByDocument(usuario.getDocumentoIdentidad())
            .flatMap(
                existing ->
                    Mono.<Usuario>error(
                        new BusinessException("El documento de identidad ya está registrado")))
            .then();

    return Mono.when(emailCheck, documentIdCheck);
  }
}
