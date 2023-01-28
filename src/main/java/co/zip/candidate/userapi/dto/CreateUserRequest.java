package co.zip.candidate.userapi.dto;

import java.math.BigDecimal;
import java.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

  private String name;

  private String email;

  private BigDecimal monthlySalary;

  private BigDecimal monthlyExpenses;

  private Currency currency;

}
