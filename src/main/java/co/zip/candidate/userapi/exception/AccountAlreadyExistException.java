package co.zip.candidate.userapi.exception;

public class AccountAlreadyExistException extends RuntimeException {
  public AccountAlreadyExistException(String message) {
    super(message);
  }
}
