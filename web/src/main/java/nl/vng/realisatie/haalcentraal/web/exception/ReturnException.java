package nl.vng.realisatie.haalcentraal.web.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * ReturnException
 */
@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
public class ReturnException extends RuntimeException {

    @Getter
    @Accessors(fluent = true)
    @RequiredArgsConstructor
    public static class Param {
        private final String name;
        private final String code;
        private final String value;
    }

    private final List<Param> params = new ArrayList<>();

    private String title;

    private int statusCode = 400;

    private String code;

    public ReturnException(int statusCode) {
        this.statusCode = statusCode;
    }

    public ReturnException addParam(final Param param) {
        this.params.add(param);
        return this;
    }

}
