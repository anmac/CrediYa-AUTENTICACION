package co.com.crediya.usecase.usuario;

import co.com.crediya.model.usuario.Usuario;
import co.com.crediya.model.usuario.gateways.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RegistrarUsuarioUseCaseImpl implements RegistrarUsuarioUseCase {

  private final UsuarioRepository usuarioRepository;

  @Override
  public Mono<Usuario> registrar(Usuario usuario) {
    return validarDatos(usuario).then(usuarioRepository.save(usuario));
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
