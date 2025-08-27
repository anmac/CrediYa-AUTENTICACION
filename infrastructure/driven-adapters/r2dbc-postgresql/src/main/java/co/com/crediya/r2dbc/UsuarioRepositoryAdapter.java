package co.com.crediya.r2dbc;

import co.com.crediya.model.usuario.Usuario;
import co.com.crediya.model.usuario.gateways.UsuarioRepository;
import co.com.crediya.r2dbc.entity.UsuarioEntity;
import co.com.crediya.r2dbc.helper.ReactiveAdapterOperations;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class UsuarioRepositoryAdapter
    extends ReactiveAdapterOperations<Usuario, UsuarioEntity, UUID, UsuarioReactiveRepository>
    implements UsuarioRepository {

  private final UsuarioReactiveRepository repository;

  public UsuarioRepositoryAdapter(UsuarioReactiveRepository repository, ObjectMapper mapper) {
    super(repository, mapper, entity -> mapper.map(entity, Usuario.class));
    this.repository = repository;
  }

  @Override
  public Mono<Usuario> findById(UUID id) {
    log.debug("findById - buscando usuario - id={}", id);

    return super.findById(id)
        .doOnSuccess(
            usuario -> {
              if (usuario != null) {
                log.info("findById - usuario encontrado - id={}", id);
              } else {
                log.debug("findById - usuario no encontrado - id={}", id);
              }
            })
        .doOnError(
            error ->
                log.error(
                    "findById - error al buscar usuario - id={} - mensaje={}",
                    id,
                    error.getMessage()));
  }

  @Override
  public Flux<Usuario> findAll() {
    log.debug("findAll - listando usuarios");

    return super.findAll()
        .doOnNext(result -> log.info("findAll - usuarios encontrados - result={}", result))
        .doOnError(
            error ->
                log.error("findAll - error al listar usuarios - mensaje={}", error.getMessage()));
  }

  @Override
  public Mono<Usuario> findByEmail(String email) {
    log.debug("findByEmail - buscando usuario - email={}", email);

    return repository
        .findByCorreoElectronico(email)
        .map(this::toEntity)
        .doOnSuccess(
            usuario -> {
              if (usuario != null) {
                log.info("findByEmail - usuario encontrado - email={}", email);
              } else {
                log.debug("findByEmail - usuario no encontrado - email={}", email);
              }
            })
        .doOnError(
            error ->
                log.error(
                    "findByEmail - error al buscar usuario - email={} - mensaje={}",
                    email,
                    error.getMessage()));
  }

  @Override
  public Mono<Usuario> findByDocument(String document) {
    log.debug("findByDocument - buscando usuario - documento={}", document);

    Usuario usuario = new Usuario();
    usuario.setDocumentoIdentidad(document);

    return findByExample(usuario)
        .next()
        .doOnSuccess(
            found -> {
              if (found != null) {
                log.info("findByDocument - usuario encontrado - documento={}", document);
              } else {
                log.debug("findByDocument - usuario no encontrado - documento={}", document);
              }
            })
        .doOnError(
            error ->
                log.error(
                    "findByDocument - error al buscar usuario - documento={} - mensaje={}",
                    document,
                    error.getMessage()));
  }

  @Override
  public Mono<Usuario> save(Usuario entity) {
    return super.save(entity)
        .doOnNext(
            usuario -> log.info("save - usuario registrado con id={}", usuario.getIdUsuario()));
  }
}
