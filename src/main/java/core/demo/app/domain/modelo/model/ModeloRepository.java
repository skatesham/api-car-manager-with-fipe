package core.demo.app.domain.modelo.model;

import core.demo.app.interfaces.modelo.ModeloResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ModeloRepository extends JpaRepository<ModeloEntity, UUID> {

    Optional<ModeloEntity> findByFipeId(Integer fipeId);

    @Query("SELECT new core.demo.app.interfaces.modelo.ModeloResponse(s.id, s.name)"
            + " FROM ModeloEntity s WHERE lower(s.name) LIKE concat('%', lower(?1), '%')"
    )
    Page<ModeloResponse> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
