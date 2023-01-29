package co.zip.candidate.userapi.dto.userDto;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
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
public class UserResponse {

  private Long id;
  private String name;
  private String email;
  private BigDecimal monthlySalary;
  private BigDecimal monthlyExpense;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;

}
