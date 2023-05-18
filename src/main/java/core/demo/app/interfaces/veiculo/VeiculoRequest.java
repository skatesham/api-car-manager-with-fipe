package core.demo.app.interfaces.veiculo;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
public class VeiculoRequest {

    @NotNull
    String placa;

    @NotNull
    Integer marcaId;

    @NotNull
    Integer modeloId;

    @NotNull
    Integer precoAnuncio;

    @NotNull
    Integer ano;

}
