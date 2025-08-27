package co.com.crediya.api.exception;

import co.com.crediya.model.exception.BusinessException;
import co.com.crediya.model.exception.NotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.resource.NoResourceFoundException;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

  private static final Logger log = LoggerFactory.getLogger(CustomErrorAttributes.class);

  @Override
  public Map<String, Object> getErrorAttributes(
      ServerRequest request, ErrorAttributeOptions options) {
    Throwable error = getError(request);

    int status = 500;
    String errorCode = "Internal Server Error";
    String message = error.getMessage();

    switch (error) {
      case BusinessException be -> {
        status = be.getStatus();
        errorCode = "Business Error";
        message = be.getMessage();
      }
      case NotFoundException nfe -> {
        status = 404;
        errorCode = "Not Found";
        message = nfe.getMessage();
      }
      case NoResourceFoundException nfe -> {
        status = 404;
        errorCode = "Not Found";
        message = nfe.getMessage();
      }
      default -> log.error("Error inesperado: ", error);
    }
    //    if (error instanceof IllegalArgumentException) {
    //      status = 400;
    //      errorCode = "Bad Request";
    //    }

    Map<String, Object> errorAttributes = new HashMap<>();
    errorAttributes.put("status", status);
    errorAttributes.put("error", errorCode);
    errorAttributes.put("message", message);
    errorAttributes.put("path", request.path());
    errorAttributes.put("timestamp", LocalDateTime.now().toString());

    return errorAttributes;
  }
}
