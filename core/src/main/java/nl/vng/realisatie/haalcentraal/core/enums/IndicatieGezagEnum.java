package nl.vng.realisatie.haalcentraal.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.vng.realisatie.haalcentraal.rest.generated.model.bip.IndicatieGezagMinderjarigeEnum;

import java.util.Arrays;

/**
 * IndicatieGezagMinderjarigeEnum
 */
@AllArgsConstructor
@Getter
public enum IndicatieGezagEnum {
    _1("1", IndicatieGezagMinderjarigeEnum.OUDER1),
    _2("2", IndicatieGezagMinderjarigeEnum.OUDER2),
    _D("D", IndicatieGezagMinderjarigeEnum.DERDEN),
    _1D("1D", IndicatieGezagMinderjarigeEnum.OUDER1_EN_DERDE),
    _2D("2D", IndicatieGezagMinderjarigeEnum.OUDER2_EN_DERDE),
    _12("12", IndicatieGezagMinderjarigeEnum.OUDER1_EN_OUDER2);

    private String value;
    private IndicatieGezagMinderjarigeEnum indicatieGezagMinderjarige;

    public static IndicatieGezagMinderjarigeEnum getIndicatieGezagMinderjarige(final String value) {
        return Arrays.stream(values()).filter(i -> i.value.equals(value)).findFirst().orElseThrow(IllegalArgumentException::new).getIndicatieGezagMinderjarige();
    }

}
