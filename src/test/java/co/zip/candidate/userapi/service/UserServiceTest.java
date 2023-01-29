package co.zip.candidate.userapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import co.zip.candidate.userapi.exception.UserAlreadyExistException;
import co.zip.candidate.userapi.exception.UserNotFoundException;
import co.zip.candidate.userapi.mapper.UserMapper;
import co.zip.candidate.userapi.mapper.UserMapperImpl;
import co.zip.candidate.userapi.repository.UserRepository;
import co.zip.candidate.userapi.service.impl.UserServiceImpl;
import co.zip.candidate.userapi.util.UserTestHelper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  private UserService userService;

  @BeforeEach
  public void setUp() {
    UserMapper mapper = new UserMapperImpl();
    userService = new UserServiceImpl(userRepository, mapper);
  }

  @Test
  public void givenCreateUserRequest_whenCreateUser_shouldReturnUserResponse() {
    when(userRepository.existsByEmail(any())).thenReturn(false);
    when(userRepository.save(any())).thenReturn(UserTestHelper.createUser_1());
    assertEquals("test1@gmail.com",
        userService.createUser(UserTestHelper.createUserRequest()).getEmail());
  }

  @Test
  public void givenCreateUserRequest_whenCreateUser_shouldThrowsUserAlreadyExistException() {
    when(userRepository.existsByEmail(any())).thenReturn(true);
    assertThrows(UserAlreadyExistException.class,
        () -> userService.createUser(UserTestHelper.createUserRequest()));
  }

  @Test
  public void givenUserId_whenGetUserById_shouldReturnUserResponse() {
    when(userRepository.findById(anyLong())).thenReturn(
        Optional.ofNullable(UserTestHelper.createUser_1()));

    assertEquals("test1", userService.getUserById(1L).getName());
  }

  @Test
  public void givenUserId_whenGetUserById_shouldThrowsUserNotFoundException() {
    when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class, () -> userService.getUserById(anyLong()));
  }

  @Test
  public void givenUserEmail_whenGetUserByEmail_shouldReturnUserResponse() {
    when(userRepository.findByEmail(any())).thenReturn(
        Optional.ofNullable(UserTestHelper.createUser_1()));

    assertEquals("test1", userService.getUserByEmail("test1@gmail.com").getName());
  }

  @Test
  public void givenUserEmail_whenGetUserByEmail_shouldThrowsUserNotFoundException() {
    when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class, () -> userService.getUserByEmail(any()));
  }

  @Test
  public void givenUserId_whenValidateUserAccountRequirements_shouldReturnUser() {
    when(userRepository.findById(anyLong())).thenReturn(
        Optional.ofNullable(UserTestHelper.createUser_1()));
    assertEquals("test1", userService.validateUserAccountRequirements(anyLong()).get().getName());

  }

  @Test
  public void givenNull_whenListUsers_shouldReturnUserList() {

    when(userRepository.findAll()).thenReturn(List.of(UserTestHelper.createUser_1(),
        UserTestHelper.createUser_2()));

    assertEquals("test2", userService.listUsers().get(1).getName());
  }

}