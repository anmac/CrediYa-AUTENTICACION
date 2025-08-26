package co.com.crediya.model.usuario;

import java.time.LocalDate;
import java.util.UUID;
import lombok.*;

@Getter
@Setter
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
