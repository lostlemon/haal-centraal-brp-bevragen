package nl.vng.realisatie.haalcentraal.web.stepdefs;

import io.cucumber.java.nl.Dan;
import io.cucumber.java.nl.En;
import org.springframework.http.HttpStatus;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

/**
 * LinksStepDefinitions
 */
public class LinksStepDefinitions extends Defaults {

    @Dan("bevat attribuut _links.self.href {}")
    public void bevat_attribuut__links_self_href_api_bevragingen_ingeschreven_personen_v_ingeschrevenpersonen(final String contains) {

        URI link = null;

        if (world.persoonHal != null) {
            link = world.persoonHal.getLinks().getSelf().getHref();
        } else if (world.persoonCollectie != null) {
            link = world.persoonCollectie.getLinks().getSelf().getHref();
        }

        assertThat(link, is(not(nullValue())));
        assertThat(link.toString(), containsString(contains));
    }

    @Dan("begint attribuut _links.self.href {}")
    public void begint_attribuut__links_self_href_api_bevragingen_ingeschreven_personen_v_ingeschrevenpersonen(final String contains) {

        URI link = null;

        if (world.persoonHal != null) {
            link = world.persoonHal.getLinks().getSelf().getHref();
        } else if (world.persoonCollectie != null) {
            link = world.persoonCollectie.getLinks().getSelf().getHref();
        }

        assertThat(link, is(not(nullValue())));
        if (contains.equals("met http:// of https://")) {
            assertThat(link.toString(), either(startsWith("http://")).or(startsWith("https://")));
        } else {
            assertThat(link.toString(), startsWith(contains));
        }
    }

    @Dan("eindigt attribuut _links.self.href met {}")
    public void eindigt_attribuut__links_self_href_api_bevragingen_ingeschreven_personen_v_ingeschrevenpersonen(final String contains) {
        assertThat(world.persoonHal.getLinks().getSelf(), is(not(nullValue())));
        assertThat(world.persoonHal.getLinks().getSelf().getHref().toString(), endsWith(contains));
    }

    @Dan("levert een GET request naar de url in _links.self.href een antwoord")
    public void levert_een_GET_request_naar_de_url_in__links_self_href_een_antwoord() throws URISyntaxException {
        ingeschrevenNatuurlijkPersoonRestTemplateUri(world.persoonCollectie.getLinks().getSelf().getHref());
        assertThat(world.statusCode, is(equalTo(HttpStatus.OK.value())));
    }

    @En("eindigt attribuut _links.self.href niet op {}")
    public void eindigtAttribuut_linksSelfHrefNietOp(String endswith) {
        assertThat(world.persoonHal.getLinks().getSelf().getHref().toString(), not(endsWith(endswith)));
    }

    @Dan("bevat het attribuut _links.self.href in elke van de gevonden _embedded.partners {}")
    public void bevat_het_attribuut__links_self_href_in_elke_van_de_gevonden__embedded_partners_api_bevragingen_ingeschreven_personen_v_ingeschrevenpersonen_partners(String contains) {
        world.persoonHal.getEmbedded().getPartners().stream().forEach(p -> {
            assertThat(p.getLinks().getSelf().getHref().toString(), containsString(contains));
        });
    }

    @Dan("eindigt het attribuut _links.self.href in geen van de gevonden _embedded.partners op {}")
    public void eindigt_het_attribuut__links_self_href_in_geen_van_de_gevonden__embedded_partners_op(String ends) {
        world.persoonHal.getEmbedded().getPartners().stream().forEach(p -> {
            assertThat(p.getLinks().getSelf().getHref().toString(), endsWith(ends));
        });
    }

}
