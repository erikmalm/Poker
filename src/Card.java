import javax.swing.*;


public class Card {

    private String      suite;
    private int         value;

    // Constructor for a new card using String (suite) and Int (value)

    public Card (String suite, int value)
    {
        this.suite = suite;
        this.value = value;
    }

    // Constructor for a new card using an object
    public Card (Card card)
    {
        this.suite = card.getSuite();
        this.value = card.getValue();
    }

    public String getSuite()
    {
        return suite;
    }

    public int getValue()
    {
        return value;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("");

        if (this.value < 11)
            sb.append(value + " of " + this.suite);

        if (this.value == 11)
            sb.append("jack of " + this.suite);

        if (this.value == 12)
            sb.append("queen of " + this.suite);

        if (this.value == 13)
            sb.append("king of " + this.suite);

        return sb.toString();
    }
}
