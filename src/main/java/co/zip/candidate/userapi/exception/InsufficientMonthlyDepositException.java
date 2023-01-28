package co.zip.candidate.userapi.exception;

public class InsufficientMonthlyDepositException extends RuntimeException {
  public InsufficientMonthlyDepositException(String message) {
    super(message);
  }
}
