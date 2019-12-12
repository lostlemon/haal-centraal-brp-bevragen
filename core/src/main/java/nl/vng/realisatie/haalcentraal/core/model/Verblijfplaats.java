package nl.vng.realisatie.haalcentraal.core.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Verblijfsplaats, heel categorie 8 niet uitgenormaliseerd
 */

@Entity
@Getter
@Setter
public class Verblijfplaats extends Persistable {

    @ManyToOne
    private Persoon persoon;

    private String gemeenteVanInschrijving;
    private String datumInschrijvingInGemeente;
    private String woonplaatsnaam;
    private String straatnaam;
    private String huisletter;
    private Integer huisnummer;
    private String postcode;
    private String ingangsdatumGeldigheid;
    private String locatiebeschrijving;
    private String huisnummerToevoeging;
    private String naamOpenbareruimte;
    private String identificatiecodeNummeraanduiding;
    private String identificatiecodeVerblijfplaats;
    private String functieAdres;
    private String datumAanvangAdreshouding;
    private String datumVestigingInNederland;
    private String landVanWaarIngeschreven;
    private String aanduidingBijHuisnummer;
    private String verblijfBuitenlandLand;
    private String verblijfBuitenlandAdresregel1;
    private String verblijfBuitenlandAdresregel2;
    private String verblijfBuitenlandAdresregel3;

}
