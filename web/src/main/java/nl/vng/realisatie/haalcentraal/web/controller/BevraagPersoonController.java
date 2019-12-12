package nl.vng.realisatie.haalcentraal.web.controller;

import com.atlassian.oai.validator.springmvc.InvalidRequestException;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.vng.realisatie.haalcentraal.core.enums.GemeenteCode;
import nl.vng.realisatie.haalcentraal.core.model.Persoon;
import nl.vng.realisatie.haalcentraal.core.model.QAfstamming;
import nl.vng.realisatie.haalcentraal.core.model.QPartnerschap;
import nl.vng.realisatie.haalcentraal.core.model.QPersoon;
import nl.vng.realisatie.haalcentraal.core.repository.AfstammingRepository;
import nl.vng.realisatie.haalcentraal.core.repository.PartnerschapRepository;
import nl.vng.realisatie.haalcentraal.core.repository.PersoonRepository;
import nl.vng.realisatie.haalcentraal.rest.generated.api.bip.IngeschrevenpersonenApi;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.Foutbericht;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.GeslachtEnum;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.HalCollectionLinks;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.HalLink;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.IngeschrevenPersoonHal;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.IngeschrevenPersoonHalCollectie;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.IngeschrevenPersoonHalCollectieEmbedded;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.InvalidParams;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.KindHal;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.KindHalCollectie;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.KindHalCollectieEmbedded;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.OuderHal;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.OuderHalCollectie;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.OuderHalCollectieEmbedded;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.PartnerHal;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.PartnerHalCollectie;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.PartnerHalCollectieEmbedded;
import nl.vng.realisatie.haalcentraal.web.Util;
import nl.vng.realisatie.haalcentraal.web.converter.KindConverter;
import nl.vng.realisatie.haalcentraal.web.converter.OuderConverter;
import nl.vng.realisatie.haalcentraal.web.converter.PartnerConverter;
import nl.vng.realisatie.haalcentraal.web.converter.PersoonConverter;
import nl.vng.realisatie.haalcentraal.web.exception.ExpandException;
import nl.vng.realisatie.haalcentraal.web.exception.FieldsException;
import nl.vng.realisatie.haalcentraal.web.exception.InvalidQueryParameterWildCardException;
import nl.vng.realisatie.haalcentraal.web.exception.NotFoundException;
import nl.vng.realisatie.haalcentraal.web.exception.ParamException;
import nl.vng.realisatie.haalcentraal.web.exception.ReturnException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.beans.PropertyEditorSupport;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BevraagPersoonController implements IngeschrevenpersonenApi {

    public static final DateTimeFormatter DBFORMATTER = new DateTimeFormatterBuilder().appendPattern("uuuu").appendPattern("MM").appendPattern("dd").toFormatter();

    public static final DateTimeFormatter QUERYFORMATTER = new DateTimeFormatterBuilder().appendPattern("uuuu").appendLiteral('-').appendPattern("MM").appendLiteral('-').appendPattern("dd").toFormatter();

    private final PersoonRepository persoonRepository;

    private final PersoonConverter persoonConverter;

    private final KindConverter kindConverter;

    private final OuderConverter ouderConverter;

    private final PartnerConverter partnerConverter;

    private final AfstammingRepository afstammingRepository;

    private final PartnerschapRepository partnerschapRepository;

    @InitBinder
    public void initBinder(final WebDataBinder webdataBinder) {
        webdataBinder.registerCustomEditor(GeslachtEnum.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(GeslachtEnum.fromValue(text));
            }
        });
    }

    @Override
    public ResponseEntity<IngeschrevenPersoonHalCollectie> ingeschrevenNatuurlijkPersonen(
            @RequestHeader(value = "api-version", required = false) String apiVersion,
            @RequestParam(value = "expand", required = false) String expand, @Size(min = 9, max = 9)
            @Valid @RequestParam(value = "burgerservicenummer", required = false) String burgerservicenummer,
            @Valid @RequestParam(value = "fields", required = false) String fields, @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) @RequestParam(value = "geboorte__datum", required = false) LocalDate geboorteDatum, @Size(max = 40)
            @Valid @RequestParam(value = "geboorte__plaats", required = false) String geboortePlaats,
            @Valid GeslachtEnum geslachtsaanduiding,
            @Valid @RequestParam(value = "inclusiefoverledenpersonen", required = false) Boolean inclusiefoverledenpersonen, @Size(max = 200)
            @Valid @RequestParam(value = "naam__geslachtsnaam", required = false) String naamGeslachtsnaam, @Size(max = 200)
            @Valid @RequestParam(value = "naam__voornamen", required = false) String naamVoornamen, @Size(max = 4)
            @Valid @RequestParam(value = "verblijfplaats__gemeentevaninschrijving", required = false) String verblijfplaatsGemeentevaninschrijving, @Size(max = 1)
            @Valid @RequestParam(value = "verblijfplaats__huisletter", required = false) String verblijfplaatsHuisletter, @Max(99999)
            @Valid @RequestParam(value = "verblijfplaats__huisnummer", required = false) Integer verblijfplaatsHuisnummer, @Size(max = 4)
            @Valid @RequestParam(value = "verblijfplaats__huisnummertoevoeging", required = false) String verblijfplaatsHuisnummertoevoeging, @Size(max = 16)
            @Valid @RequestParam(value = "verblijfplaats__identificatiecodenummeraanduiding", required = false) String verblijfplaatsIdentificatiecodenummeraanduiding, @Size(max = 80)
            @Valid @RequestParam(value = "verblijfplaats__naamopenbareruimte", required = false) String verblijfplaatsNaamopenbareruimte, @Pattern(regexp = "^[1-9]{1}[0-9]{3}[A-Z]{2}$")
            @Valid @RequestParam(value = "verblijfplaats__postcode", required = false) String verblijfplaatsPostcode, @Size(max = 10)
            @Valid @RequestParam(value = "naam__voorvoegsel", required = false) String naamVoorvoegsel) {

        final BooleanBuilder predicate = new BooleanBuilder();

        validateVoorvoegsel(naamVoorvoegsel);
        validateVerblijfplaatsGemeentevaninschrijving(verblijfplaatsGemeentevaninschrijving);

        mixInGeboorteDatum(predicate, geboorteDatum);
        mixInBsn(predicate, burgerservicenummer);
        mixInStringPath(predicate, "naam__geslachtsnaam", naamGeslachtsnaam, QPersoon.persoon.geslachtsnaam);
        mixInStringPath(predicate, "naam__voornamen", naamVoornamen, QPersoon.persoon.voornamen);
        mixInStringPath(predicate, "naam__voorvoegsel", naamVoorvoegsel, QPersoon.persoon.voorvoegsel);
        mixInStringPath(predicate, "verblijfplaats__identificatiecodenummeraanduiding", verblijfplaatsIdentificatiecodenummeraanduiding, QPersoon.persoon.verblijfplaatsHuidig.identificatiecodeNummeraanduiding);
        mixInStringPath(predicate, "verblijfplaats__naamopenbareruimte", verblijfplaatsNaamopenbareruimte, QPersoon.persoon.verblijfplaatsHuidig.naamOpenbareruimte);
        mixInStringPath(predicate, "verblijfplaats__gemeentevaninschrijving", verblijfplaatsGemeentevaninschrijving, QPersoon.persoon.verblijfplaatsHuidig.gemeenteVanInschrijving);
        mixInStringPath(predicate, "verblijfplaats__huisletter", verblijfplaatsHuisletter, QPersoon.persoon.verblijfplaatsHuidig.huisletter);
        mixInNummerPath(predicate, verblijfplaatsHuisnummer, QPersoon.persoon.verblijfplaatsHuidig.huisnummer);
        mixInPostcode(predicate, verblijfplaatsPostcode, QPersoon.persoon.verblijfplaatsHuidig.postcode);
        mixInStringPath(predicate, "geslachtsaanduiding", geslachtsaanduiding != null ? geslachtsaanduiding.toString().toUpperCase() : "", QPersoon.persoon.geslachtsaanduiding);

        // als er gezocht woprdt zonder zoek parameters
        if (!predicate.hasValue()) {
            throw new ReturnException(400).setTitle("Minimale combinatie van parameters moet worden opgegeven.").setCode("paramsRequired");
        }

        mixInOverlijden(predicate, inclusiefoverledenpersonen);

        final URI selfLink = URI.create(linkTo(methodOn(BevraagPersoonController.class)
                .ingeschrevenNatuurlijkPersonen(apiVersion, expand, burgerservicenummer, fields, geboorteDatum, geboortePlaats, geslachtsaanduiding, inclusiefoverledenpersonen, naamGeslachtsnaam, naamVoornamen, verblijfplaatsGemeentevaninschrijving,
                        verblijfplaatsHuisletter, verblijfplaatsHuisnummer, verblijfplaatsHuisnummertoevoeging, verblijfplaatsIdentificatiecodenummeraanduiding, verblijfplaatsNaamopenbareruimte, verblijfplaatsPostcode, naamVoorvoegsel))
                .withSelfRel()
                .expand(new HashMap<>())
                .getHref());

        return ResponseEntity.ok(new IngeschrevenPersoonHalCollectie() //
                .links(new HalCollectionLinks()
                        .self(new HalLink().href(selfLink))
                ) //
                .embedded(new IngeschrevenPersoonHalCollectieEmbedded() //
                        .ingeschrevenpersonen(persoonRepository.findAll(predicate, PageRequest.of(0, Integer.MAX_VALUE))//
                                .stream() //
                                .peek(p -> {
                                    log.debug("fields=" + calcFields(fields));
                                })
                                .map(p -> convert(p, calcFields(fields), calcFields(expand))) //
                                .collect(Collectors.toList()) //
                        ) //
                ));
    }

    private void validateVerblijfplaatsGemeentevaninschrijving(final String verblijfplaatsGemeentevaninschrijving) {
        if (StringUtils.hasText(verblijfplaatsGemeentevaninschrijving)) {
            try {
                GemeenteCode.fromCode(verblijfplaatsGemeentevaninschrijving);
            } catch (Exception e) {
                throw new ParamException("verblijfplaats__gemeentevaninschrijving", verblijfplaatsGemeentevaninschrijving);
            }
        }
    }

    private void validateVoorvoegsel(final String naamVoorvoegsel) {
        if (StringUtils.hasText(naamVoorvoegsel) && !Util.voorvoegSels.stream().anyMatch(v -> v.equalsIgnoreCase(naamVoorvoegsel))) {
            throw new ParamException("naam__voorvoegsel", naamVoorvoegsel);
        }
    }

    @Override
    public ResponseEntity<IngeschrevenPersoonHal> ingeschrevenNatuurlijkPersoon(@PathVariable("burgerservicenummer") String burgerservicenummer, @RequestHeader(value = "api-version", required = false) String apiVersion, @RequestParam(value = "expand", required = false) String expand, @RequestParam(value = "fields", required = false) String fields) {

        if (StringUtils.isEmpty(burgerservicenummer)) {
            throw new ParamException("burgerservicenummer", null);
        }

        return persoonRepository.findByBsn(Long.valueOf(burgerservicenummer)) //
                .map(p -> convert(p, calcFields(fields), calcFields(expand))) //
                .map(ResponseEntity::ok) //
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public ResponseEntity<KindHal> ingeschrevenpersonenBurgerservicenummerkinderenUuid(String ouderBsn, String kindBsn, String apiVersion) {
        return afstammingRepository.findOne(
                QAfstamming.afstamming.ouder.bsn.eq(Long.valueOf(ouderBsn))
                        .and(QAfstamming.afstamming.kind.bsn.eq(Long.valueOf(kindBsn)))
        )
                .map(kindConverter::convert)
                .map(ResponseEntity::ok)
                .orElseThrow(NotFoundException::new)
                ;
    }

    @Override
    public ResponseEntity<OuderHal> ingeschrevenpersonenBurgerservicenummeroudersUuid(String kindBsn, String ouderBsn, String apiVersion) {
        return afstammingRepository.findOne(
                QAfstamming.afstamming.ouder.bsn.eq(Long.valueOf(ouderBsn))
                        .and(QAfstamming.afstamming.kind.bsn.eq(Long.valueOf(kindBsn)))
        )
                .map(ouderConverter::convert)
                .map(ResponseEntity::ok)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public ResponseEntity<PartnerHal> ingeschrevenpersonenBurgerservicenummerpartnersUuid(String partner1Bsn, String partner2Bsn, String apiVersion) {
        return partnerschapRepository
                .findOne(QPartnerschap.partnerschap.partner1.bsn.eq(Long.valueOf(partner1Bsn)).and(QPartnerschap.partnerschap.partner2.bsn.eq(Long.valueOf(partner2Bsn))))//
                .map(partnerConverter::convert) //
                .map(ResponseEntity::ok) //
                .orElseThrow(NotFoundException::new)
                ;
    }

    private IngeschrevenPersoonHal convert(final Persoon persoon, final Set<String> fields, final Set<String> expand) {

        log.debug("fields=" + fields + ", expand=" + expand);

        if (fields != null && fields.isEmpty()) {
            throw new FieldsException("null");
        }

        if (expand != null && expand.isEmpty()) {
            throw new ExpandException("null");
        }

        final IngeschrevenPersoonHal ingeschrevenPersoonHal = persoonConverter.convert(persoon, fields, expand);
        persoonConverter.handleExpand(ingeschrevenPersoonHal, persoon, expand);
        return ingeschrevenPersoonHal;
    }

    @Override
    public ResponseEntity<KindHalCollectie> ingeschrevenpersonenBurgerservicenummerkinderen(String burgerservicenummer, String apiVersion) {
        final Long bsn = Long.valueOf(burgerservicenummer);
        final URI selfLink = URI.create(linkTo(methodOn(BevraagPersoonController.class).ingeschrevenpersonenBurgerservicenummerkinderen(burgerservicenummer, apiVersion)).withSelfRel().expand(burgerservicenummer).getHref());
        return ResponseEntity.ok(new KindHalCollectie() //
                .links(new HalCollectionLinks().self(new HalLink().href(selfLink)))
                .embedded(new KindHalCollectieEmbedded().kinderen(
                        afstammingRepository.findAll(QAfstamming.afstamming.ouder.bsn.eq(bsn), PageRequest.of(0, Integer.MAX_VALUE))//
                                .stream()
                                .map(kindConverter::convert)//
                                .collect(Collectors.toList()) //
                )));
    }

    @Override
    public ResponseEntity<OuderHalCollectie> ingeschrevenpersonenBurgerservicenummerouders(String burgerservicenummer, String apiVersion) {

        if (StringUtils.isEmpty(burgerservicenummer)) {
            throw new ParamException("burgerservicenummer", null);
        }

        final Long bsn = Long.valueOf(burgerservicenummer);
        final URI selfLink = URI.create(linkTo(methodOn(BevraagPersoonController.class).ingeschrevenpersonenBurgerservicenummerouders(burgerservicenummer, apiVersion)).withSelfRel().expand(burgerservicenummer).getHref());
        return ResponseEntity.ok(new OuderHalCollectie() //
                .links(new HalCollectionLinks().self(new HalLink().href(selfLink)))
                .embedded(new OuderHalCollectieEmbedded().ouders(
                        afstammingRepository.findAll(QAfstamming.afstamming.kind.bsn.eq(bsn), PageRequest.of(0, Integer.MAX_VALUE))//
                                .stream()
                                .map(ouderConverter::convert)//
                                .collect(Collectors.toList()) //
                )));
    }

    @Override
    public ResponseEntity<PartnerHalCollectie> ingeschrevenpersonenBurgerservicenummerpartners(String burgerservicenummer, String apiVersion) {
        final Long bsn = Long.valueOf(burgerservicenummer);
        final URI selfLink = URI.create(linkTo(methodOn(BevraagPersoonController.class).ingeschrevenpersonenBurgerservicenummerpartners(burgerservicenummer, apiVersion)).withSelfRel().expand(burgerservicenummer).getHref());
        return ResponseEntity.ok(new PartnerHalCollectie() //
                .links(new HalCollectionLinks().self(new HalLink().href(selfLink)))
                .embedded(new PartnerHalCollectieEmbedded().partners(
                        partnerschapRepository.findAll(QPartnerschap.partnerschap.partner1.bsn.eq(bsn), PageRequest.of(0, Integer.MAX_VALUE)) //
                                .stream() //
                                .map(partnerConverter::convert) //
                                .collect(Collectors.toList()) //
                )));
    }

    private static Set<String> calcFields(final String fields) {

        if (fields == null) {
            return null;
        }

        return Arrays.stream(fields.split(",")) //
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(StringUtils::hasText)
                .collect(Collectors.toSet());
    }

    private void mixInBsn(BooleanBuilder predicate, String burgerservicenummer) {
        if (StringUtils.hasText(burgerservicenummer)) {
            try {
                predicate.and(QPersoon.persoon.bsn.eq(Long.valueOf(burgerservicenummer)));
            } catch (NumberFormatException e) {
                throw new ParamException("burgerservicenummer", burgerservicenummer);
            }
        }
    }

    private static void mixInOverlijden(BooleanBuilder predicate, Boolean inclusiefoverledenpersonen) {
        if (inclusiefoverledenpersonen == null || !inclusiefoverledenpersonen) {
            predicate.and(QPersoon.persoon.overlijdenDatum.isNull());
        }
    }

    private static void mixInNummerPath(final BooleanBuilder predicate, final Integer huisnummer,
                                        final NumberPath<Integer> nummerPath) {
        if (huisnummer != null) {
            predicate.and(nummerPath.eq(huisnummer));
        }
    }

    private static void mixInPostcode(final BooleanBuilder predicate, final String query,
                                      final StringPath stringPath) throws ReturnException {
        if (StringUtils.hasText(query)) {
            if (!query.matches("[1-9][0-9]{3}[A-Z]{2}")) {
                throw new ParamException("postcode", query);
            } else {
                predicate.and(stringPath.like(query));
            }
        }
    }

    private void mixInStringPath(BooleanBuilder predicate, String parameterName, String query, StringPath stringPath) throws
            ReturnException {
        if (StringUtils.hasText(query)) {

            if ((query.contains("*") || query.contains("?")) && !query.matches("((^\\*|^\\?(\\?)*)([\\u00C0-\\u022FA-Za-z]{2,}))") && !query.matches("([\\u00C0-\\u022FA-Za-z]{2,}(\\*$|\\?+$))") && !query.matches("((^\\*|^\\?(\\?)*)([\\u00C0-\\u022FA-Za-z]{2,}(\\*$|\\?+$)))")) {
                throw new InvalidQueryParameterWildCardException(parameterName, query);
            }

            final String convertedClause = query.replace("*", "%").replace("?", "_");

            log.debug(parameterName + " likeIgnoreCase " + convertedClause);

            predicate.and(stringPath.likeIgnoreCase(convertedClause));
        }
    }

    private void mixInGeboorteDatum(BooleanBuilder predicate, @Valid LocalDate geboorteDatum) {
        if (geboorteDatum != null) {
            final String formattedDateOfBirth = geboorteDatum.format(DBFORMATTER);
            log.debug("geboorteDatum=" + geboorteDatum + ", formatted=" + formattedDateOfBirth);
            predicate.and(QPersoon.persoon.geboorteDatum.eq(formattedDateOfBirth));
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleNotFoundException(Exception exception, WebRequest request) {

        final Foutbericht validatieFoutbericht = new Foutbericht();

        if (exception instanceof ReturnException) {

            final ReturnException returnException = (ReturnException) exception;

            validatieFoutbericht.setCode(returnException.getCode());
            validatieFoutbericht.setTitle(returnException.getTitle());
            validatieFoutbericht.setStatus(returnException.getStatusCode());
            validatieFoutbericht.setDetail(returnException.getMessage());
            validatieFoutbericht.setInvalidParams(returnException
                    .getParams()
                    .stream()
                    .map(param -> new InvalidParams().name(param.name()).code(param.code()).reason(param.value()))
                    .collect(Collectors.toList()));

        } else if (exception instanceof InvalidRequestException) {

            final InvalidRequestException invalidRequestException = (InvalidRequestException) exception;

            final Set<String> keys = new HashSet<>(Arrays.asList("validation.request.parameter.schema.minLength",
                    "validation.request.parameter.schema.maxLength",
                    "validation.request.parameter.query.unexpected",
                    "validation.request.parameter.schema.maximum",
                    "validation.request.parameter.schema.type",
                    "validation.request.parameter.schema.invalidJson",
                    "validation.request.parameter.schema.format",
                    "validation.request.parameter.schema.pattern",
                    "validation.request.parameter.schema.enum"
            ));

            invalidRequestException.getValidationReport().getMessages().stream()
                    .peek(m -> {
                        if (!keys.contains(m.getKey())) {
                            log.warn("Unhandled validator key: " + m.getKey());
                        }
                    })
                    .filter(m -> keys.contains(m.getKey()))
                    .forEach(m -> m.getContext().ifPresent(mc -> {

                        mc.getRequestPath().ifPresent(p -> validatieFoutbericht.setInstance(URI.create(p)));

                        //log.debug("key=" + m.getKey());

                        mc.getParameter().ifPresent(p -> {
                            if (p.getSchema() != null) {
                                log.debug("par " + p.getName() + " is of type " + p.getSchema().getType());
                            }

                            String code = null;

                            if (m.getKey().equals("validation.request.parameter.schema.type") ||
                                    m.getKey().equals("validation.request.parameter.schema.invalidJson")) {
                                if (p.getSchema() != null && p.getSchema().getType() != null) {
                                    code = p.getSchema().getType();
                                }
                            } else if (m.getKey().equals("validation.request.parameter.query.unexpected")) {
                                code = "unknownParam";
                            } else {
                                code = m.getKey().substring(m.getKey().lastIndexOf('.') + 1);
                            }

                            validatieFoutbericht.setTitle("Een of meerdere parameters zijn niet correct");
                            validatieFoutbericht.addInvalidParamsItem(new InvalidParams()
                                    .name(p.getName())
                                    .reason(m.getMessage())
                                    .code(code)
                            );
                            validatieFoutbericht.setStatus(400);
                        });
                    }));

        } else {
            log.error("Unknown exception type: " + exception.getClass());
            validatieFoutbericht.setTitle(exception.getMessage());
            validatieFoutbericht.setDetail(exception.getLocalizedMessage());
        }

        return ResponseEntity
                .status(validatieFoutbericht.getStatus())
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(validatieFoutbericht)
                ;
    }

}
