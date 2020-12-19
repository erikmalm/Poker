import javax.swing.*;

// ♦♣♥♠


public class Card {

    private String      suite;
    private int         position;

    // Constructor for a new card using String (suite) and Int (value)
    public Card (String suite, int position)
    {
        this.suite = suite;
        this.position = position;
    }

    // Constructor for a new card using an object
    public Card (Card card)
    {
        this.suite = card.getSuite();
        this.position = card.getPosition();
    }

    public String getSuite()
    {
        return suite;
    }

    // The value is equal to it's position in the array + 1
    // unless it's an ACE, then we have to add 13!
    public int getValue() {
        int value = position + 1;

        if (position == 1)
            value += 13;

        return value;
    }

    // This method simply returns the position of the card
    public int getPosition()
    {
        return position;
    }

    // This is what should be returned when printing card (text)
    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");

        if (this.position == 1)
            sb.append("A " + getSuiteSymbol());

        if (position > 1 && position < 11)
            sb.append(position + " " + getSuiteSymbol());

        if (this.position == 11)
            sb.append("J " + getSuiteSymbol());

        if (this.position == 12)
            sb.append("Q " + getSuiteSymbol());

        if (this.position == 13)
            sb.append("K " + getSuiteSymbol());

        sb.append("]");

        return sb.toString();
    }

    // ♦♣♥♠

    public String getSuiteSymbol() {
        StringBuilder sb = new StringBuilder("");

        if (this.suite.equals("hearts"))
            sb.append("♥");

        if (this.suite.equals("spades"))
            sb.append("♠");

        if (this.suite.equals("diamonds"))
            sb.append("♦");

        if (this.suite.equals("clubs"))
            sb.append("♣");

        return sb.toString();

    }
}
