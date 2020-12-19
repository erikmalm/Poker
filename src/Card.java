import javax.swing.*;
// ♦♣♥♠

public class Card {

    private String suite;
    private int position;

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
    public int getValue() {
        int value = position + 1;

        if (position == 1)
            value += 13;

        return value;
    }

    public int getPosition()
    {
        return position;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder("[");

        if (this.position == 1)
            sb.append("ace of " + this.suite);

        else if (position > 1 && position < 11)
            sb.append(position + " of " + this.suite);

        else if (this.position == 11)
            sb.append("jack of " + this.suite);

        else if (this.position == 12)
            sb.append("queen of " + this.suite);

        else if (this.position == 13)
            sb.append("king of " + this.suite);

        sb.append("]");

        return sb.toString();
    }
}
