package nl.vng.realisatie.haalcentraal.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * AdellijkeTitelPredikaat
 */
@AllArgsConstructor
@Getter
public enum AdellijkeTitelPredikaat {

    B("B", "Baron", "titel", "Hoogwelgeboren heer"),
    BS("BS", "Barones", "titel", "Hoogwelgeboren vrouw"),
    G("G", "Graaf", "titel", "Hoogwelgeboren heer"),
    GI("GI", "Gravin", "titel", "Hoogwelgeboren vrouw"),
    H("H", "Hertog", "titel", "Hoogwelgeboren heer"),
    HI("HI", "Hertogin", "titel", "Hoogwelgeboren vrouw"),
    JH("JH", "Jonkheer", "predikaat", "Hoogwelgeboren heer"),
    JV("JV", "Jonkvrouw", "predikaat", "Hoogwelgeboren vrouw"),
    M("M", "Markies", "titel", "Hoogwelgeboren heer"),
    MI("MI", "Markiezin", "titel", "Hoogwelgeboren vrouw"),
    P("P", "Prins", "titel", "Hoogheid"),
    PS("PS", "Prinses", "titel", "Hoogheid"),
    R("R", "Ridder", "titel", "Hoogwelgeboren heer");
    private String code;
    private String omschrijving;
    private String soort;
    private String aanhef;

    public static AdellijkeTitelPredikaat fromCode(final String code) {
        AdellijkeTitelPredikaat result = null;
        if (StringUtils.hasText(code)) {
            result = Arrays.stream(values()).filter(v -> v.code.equals(code)).findFirst().orElseThrow(() -> new IllegalArgumentException("onbekende adellijkeTitelPredikaat code:" + code));
        }
        return result;
    }

    public static AdellijkeTitelPredikaat fromOmschrijving(final String omschrijving) {
        AdellijkeTitelPredikaat result = null;
        if (StringUtils.hasText(omschrijving)) {
            result = Arrays.stream(values()).filter(v -> v.omschrijving.equalsIgnoreCase(omschrijving)).findFirst().orElseThrow(() -> new IllegalArgumentException("onbekende adellijkeTitelPredikaat omschrijving:" + omschrijving));
        }
        return result;
    }

    public static String getOmschrijvingFromCode(final String code) {
        return fromCode(code).getOmschrijving();
    }

    public static String getSoortFromCode(final String code) {
        return fromCode(code).getSoort();
    }

}
