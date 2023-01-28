package co.zip.candidate.userapi.service.impl;

import co.zip.candidate.userapi.dto.userDto.CreateUserRequest;
import co.zip.candidate.userapi.dto.userDto.UserResponse;
import co.zip.candidate.userapi.exception.UserAlreadyExistException;
import co.zip.candidate.userapi.exception.UserNotFoundException;
import co.zip.candidate.userapi.mapper.UserMapper;
import co.zip.candidate.userapi.model.User;
import co.zip.candidate.userapi.repository.UserRepository;
import co.zip.candidate.userapi.service.UserService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

    return userMapper.toResponse(userRepository.save(
        userMapper.toEntity(createUserRequest)
    ));
  }

  @Override
  public UserResponse getUserById(Long id) {

    User user = userRepository.findById(id).orElseThrow(() -> {
      log.info("User with Id: {} not existed", id);
      return new UserNotFoundException("User not found");
    });

    return userMapper.toResponse(user);
  }

  @Override
  public UserResponse getUserByEmail(String email) {

    User user = userRepository.findByEmail(email).orElseThrow(() -> {
      log.info("User with email: {} not existed", email);
      return new UserNotFoundException("User not found");
    });

    return userMapper.toResponse(user);
  }

  @Override
  public List<UserResponse> listUsers() {

    return userRepository.findAll()
        .stream().map(userMapper::toResponse).collect(Collectors.toList());

  }

  @Override
  public Optional<User> validateUserAccountRequirements(Long id) {

    User user = userRepository.findById(id).orElseThrow(() -> {
      log.info("User with Id: {} not existed", id);
      return new UserNotFoundException("User not found");
    });

    return user.getMonthlySalary().subtract(user.getMonthlyExpenses())
        .compareTo(BigDecimal.valueOf(1000)) >= 0 ? Optional.of(user) : Optional.empty();
  }

  private void checkEmail(String email) {
    if (userRepository.existsByEmail(email)) {

      log.info("User with email: {} already existed", email);

      throw new UserAlreadyExistException(
          String.format("User with email: %s already existed", email));
    }
  }
}
