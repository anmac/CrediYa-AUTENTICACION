package co.com.crediya.r2dbc.entity;

import co.com.crediya.model.usuario.Email;
import co.com.crediya.model.usuario.Salary;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioEntity {

  @Id
  @Column("id_usuario")
  private UUID idUsuario;

  private String nombres;
  private String apellidos;

  @Column("fecha_nacimiento")
  private LocalDate fechaNacimiento;

  private String direccion;
  private String telefono;

  @Column("correo_electronico")
  private Email correoElectronico;

  @Column("salario_base")
  private Salary salarioBase;

  @Column("documento_identidad")
  private String documentoIdentidad;

  @Column("id_rol")
  private UUID idRol;
}
