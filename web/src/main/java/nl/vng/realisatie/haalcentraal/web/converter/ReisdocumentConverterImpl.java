package nl.vng.realisatie.haalcentraal.web.converter;


import nl.vng.realisatie.haalcentraal.core.model.Reisdocument;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.HalLink;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.ReisdocumentHal;
import nl.vng.realisatie.haalcentraal.web.controller.ReisDocumentenController;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Function;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ReisdocumentConverterImpl extends BaseConverterImpl<ReisdocumentHal, Reisdocument> implements ReisdocumentConverter {

    @Override
    public ReisdocumentHal createTarget(final Reisdocument source, final Set<String> fields) {
        return new ReisdocumentHal();
    }

//    @Override
//    public ReisdocumentHal convert(final Reisdocument source, final ReisdocumentHal target, final Set<String> fields, final Set<String> expand) throws ReturnException {
//
//        target.setReisdocumentnummer(source.getReisdocumentnummer());
//        target.setLinks(new ReisdocumentLinks()
//                .self(createReisdocumentSelfLink.apply(source))
//                .ingeschrevenpersoon(PersoonConverterImpl.createIngeschrevenPersoonLink(source.getPersoon().getBsn(), fields, null)
//                )
//        );
//
//        return target;
//    }

    public static final Function<Reisdocument, HalLink> createReisdocumentSelfLink = r ->
            new HalLink().href(
                    URI.create(linkTo(methodOn(ReisDocumentenController.class)
                                            .reisdocumentenReisdocumentnummer(r.getReisdocumentnummer(), null)
                            ).withSelfRel().expand(new HashMap<>()).getHref()
                    )
            );

}
