package co.com.crediya.r2dbc.converter;

import co.com.crediya.model.usuario.Email;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@Component
public class EmailConverters {

  @ReadingConverter
  public static class StringToEmailConverter implements Converter<String, Email> {

    @Override
    public Email convert(String source) {
      if (source.trim().isEmpty()) {
        return null;
      }

      if (source.startsWith("Email[value=") && source.endsWith("]")) {
        source = source.substring(12, source.length() - 1);
      }

      return new Email(source);
    }
  }

  @WritingConverter
  public static class EmailToStringConverter implements Converter<Email, String> {

    @Override
    public String convert(Email source) {
      return source.value();
    }
  }
}
