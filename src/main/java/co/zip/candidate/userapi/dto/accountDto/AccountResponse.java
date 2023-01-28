package co.zip.candidate.userapi.dto.accountDto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

  private Long id;
  private String accountNumber;
  private BigDecimal deposit;
  private BigDecimal credits;
  private String billingAddress;
  private String contactNumber;
  private Currency currency;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
}
