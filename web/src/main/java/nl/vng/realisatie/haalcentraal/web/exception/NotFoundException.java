package nl.vng.realisatie.haalcentraal.web.exception;

/**
 * NotFoundException
 */
public class NotFoundException extends ReturnException {

    public NotFoundException() {
        super(404);
        setTitle("Opgevraagde resource bestaat niet.");
        setCode("notFound");
    }

}
