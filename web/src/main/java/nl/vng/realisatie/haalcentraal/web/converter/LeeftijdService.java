package nl.vng.realisatie.haalcentraal.web.converter;

import nl.vng.realisatie.haalcentraal.core.model.Persoon;
import nl.vng.realisatie.haalcentraal.web.controller.ServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Optional;

/**
 * LeeftijdService
 */
@Service
public class LeeftijdService {

    @Autowired
    private ServiceConfig serviceConfig;

    @Autowired
    private PersoonConverterImpl persoonConverter;

    public Optional<Integer> calcLeeftijd(Persoon persoon) {
        return persoonConverter.calcGeboorteDatum(persoon).map((geboorteDatum) -> Period.between(geboorteDatum, serviceConfig.getLocalDate()).getYears());
    }

}
