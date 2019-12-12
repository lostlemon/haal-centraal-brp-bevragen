package nl.vng.realisatie.haalcentraal.web.converter;

import nl.vng.realisatie.haalcentraal.web.exception.ReturnException;

import java.util.Set;

/**
 * BaseConverter
 */
public interface BaseConverter<T, S> {

    default T convert(final S source) throws ReturnException {
        return convert(source, null, null);
    }

    default T convert(final S source, final Set<String> fields, final Set<String> expand) throws ReturnException {
        return convert(source, createTarget(source, fields), fields, expand);
    }

    T convert(final S source, final T target, final Set<String> fields, final Set<String> expand) throws ReturnException;

    T createTarget(final S source, final Set<String> fields);

    void validate(final Set<String> fields);

}
