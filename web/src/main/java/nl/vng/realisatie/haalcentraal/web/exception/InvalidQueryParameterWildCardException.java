package nl.vng.realisatie.haalcentraal.web.exception;

/**
 * InvalidQueryParameterWildCardException
 */
public class InvalidQueryParameterWildCardException extends ParamException {

    public InvalidQueryParameterWildCardException(final String paramName, final String value) {
        super(paramName, "wildcard", value);
    }

}
