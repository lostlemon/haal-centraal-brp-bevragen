package nl.vng.realisatie.haalcentraal.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.OuderAanduidingEnum;

import java.util.Arrays;

/**
 * OuderAanduiding
 */
@Getter
@AllArgsConstructor
public enum OuderAanduiding {
    OUDER1(1, OuderAanduidingEnum.OUDER1),
    OUDER2(2, OuderAanduidingEnum.OUDER2);

    private Integer code;
    private OuderAanduidingEnum ouderAanduidingEnum;

    public static OuderAanduidingEnum fromCode(final Integer code) {
        return Arrays.stream(values())
                .filter(v -> v.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Onbekende OuderAanduiding code " + code)).getOuderAanduidingEnum();
    }

}

