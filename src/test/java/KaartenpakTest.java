import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;

public class KaartenpakTest {

    @Test
    public void newKaartenPak_bevatKaarten() {
        List<Kaart> kaarten = newArrayList(new Kaart(Kleur.GROEN, 4), new Kaart(Kleur.GROEN, 4));
        Kaartenpak kaartenpak = new Kaartenpak(kaarten);

        Kaart randomKaart = kaartenpak.pakRandomKaart();
        List<Kaart> overblijvendeKaarten = kaartenpak.getOverblijvendeKaarten();
        assertThat(overblijvendeKaarten).hasSize(1);
        assertThat(kaarten).contains(randomKaart);
        overblijvendeKaarten.add(randomKaart);
        assertThat(overblijvendeKaarten).containsAll(kaarten);
    }

    @Test
    public void pakRandomKaartMeerdere() throws Exception {
        List<Kaart> kaarten = newArrayList(new Kaart(Kleur.GROEN, 4), new Kaart(Kleur.GROEN, 4), new Kaart(Kleur.GROEN, 4),new Kaart(Kleur.GROEN, 4));
        Kaartenpak kaartenpak = new Kaartenpak(kaarten);

        List<Kaart> actual = kaartenpak.pakRandomKaarten(3);

        assertThat(actual).hasSize(3);
        assertThat(kaartenpak.getOverblijvendeKaarten()).hasSize(1);
        assertThat(kaarten).containsAll(actual);
    }
}