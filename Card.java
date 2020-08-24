public class Card 
{
    private int rank;
    private int suit;
    
    private String[] ranks = {"X", "X", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private String[] suits = {"H","C", "D", "S"};
    
    /**
     * This constructor creates a new card that has a rank and a suit
     * 
     * @param rank the rank of the card as an int
     * @param suit the suit of the card as an int
     */
    public Card(int rank, int suit)
    {
        this.rank = rank;
        this.suit = suit;
    }
    
    /**
     * This returns the rank of the card as an integer
     * 
     * @return rank of card as an int
     */
    public int getRank()
    {
        return rank;
    }
    
    /**
     * This returns the suit of the card as an integer
     * 
     * @return suit of card as an int
     */ 
    public int getSuit()
    {
        return suit;
    }
    
    /**
     * This returns the rank as a string
     * 
     * @return String version of rank
     */
    public String rankToString()
    {
        return ranks[rank];
    }
    
    /**
     * This returns the suit as a string
     * 
     * @return String version of suit
     */
    public String suitToString()
    {
        return suits[suit];
    }
    
    /**
     * This returns the String representation of a card. 
     * For example, the three of diamonds would return 3D
     * 
     * @return String representation of the card
     */
    public String toString()
    {
        return rankToString() + suitToString();
    }
}

