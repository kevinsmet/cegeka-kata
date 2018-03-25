package dis.will.be.epic.sauce;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Game {

    private final Deck deck;
    private final GameStateManager gamestateManager;
    private List<Player> players;

    public Game() {
        this.deck = new Deck();

        players = newArrayList(
                new Player(deck.giveRandomCards(7), deck.giveRandomCard()),
                new Player(deck.giveRandomCards(7), deck.giveRandomCard()),
                new Player(deck.giveRandomCards(7), deck.giveRandomCard()),
                new Player(deck.giveRandomCards(7), deck.giveRandomCard()));

        this.gamestateManager = new GameStateManager(players);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public void currentPlayerChangesRule(int index) {
        gamestateManager.currentPlayerChangesRule(index);
    }

    public void currentPlayerGivesUp() {
        gamestateManager.currentPlayerGivesUp();
    }

    public void currentPlayerPlaysCardToHisTableau(int index) {
        gamestateManager.currentPlayerPlaysCardToHisTableau(index);
    }

    public void currentPlayerPlaysCardIntoTableauAndChangesRule(int indexToPlayIntoTableau, int indexToPlayAsNewRule) {
        gamestateManager.currentPlayerPlaysCardIntoTableauAndChangesRule(indexToPlayIntoTableau, indexToPlayAsNewRule);
    }
}
