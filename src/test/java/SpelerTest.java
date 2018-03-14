import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class SpelerTest {
    @Test
    public void winsFrom_GivenColorHigher_Then1() throws Exception {
        Speler speler1 = new Speler(newArrayList(), new Kaart(Kleur.GEEL, 5));
        Speler speler2 = new Speler(newArrayList(), new Kaart(Kleur.GROEN, 5));
        assertThat(speler1.winsFrom(speler2)).isEqualTo(1);
    }

    @Test
    public void winsFrom_GivenColorLower_ThenMinus1() throws Exception {
        Speler speler1 = new Speler(newArrayList(), new Kaart(Kleur.GEEL, 5));
        Speler speler2 = new Speler(newArrayList(), new Kaart(Kleur.GROEN, 5));
        assertThat(speler2.winsFrom(speler1)).isEqualTo(-1);
    }

    @Test
    public void winsFrom_GivenNumberHigher_Then1() throws Exception {
        Speler speler1 = new Speler(newArrayList(), new Kaart(Kleur.GROEN, 5));
        Speler speler2 = new Speler(newArrayList(), new Kaart(Kleur.GROEN, 4));
        assertThat(speler1.winsFrom(speler2)).isEqualTo(1);
    }

    @Test
    public void winsFrom_GivenNumberLower_ThenMinus1() throws Exception {
        Speler speler1 = new Speler(newArrayList(), new Kaart(Kleur.GROEN, 5));
        Speler speler2 = new Speler(newArrayList(), new Kaart(Kleur.GROEN, 4));
        assertThat(speler2.winsFrom(speler1)).isEqualTo(-1);
    }

}