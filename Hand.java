import java.util.*;

public class Hand extends Deck
{
    private static final int HEARTS = 0;
    private static final int DIAMONDS = 1;
    private static final int SPADES = 2;
    private static final int CLUBS = 3;
    
    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;
    
    //This is the list of cards in a hand
    private ArrayList<Card> cards;
    private int pairs;
    private String name;
    
    /**
     * This constructor sets up the hand by initializing the ArrayList and 
     * the number of pairs to zero and the player's name
     * 
     * @param playerName The player's name as a string
     */
    public Hand(String playerName)
    {
        cards = new ArrayList<Card>();
        pairs = 0;
        name = playerName;
    }    
    
     /**
     * This returns the name of the player
     * 
     * @return name of player as a string
     */
    public String getName()
    {
        return name;
    }
    /**
     * This adds a card to the hand by removing one from the deck
     * 
     * @param c The deck that the card is taken from
     */
    public void addCard(Deck c)
    {
        cards.add(c.deal());
    }
    
    /**
     * This removes a card from the hand if there is a card in the hand with the same rank
     * 
     * @param rank The rank of the card in question as an integer
     */
    public void removeCard(int rank)
    {
        for(int i = 0; i < cards.size(); i ++)
        {
            if(cards.get(i).getRank() == rank)
            {
                cards.remove(cards.get(i));
            }    
        }
    }
    
    /**
     * This checks if there is a preexisting pair in the hand
     * If so, it removes the cards from hand and updates the number of pairs
     */ 
    public void hasPair(Deck c)
    {
        for(int i = 0; i < cards.size()-1; i++)
        {
            for(int j = i+1; j < cards.size(); j++)
            {
                if(cards.get(i).getRank()==cards.get(j).getRank())
                {
                    //removes the cards with the same rank from hand
                    cards.remove(i);
                    cards.remove(j-1);
                    pairs++;
                    i--;
                    System.out.println(name + " has a pair in their hand.");
                    System.out.println(name + "'s updated number of pairs: " + pairs);
                    //checks if the player has multiple pairs in hand
                    hasPair(c);
                    break;
                }
            }
            
        }
    }
    
    //returns the hand
    public ArrayList<Card> getCards()
    {
        return cards;
    }
    
     /**
     * This checks if the card between the player and opponent match then updates hand. 
     * It will return whether there is a match or not.
     * 
     * @param card The card that we are checking if the opponent has
     * @param opponent The opponent's hand of cards that we are checking 
     * @return true if there is a match or false if there is no match
     */
    public boolean pair(Card card, Hand opponent)
    {
        for(int i = 0; i < opponent.numOfCards(); i++)
        {
            if(opponent.cards.get(i).getRank() == card.getRank())
            {
                cards.remove(card);
                pairs++;
                return true;
            }
        }
        return false;
    }
    
    /**
     * This prints number of cards left of opponents
     * @return the number of cards in the hand as an int
     */
    public int numOfCards()
    {
        return cards.size();
    }
    
    /**
     * This deals a new hand of 5 cards
     * @param deck The deck where the cards are being removed from
     */
    public void newCards(Deck deck)
    {
        for(int i = 0; i < 5; i++)
        {
            addCard(deck);
        }
    }
    
    /**
     * This prints current number of pairs
     * @return the number of pairs as an int
     */
    public int getPairs()
    {
        return pairs;
    }
    
    //This returns a string representation of the hand
    public String toString()
    {
        String result = "";
        
        for(Card c: cards)
        {
            result += c + " ";
        }
        return result;
    }
}
