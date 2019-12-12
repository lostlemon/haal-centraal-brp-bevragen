package nl.vng.realisatie.haalcentraal.core.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * AanduidingBijzonderNederlanderschapEnum
 */
@AllArgsConstructor
public enum AanduidingBijzonderNederlanderschapEnum {

    BEHANDELD_ALS_NEDERLANDER("behandeld_als_nederlander"),
    VASTGESTELD_NIET_NEDERLANDER("vastgesteld_niet_nederlander");

    private String value;

    public AanduidingBijzonderNederlanderschapEnum withValue(final String value) {
        return Arrays.stream(values()).filter(i -> i.value.startsWith(value)).findFirst().orElse(null);
    }

}
