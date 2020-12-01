public class Main {

    public static void main (String [] args)
    {
        Deck deck = new Deck();

        for (int i = 0; i < 52; i ++)
            deck.drawCard();

        for (int i = 0; i < deck.cards.length; i ++)
            System.out.println(i + ": " + deck.cards[i]);

        System.out.println("Cards left in deck: " + deck.leftInDeck());
    }
}
