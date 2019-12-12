package nl.vng.realisatie.haalcentraal.web.converter;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.vng.realisatie.haalcentraal.core.enums.GemeenteCode;
import nl.vng.realisatie.haalcentraal.core.enums.LandCode;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.Waardetabel;
import nl.vng.realisatie.haalcentraal.web.controller.BevraagPersoonController;
import nl.vng.realisatie.haalcentraal.web.exception.ExpandException;
import nl.vng.realisatie.haalcentraal.web.exception.FieldsException;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * BaseConverterImpl
 */
@Slf4j
public abstract class BaseConverterImpl<T, S> implements BaseConverter<T, S> {

    protected static final Handler<?, ?> noop = (target, source, fields, expand) -> {
    };

    @FunctionalInterface
    interface Handler<T, S> {
        void handle(T target, S source, Set<String> fields, Set<String> expand);
    }

    @Getter
    private final Map<String, Handler<T, S>> map = new HashMap<>();

    @Override
    public T convert(final S source, T result, final Set<String> fields, final Set<String> expand) {

        log.debug("fields3=" + fields + ", expand=" + expand);

        validate(fields);
        (fields == null || fields.isEmpty() ? getMap().keySet() : fields).stream() //
                .map(s -> {
                    if (s.contains(".")) {
                        return s.substring(0, s.indexOf("."));
                    } else {
                        return s;
                    }
                }).distinct()
                .forEach(e -> getMap().get(e).handle(result, source, PersoonConverterImpl.leaf(fields, e), expand));

        return postConvert(source, result, fields);

    }

    public void validate(final Set<String> fields) {
        if (fields != null) {
            fields.stream() //
                    .filter(field ->
                            !getMap().keySet().contains(field) && //
                            getMap().keySet().stream().noneMatch(i -> field.startsWith(i + ".")) //
                    ) //
//                    .peek(c -> log.debug("checking: " + c + ", keys=" + getMap().keySet()))
                    .findAny().ifPresent(entry -> {
                        if (this instanceof PersoonConverterImpl) {
                            throw new FieldsException(entry);
                        } else {
                            throw new ExpandException(entry);
                        }
                    });
        }

    }

    public T postConvert(final S source, T result, final Set<String> fields) {
        return result;
    }

    public static Waardetabel calcLand(final String code) {
        Waardetabel result = null;
        if (StringUtils.hasText(code)) {
            result = new Waardetabel();
            try {
                final LandCode landCode = LandCode.fromCode(code);
                result = result.code(landCode.getCode()).omschrijving(landCode.getNaam());
            } catch (final IllegalArgumentException e) {
                result.omschrijving(code);
            }
        }
        return result;
    }

    public static Waardetabel calcPlaats(final String code) {
        Waardetabel result = null;
        if (StringUtils.hasText(code)) {
            result = new Waardetabel();
            try {
                final GemeenteCode gemeenteCode = GemeenteCode.fromCode(code);
                result.code(gemeenteCode.getCode()).omschrijving(gemeenteCode.getNaam());
            } catch (final IllegalArgumentException e) {
                try {
                    final GemeenteCode gemeenteCode = GemeenteCode.fromNaam(code);
                    result.code(gemeenteCode.getCode()).omschrijving(gemeenteCode.getNaam());
                } catch (final IllegalArgumentException f) {
                    result.omschrijving(code);
                    result.code(code);
                }
            }
        }
        return result;
    }

    public static Optional<LocalDate> convertLocalDatum(final String dateString) {
        try {
            if (StringUtils.hasText(dateString)) {
                return Optional.of(LocalDate.from(BevraagPersoonController.DBFORMATTER.parse(dateString)));
            } else {
                return Optional.empty();
            }
        } catch (java.time.DateTimeException e) {
            return Optional.empty();
        }
    }

    public static String calcVoorletters(String voornamen) {
        if (StringUtils.hasText(voornamen)) {
            StringBuilder voorletters = new StringBuilder();
            String[] namen = voornamen.split(" ");
            for (String naam : namen) {
                if (naam.length() > 1) {
                    voorletters.append(Character.toUpperCase(naam.charAt(0)) + ".");
                } else {
                    voorletters.append(naam.toUpperCase() + " ");
                }
            }
            return voorletters.toString().trim();
        } else {
            return null;
        }
    }

}
