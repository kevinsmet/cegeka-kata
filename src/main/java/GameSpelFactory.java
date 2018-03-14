import com.google.common.collect.Lists;

import java.util.List;

public class GameSpelFactory {

    private final static List<Kaart> KAARTEN;

    static {
        KAARTEN = Lists.newArrayList();
        for(int i = 0; i < 49; i++){
            KAARTEN.add(new Kaart(Kleur.GROEN, 4));
        }
    }

    public GameSpel createNewGame() {
        return new GameSpel(KAARTEN);
    }
}
