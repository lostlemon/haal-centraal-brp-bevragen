package nl.vng.realisatie.haalcentraal.core.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * RedenOpnemen
 */
@AllArgsConstructor
public enum RedenOpnemen {
    A000("000", "ONBEKEND"),
    A001("001", "Wet op het Nederlanderschap 1892,art.1, lid 1a");

    private String code;
    private String omschrijving;

    public static RedenOpnemen fromCode(final String code) {
        return Arrays.stream(values()).filter(i -> i.code.equals(code)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
