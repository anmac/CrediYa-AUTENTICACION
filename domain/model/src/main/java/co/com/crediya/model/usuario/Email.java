package co.com.crediya.model.usuario;

import java.util.regex.Pattern;

public record Email(String value) {

  private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");

  // RFC 5322 -> doesn’t allow the pipe character (|) and single quote (‘)
  private static final Pattern EMAIL_PATTERN2 =
      Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

  public Email {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("El correo no puede estar vacío");
    }
    if (!EMAIL_PATTERN.matcher(value).matches()) {
      throw new IllegalArgumentException("Formato de correo inválido: " + value);
    }
  }
}
