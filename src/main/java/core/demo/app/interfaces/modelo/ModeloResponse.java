package core.demo.app.interfaces.modelo;

import core.demo.app.domain.modelo.model.ModeloRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Used on query {@link ModeloRepository#findByNameContainingIgnoreCase(String, Pageable)}
 */
@Value
@Builder
@AllArgsConstructor
public class ModeloResponse {

    UUID id;
    @NotNull
    String name;

}
