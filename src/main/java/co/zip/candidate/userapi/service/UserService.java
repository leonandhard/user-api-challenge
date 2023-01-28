package co.zip.candidate.userapi.service;

import co.zip.candidate.userapi.dto.CreateUserRequest;
import co.zip.candidate.userapi.dto.UserResponse;
import java.util.List;

public interface UserService {
  UserResponse createUser(CreateUserRequest createUserRequest);

  UserResponse getUserById(Long id);

  UserResponse getUserByEmail(String email);

  List<UserResponse> listUsers();
}
