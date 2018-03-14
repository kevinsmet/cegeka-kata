import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;

public class GameSpel {

    private final List<Speler> spelers;
    private List<Kaart> resterendeKaarten;

    public GameSpel(List<Kaart> kaartenLijst) {
        Kaartenpak kaartenpak = new Kaartenpak(kaartenLijst);
        int aantal = kaartenLijst.size() > 28 ? 7 : kaartenLijst.size()/4 - 1;
        this.spelers = newArrayList(
                new Speler(kaartenpak.pakRandomKaarten(aantal), kaartenpak.pakRandomKaart()),
                new Speler(kaartenpak.pakRandomKaarten(aantal), kaartenpak.pakRandomKaart()),
                new Speler(kaartenpak.pakRandomKaarten(aantal), kaartenpak.pakRandomKaart()),
                new Speler(kaartenpak.pakRandomKaarten(aantal), kaartenpak.pakRandomKaart()));
        this.resterendeKaarten = kaartenpak.getOverblijvendeKaarten();
    }

    public List<Speler> getSpelers() {
        return spelers;
    }

    public List<Kaart> getResterendeKaarten() {
        return this.resterendeKaarten;
    }

    public Speler getStartSpeler() {
        Speler winnendeSpeler = spelers.stream().max(Speler::winsFrom).orElseThrow(() -> new IllegalArgumentException("iets"));
        return spelers.get((spelers.indexOf(winnendeSpeler)+1)%4);
    }
}
