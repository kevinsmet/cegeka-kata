import org.apache.commons.lang.math.RandomUtils;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Kaartenpak {

    private List<Kaart> kaarten;

    public Kaartenpak(List<Kaart> kaarten) {
        this.kaarten = newArrayList(kaarten);
    }

    public Kaart pakRandomKaart() {
        if (kaarten.isEmpty()) {
            throw new RuntimeException("No more cards in kaartenpak");
        }
        Kaart kaart = kaarten.get(RandomUtils.nextInt(kaarten.size()));
        kaarten.remove(kaart);
        return kaart;
    }

    public List<Kaart> pakRandomKaarten(int aantal) {
        List<Kaart> gepakteKaarten = newArrayList();
        for (int i = 0; i<aantal; i++){
            gepakteKaarten.add(pakRandomKaart());
        }
        return gepakteKaarten;
    }

    public List<Kaart> getOverblijvendeKaarten() {
        return kaarten;
    }
}
