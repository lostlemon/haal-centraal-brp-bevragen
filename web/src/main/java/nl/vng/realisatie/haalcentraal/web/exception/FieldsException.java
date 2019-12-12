package nl.vng.realisatie.haalcentraal.web.exception;

/**
 * FieldsException
 */
public class FieldsException extends ParamException {

    public FieldsException(final String value) {
        super("fields", value);
    }

}
