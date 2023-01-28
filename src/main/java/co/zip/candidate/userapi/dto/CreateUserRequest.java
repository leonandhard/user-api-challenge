package co.zip.candidate.userapi.dto;

import java.math.BigDecimal;
import java.util.Currency;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

  @NotBlank(message = "User's name can not be null")
  @Size(min = 2, message = "The length of name cannot less than 2")
  private String name;

  @NotBlank(message = "User's email can not be null")
  @Email(message = "Email's format is not correct")
  @Size(max = 50, message = "The length of email cannot over 50")
  private String email;

  @NotNull(message = "User's monthly salary can not be null")
  @DecimalMin(value = "0.00", message = "User's monthly salary can not be less than 0",
      inclusive = false)
  private BigDecimal monthlySalary;

  @NotNull(message = "User's monthly expenses can not be null")
  @DecimalMin(value = "0.00", message = "User's monthly expenses can not be less than 0",
      inclusive = false)
  private BigDecimal monthlyExpenses;

  @NotNull(message = "User's currency can not be null")
  private Currency currency;

}
