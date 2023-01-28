package co.zip.candidate.userapi.mapper;

import co.zip.candidate.userapi.dto.accountDto.AccountResponse;
import co.zip.candidate.userapi.dto.accountDto.CreateAccountRequest;
import co.zip.candidate.userapi.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

  Account toEntity(CreateAccountRequest createAccountRequest);

  AccountResponse toResponse(Account account);

}
