public enum Kleur {
    ROOD(7), ORANJE(6), GEEL(5), GROEN(4), BLAUW(3), INDIGO(2), PAARS(1);

    private int waarde;

    Kleur(int waarde) {
        this.waarde = waarde;
    }


    public int winsFrom(Kleur kleurOtherSpeler) {
        if(this.waarde > kleurOtherSpeler.waarde){
            return 1;
        } else {
            return -1;
        }
    }
}
