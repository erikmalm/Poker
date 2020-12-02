public class Main {

    public static void main (String [] args)
    {
        Deck deck = new Deck();
        int cardCount = 0;




        for (int i = 0; i < 52; i ++)
            deck.drawCard();
/*

        printCardsLeftOfSuite(deck, "hearts");
        printCardsLeftOfSuite(deck, "clubs");
        printCardsLeftOfSuite(deck, "diamonds");
        printCardsLeftOfSuite(deck, "spades");

 */



        for (int i = 0; i < deck.cards.length; i ++)
            System.out.println(i + ": " + deck.cards[i]);

        System.out.println("Cards left in deck: " + deck.leftInDeck());





        // Deal new hand

        /*

        Card [] playerHand = new Card[5];

        for (int i = 0; i < playerHand.length; i ++) {
            deck.drawCard();
            playerHand[i] = deck.cards[cardCount++];
        }

        for (int i = 0; i < playerHand.length; i ++)
            System.out.println(i + ": " + playerHand[i]);

        highestRank(playerHand);

         */





    }

    private static void printCardsLeftOfSuite(Deck deck, String suite) {

        System.out.print(suite + " left: ");

        System.out.print(deck.countValuesOfSuite(suite));

        System.out.print(" with the following values: ");

        int [] cardsOfSuite = deck.getRemainingCardsOfSuite(suite);


        for (int i = 0; i < cardsOfSuite.length - 1; i ++)
            System.out.print(cardsOfSuite[i] + ", ");

        System.out.print(cardsOfSuite[cardsOfSuite.length -1]);

        System.out.println("");
    }

    private static void highestRank(Card[] hand) {

        Card [] sortHand = new Card [hand.length];

        int x = 0;

        for (Card card : hand)
            sortHand[x ++] = new Card (card);

        for (int i = 0; i < sortHand.length; i ++) {
            boolean changed = false;
            int index = 1;
            for (int j = 1 + i; j < sortHand.length; j++) {
                if (sortHand[j].getValue() < sortHand[index].getValue()) {
                    index = j;
                    changed = true;
                }
            }

            Card leastValueCard = new Card(sortHand[index]);
            Card higherValueCard = new Card(sortHand[i]);
        }



        // int [] same = new int [hand.length];

        /*

        for (int i = 0; i < hand.length; i++)
            for (int j = 0; j < hand.length - 1; j++)
                if (hand[i].getValue() == hand[j + 1].getValue())
                    same[i] ++;

        for (int i = 0; i < same.length; i ++)
            System.out.println(same[i]);

         */

    }


}
