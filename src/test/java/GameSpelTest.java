import com.google.common.collect.Lists;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class GameSpelTest {

    @Test
    public void newGameSpel_ShouldVerdeelKaartenOverSpelers() throws Exception {
        List<Kaart> kaarten = Lists.newArrayList();

        IntStream.range(0, 49).forEach(i -> kaarten.add(new Kaart(Kleur.GROEN, 4)));

        GameSpel gameSpel = new GameSpel(kaarten);

        List<Kaart> verdeeldeKaarten = newArrayList();
        gameSpel.getSpelers().forEach(speler -> verdeeldeKaarten.addAll(speler.getKaarten()));
        gameSpel.getSpelers().forEach(speler -> verdeeldeKaarten.addAll(speler.getTableauKaarten()));
        verdeeldeKaarten.addAll(gameSpel.getResterendeKaarten());
        assertThat(verdeeldeKaarten).containsAll(kaarten);

        gameSpel.getSpelers().forEach(speler -> assertThat(speler.getTableauKaarten()).hasSize(1));
    }

    @Test
    public void newGameSpel_GameWithMoreThan28Cards() {
        List<Kaart> kaarten = Lists.newArrayList();

        IntStream.range(0, 49).forEach(i -> kaarten.add(new Kaart(Kleur.GROEN, 4)));

        GameSpel gameSpel = new GameSpel(kaarten);

        Assertions.assertThat(kaarten).hasSize(49);
        gameSpel.getSpelers().forEach(s -> Assertions.assertThat(s.getKaarten()).hasSize(7));

    }

    @Test
    public void getStartSpeler_HoogsteCijfer() throws Exception {
        GameSpel gameSpel = new GameSpel(Lists.newArrayList(new Kaart(Kleur.GROEN, 1), new Kaart(Kleur.GROEN, 2), new Kaart(Kleur.GROEN, 3), new Kaart(Kleur.GROEN, 4)));

        Speler startSpeler = gameSpel.getStartSpeler();

        Speler winnendeSpeler = gameSpel.getSpelers().stream().filter(speler -> speler.getTableauKaarten().get(0).getCijfer() == 4).findFirst().orElseThrow(()->new IllegalArgumentException("wtf"));
        int index = gameSpel.getSpelers().indexOf(winnendeSpeler)+1 % 4;
        assertThat(gameSpel.getSpelers().indexOf(startSpeler)).isEqualTo(index);
    }

    @Test
    public void getStartSpeler_HoogsteKleur() throws Exception {
        GameSpel gameSpel = new GameSpel(Lists.newArrayList(new Kaart(Kleur.GROEN, 1), new Kaart(Kleur.GROEN, 2), new Kaart(Kleur.GROEN, 4), new Kaart(Kleur.GEEL, 4)));

        Speler startSpeler = gameSpel.getStartSpeler();

        Speler winnendeSpeler = gameSpel.getSpelers().stream().filter(speler -> speler.getTableauKaarten().get(0).getCijfer() == 4 && speler.getTableauKaarten().get(0).getKleur().equals(Kleur.GEEL)).findFirst().orElseThrow(()->new IllegalArgumentException("wtf"));
        int index = gameSpel.getSpelers().indexOf(winnendeSpeler)+1 % 4;
        assertThat(gameSpel.getSpelers().indexOf(startSpeler)).isEqualTo(index);
    }
}