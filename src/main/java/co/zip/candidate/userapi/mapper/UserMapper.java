package co.zip.candidate.userapi.mapper;

import co.zip.candidate.userapi.dto.userDto.CreateUserRequest;
import co.zip.candidate.userapi.dto.userDto.UserResponse;
import co.zip.candidate.userapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

  UserResponse toResponse(User user);

  User toEntity(CreateUserRequest createUserRequest);
}
