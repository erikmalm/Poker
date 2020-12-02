import java.util.*; // Scanner, Locale

public class Main {

    public static void main (String [] args)
    {
        Deck deck = new Deck();

        Card [] hand = new Card [5];

        for (int i = 0; i < hand.length; i ++)
            hand[i] = deck.dealCard();


/*
        for (int i = 0; i < hand.length; i ++)
            System.out.println(i + ": " + hand[i]);

 */

        hand = sortCards(hand);

        printCards(hand);

        System.out.println("Cards left in deck: " + deck.cardsLeftInDeck());

        Scanner input = new Scanner(System.in);  // Create a Scanner object

        String cardChanges = "";



        while (!(cardChanges.length() == hand.length)) {

            System.out.println("Which cards to hold (h) and which to discard?");
            cardChanges = input.nextLine();  // Read user input
        }

        for (int i = 0; i < hand.length; i ++)
            if (cardChanges.charAt(i) == 'd')
                hand[i] = deck.dealCard();

        /*

        for (int i = 0; i < cardChanges.length(); i ++)
            System.out.print(cardChanges.charAt(i));

         */

        printCards(hand);
        cardRanking(hand);

    }

    /*

    private static Card[] drawNewCards(Card[] hand, String cardChanges, Deck deck) {

        Card [] newHand = new Card [hand.length];

        for (int i = 0; i < hand.length; i ++)
            if (cardChanges.charAt(i) == 'd')
                newHand[i] = deck.dealCard();

        return newHand;
    }

     */

    private static void printCards(Card[] cards) {

        for (int i = 0; i < cards.length - 1; i ++)
            System.out.print(cards[i] + ", ");

        System.out.println(cards[cards.length - 1]);

    }


    private static void cardRanking(Card[] hand) {


        Card [] sortedCards = sortCards(hand);

        System.out.print("sorted cards: ");
        printCards(sortedCards);

        int [] couples = countEquals(sortedCards);

        int pairs = countPairs(couples);
        int threeOfAKind = countThree(couples);
        int fourOfAKind = countFour(couples);
        boolean flush = hasFlush(sortedCards);
        boolean straight = hasStraight(sortedCards);
        boolean royal = hasRoyal(sortedCards);

        // for (int i = 0; i < couples.length; i ++)
        //    System.out.print(couples[i] + ", ");

        System.out.println("Highest card: " + sortedCards[sortedCards.length - 1]);
        System.out.println("Pairs: " + pairs);
        System.out.println("Three of a kind: " + threeOfAKind);
        System.out.println("Four of a kind: " + fourOfAKind);
        if (flush)
            System.out.println("Is a flush, same suite");
        if (straight)
            System.out.println("Cards make a straight");
        if (royal)
            System.out.println("Cards are royal");
    }

    private static boolean hasRoyal(Card[] cards) {
        boolean royal = true;

        for (int i = 0; i < cards.length - 1; i ++)
            if (!(cards[i].getValue() > 10)) {
                royal = false;
                break;
            }

        return royal;
    }

    private static boolean hasStraight(Card[] cards) {
        boolean straight = true;

        for (int i = 0; i < cards.length - 1; i ++)
            if (!(cards[i].getValue() == (cards[i + 1].getValue()) + 1)) {
                straight = false;
                break;
            }

        return straight;
    }

    private static boolean hasFlush(Card [] cards) {
        boolean flush = true;

        for (Card card : cards)
            if (!cards[0].getSuite().equals(card.getSuite())) {
                flush = false;
                break;
            }

        return flush;
    }

    private static int countFour(int[] couples) {
        int fourOfAKind = 0;

        for (int i = 0; i < couples.length; i ++)
            if (couples[i] == 3)
                fourOfAKind ++;

        return fourOfAKind;
    }

    private static int countThree(int[] couples) {

        int threeOfAKind = 0;

        for (int i = 0; i < couples.length; i ++)
            if (couples[i] == 2)
                threeOfAKind ++;

        return threeOfAKind;

    }

    private static int countPairs(int[] couples) {

        int pairs = 0;

        for (int i = 0; i < couples.length; i ++)
            if (couples[i] == 1)
                pairs ++;

        return pairs;
    }

    private static int [] countEquals(Card[] sortedCards) {

        int [] equalCards = new int [sortedCards.length];

        int pos = 0;

        while (pos < sortedCards.length - 1)
        {
            for (int i = pos + 1; i < sortedCards.length; i ++)
            if (sortedCards[pos].getValue() == sortedCards[i].getValue())
                equalCards[pos] ++;

            int amount = equalCards[pos];
            pos += amount + 1;
        }
        return equalCards;
    }


    // This method sorts the selected cards which should
    // simplify ranking of hand score etc.
    private static Card [] sortCards (Card [] cards) {

        Card[] sortedCards = new Card[cards.length];

        // We begin by making a copy of the array of cards that we can sort
        int pos = 0;
        for (Card card : cards)
            sortedCards[pos++] = new Card(card);

        for (int i = 0; i < sortedCards.length; i++)
        {
            int index = i;

            for (int j = 1 + i; j < sortedCards.length; j++)
                if (sortedCards[j].getValue() < sortedCards[index].getValue())
                    index = j;


            Card smallerCard = new Card(sortedCards[index]);
            sortedCards[index] = new Card(sortedCards[i]);
            sortedCards[i] = new Card(smallerCard);
        }
        return sortedCards;
    }
}
