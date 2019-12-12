package nl.vng.realisatie.haalcentraal.core.repository;

import nl.vng.realisatie.haalcentraal.core.model.Persoon;

import java.util.Optional;

public interface PersoonRepository extends BaseRepository<Persoon> {

    Optional<Persoon> findByBsn(final Long bsn);

    Optional<Persoon> findByUuid(final String uuid);

    boolean existsByBsn(final Long bsn);
}


