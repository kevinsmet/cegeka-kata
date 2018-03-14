public class Kaart {

    private Kleur kleur;
    private int cijfer;

    public Kaart(Kleur kleur, int cijfer) {
        if(cijfer <= 0){
            throw new IllegalArgumentException("Fuck that shit");
        }
        this.kleur = kleur;
        this.cijfer = cijfer;
    }

    public Kleur getKleur() {
        return kleur;
    }

    public int getCijfer() {
        return cijfer;
    }
}
