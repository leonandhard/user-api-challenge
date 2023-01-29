package co.zip.candidate.userapi.dto.accountDto;

import java.util.List;
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
public class ListAccountsDto {
  private String userName;
  private List<AccountResponse> accountList;
}
