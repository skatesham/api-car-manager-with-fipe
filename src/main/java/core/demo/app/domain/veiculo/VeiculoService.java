package core.demo.app.domain.veiculo;


import core.demo.app.domain.marca.model.MarcaEntity;
import core.demo.app.domain.marca.model.MarcaRepository;
import core.demo.app.domain.modelo.model.ModeloEntity;
import core.demo.app.domain.modelo.model.ModeloRepository;
import core.demo.app.domain.utils.DirtyValueUtils;
import core.demo.app.domain.veiculo.exceptions.FipeIntegrationNotFoundException;
import core.demo.app.domain.veiculo.exceptions.MarcaNotFoundException;
import core.demo.app.domain.veiculo.exceptions.ModeloNotFoundException;
import core.demo.app.domain.veiculo.exceptions.VeiculoAlreadyExistException;
import core.demo.app.domain.veiculo.model.VeiculoEntity;
import core.demo.app.domain.veiculo.model.VeiculoRepository;
import core.demo.app.domain.veiculo.model.VeiculoRequestedSender;
import core.demo.app.infrastucture.clients.FipeClient;
import core.demo.app.infrastucture.clients.FipePriceRequest;
import core.demo.app.interfaces.veiculo.VeiculoRequest;
import core.demo.app.interfaces.veiculo.VeiculoResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class VeiculoService {

    private final VeiculoRequestedSender veiculoRequestedSender;
    private final VeiculoRepository veiculoRepository;
    private final MarcaRepository marcaRepository;
    private final ModeloRepository modeloRepository;
    private final FipeClient fipeClient;

    public VeiculoEntity scheduleCreation(final VeiculoRequest veiculoRequest) {
        log.trace("action=veiculo-creation status=start placa={}", veiculoRequest.getPlaca());

        final var veiculoByPlacaFoundOpt = this.veiculoRepository.findByPlaca(veiculoRequest.getPlaca());
        if (veiculoByPlacaFoundOpt.isPresent()) {
            throw new VeiculoAlreadyExistException(veiculoRequest.getPlaca());
        }

        final MarcaEntity marca = marcaRepository.findByFipeId(veiculoRequest.getMarcaId())
                .orElseThrow(() -> new MarcaNotFoundException(veiculoRequest.getMarcaId()));

        final ModeloEntity modelo = modeloRepository.findByFipeId(veiculoRequest.getModeloId())
                .orElseThrow(() -> new ModeloNotFoundException(veiculoRequest.getModeloId()));

        final VeiculoEntity newEntity = VeiculoConverter.toNewEntity(veiculoRequest, marca, modelo);
        this.veiculoRequestedSender.send(newEntity);

        log.trace("action=veiculo-creation status=scheduled placa={}", newEntity.getPlaca());
        return newEntity;
    }

    @Transactional
    public void processCreation(final VeiculoEntity veiculo) {
        log.trace("action=veiculo-creation status=start");
        final var request = FipePriceRequest.of(veiculo.getMarca().getFipeId(), veiculo.getModelo().getFipeId(), veiculo.getAno());
        final var fipePriceResponse = fipeClient.requestVehiclePrice(request);

        if (!StringUtils.isEmpty(fipePriceResponse.getErro())) {
            log.error("action=veiculo-creation status=error error={} id={}", fipePriceResponse.getErro(), veiculo.getId());
            throw new FipeIntegrationNotFoundException();
        }

        final Integer precoFipe = DirtyValueUtils.convertToNumber(fipePriceResponse.getValor());
        veiculo.setPrecoFipe(precoFipe);

        this.veiculoRepository.save(veiculo);
        log.trace("action=veiculo-creation status=success placa={} value={}", veiculo.getPlaca(), precoFipe);
    }

    public Page<VeiculoResponse> getPageByPlaca(String placa, Pageable pageable) {

        List<VeiculoResponse> responses = veiculoRepository.findByPlacaContaining(placa, pageable)
                .stream()
                .map(veiculoEntity -> VeiculoResponse.builder()
                        .placa(veiculoEntity.getPlaca())
                        .preco_anuncio(BigDecimal.valueOf(veiculoEntity.getPrecoAnuncio(), 2).floatValue())
                        .ano(veiculoEntity.getAno())
                        .preco_fipe(BigDecimal.valueOf(veiculoEntity.getPrecoFipe(), 2).floatValue())
                        .modelo(veiculoEntity.getModelo().getName())
                        .marca(veiculoEntity.getMarca().getName())
                        .build())
                .collect(Collectors.toList());
        return new PageImpl<>(responses);
    }

}
