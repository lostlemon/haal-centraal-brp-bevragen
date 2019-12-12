package nl.vng.realisatie.haalcentraal.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.RedenOpschortingBijhoudingEnum;

import java.util.Arrays;

/**
 * OpschortingBijhouding
 */
@AllArgsConstructor
@Getter
public enum OpschortingEnum {
    O(RedenOpschortingBijhoudingEnum.OVERLIJDEN),
    E(RedenOpschortingBijhoudingEnum.EMIGRATIE),
    M(RedenOpschortingBijhoudingEnum.MINISTERIEEL_BESLUIT),
    R(RedenOpschortingBijhoudingEnum.PL_AANGELEGD_IN_DE_RNI),
    F(RedenOpschortingBijhoudingEnum.FOUT);

    private RedenOpschortingBijhoudingEnum reden;

    public static RedenOpschortingBijhoudingEnum fromCode(final String code) {
        final OpschortingEnum opschortingBijhouding = Arrays.stream(values())
                .filter(i -> i.name().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Onbekende code: " + code));
        return opschortingBijhouding.getReden();
    }

}
