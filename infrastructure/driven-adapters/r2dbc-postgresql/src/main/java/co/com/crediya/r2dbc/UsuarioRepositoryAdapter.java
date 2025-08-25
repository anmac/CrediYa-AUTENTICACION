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
  public UsuarioRepositoryAdapter(UsuarioReactiveRepository repository, ObjectMapper mapper) {
    /**
     * Could be use mapper.mapBuilder if your domain model implement builder pattern
     * super(repository, mapper, d ->
     * mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build()); Or using mapper.map with
     * the class of the object model
     */
    super(repository, mapper, entity -> mapper.map(entity, Usuario.class));
  }

  @Override
  public Mono<Usuario> findById(UUID id) {
    return null;
  }

  @Override
  public Mono<Usuario> findByEmail(String email) {
    return null;
  }

  @Override
  public Mono<Void> deleteById(UUID id) {
    return null;
  }
}
