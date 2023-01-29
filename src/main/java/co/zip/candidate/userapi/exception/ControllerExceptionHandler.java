package co.zip.candidate.userapi.exception;

import co.zip.candidate.userapi.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

  @ExceptionHandler(value = UserAlreadyExistException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto handleUserAlreadyExistException(UserAlreadyExistException e) {

    return ErrorDto.builder()
        .errorCode(HttpStatus.BAD_REQUEST.value())
        .details(e.getMessage())
        .build();

  }

  @ExceptionHandler(value = UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorDto handleUserNotFoundException(UserNotFoundException e) {

    return ErrorDto.builder()
        .errorCode(HttpStatus.NOT_FOUND.value())
        .details(e.getMessage())
        .build();

  }


  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorDto handleMethodArgumentNotValid(MethodArgumentNotValidException e) {

    log.debug("Method argument is not valid", e);

    return ErrorDto.builder()
        .errorCode(HttpStatus.BAD_REQUEST.value())
        .details(e.getFieldError().getDefaultMessage())
        .build();

  }

  @ExceptionHandler(value = InsufficientMonthlyDepositException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ErrorDto handleInsufficientMonthlyDepositException(InsufficientMonthlyDepositException e) {

    return ErrorDto.builder()
        .errorCode(HttpStatus.FORBIDDEN.value())
        .details(e.getMessage())
        .build();

  }

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorDto handleAllException(Exception e) {

    log.error("error occurred", e);

    return ErrorDto.builder()
        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .details(e.getMessage())
        .build();
  }
}
