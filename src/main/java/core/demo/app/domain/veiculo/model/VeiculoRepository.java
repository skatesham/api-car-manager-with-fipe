package core.demo.app.domain.veiculo.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<VeiculoEntity, UUID> {

    Optional<VeiculoEntity> findByPlaca(String placa);

    @Query("SELECT s FROM VeiculoEntity s WHERE lower(s.placa) LIKE concat('%', lower(?1), '%')")
    Page<VeiculoEntity> findByPlacaContaining(String name, Pageable pageable);
}
