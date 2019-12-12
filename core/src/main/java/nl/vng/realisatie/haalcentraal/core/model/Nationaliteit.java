package nl.vng.realisatie.haalcentraal.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Nationaliteit, many to many between Persoon en Nationaliteiten
 */
@Entity
@Getter
@Setter
public class Nationaliteit extends Persistable {

    @ManyToOne(optional = false)
    private Persoon persoon;
    private String nationaliteitCode;
    private String redenOpnameNationaliteit;
    private String aanduidingBijzonderNederlanderschap;
    private String datumIngangGeldigheidNationaliteit;

}
