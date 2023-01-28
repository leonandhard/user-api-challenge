package co.zip.candidate.userapi.dto.accountDto;

import java.util.Currency;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
public class CreateAccountRequest {

  @NotNull(message = "User's id can not be null")
  private Long userId;
  @NotBlank(message = "Account's billing address can not be null")
  private String billingAddress;
  @NotBlank(message = "Account's contact number can not be null")
  @Pattern(regexp = "^0[0-9]{9}|[0-9]{9}$", message = "Incorrect phone number format")
  private String contactNumber;
  @NotNull(message = "User's currency can not be null")
  private Currency currency;

}
