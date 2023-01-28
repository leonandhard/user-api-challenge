package co.zip.candidate.userapi.controller;

import co.zip.candidate.userapi.dto.userDto.CreateUserRequest;
import co.zip.candidate.userapi.dto.userDto.UserResponse;
import co.zip.candidate.userapi.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponse createNewUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
    return userService.createUser(createUserRequest);
  }

  @GetMapping(params = "id")
  public UserResponse getUserById(@RequestParam Long id) {
    return userService.getUserById(id);
  }

  @GetMapping(params = "email")
  public UserResponse getUserByEmail(@RequestParam String email) {
    return userService.getUserByEmail(email);
  }

  @GetMapping
  public List<UserResponse> listUsers() {
    return userService.listUsers();
  }

}
