import java.util.NoSuchElementException;
import java.util.Random;
import static java.lang.System.*;

public class Deck {

    public static final Random rand = new Random();

    // These are all the cards that have been dealt from the deck
    Card [] cards;

    // There are four typs of suites
    // [0] = "hearts"
    // [1] = "clubs"
    // [2] = "diamonds"
    // [3] = "spades"
    String [] suites = {"hearts","clubs","diamonds","spades"};

    // We also want to keep track of the remaining card in the suites
    // When a card is removed, we will remove the integer by changing it to -1
    // We will use these arrays when counting all the cards as well
    private int [] hearts = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    private int [] clubs = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    private int [] diamonds = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    private int [] spades = {1,2,3,4,5,6,7,8,9,10,11,12,13};

    /**************************************************
     * METHODS THAT LETS THE USER DRAW AND
     * DEAL NEW CARDS FROM THE DECK
     * ***********************************************/

    // The method deal card utilizes the draw card method,
    // but only if there are more cards in the deck AND then
    // returns a copy of the new card

    public Card dealCard() {

        if (cardsLeftInDeck() == 0 )
            return null;

        int position = 52 - cardsLeftInDeck();
        drawCard();
        return getCard(position);
    }

    // This method allows the user to draw a card from the deck
    // This will generate a new card and add it to array cards
    private void drawCard()
    throws NoMoreCardsException
    {
        if (cardsLeftInDeck() == 0)
            throw new NoMoreCardsException ("Can't draw more cards, deck is empty!");

        Card []     cards = new Card[53 - cardsLeftInDeck()];
        int i = 0;

        if (cardsLeftInDeck() < 52)
            for (i = 0; i < cards.length - 1; i ++)
                cards[i] = this.cards[i];

        cards[i] = randomCard();
        this.cards = cards;
        removeCardFromDeck(cards[i]);
    }

    // Method that creates a new random card from the deck,
    // making the necessary control that it doesn't exist etc.
    private Card randomCard()
    {
        // First we have to make sure what suite the given card should have
        String [] availableSuites = availableSuites();
        int suite = rand.nextInt(availableSuites.length);

        // Now we can obtain its value
        int [] availableValues = getRemainingCardsOfSuite(availableSuites[suite]);
        int value = rand.nextInt(availableValues.length);

        // Knowing its suite and value, we can now create the card
        return new Card (availableSuites[suite], availableValues[value]);
    }

    // The method copies the card in the current position
    private Card getCard (int position)
    {
        Card card = new Card(cards[position]);
        return card;
    }

    // When drawing a card from the deck,
    // we also want to remove this from the deck
    private void removeCardFromDeck(Card card) {

        if (card.getSuite().equals("hearts"))
            hearts[card.getPosition() - 1] = -1;

        if (card.getSuite().equals("clubs"))
            clubs[card.getPosition() - 1] = - 1;

        if (card.getSuite().equals("diamonds"))
            diamonds[card.getPosition() - 1] = - 1;

        if (card.getSuite().equals("spades"))
            spades[card.getPosition() - 1] = - 1;
    }

     /**************************************************
     * METHODS THAT CHECK FOR AVAILABILITY OF SUITES
     * AND VALUES OF CARDS
     * ***********************************************/

     // The method counts the amount of cards remaining in the deck
     public int cardsLeftInDeck ()
     {
         int amount = 0;

         for (int i = 0; i < 4; i ++)
             amount += getRemainingCardsOfSuite(suites[i]).length;

         return amount;
     }

    // The method returns an array of the available suites for the user
    private String [] availableSuites()
    {
        int amountOfSuitesLeft = 0;

        // Count the total amount of suites left
        for (int i = 0; i < 4; i ++)
            if (countValuesOfSuite(suites[i]) > 0)
                amountOfSuitesLeft ++;

        // Declare String array to store the available suites to randomize from AND,
        // declare variable j that will be used to assign values to array of available suites
        String [] availableSuites = new String[amountOfSuitesLeft];
        int j = 0;

        // Declare which suites are left to select from
        for (int i = 0; i < 4; i ++)
            if (countValuesOfSuite(suites[i]) > 0)
                availableSuites[j++] = suites[i];

        // Return array of available suites
        return availableSuites;
    }

    // Method to count the amount of cards left in each suite
    // This method can also easily be used to check if the suit has any cards left
    public int countValuesOfSuite(String suite) {

        int amount = 0;

        if (suite.equals("hearts"))
            for (int heart : hearts)
                if (heart > 0)
                    amount++;

        if (suite.equals("clubs"))
            for (int club : clubs)
                if (club > 0)
                    amount++;

        if (suite.equals("diamonds"))
            for (int diamond : diamonds)
                if (diamond > 0)
                    amount++;

        if (suite.equals("spades"))
            for (int spade : spades)
                if (spade > 0)
                    amount++;

        return amount;
    }



    // This method uses an array of a specific suite, with the
    // removed cards denoted as -1 in the array.
    // The method returns the remaining  cards by excluding these
    // removed cards from the array and then returning the modified array.
    public int [] getRemainingCardsOfSuite (String suite) {

        int[] copy = getCardsOfSuite(suite);
        int[] remainingCards = new int[countValuesOfSuite(suite)];
        int j = 0;

        for (int i = 0; i < copy.length; i++)
            if (copy[i] > 0)
                remainingCards[j++] = copy[i];

        return remainingCards;
    }

    // The method returns a copy of the cards in suite in RAW format,
    // which means that cards removed from the deck are returned as -1 in the array
    private int [] getCardsOfSuite(String suite) {

        int j = 0;

        if (suite.equals("hearts")) {
            int [] copyHearts = new int [hearts.length];
            arraycopy(hearts, 0, copyHearts, 0, hearts.length);
            return copyHearts;
        }

        if (suite.equals("clubs")) {
            int [] copyClubs = new int [clubs.length];
            arraycopy(clubs, 0, copyClubs, 0, clubs.length);
            return copyClubs;
        }

        if (suite.equals("diamonds")) {
            int [] copyDiamonds = new int [diamonds.length];
            arraycopy(diamonds, 0, copyDiamonds, 0, diamonds.length);
            return copyDiamonds;
        }

        if (suite.equals("spades")) {
            int [] copySpades = new int [spades.length];
            arraycopy(spades, 0, copySpades, 0, spades.length);
            return copySpades;
        }

        return null;
    }

    // EXCEPTIONS

    public class NoMoreCardsException extends NoSuchElementException
    {
        public NoMoreCardsException ()
        {
            super ();
        }

        public NoMoreCardsException (String message)
        {
            super (message);
        }
    }


}