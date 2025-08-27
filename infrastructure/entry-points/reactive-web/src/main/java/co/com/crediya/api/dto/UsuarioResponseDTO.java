package co.com.crediya.api.dto;

import co.com.crediya.model.usuario.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record UsuarioResponseDTO(
    @Schema(description = "ID del usuario", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        UUID idUsuario,
    @Schema(description = "Nombre(s) del usuario", example = "John") String nombres,
    @Schema(description = "Apellido(s) del usuario", example = "Doe") String apellidos,
    @Schema(description = "Fecha de nacimiento del usuario", example = "1998-05-24")
        LocalDate fechaNacimiento,
    @Schema(description = "Direccion del usuario", example = "Av. Neverland") String direccion,
    @Schema(description = "Telefono del usuario", example = "123456890") String telefono,
    @Schema(description = "Correo electronico del usuario", example = "test@correo.com")
        String correoElectronico,
    @Schema(description = "Salario base del usuario", example = "1234500.0") BigDecimal salarioBase,
    @Schema(description = "Documento de identidad del usuario", example = "123456789")
        String documentoIdentidad,
    @Schema(
            description = "ID del rol del usuario",
            example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
        UUID idRol) {

  public static UsuarioResponseDTO fromDomain(Usuario u) {
    return new UsuarioResponseDTO(
        u.getIdUsuario(),
        u.getNombres(),
        u.getApellidos(),
        u.getFechaNacimiento(),
        u.getDireccion(),
        u.getTelefono(),
        u.getCorreoElectronico() != null ? u.getCorreoElectronico().value() : null,
        u.getSalarioBase() != null ? u.getSalarioBase().value() : null,
        u.getDocumentoIdentidad(),
        u.getIdRol());
  }
}
