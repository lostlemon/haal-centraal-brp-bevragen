package nl.vng.realisatie.haalcentraal.core.model;

import lombok.Getter;
import lombok.Setter;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.OuderAanduidingEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.util.UUID;

/**
 * Afstamming
 */
@Entity
@Getter
@Setter
public class Afstamming extends Persistable {

    @PrePersist
    public void prePersist() {
        uuid = UUID.randomUUID().toString();
    }

    @ManyToOne(optional = false)
    private Persoon kind;

    @ManyToOne(optional = false)
    private Persoon ouder;

    @Enumerated(EnumType.STRING)
    private OuderAanduidingEnum ouderAanduidingEnum;

    private String datumIngangFamilierechtelijkeBetrekking; //02.62.10 for ouder1, 03.62.10 for ouder2

    private String uuid;

}
