import java.util.*;

public class Deck
{
    private static final int HEARTS = 0;
    private static final int DIAMONDS = 1;
    private static final int SPADES = 2;
    private static final int CLUBS = 3;
    
    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;
    
    private ArrayList<Card> deck;
    
    //This creates a deck of 52 cards.
    public Deck()
    {
        deck = new ArrayList<Card>();
        
        for(int rank = 2; rank <= ACE; rank++)
        {
            for(int suit = HEARTS; suit <= CLUBS; suit++)
            {
                Card card = new Card(rank, suit);
                deck.add(card);
            }
        }
    }
    
    /**
     * This returns the ArrayList of cards
     * @return ArrayList<Card> of the cards
     */
    public ArrayList<Card> getCards()
    {
        return deck;
    }
    
    /**
     * This deals the first card from the deck by removing it
     * @return first card in deck
     */
    public Card deal()
    {
        return deck.remove(0);
    }
    
    //This prints out the current deck
    public void print()
    {
        for(Card card: deck)
        {
            System.out.println(card);
        }
    }
    
    /**
     * This returns the amount of cards in the deck
     * @return number of cards in deck as an int
     */
    public int getDeckSize()
    {
        return deck.size();
    }
    
    //This shuffles the deck by switching 52 card positions
    public void shuffle()
    {
        for(int i = 0; i < deck.size(); i++)
        {
            int randomIndex = Randomizer.nextInt(52);
            Card x = deck.get(i);
            Card y = deck.get(randomIndex);
            
            deck.set(i, y);
            deck.set(randomIndex, x);
        }
    }
}


