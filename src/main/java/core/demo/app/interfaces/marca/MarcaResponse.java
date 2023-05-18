package core.demo.app.interfaces.marca;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@AllArgsConstructor
public class MarcaResponse {

    UUID id;
    String name;
    @JsonProperty("total_modelos")
    long totalModelos;

}
