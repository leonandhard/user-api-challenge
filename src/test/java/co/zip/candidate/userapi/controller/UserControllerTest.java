package co.zip.candidate.userapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.zip.candidate.userapi.service.UserService;
import co.zip.candidate.userapi.util.UserTestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {

  @Mock
  private UserService userService;


  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @InjectMocks
  private UserController userController;

  @BeforeEach
  public void setUp() {
    objectMapper = new ObjectMapper();
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  public void shouldReturnUserResponseSuccessfullyWhenCreateNewUser() throws Exception {
    when(userService.createUser(any())).thenReturn(
        UserTestHelper.createUserResponse_1());
    mockMvc.perform(MockMvcRequestBuilders.post("/users")
            .content(objectMapper.writeValueAsString(UserTestHelper.createUserRequest()))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(2L))
        .andExpect(jsonPath("$.email").value("test2@gmail.com"));

  }

  @Test
  public void shouldReturnUserResponseSuccessfullyWhenGetUserById() throws Exception {
    when(userService.getUserById(anyLong())).thenReturn(UserTestHelper.createUserResponse_1());
    mockMvc.perform(MockMvcRequestBuilders.get("/users?id=1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.monthlySalary").value(BigDecimal.valueOf(2000)))
        .andExpect(jsonPath("$.name").value("test2"));

  }

  @Test
  public void shouldReturnUserResponseSuccessfullyWhenGetUserByEmail() throws Exception {
    when(userService.getUserByEmail(any())).thenReturn(UserTestHelper.createUserResponse_1());
    mockMvc.perform(MockMvcRequestBuilders.get("/users?email=test2@gmail.com"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.monthlySalary").value(BigDecimal.valueOf(2000)))
        .andExpect(jsonPath("$.name").value("test2"));
  }

  @Test
  public void shouldReturnUserResponseListSuccessfullyWhenListUsers() throws Exception {
    when(userService.listUsers()).thenReturn(List.of(UserTestHelper.createUserResponse_1(),
        UserTestHelper.createUserResponse_2()));
    mockMvc.perform(MockMvcRequestBuilders.get("/users"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].monthlySalary").value(BigDecimal.valueOf(2000)))
        .andExpect(jsonPath("$[0].name").value("test2"))
        .andExpect(jsonPath("$[1].id").value(3L))
        .andExpect(jsonPath("$[1].name").value("test3"));
  }
}
