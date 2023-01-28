package co.zip.candidate.userapi.service.impl;

import co.zip.candidate.userapi.dto.accountDto.AccountResponse;
import co.zip.candidate.userapi.dto.accountDto.CreateAccountRequest;
import co.zip.candidate.userapi.exception.AccountAlreadyExistException;
import co.zip.candidate.userapi.exception.InsufficientMonthlyDepositException;
import co.zip.candidate.userapi.mapper.AccountMapper;
import co.zip.candidate.userapi.model.Account;
import co.zip.candidate.userapi.model.User;
import co.zip.candidate.userapi.repository.AccountRepository;
import co.zip.candidate.userapi.service.AccountService;
import co.zip.candidate.userapi.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  private final UserService userService;

  private final AccountMapper accountMapper;

  @Override
  public AccountResponse createAccount(CreateAccountRequest createAccountRequest) {

    User user =
        userService.validateUserAccountRequirements(createAccountRequest.getUserId())
            .orElseThrow(() -> {
              log.info("create account with user id : {} is rejected",
                  createAccountRequest.getUserId());

              return new InsufficientMonthlyDepositException(
                  String.format("User: %d create account failed",
                      createAccountRequest.getUserId()));
            });
    checkAccountExistByContactNumber(createAccountRequest.getContactNumber());

    Account account = accountMapper.toEntity(createAccountRequest);
    account.setAccountNumber(generateAccountNumber(user));
    account.setUser(user);

    return accountMapper.toResponse(accountRepository.save(account));
  }

  @Override
  public List<AccountResponse> listAccountsById(Long userId) {
    return accountRepository.findByUserId(userId).stream()
        .map(accountMapper::toResponse).collect(Collectors.toList());
  }

  @Override
  public List<AccountResponse> ListAllAccounts() {
    return accountRepository.findAll().stream().map(accountMapper::toResponse)
        .collect(Collectors.toList());
  }

  private void checkAccountExistByContactNumber(String contactNumber) {
    if (accountRepository.existsByContactNumber(contactNumber)) {

      log.info("User with Contact number: {} already existed", contactNumber);

      throw new AccountAlreadyExistException(
          String.format("Account with contact number: %s already exists", contactNumber));

    }
  }

  private String generateAccountNumber(User user) {

    return (String.valueOf(System.currentTimeMillis())
        + user.getAccounts().size()
        + user.getEmail().length()).substring(5, 16);
  }
}
