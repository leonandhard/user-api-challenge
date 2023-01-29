package co.zip.candidate.userapi.controller;

import co.zip.candidate.userapi.dto.accountDto.AccountResponse;
import co.zip.candidate.userapi.dto.accountDto.CreateAccountRequest;
import co.zip.candidate.userapi.dto.accountDto.ListAccountsDto;
import co.zip.candidate.userapi.service.AccountService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AccountResponse createNewAccountWithUser(
      @RequestBody @Valid CreateAccountRequest createAccountRequest) {
    return accountService.createAccount(createAccountRequest);
  }

  @GetMapping("/{userId}")
  public ListAccountsDto getAccountsList(@PathVariable Long userId) {
    return accountService.listAccountsById(userId);
  }

  @GetMapping
  public List<ListAccountsDto> getAccountsList() {
    return accountService.ListAllAccounts();
  }
}
