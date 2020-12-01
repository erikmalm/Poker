import java.util.NoSuchElementException;
import java.util.Random;

public class Deck {

    public static final Random rand = new Random();

    // We want to keep track of the amount of cards left in the deck
    int cardsInDeck = 52;

    // We also want to keep track of the different suites
    int heartsInDeck = 13;
    int clubsInDeck = 13;
    int diamondsInDeck = 13;
    int spadesInDeck = 13;

    // There are four typs of suites
    // [0] = "hearts"
    // [1] = "clubs"
    // [2] = "diamonds"
    // [3] = "spades"
    String [] suites = {"hearts","clubs","diamonds","spades"};

    // This is all the cards that have been dealt from the deck
    Card [] cards;

    public Card dealCard ()
            throws NoSuchElementException
    {
        if (!hasCards())
            throw new NoSuchElementException("No cards left!");

        Card card = randomCard();

        return card;
    }

    // Method that checks if there are still cards left in the deck
    public boolean hasCards ()
    {
        return cardsInDeck > 0;
    }

    public boolean hasSuite (int t)
    {

        boolean suiteHasCards = false;

        if (t == 0)
            suiteHasCards = heartsInDeck > 0;
        if (t == 1)
            suiteHasCards = clubsInDeck > 0;
        if (t == 2)
            suiteHasCards = diamondsInDeck > 0;
        if (t == 3)
            suiteHasCards = spadesInDeck > 0;

        return suiteHasCards;
    }

    private boolean hasValue(int randValue, int suite) {

        boolean uniqueCard = true;

        // This method confirms if the card being sought after is unique or not
        if (cardsInDeck < 52)
            for (Card card : cards)
                if (card.getSuite().equals(suites[suite]) && card.getValue() == randValue)
                    uniqueCard = false;

        return uniqueCard;
    }

    // The method returns an array of the available suites for the user
    public String [] availableSuites()
    {
        int amountOfSuitesLeft = 0;

        // Count the amount of suites left
        for (int i = 0; i < 4; i ++)
        {
            if (hasSuite(i)) {
                amountOfSuitesLeft ++;
            }
        }

        System.out.println("amountOfSuitesLeft = " + amountOfSuitesLeft);

       int j = 0;
       String [] availableSuites = new String[amountOfSuitesLeft];

       for (int i = 0; i < 4; i ++)
       {
           if (hasSuite(i)) {
               availableSuites[j++] = suites[i];
           }
       }

        return availableSuites;
    }


    // Method that creates a new random card from the deck,
    // making the necessary control that it doesn't exist etc.
    public Card randomCard()
    {
        boolean foundSuite = false;
        boolean foundValue = false;
        int suite = -1;
        int value = -1;


        // First we have to make sure what suite the given card should have
        /*
        while (!foundSuite)
        {
            int randSuite = rand.nextInt(4);
            if (hasSuite(randSuite))
            {
                foundSuite = true;
                suite = randSuite;
            }
        }

         */
        
        String [] availableSuites = availableSuites();
        int randSuite = rand.nextInt(availableSuites.length);

        // Now we can obtain it's value
        while (!foundValue)
        {
            int randValue = 1 + rand.nextInt(13);
                    if (hasValue(randValue, suite))
                    {
                        foundValue = true;
                        value = randValue;
                    }
        }

        Card card = new Card (suites[suite], value);

        String [] availableSuites = availableSuites();

        for (int i = 0; i < availableSuites.length; i ++)
            System.out.print(availableSuites[i] + " ");
        return card;
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


