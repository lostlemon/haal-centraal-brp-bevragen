package nl.vng.realisatie.haalcentraal.web.stepdefs;

import lombok.ToString;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.Foutbericht;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.IngeschrevenPersoonHal;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.IngeschrevenPersoonHalCollectie;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.KindHalCollectie;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.OuderHalCollectie;
import nl.vng.realisatie.haalcentraal.restclient.generated.model.bip.PartnerHalCollectie;

/**
 * World
 */
@ToString
public class World {

    public Integer statusCode;

    public IngeschrevenPersoonHalCollectie persoonCollectie;

    public IngeschrevenPersoonHal persoonHal;

    public PartnerHalCollectie partnerHalCollectie;

    public OuderHalCollectie ouderHalCollectie;

    public KindHalCollectie kindHalCollectie;

    public Foutbericht validatieFoutbericht;

    public void clean() {
        statusCode = null;
        persoonHal = null;
        persoonCollectie = null;
        partnerHalCollectie = null;
        ouderHalCollectie = null;
        kindHalCollectie = null;
        validatieFoutbericht = null;
    }

}
