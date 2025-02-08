package wcosta.dishmaster.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public Mono<ResponseEntity<String>> handleEntityNotFound(NotFoundException ex) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Entity not found: " + ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public Mono<ResponseEntity<String>> handleBadRequest(BadRequestException ex) {
        return Mono.just(ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Bad request: " + ex.getMessage()));
    }
}
