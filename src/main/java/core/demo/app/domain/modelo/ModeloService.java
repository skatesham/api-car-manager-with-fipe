package core.demo.app.domain.modelo;


import core.demo.app.domain.modelo.model.ModeloRepository;
import core.demo.app.interfaces.modelo.ModeloResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ModeloService {

    private final ModeloRepository modeloRepository;

    public Page<ModeloResponse> getPageByName(String name, Pageable pageable) {
        return modeloRepository.findByNameContainingIgnoreCase(name, pageable);
    }

}
