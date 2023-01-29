package co.zip.candidate.userapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import co.zip.candidate.userapi.exception.AccountAlreadyExistException;
import co.zip.candidate.userapi.exception.InsufficientMonthlyDepositException;
import co.zip.candidate.userapi.mapper.AccountMapper;
import co.zip.candidate.userapi.mapper.AccountMapperImpl;
import co.zip.candidate.userapi.repository.AccountRepository;
import co.zip.candidate.userapi.service.impl.AccountServiceImpl;
import co.zip.candidate.userapi.util.AccountTestHelper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
  @Mock
  private AccountRepository accountRepository;
  @Mock
  private UserService userService;

  private AccountService accountService;

  @BeforeEach
  public void setUp() {
    AccountMapper accountMapper = new AccountMapperImpl();
    accountService = new AccountServiceImpl(accountRepository, userService, accountMapper);
  }

  @Test
  public void givenCreateAccountRequest_whenCreateAccount_shouldReturnAccountResponse() {
    when(userService.validateUserAccountRequirements(anyLong())).thenReturn(
        Optional.ofNullable(AccountTestHelper.createUser()));
    when(accountRepository.existsByContactNumber(any())).thenReturn(false);
    when(accountRepository.save(any())).thenReturn(AccountTestHelper.createAccount());

    assertEquals("address",
        accountService.createAccount(AccountTestHelper.createAccountRequest()).getBillingAddress());

  }

  @Test
  public void givenCreateAccountRequest_whenCreateAccount_shouldThrowsInsufficientMonthlyDepositException() {
    when(userService.validateUserAccountRequirements(anyLong())).thenReturn(Optional.empty());
    assertThrows(InsufficientMonthlyDepositException.class,
        () -> accountService.createAccount(AccountTestHelper.createAccountRequest()));
  }

  @Test
  public void givenCreateAccountRequest_whenCreateAccount_shouldThrowsAccountAlreadyExistException() {
    when(userService.validateUserAccountRequirements(anyLong())).thenReturn(
        Optional.ofNullable(AccountTestHelper.createUser()));
    when(accountRepository.existsByContactNumber(any())).thenReturn(true);
    assertThrows(AccountAlreadyExistException.class,
        () -> accountService.createAccount(AccountTestHelper.createAccountRequest()));
  }

  @Test
  public void givenUserId_whenListAccountsById_shouldReturnListAccounts() {
    when(accountRepository.findByUserId(anyLong())).thenReturn(
        List.of(AccountTestHelper.createAccount()));
    when(userService.getUserById(anyLong())).thenReturn(AccountTestHelper.createUserResponse());

    assertEquals("address",
        accountService.listAccountsById(anyLong()).getAccountList().get(0).getBillingAddress());
  }

  @Test
  public void givenNull_whenListAllAccounts_shouldReturnListAccountsDto() {
    when(accountRepository.findDistinctUserIds()).thenReturn(List.of(1L));
    when(accountRepository.findByUserId(anyLong())).thenReturn(
        List.of(AccountTestHelper.createAccount()));
    when(userService.getUserById(anyLong())).thenReturn(AccountTestHelper.createUserResponse());

    assertEquals("test1", accountService.ListAllAccounts().get(0).getUserName());
  }
}
