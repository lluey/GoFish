import java.util.Scanner;
import java.util.*;

public class MyProgram extends ConsoleProgram
{
    public void run()
    {
        
        //creates hands for all 4 players
        Hand player1 = new Hand("player1");
        Hand player2 = new Hand("player2");
        Hand player3 = new Hand("player3");
        Hand player4 = new Hand("player4");
        Hand X = new Hand("x");
        Hand[] players = {X, player1, player2, player3, player4};
        
        Deck deck = new Deck();
        deck.shuffle();
        
        System.out.println("Welcome to Go Fish!");
        System.out.println("*shuffling and dealing cards*");
        
        //deals 5 cards to each player
        for(int i = 1; i < players.length; i++)
        {
            players[i].newCards(deck);
            players[i].hasPair(deck);
        }
        
        System.out.println();
        System.out.println("Your hand: " + player1);
        System.out.println("Current number of pairs: " + player1.getPairs());
        
        //keeps going until all players have no cards left 
        while(player1.numOfCards() > 0 || player2.numOfCards() > 0 || player3.numOfCards() > 0 || player4.numOfCards() > 0)
        {
            round(players, deck);
        }
        
        winner(players);
    }
    
    /**
     * This method runs the user's turn. 
     * The user is asked for which player to choose to ask a card from then is asked which card they want to ask for. 
     * The method then determines if the opponent has a card with the same rank. 
     * If they have the card, the user gets a pair added and the card remove from their hand and the opponent's.
     * If they don't have the card, the user gets another card added to their hand.
     * 
     * @param player1 user's hand of cards
     * @param players an array of all the players' hands
     * @param deck the deck of cards
     */ 
    private void playerTurn(Hand player1, Hand[] players, Deck deck)
    {
        int move = chooseHand();
        Card chosenCard = player1.getCards().get(chooseCard(players, player1)-1); 
        System.out.println("You chose player " + move + " and asked for a " + chosenCard.rankToString());
        if(player1.pair(chosenCard, players[move]))
        {
            System.out.println("It's a match!");
            players[move].removeCard(chosenCard.getRank());
            if(player1.numOfCards() == 0)
            {
                System.out.println("You ran out of cards!");
                if(deck.getDeckSize() >=5 )
                {
                    player1.newCards(deck);
                    player1.hasPair(deck);
                }    
            }
        }
        else
        {
            System.out.println("It's not a match. Go Fish!");
            if(deck.getDeckSize() > 0)
            {
                player1.addCard(deck);
                player1.hasPair(deck);
                
            }    
        }
        System.out.println("Current hand: " + player1);
        System.out.println("Current number of pairs: " + player1.getPairs());
    }
    
    
    /**
     * This gets input from the user to choose which hand to choose from and returns the player number.
     * 
     * @return which player number the user chose
     */
    private int chooseHand()
    {
        while(true)
        {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Which player would you like to choose?(2, 3 or 4) ");
            int move = myObj.nextInt();
            if(move == 2 || move == 3 || move == 4)
            {
                return move;
            }
            System.out.println("Please enter valid oponent.");
        }    
    }
    
    /**
     * This gets input to choose which card to choose out of the hand chosen and returns the card number.
     * 
     * @param players an array of all the players' hands
     * @param player the user's hand of cards
     * @return the card number that the user is asking for
     */
    private int chooseCard(Hand[] players, Hand player)
    {
        while(true)
        {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Which card would you like to ask for? (card number 1, 2, 3...)");
            int move = myObj.nextInt();
            //ensures that the player chooses a card within the hand and not negative
            if (move <= player.numOfCards() && move > 0)
            {
                System.out.println("**************************************************************");
                return move;
            }
            System.out.println("Please enter valid card.");
        }    
    }
    
    /**
     * This method runs a cpu's turn. 
     * A random opponent is chosen, then a random card is chosen from the cpu's hand.
     * The method then determines if the cards have the same rank. 
     * If yes, the cpu gets a pair added and the card remove from their hand and the opponent's.
     * If not, the cpu gets another card added to their hand.
     * 
     * @param player cpu's hand of cards
     * @param players an array of all the players' hands
     * @param deck the deck of cards
     */ 
    private void cpuTurn(Hand player, Hand[] players, Deck deck)
    {
        ArrayList<Hand> newPlayers = new ArrayList<>(Arrays.asList(players));
        System.out.println("It is " + player.getName() + "'s turn.");
        //removes cpu from array so they can't choose themselves
        newPlayers.remove(player);
        int choose = Randomizer.nextInt(1, 3);
        //chooses a random card out of the cpus hand
        Card chosenCard = player.getCards().get(Randomizer.nextInt(0, player.numOfCards()-1)); 
        System.out.println(player.getName() + " chose " + newPlayers.get(choose).getName() + " and asked for a " + chosenCard.rankToString());
        if(player.pair(chosenCard, newPlayers.get(choose)))
        {
            System.out.println("It's a match!");
            newPlayers.get(choose).removeCard(chosenCard.getRank());
            if(player.numOfCards() == 0)
            {
                System.out.println(player.getName() + " ran out of cards!");
                if(deck.getDeckSize() >=5 )
                {
                    player.newCards(deck);
                    player.hasPair(deck);
                }    
            }
            
        }
        else
        {
            System.out.println("It's not a match. Go Fish!");
            if(deck.getDeckSize() > 0)
            {
                player.addCard(deck);
                player.hasPair(deck);
            }    
        }
    }
    
    /**
     * This declares the winner of the game by comparing the number of pairs in each player's hand.
     * The player with the most pairs wins.
     * 
     * @param players an array of all the players' hands
     */ 
    private void winner(Hand[] players)
    {
        for(int i = 1; i <= 4; i++)
        {
            System.out.println(players[i].getName() + "'s number of pairs: " + players[i].getPairs());
        }
        
        //determines max num of pairs
        int winner = Math.max(Math.max(Math.max(players[1].getPairs(), players[2].getPairs()), players[3].getPairs()), players[4].getPairs());
        
        //determines whether multiple players have the max amount of pairs
        //if so, declares a tie
        int count = 0;
        for(int i = 1; i <= 4; i++)
        {
            if(players[i].getPairs() == winner)
            {
                count++;
            }
        }
        if(count > 1)
        {
            System.out.println("It's a tie!");
        }
        
        else 
        {
            for(int i = 1; i <= 4; i++)
            {
                if(players[i].getPairs() == winner)
                {
                    System.out.println(players[i].getName() + " is the winner!");
                }
            }
        }
        
        System.out.println("Thanks for playing!");
    }
    
    /**
     * This method runs a round of the game. Each player plays their turn, then declares the opponents' updated number of cards.
     * 
     * @param players an array of all the players' hands
     * @param deck the deck of cards
     */ 
    private void round(Hand[] players, Deck deck)
    {
        if(players[1].numOfCards() > 0)
        {
            playerTurn(players[1], players, deck);
            System.out.println();
        }
        
        for(int i = 2; i <= 4; i++)
        {
            if(players[i].numOfCards() > 0)
            {
                cpuTurn(players[i], players, deck);
            }    
        }
        System.out.println("end of round");
        System.out.println("**************************************************************");
        for(int i = 2; i <= 4; i++)
        {
            System.out.println(players[i].getName() + " has " + players[i].numOfCards() + " cards");
        }    
        System.out.println("Current hand: " + players[1]);
    }
    
    
}
