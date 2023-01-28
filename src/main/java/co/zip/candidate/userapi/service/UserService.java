package co.zip.candidate.userapi.service;

import co.zip.candidate.userapi.dto.userDto.CreateUserRequest;
import co.zip.candidate.userapi.dto.userDto.UserResponse;
import co.zip.candidate.userapi.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
  UserResponse createUser(CreateUserRequest createUserRequest);

  UserResponse getUserById(Long id);

  UserResponse getUserByEmail(String email);

  List<UserResponse> listUsers();

  Optional<User> validateUserAccountRequirements(Long id);

}
