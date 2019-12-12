package nl.vng.realisatie.haalcentraal.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import java.util.UUID;

/**
 * Partnerschap
 */
@Entity
@Getter
@Setter
public class Partnerschap extends Persistable {

    @PrePersist
    public void prePersist() {
        uuid = UUID.randomUUID().toString();
    }

    @ManyToOne(optional = false)
    private Persoon partner1;

    @ManyToOne(optional = false)
    private Persoon partner2;

    private String datumOntbinding;
    private String soortVerbintenis;
    private String aangaanHuwelijkPartnerschapDatum;
    private String aangaanHuwelijkPartnerschapPlaats;
    private String aangaanHuwelijkPartnerschapLand;
    private String indicatieOnjuist;
    private String uuid;

}