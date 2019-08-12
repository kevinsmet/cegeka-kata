# Red7

Red7 is card gameInitializer released in 2014 that features 'Hand Management', 'Player Elimination' and 'Set Collection' mechanisms.
The gameInitializer components consist of 49 cards, numbered from 1 to 7 in 7 different colors. 
Over the course of the gameInitializer, players build a set of cards in front of them (their 'palette').
There is always an active rule (eg: the player with the most even cards in their palette is the winner).

During their turn, a player has to make sure he beats all other players. 
He can do this by adding a card to his palette or by changing the rule (or doing both).
If he finds no way to build the best palette during his turn, he is eliminated from the gameInitializer.
Players keep on taking turns until only one player is left. 
That player is then crowned the winner. 

You already start with some classes to get you to the good parts faster.

A card has a number (1-7) and a CardColor. The deck contains all 49 cards.
You can ask the deck to get you a card. This should return the top card of the deck.

1. Set up the initial gameInitializer state
    1. Every player should receive 7 cards in their hand and 1 card in their palette
    2. There are 2 - 4 players 
    3. There are no duplicate cards in the deck
    4. The starting rule is always 'Highest card wins'. 
    This starting rule is a card that is not part of the deck. 
2. Determine the starting player. 
    1. The starting player is the player right after the current winner, going clock-wise
3. A player can give up
    1. Only if he is the current player
    1. This player can no longer participate in the gameInitializer in any way (and thus can no longer win the gameInitializer)
    3. All of the eliminated player's cards (hand and palette cards) are removed from the gameInitializer
    2. If you are out of cards at the start of your turn and at least one other player is still in the gameInitializer, your only option is to give up
    3. If only one player remains (because every one else has given up), that player is the winner and the gameInitializer ends     
4. A player can play a card from his hand into his palette
    1. Only if he is the current player
    2. Only if playing that card would make him the winner when looking at the current rule.
    3. Only if he has any cards left in hs hand
    4. You can only play 1 card at max
5. A player can change the current rule by placing a card from his hand on top of it*
    1. Only if he is the current player
    2. Only if playing that card as a rule would make him the winner.
    3. Only if he has any cards left in hs hand  
    4. You can only play 1 card at max
6. A player can change the current rule _and_  play a card into his palette
    1. Only if he is the current player
    2. Only if playing those cards would make him the winner.
    3. Only if he has at least two cards left in hs hand
    4. He can't use the same card in his palette and as a rule
    5. He plays the card in his palette first and then the card as a rule
    6. You can only play 2 cards at max: one in your palette and the other as a new rule
7. Make sure you can play a complete gameInitializer using a test

\* You don't have to implement all 7 rules for this story yet. 
The most 'interesting' rule to implement is the 'most in a row' and the easiest ones are 'most of a number/color'     
  
## Extra (pick the one that looks the most interesting)   
1. Implement the advanced rules (see the image in the references folder). 
These effects happen when you play a card in your palette that corresponds with the number mentioned on the image.
    1. The number 1 effect can only be applied to player who has at least as many cards in its palette as you _after_ you've played your 1
    2. You can play a 3 even if it would not make you the winner, hoping that the card you draw due to its effect might be played as a new rule that would make you the winner
    3. 'Discarding' a card means that you place it as a new rule. The 'canvas' is the space where rules are placed
    4. None of these effects are optional. Only for 1 there's a possibility you might not have a valid target player (if you have the biggest palette). 

2. Implement a multi-round gameInitializer defined in the multi_round_rules file in references