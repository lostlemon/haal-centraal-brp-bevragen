package nl.vng.realisatie.haalcentraal.core.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * AanduidingInhoudingVermissingReisdocumentEnum
 */
@AllArgsConstructor
public enum AanduidingInhoudingVermissingReisdocumentEnum {

    INGEHOUDEN_OF_INGELEVERD("ingehouden_of_ingeleverd"),

    VERMIST("vermist"),

    RECHTSWEGE("rechtswege"),

    NIET_IN_BEZIT_VAN("niet_in_bezit_van");

    private String value;

    public AanduidingInhoudingVermissingReisdocumentEnum withValue(final String value) {
        return Arrays.stream(values()).filter(i -> i.value.startsWith(value)).findFirst().orElse(null);
    }

}
