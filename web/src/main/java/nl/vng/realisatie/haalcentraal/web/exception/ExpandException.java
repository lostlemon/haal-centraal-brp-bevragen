package nl.vng.realisatie.haalcentraal.web.exception;

/**
 * ExpandException
 */
public class ExpandException extends ParamException {

    public ExpandException(final String value) {
        super("expand", value);
    }

}
