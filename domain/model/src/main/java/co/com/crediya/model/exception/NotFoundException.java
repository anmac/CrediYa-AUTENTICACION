package co.com.crediya.model.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

  private final int status;

  public NotFoundException(String message) {
    super(message);
    this.status = 400;
  }

  public NotFoundException(String message, int status) {
    super(message);
    this.status = status;
  }
}
