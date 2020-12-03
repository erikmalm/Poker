import java.util.*; // Scanner, Locale

public class FiveCardDraw {

    private Deck deck;
    private Card [] playerCards;

    // This is used to keep track of the current rank of the cards
    private int pairs;
    private boolean threeOfAKind;
    private boolean fourOfAKind;
    private boolean flush = false;
    private boolean straight = false;
    private boolean royal = false;
    private Card highestCard;

    //
    private int highestRankValue = 0;
    private String highestRank = "";


    public void setupGame()
    {
        this.deck = new Deck();
        dealCards(5);
        printHand();
        playerTurn();
        printHand();
        playerTurn();
        printHand();
    }

    public void playerTurn() {

        String cardChanges = "";

        Scanner input = new Scanner(System.in);  // Create a Scanner object
        while (!(cardChanges.length() == playerCards.length)) {

            System.out.println("Which cards to hold (h) and which to discard?");
            cardChanges = input.nextLine();  // Read user input
        }

        for (int i = 0; i < playerCards.length; i ++)
            if (cardChanges.charAt(i) == 'd')
                playerCards[i] = deck.dealCard();

        sortCards();
        cardRanking();
    }



    /*******************************************************
     *                  GAME CHANGERS
     *
     * The following methods all make the game progress and
     * move forward
     *
     *******************************************************/

    // The primary function of the dealCards method is to deal the starting hand,
    // however the code is written so that it could possibly deal more cards
    private void dealCards(int cards) {

        int prevDealtCards = 0;

        if (!(playerCards == null))
            prevDealtCards = playerCards.length;


        Card [] dealtCards = new Card [cards + prevDealtCards];

        int pos = 0;

        // If the player already has cards, we do not want to remove these
        if (!(playerCards == null))
            for (pos = 0; pos < playerCards.length; pos ++)
                dealtCards[pos] = new Card(playerCards[pos]);

        for (int i = pos; i < dealtCards.length; i ++)
            dealtCards[i] = deck.dealCard();

        playerCards = dealtCards;

        // We finish by sorting the cards
        sortCards();
        cardRanking();
    }



    /*******************************************************
     *                COUNTERS FOR RANKINGS
     *
     * In order to determine the greatest rank that the player
     * currently holds in their cards, we need to count the occurence
     * of equal cards as well as if the player holds a flush, a
     * straight and/or royal cards.
     *
     *******************************************************/

    // The countEquals method loop through the players card and counting for the
    // appearance of each card. It is essential for the loop to function that
    // the cards are sorted from least to greatest.
    private int [] countEquals() {

        // This criteria should already be met, but just to be sure, we sort the cards
        sortCards();

        int [] equalCards = new int [playerCards.length];
        int pos = 0;

        while (pos < playerCards.length - 1)
        {
            for (int i = pos + 1; i < playerCards.length; i ++)
                if (playerCards[pos].getValue() == playerCards[i].getValue())
                    equalCards[pos] ++;

            int amount = equalCards[pos];
            pos += amount + 1;
        }

        return equalCards;
    }

    private void countPairs(int[] couples) {

        int pairs = 0;

        for (int i = 0; i < couples.length; i ++)
            if (couples[i] == 1)
                pairs ++;

        this.pairs = pairs;
    }

    private void countThree(int[] couples) {

        for (int i = 0; i < couples.length; i ++)
            if (couples[i] == 2)
                threeOfAKind = true;
    }

    private void countFour(int[] couples) {

        for (int couple : couples)
            if (couple == 3)
                fourOfAKind = true;
    }

    private void hasFlush() {

        flush = true;

        for (Card card : playerCards)
            if (!playerCards[0].getSuite().equals(card.getSuite())) {
                flush = false;
                break;
            }
    }

    private void hasStraight() {

        straight = true;

        for (int i = 0; i < playerCards.length - 1; i ++)
            if (!(playerCards[i].getValue() == (playerCards[i + 1].getValue()) + 1)) {
                straight = false;
                break;
            }
    }

    private void hasRoyal() {

        royal = true;

        for (int i = 0; i < playerCards.length - 1; i ++)
            if (!(playerCards[i].getValue() > 10)) {
                royal = false;
                break;
            }

     }


    /*******************************************************
     *               MAINTENANCE METHODS
     *
     * The following methods all make the game flow and make
     * sure that cards are being sorted, ranks are being updated etc.
     *
     *******************************************************/

    // This method sorts the selected cards which should
    // simplify ranking of hand score etc.
    private void sortCards () {

        Card[] sortedCards = new Card[playerCards.length];

        // We begin by making a copy of the array of cards that we can sort
        int pos = 0;
        for (Card card : playerCards)
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
        playerCards = sortedCards;
        highestCard = new Card(playerCards[playerCards.length - 1]);
    }

    // The cardRanking method should always be called after changing the hand
    // It updates the current ranking of the card by going through the cards,
    // counting equals, determining if player has flush, straight and/or royal cards
    private void cardRanking() {

        int [] couples = countEquals();

        countPairs(couples);
        countThree(couples);
        countFour(couples);
        hasFlush();
        hasStraight();
        hasRoyal();

        updateRanking();
    }

    // The method will print the players current hand
    private void printHand() {

        System.out.print("YOUR CURRENT HAND: ");

        for (int i = 0; i < playerCards.length - 1; i ++)
            System.out.print(playerCards[i] + ", ");

        System.out.println(playerCards[playerCards.length - 1]);

        System.out.print("YOU CURRENTLY HAVE: " + getRank());

        System.out.println("");
    }

    // Returns the current highest rank as a string
    private String getRank() {
        return highestRank;
    }

    // The method is called after ranking has been determined via cardRanking
    // It looks through the highest rank that the player currently holds
    // and updates the global variable "highestRank"
    private void updateRanking() {
        highestRankValue = 0;
        highestRank = "High card: " + highestCard.toString();

        if (pairs == 1) {
            highestRankValue = 1;
            highestRank = "Pairs";
        }

        if (pairs == 2) {
            highestRankValue = 2;
            highestRank = "Two pairs";
        }

        if (threeOfAKind) {
            highestRankValue = 3;
            highestRank = "Three of a kind";
        }

        if (straight) {
            highestRankValue = 4;
            highestRank = "Straight";
        }

        if (flush) {
            highestRankValue = 5;
            highestRank = "Flush";
        }

        if (threeOfAKind && pairs == 1) {
            highestRankValue = 6;
            highestRank = "Full house";
        }

        if (fourOfAKind) {
            highestRankValue = 7;
            highestRank = "Four of a kind";
        }

        if (straight && flush) {
            highestRankValue = 8;
            highestRank = "Straight flush";
        }

        if (royal && straight && flush) {
            highestRankValue = 9;
            highestRank = "Royal straight flush";
        }
    }

}
