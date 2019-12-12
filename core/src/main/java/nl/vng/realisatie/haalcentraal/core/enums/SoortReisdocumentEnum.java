package nl.vng.realisatie.haalcentraal.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * SoortReisdocumentEnum
 */
@AllArgsConstructor
@Getter
public enum SoortReisdocumentEnum {

    BJ("Identiteitskaart(toeristenkaart) BJ"),
    EK("Europese identiteitskaart"),
    IA("Identiteitskaart(toeristenkaart) A"),
    IB("Identiteitskaart(toeristenkaart) B"),
    IC("Identiteitkaart(toeristenkaart) C"),
    ID("Gemeentelijke Identiteitskaart"),
    KE("Kaart met paspoortboekje, 64 pag"),
    KN("Kaart zonder paspoortboekje, 32 pag"),
    LP("Laissez-passer"),
    NB("Nooddocument(model reisdocument vreemdelingen)"),
    NI("Nederlandse identiteitskaart"),
    NN("Noodpaspoort(model nationaal paspoort)"),
    NP("Noodpaspoort"),
    NV("Nooddocument(model reisdocument vluchtelingen)"),
    PB("Reisdocument voor vreemdelingen"),
    PD("Diplomatiek paspoort"),
    PF("Faciliteitenpaspoort"),
    PN("Nationaal paspoort"),
    PV("Reisdocument voor vluchtelingen"),
    PZ("Dienstpaspoort"),
    R1("Reisdocument ouder1"),
    R2("Reisdocument ouder2"),
    RD("Reisdocument voogd"),
    RM("Riesdocument moeder"),
    RV("Reisdocument vader"),
    TE("Tweede paspoort(zakenpaspoort)"),
    TN("Tweede paspoort"),
    ZN("Naionaal paspoort(zakenpaspoort)");

    private String omschrijving;

}
