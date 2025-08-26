package co.com.crediya.r2dbc;

import co.com.crediya.r2dbc.entity.UsuarioEntity;
import java.util.UUID;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UsuarioReactiveRepository
    extends ReactiveCrudRepository<UsuarioEntity, UUID>,
        ReactiveQueryByExampleExecutor<UsuarioEntity> {

  Mono<UsuarioEntity> findByCorreoElectronico(String email);
}
