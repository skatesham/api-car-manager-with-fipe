package core.demo.app.domain.veiculo;

import core.demo.app.domain.marca.model.MarcaEntity;
import core.demo.app.domain.modelo.model.ModeloEntity;
import core.demo.app.domain.veiculo.model.VeiculoEntity;
import core.demo.app.interfaces.veiculo.VeiculoRequest;

import java.time.LocalDateTime;
import java.util.UUID;

public class VeiculoConverter {

    public static VeiculoEntity toNewEntity(VeiculoRequest veiculoRequest, MarcaEntity marca, ModeloEntity modelo) {
        return VeiculoEntity
                .builder()
                .id(UUID.randomUUID())
                .placa(veiculoRequest.getPlaca())
                .precoAnuncio(veiculoRequest.getPrecoAnuncio())
                .marca(marca)
                .modelo(modelo)
                .ano(veiculoRequest.getAno())
                .createdAt(LocalDateTime.now())
                .build();
    }

}
