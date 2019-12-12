package nl.vng.realisatie.haalcentraal.core.enums;

import lombok.Getter;
import nl.vng.realisatie.haalcentraal.core.model.Persoon;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * AanschrijfwijzeEnum
 */
@Getter
public enum AanschrijfwijzeEnum {

    VL("vl"),//voorletters
    VV("vv"),//voorvoegselGeslachtsnaam
    GN("gn"),//geslachtsnaam
    VP("vp"),//voorvoegselGeslachtsnaam partner
    GP("gp"),//geslachtsnaam partner
    AT("at"),//adelijke titel
    AP("ap"),//adelijke titel partner
    PK("pk"),//predikaat
    STREEPJE("-");//streepje

    private String code;

    AanschrijfwijzeEnum(final String code) {
        this.code = code;
    }

    public static List<AanschrijfwijzeEnum> calcSamenstelling(final Persoon persoon) {
        List<AanschrijfwijzeEnum> list = Collections.emptyList();
        if (persoon != null && StringUtils.hasText(persoon.getAanduidingAanschrijving())) {
            String aanduiding = persoon.getAanduidingAanschrijving();
            switch (aanduiding) {
                case "E":
                    if (StringUtils.hasText(persoon.getAdellijketitelPredikaat())
                            && AdellijkeTitelPredikaat.fromCode(persoon.getAdellijketitelPredikaat()).getSoort().equals("predikaat")) {
                        list = new LinkedList<>(Arrays.asList(AT, VL, VV, GN));
                    } else {
                        list = new LinkedList<>(Arrays.asList(VL, AT, VV, GN));
                    }
                    break;
                case "N":
                    list = new LinkedList<>(Arrays.asList(VL, AT, VV, GN, STREEPJE, AP, VP, GP));
                    break;
                case "P":
                    list = new LinkedList<>(Arrays.asList(VL, AP, VP, GP));
                    break;
                case "V":
                    list = new LinkedList<>(Arrays.asList(VL, AP, VP, GP, STREEPJE, AT, VV, GN));
                    break;
                default:
            }
        }
        return list;
    }
}