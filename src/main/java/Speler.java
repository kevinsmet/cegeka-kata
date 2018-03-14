import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Speler {

    private final List<Kaart> kaarten;
    private final List<Kaart> tableauKaarten;


    public Speler(List<Kaart> kaarten, Kaart tableauKaart) {
        this.kaarten = kaarten;
        this.tableauKaarten = newArrayList(tableauKaart);
    }

    public List<Kaart> getKaarten() {
        return kaarten;
    }

    public List<Kaart> getTableauKaarten() {
        return tableauKaarten;
    }

    public int winsFrom(Speler otherPlayer) {
        int cijferSpeler = tableauKaarten.get(0).getCijfer();
        int cijferOtherSpeler = otherPlayer.getTableauKaarten().get(0).getCijfer();
        Kleur kleurSpeler = tableauKaarten.get(0).getKleur();
        Kleur kleurOtherSpeler = otherPlayer.getTableauKaarten().get(0).getKleur();
        if(cijferSpeler > cijferOtherSpeler){
            return 1;
        } else if(cijferSpeler< cijferOtherSpeler){
            return -1;
        } else {
          return kleurSpeler.winsFrom(kleurOtherSpeler);
        }
    }
}
