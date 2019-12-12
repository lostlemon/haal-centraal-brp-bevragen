package nl.vng.realisatie.haalcentraal.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Verblijfstitel
 */
@Entity
@Getter
@Setter
public class Verblijfstitel extends Persistable {

    private String aanduidingVerblijfstitel;
    private String datumEindeVerblijfstitel;
    private String datumIngangVerblijfstitel;

    @OneToMany
    private List<Persoon> persoons;

}
