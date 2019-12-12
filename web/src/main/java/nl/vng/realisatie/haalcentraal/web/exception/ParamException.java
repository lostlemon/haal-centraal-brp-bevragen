package nl.vng.realisatie.haalcentraal.web.exception;

/**
 * ParamException
 */
public class ParamException extends ReturnException {

    public ParamException(final String param, final String value) {
        this(param, param, value);
    }

    public ParamException(final String param, final String code, final String value) {
        setTitle("Een of meerdere parameters zijn niet correct");
        addParam(new Param(param, code, value));
    }

}
