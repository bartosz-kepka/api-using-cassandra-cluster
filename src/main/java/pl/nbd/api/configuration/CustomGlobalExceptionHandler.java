package pl.nbd.api.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.nbd.api.exception.DuplicateException;
import pl.nbd.api.exception.NotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        Map<String, Object> body = new LinkedHashMap<>(createBody(status, ex.getMessage(), request));

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(value = DuplicateException.class)
    public ResponseEntity<Object> handleDuplicateException(DuplicateException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Map<String, Object> body = new LinkedHashMap<>(createBody(status, ex.getMessage(), request));

        return new ResponseEntity<>(body, status);
    }

    private Map<String, Object> createBody(HttpStatus status, Object errors, HttpServletRequest request) {
        return Map.of("timestamp", Timestamp.valueOf(LocalDateTime.now()),
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", errors,
                "path", request.getRequestURI());
    }
}
