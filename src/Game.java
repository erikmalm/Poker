import java.util.*; // Scanner, Locale

public class Game {

    private Deck deck;
    private Card [] playerCards;

    public void setupGame()
    {
        this.deck = new Deck();
        dealCards(5);
    }

    private void dealCards(int cards) {

        Card [] dealtCards = new Card [cards + playerCards.length];

        int pos = 0;

        if (playerCards.length > 0)
            for (pos = 0; pos < playerCards.length; pos ++)
                dealtCards[pos] = new Card(playerCards[pos]);

        for (int i = pos; i < dealtCards.length; i ++)
            dealtCards[i] = deck.dealCard();

    }

}
