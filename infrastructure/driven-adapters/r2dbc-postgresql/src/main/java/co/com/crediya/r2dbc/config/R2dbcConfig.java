package co.com.crediya.r2dbc.config;

import co.com.crediya.r2dbc.converter.EmailConverters;
import co.com.crediya.r2dbc.converter.SalaryConverters;
import io.r2dbc.spi.ConnectionFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(
    basePackages = "co.com.crediya.infrastructure.driven_adapters.r2dbc.repository")
@RequiredArgsConstructor
public class R2dbcConfig extends AbstractR2dbcConfiguration {

  private final ConnectionFactory connectionFactory;

  @Override
  public ConnectionFactory connectionFactory() {
    return connectionFactory;
  }

  @Bean
  @Override
  public R2dbcCustomConversions r2dbcCustomConversions() {
    List<Object> converters = new ArrayList<>();

    converters.add(new EmailConverters.StringToEmailConverter());
    converters.add(new EmailConverters.EmailToStringConverter());
    converters.add(new SalaryConverters.BigDecimalToSalary());
    converters.add(new SalaryConverters.SalaryToBigDecimal());

    return R2dbcCustomConversions.of(getDialect(connectionFactory), converters);
  }
}
