package co.com.crediya.r2dbc;

import co.com.crediya.model.usuario.Usuario;
import co.com.crediya.model.usuario.gateways.UsuarioRepository;
import co.com.crediya.r2dbc.entity.UsuarioEntity;
import co.com.crediya.r2dbc.helper.ReactiveAdapterOperations;
import java.util.UUID;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

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
  public Mono<Usuario> findByEmail(String email) {
    return repository.findByCorreoElectronico(email).map(this::toEntity);
  }

  @Override
  public Mono<Usuario> findByDocument(String document) {
    return repository.findByCorreoElectronico(document).map(this::toEntity);
  }
}
