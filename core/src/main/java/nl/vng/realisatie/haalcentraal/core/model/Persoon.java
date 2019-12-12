package nl.vng.realisatie.haalcentraal.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Persoon extends Persistable {

    @PrePersist
    public void prePersist() {
        uuid = UUID.randomUUID().toString();
    }

    private Long bsn;
    private Long anummer;
    private String uuid;

    private String voornamen;
    private String geslachtsnaam;
    private String voorvoegsel;

    public Boolean inOnderzoek;
    public Boolean inOnderzoekNaam;
    public Boolean inOnderzoekVoornamen;
    public Boolean inOnderzoekVoorvoegsel;
    public Boolean inOnderzoekGeslachtsnaam;

    private String aanduidingAanschrijving;

    private String europeesKiesrecht;
    private String einddatumUitsluitingEuropeesKiesrecht;
    private String einddatumUitsluitingKiesrecht;

    private Boolean indicatieGeheim;

    private String datumEersteInschrijvingGBA;

    private String datumOpschortingBijhouding;
    private String redenOpschortingBijhouding;
    private String geslachtsaanduiding;

    private String indicatieCurateleRegister;
    private String indicatieGezagMinderjarige;

    private String geboorteDatum;
    private String geboortePlaats;
    private String geboorteLand;

    private Boolean inOnderzoekGeboorte;
    private Boolean inOnderzoekGeboorteDatum;
    private Boolean inOnderzoekGeboortePlaats;
    private Boolean inOnderzoekGeboorteLand;

    private String overlijdenDatum;
    private String overlijdenPlaats;
    private String overlijdenLand;

    private String ouderAanduiding;
    private String datumIngangFamilierechtelijkeBetrekking;

    private String aktenummer;
    private String registergemeenteAkte;
    private String ontbindinghuwelijkPartnerschapDatum;
    private String ontbindinghuwelijkPartnerschapPlaats;
    private String ontbindinghuwelijkPartnerschapLand;
    private String ontbindinghuwelijkPartnerschapReden;

    private String adellijketitelPredikaat;

    @ManyToMany
    private List<Verblijfstitel> verblijfstitel;

    @ManyToOne
    private Verblijfplaats verblijfplaatsHuidig;

    @OneToMany
    private List<Verblijfplaats> verblijfplaatsHistorie;

    @ManyToMany
    private List<Nationaliteit> nationaliteit;

}
