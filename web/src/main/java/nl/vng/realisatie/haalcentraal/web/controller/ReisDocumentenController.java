package nl.vng.realisatie.haalcentraal.web.controller;

import nl.vng.realisatie.haalcentraal.core.repository.ReisdocumentRepository;
import nl.vng.realisatie.haalcentraal.rest.generated.api.bip.ReisdocumentenApi;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.ReisdocumentHal;
import nl.vng.realisatie.haalcentraal.web.converter.ReisdocumentConverter;
import nl.vng.realisatie.haalcentraal.web.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * ReisDocumentenController
 */

@RestController
public class ReisDocumentenController implements ReisdocumentenApi {

    @Inject
    private ReisdocumentRepository reisdocumentRepository;

    @Inject
    private ReisdocumentConverter reisdocumentConverter;

    @Override
    public ResponseEntity<ReisdocumentHal> reisdocumentenReisdocumentnummer(final String reisdocumentnummer, final String apiVersion) {
        return reisdocumentRepository.findByReisdocumentnummer(reisdocumentnummer) //
                .map(p -> reisdocumentConverter.convert(p))
                .map(ResponseEntity::ok) //
                .orElseThrow(NotFoundException::new);
    }

}
