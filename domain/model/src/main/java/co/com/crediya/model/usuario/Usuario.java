package co.com.crediya.model.usuario;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
  private UUID idUsuario;
  private String nombres;
  private String apellidos;
  private LocalDate fechaNacimiento;
  private String direccion;
  private String telefono;
  private Email correoElectronico;
  private Salary salarioBase;
  private String documentoIdentidad;
  private UUID idRol;
}
