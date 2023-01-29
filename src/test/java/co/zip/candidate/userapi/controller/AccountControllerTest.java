package co.zip.candidate.userapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import co.zip.candidate.userapi.service.AccountService;
import co.zip.candidate.userapi.util.AccountTestHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class AccountControllerTest {

  @Mock
  private AccountService accountService;

  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @InjectMocks
  private AccountController accountController;

  @BeforeEach
  public void setUp() {
    objectMapper = new ObjectMapper();
    mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
  }

  @Test
  public void shouldReturnAccountResponseSuccessfullyWhenCreateNewAccount()
      throws Exception {
    when(accountService.createAccount(any())).thenReturn(AccountTestHelper.accountResponse());
    mockMvc.perform(MockMvcRequestBuilders.post("/accounts")
            .content(objectMapper.writeValueAsString(AccountTestHelper.createAccountRequest()))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.accountNumber").value("13785749014"))
        .andExpect(jsonPath("$.contactNumber").value("466266853"))
        .andExpect(jsonPath("$.id").value(1L));
  }

  @Test
  public void shouldReturnListAccountsDtoSuccessfullyWhenGetAccountsList() throws Exception {
    when(accountService.listAccountsById(anyLong())).thenReturn(
        AccountTestHelper.listAccountsDto());
    mockMvc.perform(MockMvcRequestBuilders.get("/accounts/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.userName").value("username"))
        .andExpect(jsonPath("$.accountList[0].id").value(1L))
        .andExpect(jsonPath("$.accountList[0].billingAddress").value("address"));

  }

  @Test
  public void shouldReturnAllListAccountDtoSuccessfullyWhenGetAllAccountList() throws Exception {
    when(accountService.ListAllAccounts()).thenReturn(
        List.of(AccountTestHelper.listAccountsDto()));
    mockMvc.perform(MockMvcRequestBuilders.get("/accounts"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].userName").value("username"))
        .andExpect(jsonPath("$.[0].accountList[0].id").value(1L))
        .andExpect(jsonPath("$.[0].accountList[0].billingAddress").value("address"));
  }

}
