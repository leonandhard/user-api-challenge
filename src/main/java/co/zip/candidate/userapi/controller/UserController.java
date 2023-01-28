package co.zip.candidate.userapi.controller;

import co.zip.candidate.userapi.dto.CreateUserRequest;
import co.zip.candidate.userapi.dto.UserResponse;
import co.zip.candidate.userapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping()
  public UserResponse createNewUser(@RequestBody CreateUserRequest createUserRequest) {
    return userService.createUser(createUserRequest);
  }
}
