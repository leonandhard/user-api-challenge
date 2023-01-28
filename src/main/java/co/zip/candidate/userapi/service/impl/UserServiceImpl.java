package co.zip.candidate.userapi.service.impl;

import co.zip.candidate.userapi.dto.CreateUserRequest;
import co.zip.candidate.userapi.dto.UserResponse;
import co.zip.candidate.userapi.mapper.UserMapper;
import co.zip.candidate.userapi.model.User;
import co.zip.candidate.userapi.repository.UserRepository;
import co.zip.candidate.userapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  @Override
  public UserResponse createUser(CreateUserRequest createUserRequest) {

    User newUser = userMapper.toEntity(createUserRequest);

    return userMapper.toDto(userRepository.save(newUser));
  }
}
