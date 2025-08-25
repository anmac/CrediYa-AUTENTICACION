package co.com.crediya.model.usuario;

import java.math.BigDecimal;

public record Salary(BigDecimal value) {

  public Salary {
    if (value == null) {
      throw new IllegalArgumentException("El salario no puede ser nulo");
    }
    if (value.compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("El salario debe ser mayor que cero");
    }
    if (value.compareTo(new BigDecimal("15000000")) > 0) {
      throw new IllegalArgumentException("El salario excede el límite permitido");
    }
  }
}
