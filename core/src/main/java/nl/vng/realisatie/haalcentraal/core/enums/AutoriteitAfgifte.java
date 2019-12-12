package nl.vng.realisatie.haalcentraal.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AutoriteitAfgifte
 */
@AllArgsConstructor
@Getter
public enum AutoriteitAfgifte {
    ONBEKEND("", "onbekend"),
    B("B", "Burgemeester van gecodeerd genoemde gemeente"),
    BI("BI", "Minister van Binnenlandse Zaken"),
    BI0518("BI0518", "Minister van Binnenlandse Zaken");

    private String code;
    private String omschrijving;

}
