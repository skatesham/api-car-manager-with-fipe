package core.demo.app.config.api_error;

import core.demo.app.domain.veiculo.exceptions.VeiculoAlreadyExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(VeiculoAlreadyExistException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ApiError handleConversion(VeiculoAlreadyExistException ex) {
        log.error("status=type-input-error message={}", ex.getMessage(), ex);
        return new ApiError(ApiErrorEnum.VEHICULO_PLATE_VIOLATION, ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error("status=type-input-error message={}", ex.getMessage(), ex);
        return new ApiError(ApiErrorEnum.NOT_FOUND, ex.getMessage());
    }

}