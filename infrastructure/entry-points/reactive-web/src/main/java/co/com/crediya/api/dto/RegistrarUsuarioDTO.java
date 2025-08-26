package co.com.crediya.api.dto;

import co.com.crediya.model.usuario.Email;
import co.com.crediya.model.usuario.Salary;
import co.com.crediya.model.usuario.Usuario;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RegistrarUsuarioDTO(
    String nombres,
    String apellidos,
    LocalDate fechaNacimiento,
    String direccion,
    String telefono,
    String correoElectronico,
    BigDecimal salarioBase,
    String documentoIdentidad,
    UUID idRol) {

  public Usuario toDomain() {
    return Usuario.builder()
        .nombres(nombres)
        .apellidos(apellidos)
        .fechaNacimiento(fechaNacimiento)
        .direccion(direccion)
        .telefono(telefono)
        .correoElectronico(new Email(correoElectronico))
        .salarioBase(new Salary(salarioBase))
        .documentoIdentidad(documentoIdentidad)
        .idRol(idRol)
        .build();
  }
}
