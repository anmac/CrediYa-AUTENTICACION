package co.com.crediya.r2dbc.converter;

import co.com.crediya.model.usuario.Salary;
import java.math.BigDecimal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@Component
public class SalaryConverters {

  @ReadingConverter
  public static class BigDecimalToSalary implements Converter<BigDecimal, Salary> {

    @Override
    public Salary convert(BigDecimal source) {
      return new Salary(source);
    }
  }

  @WritingConverter
  public static class SalaryToBigDecimal implements Converter<Salary, BigDecimal> {

    @Override
    public BigDecimal convert(Salary source) {
      return source.value();
    }
  }
}
