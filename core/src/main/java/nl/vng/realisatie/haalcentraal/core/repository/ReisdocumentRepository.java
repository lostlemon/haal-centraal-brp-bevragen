package nl.vng.realisatie.haalcentraal.core.repository;

import nl.vng.realisatie.haalcentraal.core.model.Reisdocument;

import java.util.List;
import java.util.Optional;

/**
 * ReisdocumentRepository
 */
public interface ReisdocumentRepository extends BaseRepository<Reisdocument> {

    Optional<Reisdocument> findByReisdocumentnummer(String reisdocumentnummer);

    List<Reisdocument> findAllByPersoonBsn(final Long bsn);

}

