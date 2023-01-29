package co.zip.candidate.userapi.util;

import co.zip.candidate.userapi.dto.accountDto.AccountResponse;
import co.zip.candidate.userapi.dto.accountDto.CreateAccountRequest;
import co.zip.candidate.userapi.dto.accountDto.ListAccountsDto;
import co.zip.candidate.userapi.dto.userDto.UserResponse;
import co.zip.candidate.userapi.model.Account;
import co.zip.candidate.userapi.model.User;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.List;
import java.util.Set;

public class AccountTestHelper {
  public static CreateAccountRequest createAccountRequest() {
    return CreateAccountRequest.builder()
        .billingAddress("address")
        .contactNumber("466266853")
        .currency(Currency.getInstance("AUD"))
        .userId(1L)
        .build();
  }

  public static User createUser() {
    return User
        .builder()
        .id(1L)
        .email("test1@gmail.com")
        .currency(Currency.getInstance("AUD"))
        .monthlySalary(BigDecimal.valueOf(2000))
        .monthlyExpenses(BigDecimal.valueOf(1000))
        .name("test1")
        .accounts(Set.of())
        .createdAt(OffsetDateTime.now())
        .updatedAt(OffsetDateTime.now())
        .build();
  }

  public static UserResponse createUserResponse() {
    return UserResponse
        .builder()
        .id(1L)
        .email("test1@gmail.com")
        .monthlySalary(BigDecimal.valueOf(2000))
        .monthlyExpenses(BigDecimal.valueOf(1000))
        .name("test1")
        .createdAt(OffsetDateTime.now())
        .updatedAt(OffsetDateTime.now())
        .build();
  }

  public static Account createAccount() {
    return Account.builder()
        .id(1L)
        .accountNumber("13785749014")
        .billingAddress("address")
        .contactNumber("466266853")
        .createdAt(OffsetDateTime.now())
        .updatedAt(OffsetDateTime.now())
        .credits(BigDecimal.ZERO)
        .deposit(BigDecimal.ZERO)
        .currency(Currency.getInstance("AUD"))
        .user(User.builder().id(1L).accounts(Set.of()).build())
        .build();
  }

  public static AccountResponse accountResponse() {
    return AccountResponse.builder()
        .id(1L)
        .accountNumber("13785749014")
        .billingAddress("address")
        .contactNumber("466266853")
        .createdAt(OffsetDateTime.now())
        .updatedAt(OffsetDateTime.now())
        .credits(BigDecimal.ZERO)
        .deposit(BigDecimal.ZERO)
        .currency(Currency.getInstance("AUD"))
        .build();
  }

  public static ListAccountsDto listAccountsDto() {
    return ListAccountsDto.builder()
        .userName("username")
        .accountList(List.of(accountResponse()))
        .build();
  }
}
