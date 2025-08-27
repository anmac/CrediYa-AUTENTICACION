package co.com.crediya.api.dto;

import co.com.crediya.model.usuario.Email;
import co.com.crediya.model.usuario.Salary;
import co.com.crediya.model.usuario.Usuario;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record RegistrarUsuarioDTO(
    @NotBlank(message = "El nombre es obligatorio") String nombres,
    @NotBlank(message = "El apellido es obligatorio") String apellidos,
    @NotNull(message = "La fecha de nacimiento no puede estar vacia") @Past
        LocalDate fechaNacimiento,
    @NotBlank(message = "La direccion es obligatoria") String direccion,
    @NotBlank(message = "El telefono es obligatorio")
        @Size(min = 8, max = 20, message = "El telefono no debe tener entre 8 y 20 digitos")
        String telefono,
    @jakarta.validation.constraints.Email String correoElectronico,
    @NotNull(message = "El salario no puede estar vacio")
        @DecimalMin(value = "0.0", message = "El salario no puede ser menor a 0")
        @DecimalMax(value = "15000000.0", message = "El salario no puede ser mayor a 15000000")
        BigDecimal salarioBase,
    @NotBlank String documentoIdentidad) {

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
        .build();
  }
}
