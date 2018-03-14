import org.junit.Test;

public class KaartTest {

    @Test(expected = IllegalArgumentException.class)
    public void noCardCanHaveNegativeNumber() throws Exception {
        new Kaart(Kleur.GROEN, -1);
    }
}