import java.util.*; // Scanner, Locale

public class Game {

    Deck deck;

    deck = new Deck();

    public Card [] hand = new Card [5];

    for (int i = 0; i < hand.length; i ++)
        hand[i] = deck.dealCard();

}
