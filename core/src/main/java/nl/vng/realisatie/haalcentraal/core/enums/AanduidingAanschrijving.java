package nl.vng.realisatie.haalcentraal.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * AanduidingAanschrijving
 */
@AllArgsConstructor
@Getter
public enum AanduidingAanschrijving {

    E, P, V, N;

    public static AanduidingAanschrijving fromCode(final String code) {
        return Arrays.stream(values()).filter(i -> i.name().equals(code)).findFirst().orElseThrow(() -> new IllegalArgumentException("Onbekende code" + code));
    }

}
