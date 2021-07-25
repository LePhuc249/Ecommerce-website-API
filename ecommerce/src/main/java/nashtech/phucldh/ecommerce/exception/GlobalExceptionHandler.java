package nashtech.phucldh.ecommerce.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountAuthenticationException.class)
    public ResponseEntity<?> accountAuthenticationException(AccountAuthenticationException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccountExistedException.class)
    public ResponseEntity<?> accountExistedException(AccountExistedException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> accountNotFoundException(AccountNotFoundException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConvertEntityDTOException.class)
    public ResponseEntity<?> convertEntityDTOException(ConvertEntityDTOException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> dataNotFoundException(DataNotFoundException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CreateDataFailException.class)
    public ResponseEntity<?> createDataFailException(CreateDataFailException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DeleteDataFailException.class)
    public ResponseEntity<?> deleteDataFailException(DeleteDataFailException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UpdateDataFailException.class)
    public ResponseEntity<?> updateDataFailException(UpdateDataFailException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<?> duplicateDataException(DuplicateDataException ex, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(
            Exception ex, WebRequest request) {
        ErrorDetail errorDetails = new ErrorDetail(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}