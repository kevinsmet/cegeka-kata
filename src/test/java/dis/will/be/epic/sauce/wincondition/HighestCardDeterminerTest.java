package dis.will.be.epic.sauce.wincondition;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class HighestCardDeterminerTest {

    @Test
    public void determineWinner_Returns0() throws Exception {
        Assertions.assertThat(new HighestCardDeterminer().determineWinner(null, null)).isZero();
    }
}