package co.zip.candidate.userapi.service;

import co.zip.candidate.userapi.dto.accountDto.AccountResponse;
import co.zip.candidate.userapi.dto.accountDto.CreateAccountRequest;
import java.util.List;

public interface AccountService {

  AccountResponse createAccount(CreateAccountRequest createAccountRequest);

  List<AccountResponse> listAccountsById(Long userId);

  List<AccountResponse> ListAllAccounts();
}
