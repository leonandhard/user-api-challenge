package co.zip.candidate.userapi.service;

import co.zip.candidate.userapi.dto.CreateUserRequest;
import co.zip.candidate.userapi.dto.UserResponse;

public interface UserService {
  UserResponse createUser(CreateUserRequest createUserRequest);
}
