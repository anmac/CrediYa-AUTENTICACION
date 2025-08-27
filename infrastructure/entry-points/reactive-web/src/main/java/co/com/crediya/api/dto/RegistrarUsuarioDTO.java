package co.com.crediya.api.dto;

import co.com.crediya.model.usuario.Email;
import co.com.crediya.model.usuario.Salary;
import co.com.crediya.model.usuario.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record RegistrarUsuarioDTO(
    @Schema(description = "Nombre(s) del usuario", example = "John")
        @NotBlank(message = "El nombre es obligatorio")
        String nombres,
    @Schema(description = "Apellido(s) del usuario", example = "Doe")
        @NotBlank(message = "El apellido es obligatorio")
        String apellidos,
    @Schema(description = "Fecha de nacimiento del usuario", example = "1998-05-24")
        @NotNull(message = "La fecha de nacimiento no puede estar vacia")
        @Past
        LocalDate fechaNacimiento,
    @Schema(description = "Direccion del usuario", example = "Av. Neverland")
        @NotBlank(message = "La direccion es obligatoria")
        String direccion,
    @Schema(description = "Telefono del usuario", example = "123456890")
        @NotBlank(message = "El telefono es obligatorio")
        @Size(min = 8, max = 20, message = "El telefono no debe tener entre 8 y 20 digitos")
        String telefono,
    @Schema(description = "Correo electronico del usuario", example = "test@correo.com")
        @jakarta.validation.constraints.Email
        String correoElectronico,
    @Schema(description = "Salario base del usuario", example = "1234500.0")
        @NotNull(message = "El salario no puede estar vacio")
        @DecimalMin(value = "0.0", message = "El salario no puede ser menor a 0")
        @DecimalMax(value = "15000000.0", message = "El salario no puede ser mayor a 15000000")
        BigDecimal salarioBase,
    @Schema(description = "Documento de identidad del usuario", example = "123456789") @NotBlank
        String documentoIdentidad) {

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
