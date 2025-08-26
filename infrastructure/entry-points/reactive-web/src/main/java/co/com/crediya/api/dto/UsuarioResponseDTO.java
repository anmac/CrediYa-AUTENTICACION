package co.com.crediya.api.dto;

import co.com.crediya.model.usuario.Usuario;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record UsuarioResponseDTO(
    UUID idUsuario,
    String nombres,
    String apellidos,
    LocalDate fechaNacimiento,
    String direccion,
    String telefono,
    String correoElectronico,
    BigDecimal salarioBase,
    String documentoIdentidad,
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
