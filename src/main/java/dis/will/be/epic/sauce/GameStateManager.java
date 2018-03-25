package dis.will.be.epic.sauce;

import com.google.common.base.Preconditions;

import java.util.List;

import static dis.will.be.epic.sauce.WinCondition.HIGHEST_CARD;

public class GameStateManager {

    private List<Player> players;
    private WinCondition activeWincondition = HIGHEST_CARD;
    private Player currentPlayer;

    public GameStateManager(List<Player> players) {
        this.players = players;
        this.calculateStartingPlayer();
    }

    private void calculateStartingPlayer() {
        this.currentPlayer = players.get((players.indexOf(getWinningPlayer()) + 1) % 4);
    }

    private Player getWinningPlayer() {
        return players.stream()
                .max((p1, p2) -> p1.winsAgainst(p2, activeWincondition))
                .orElseThrow(() -> new IllegalArgumentException("Winner has to be determinable"));
    }

    public void currentPlayerPlaysCardToHisTableau(int index) {
        getCurrentPlayer().playCardInTableau(index);
        performPostPlayerMoveActions();
    }

    public void currentPlayerPlaysCardIntoTableauAndChangesRule(int indexToPlayIntoTableau, int indexToPlayAsNewRule) {
        checkIfPlayerDidntTryToPlayTheSameCardTwice(indexToPlayAsNewRule, indexToPlayIntoTableau);
        Card cardToPlayIntoTableau = getCurrentPlayer().getHandcard(indexToPlayIntoTableau);
        Card cardToSetAsNewRule = getCurrentPlayer().getHandcard(indexToPlayAsNewRule);
        getCurrentPlayer().playCardInTableau(cardToPlayIntoTableau);
        getCurrentPlayer().removeCardFromHand(cardToSetAsNewRule);
        changeRuleTo(cardToSetAsNewRule.getCardColor().getWinCondition());
        performPostPlayerMoveActions();
    }

    private void checkIfPlayerDidntTryToPlayTheSameCardTwice(int indexToPlayAsNewRule, int indexToPlayIntoTableau) {
        Preconditions.checkArgument(indexToPlayAsNewRule != indexToPlayIntoTableau, "Tried to play the same card in tableau and as the new rule");
    }

    private void performPostPlayerMoveActions() {
        checkIfCurrentPlayerWins();
        switchCurrentPlayer();
    }

    public void currentPlayerGivesUp() {
        Player playerToRemove = getCurrentPlayer();
        switchCurrentPlayer();
        removePlayerFromPlayers(playerToRemove);
    }

    public void currentPlayerChangesRule(int index) {
        Card cardToPlayAsRule = getCurrentPlayer().removeCardFromHand(index);
        changeRuleTo(cardToPlayAsRule.getCardColor().getWinCondition());
        performPostPlayerMoveActions();
    }

    private void changeRuleTo(WinCondition newWinCondition) {
        this.activeWincondition = newWinCondition;
    }

    private void removePlayerFromPlayers(Player playerToRemove) {
        this.players.remove(playerToRemove);
    }

    private void switchCurrentPlayer() {
        if (players.indexOf(currentPlayer) == players.size()) {
            currentPlayer = players.get(0);
        } else {
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
        }
        if (currentPlayer.getHandCards().size() == 0 && getWinner() == null) {
            removePlayerFromPlayers(getCurrentPlayer());
            switchCurrentPlayer();
        }
    }

    private void checkIfCurrentPlayerWins() {
        Preconditions.checkArgument(getCurrentPlayer().equals(getWinningPlayer()), "Player did not win after move");
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public WinCondition getActiveWincondition() {
        return activeWincondition;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getWinner() {
        return getPlayers().size() == 1 ? getCurrentPlayer() : null;
    }
}
