package core.demo.app.interfaces.veiculo;

import core.demo.app.domain.veiculo.VeiculoService;
import core.demo.app.domain.veiculo.model.VeiculoEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/veiculos")
@RestController
@AllArgsConstructor
@Tag(name = "Veiculo", description = "The Veiculo API. Contains all the operations that can be performed on a Veiculo.")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Create Veiculo")
    public VeiculoEntity createVeiculo(@Valid @RequestBody VeiculoRequest veiculoRequest) {
        return veiculoService.scheduleCreation(veiculoRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Page Veiculo by optional placa")
    public Page<VeiculoResponse> getVeiculoPageByPlaca(
            @RequestParam(name = "placa", required = false, defaultValue = "") String placa,
            @ParameterObject Pageable pageable) {
        return veiculoService.getPageByPlaca(placa, pageable);
    }


}
