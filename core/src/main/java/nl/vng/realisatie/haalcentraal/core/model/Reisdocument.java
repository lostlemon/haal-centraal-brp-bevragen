package nl.vng.realisatie.haalcentraal.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Reisdocument
 */
@Entity
@Getter
@Setter
public class Reisdocument extends Persistable {

    @ManyToOne(optional = false)
    private Persoon persoon;

    private String aanduidingInhoudingVermissingReisdocument;
    private String soortReisdocument;
    private String reisdocumentnummer;
    private String datumUitgifte;
    private String autoriteitAfgifte;
    private String datumEindeGeldigheid;
    private String datumInhoudingOfVermissing;

}
