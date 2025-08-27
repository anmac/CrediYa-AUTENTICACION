package co.com.crediya.model.role;

import java.util.UUID;
import lombok.Getter;

@Getter
public enum Roles {
  ADMIN(UUID.fromString("8b87f702-e747-4075-83cc-fbc167b79870")),
  CLIENT(UUID.fromString("e5cc3e4a-4c8b-468e-ab24-3fd9e79cf245"));

  private final UUID id;

  Roles(UUID id) {
    this.id = id;
  }
}
