package co.zip.candidate.userapi.util;

import co.zip.candidate.userapi.dto.userDto.CreateUserRequest;
import co.zip.candidate.userapi.model.User;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Currency;

public class UserTestHelper {

  public static CreateUserRequest createUserRequest() {
    return CreateUserRequest.builder()
        .currency(Currency.getInstance("AUD"))
        .email("test@gmail.com")
        .monthlySalary(BigDecimal.valueOf(2000))
        .monthlyExpenses(BigDecimal.valueOf(1000))
        .name("name")
        .build();
  }

  public static User createUser_1() {
    return User
        .builder()
        .id(1L)
        .email("test1@gmail.com")
        .currency(Currency.getInstance("AUD"))
        .monthlySalary(BigDecimal.valueOf(2000))
        .monthlyExpenses(BigDecimal.valueOf(1000))
        .name("test1")
        .createdAt(OffsetDateTime.now())
        .updatedAt(OffsetDateTime.now())
        .build();
  }

  public static User createUser_2() {
    return User
        .builder()
        .id(2L)
        .email("test2@gmail.com")
        .currency(Currency.getInstance("AUD"))
        .monthlySalary(BigDecimal.valueOf(2000))
        .monthlyExpenses(BigDecimal.valueOf(1000))
        .name("test2")
        .createdAt(OffsetDateTime.now())
        .updatedAt(OffsetDateTime.now())
        .build();
  }
}
