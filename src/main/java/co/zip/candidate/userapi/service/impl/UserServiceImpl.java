package co.zip.candidate.userapi.service.impl;

import co.zip.candidate.userapi.dto.CreateUserRequest;
import co.zip.candidate.userapi.dto.UserResponse;
import co.zip.candidate.userapi.exception.UserAlreadyExistException;
import co.zip.candidate.userapi.mapper.UserMapper;
import co.zip.candidate.userapi.repository.UserRepository;
import co.zip.candidate.userapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  @Override
  @Transactional
  public UserResponse createUser(CreateUserRequest createUserRequest) {

    checkEmail(createUserRequest.getEmail());

    return userMapper.toDto(userRepository.save(
        userMapper.toEntity(createUserRequest)
    ));
  }

  private void checkEmail(String email) {
    if (userRepository.existsByEmail(email)) {

      log.info("User with email: {} already existed", email);

      throw new UserAlreadyExistException(
          String.format("User with email: %s already existed", email));
    }
  }
}
