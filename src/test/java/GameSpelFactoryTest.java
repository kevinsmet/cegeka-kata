import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameSpelFactoryTest {

    private final GameSpelFactory gameSpelFactory = new GameSpelFactory();

    @Test
    public void creatingGame_ShouldHave4Players() throws Exception {
        GameSpel gameSpel = gameSpelFactory.createNewGame();

        assertThat(gameSpel.getSpelers()).hasSize(4);

    }

    @Test
    public void creatingGame_EachPlayerSHouldHave7Cards() throws Exception {
        GameSpel gameSpel = gameSpelFactory.createNewGame();

        gameSpel.getSpelers()
                .forEach(speler -> assertThat(speler.getKaarten()).hasSize(7));
    }
}