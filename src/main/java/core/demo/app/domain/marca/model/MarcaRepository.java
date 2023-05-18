package core.demo.app.domain.marca.model;

import core.demo.app.interfaces.marca.MarcaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface MarcaRepository extends JpaRepository<MarcaEntity, UUID> {

    Optional<MarcaEntity> findByFipeId(Integer fipeId);

    @Query("SELECT new core.demo.app.interfaces.marca.MarcaResponse(a.id, a.name, count(m))"
            + " FROM ModeloEntity m "
            + " JOIN m.marca a "
            + " GROUP BY a.id"
    )
    Page<MarcaResponse> findMarcaGroupWithCountModelo(Pageable pageable);

}
