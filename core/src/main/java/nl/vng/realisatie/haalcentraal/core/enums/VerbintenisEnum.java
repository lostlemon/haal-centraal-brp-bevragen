package nl.vng.realisatie.haalcentraal.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.SoortVerbintenisEnum;

import java.util.Arrays;

/**
 * VerbintenisEnum
 */
@AllArgsConstructor
@Getter
public enum VerbintenisEnum {
    H(SoortVerbintenisEnum.HUWELIJK),

    P(SoortVerbintenisEnum.GEREGISTREERD_PARTNERSCHAP);

    private SoortVerbintenisEnum soortVerbintenis;

    public static SoortVerbintenisEnum fromCode(final String code) {
        return Arrays.stream(values()).filter(i -> i.name().equals(code)).findFirst().orElseThrow(() -> new IllegalArgumentException("Onbekende code : " + code)).getSoortVerbintenis();
    }

}
