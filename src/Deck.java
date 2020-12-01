import java.util.NoSuchElementException;
import java.util.Random;

public class Deck {

    public static final Random rand = new Random();

    String [] suites = {"hearts","clubs","diamonds","spades"};

    // These are all the cards that have been dealt from the deck
    Card [] cards;

    // We want to keep track of the amount of cards left in the deck
    private int cardsInDeck = 52;

    // We also want to keep track of the different suites
    private int heartsInDeck = 13;
    private int clubsInDeck = 13;
    private int diamondsInDeck = 13;
    private int spadesInDeck = 13;

    // There are four typs of suites
    // [0] = "hearts"
    // [1] = "clubs"
    // [2] = "diamonds"
    // [3] = "spades"

    // Method that creates a new random card from the deck,
    // making the necessary control that it doesn't exist etc.
    public Card randomCard()
    {
        // First we have to make sure what suite the given card should have
        String [] availableSuites = availableSuites();
        int suite = rand.nextInt(availableSuites.length);

        // Now we can obtain its value
        int [] availableValues = availableValues(availableSuites[suite]);
        int value = rand.nextInt(availableValues.length);

        // Knowing its suite and value, we can now create the card
        return new Card (availableSuites[suite], availableValues[value]);
    }


    /**************************************************
     * METHODS THAT CHECK FOR AVAILABILITY OF SUITES
     * AND VALUES OF CARDS
     * ***********************************************/

    private int[] availableValues(String suite) {

        // By default, the available cards are 1 to 13 as follows
        int [] availableCards = {1,2,3,4,5,6,7,8,9,10,11,12,13};

        // We will begin by counting the number of cards left in our suite
        int countValuesOfSuite = countValuesOfSuite(suite);

        // This will remove all cards from array availableCards by changing their value to -1
        // Their position is by default relative to their value by - 1
        if (cardsInDeck < 52)
            for (Card card : cards)
                if (card.getSuite().equals(suite))
                    availableCards[card.getValue() - 1] = -1;

        // We can now declare our array of available values, which should hold
        // all the available cards of our current suite
        int [] availableValues = new int [countValuesOfSuite];
        int j = 0;

        // This assigns the available values to an array of available values
        for (int availableCard : availableCards)
            if (availableCard > 0)
                availableValues[j++] = availableCard;

        return availableValues;
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
    private int countValuesOfSuite(String suite) {

        if (suite.equals("hearts"))
            return heartsInDeck;

        if (suite.equals("clubs"))
            return clubsInDeck;

        if (suite.equals("diamonds"))
            return diamondsInDeck;

        if (suite.equals("spades"))
            return spadesInDeck;

        else {
            return 13;
        }
    }


    // This method allows the user to draw a card from the deck
    // This will generate a new card
    // TODO Create an exception!
    public void drawCard(Card card)
    {
        Card []     cards = new Card[53 - cardsInDeck];
        int i = 0;

        if (cardsInDeck < 52)
            for (i = 0; i < cards.length - 1; i ++)
                cards[i] = this.cards[i];

        cards[i] = new Card (card);
        this.cards = cards;

        cardsInDeck --;
        if (card.getSuite().equals("hearts"))
            heartsInDeck --;
        if (card.getSuite().equals("clubs"))
            clubsInDeck --;
        if (card.getSuite().equals("diamonds"))
            diamondsInDeck --;
        if (card.getSuite().equals("spades"))
            spadesInDeck --;
    }

    public int leftInDeck ()
    {
        return cardsInDeck;
    }
}