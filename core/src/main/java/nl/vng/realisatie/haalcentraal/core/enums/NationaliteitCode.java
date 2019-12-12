package nl.vng.realisatie.haalcentraal.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * NationaliteitCode
 */
@AllArgsConstructor
@Getter
public enum NationaliteitCode {
    A0000("0000", "onbekend"),
    A0001("0001", "Nederlandse"),
    A0027("0027", "Slowaakse"),
    A0030("0030", "Georgische"),
    A0034("0034", "Oekraïense"),
    A0041("0041", "Russische"),
    A0042("0042", "Sloveense"),
    A0052("0052", "Belgische"),
    A0053("0053", "Bulgaarse"),
    A0055("0055", "Burger van de Bondsrepubliek Duitsland"),
    A0057("0057", "Franse"),
    A0060("0060", "Brits burger"),
    A0063("0063", "IJslandse"),
    A0068("0068", "Maltese"),
    A0072("0072", "Poolse"),
    A0074("0074", "Roemeense"),
    A0077("0077", "Spaanse"),
    A0080("0080", "Zweedse"),
    A0100("0100", "Algerijnse"),
    A0112("0112", "Egyptische"),
    A0149("0149", "Somalische"),
    A0250("0250", "Argentijnse"),
    A0251("0251", "Barbadaanse"),
    A0252("0252", "Boliviaanse"),
    A0263("0263", "Surinaamse"),
    A0307("0307", "Chinese"),
    A0312("0312", "Indiase"),
    A0313("0313", "Indonesische"),
    A0315("0315", "Iraanse"),
    A0317("0317", "Japanse"),
    A0324("0324", "Maldivische"),
    A0337("0337", "Thaise"),
    A0339("0339", "Turkse"),
    A0399("0399", "onbekend"),
    A0499("0499", "Staatloos");

    private String code;
    private String naam;

    public static NationaliteitCode fromCode(final String code) {
        return Arrays.stream(values()).filter(i -> i.code.equals(code)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
