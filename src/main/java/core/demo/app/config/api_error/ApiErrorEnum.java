package core.demo.app.config.api_error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiErrorEnum {

    NOT_FOUND(1, "Não foi encontrado"),
    VEHICULO_PLATE_VIOLATION(2, "Placa já está registrada no sistema");

    private final int code;
    private final String descriptionPt;

}
