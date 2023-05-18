package core.demo.app.domain.marca;


import core.demo.app.domain.marca.model.MarcaRepository;
import core.demo.app.interfaces.marca.MarcaResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;

    public Page<MarcaResponse> getPageByName(Pageable pageable) {
        return marcaRepository.findMarcaGroupWithCountModelo(pageable);
    }

}
