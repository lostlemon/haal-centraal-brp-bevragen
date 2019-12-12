package nl.vng.realisatie.haalcentraal.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * VerblijfstitelHistorie
 */
@Entity
@Getter
@Setter
public class VerblijfstitelHistorie extends Persistable{

    @ManyToOne(optional = false)
    private Persoon persoon;

    @ManyToOne(optional = false)
    private Verblijfstitel verblijfstitel;

}
