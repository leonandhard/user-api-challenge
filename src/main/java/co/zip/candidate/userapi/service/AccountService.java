package co.zip.candidate.userapi.service;

import co.zip.candidate.userapi.dto.accountDto.AccountResponse;
import co.zip.candidate.userapi.dto.accountDto.CreateAccountRequest;
import co.zip.candidate.userapi.dto.accountDto.ListAccountsDto;
import java.util.List;

public interface AccountService {

  AccountResponse createAccount(CreateAccountRequest createAccountRequest);

  ListAccountsDto listAccountsById(Long userId);

  List<ListAccountsDto> ListAllAccounts();
}
