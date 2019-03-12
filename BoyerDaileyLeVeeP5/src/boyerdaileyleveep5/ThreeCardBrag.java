/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Angela LeVee (aleve@cnm.edu) wrote rules
package boyerdaileyleveep5;

/**
 *
 * @author Dion
 * //Template constructed by John
 */
public class ThreeCardBrag {
    //class variables
    private final String rules;
    private Card p1Hand[];
    private Card p2Hand[];
    
    // bool variables for hand types
    private boolean twoOrThree = false;
    private boolean Three3 = false;
    private boolean ace = false;
    private boolean kQj = false;
        
    // individual player 1 temp ranks
    private int p1c1Rank;
    private int p1c2Rank;
    private int p1c3Rank;
    // player 2 temp ranks
    private int p2c1Rank;
    private int p2c2Rank;
    private int p2c3Rank;
        
    // winner int 1/2
    private int winner;
        
    // level of win opportunity comparison variables
    private int winLvlP1;
    private int winLvlP2;

    
    
    //Constructor - default only
    public ThreeCardBrag(){
        rules = "Welcome to Dion Boyer, John Dailey, and Angela LeVee's Three Card Brag game! " +
                "\n Heirarchy rules are:" +
                "\n1. Three 3's or an Ace " +
                "\n2. Two or three of the same rank or suit " +
                "\n3. A King, Queen or Jack" +
                "\nPlease enter your names and click to play the game! (8 hands total)";

        p1Hand = new Card[3];
        p2Hand = new Card[3];

    }
    
    // get method for rules string
    public String getRules()
    {
        return rules;
    }
    
    // setter for player handers
    public void setPlayerHand(Card[] p1HandInput, Card[] p2HandInput)
    {
        //need p1 cards and p2 cards
        p1Hand = p1HandInput;
        p2Hand = p2HandInput;
    }
    
    // getter for winner
    public int getWinner()
    {   
        //returns winner
        determineWinner();
        return winner;
    }
    
    // determines winner of hand
    private int determineWinner()
    {
        p1c1Rank = p1Hand[0].getRank();
        p1c2Rank = p1Hand[1].getRank();
        p1c3Rank = p1Hand[2].getRank();
        p2c1Rank = p2Hand[0].getRank();
        p2c2Rank = p2Hand[1].getRank();
        p2c3Rank = p2Hand[2].getRank();
        twoOrThree = false;
        Three3 = false;
        ace = false;
        kQj = false;
        winLvlP1 = 0;
        winLvlP2 = 0;
        winner = 0;

        // Determining Player 1 win opportunity value
        // determining if 3 or 2 of a kind is true
        if(p1c1Rank == 13 || p1c1Rank == 12 || p1c1Rank == 11 || p1c2Rank == 13 || p1c2Rank == 12 || p1c2Rank == 11 || p1c3Rank == 13 || p1c3Rank == 12 || p1c3Rank == 11 )
        {
            // determining if kingQueenJack exists by temp ranking
            // highest value of win opportunity
            winLvlP1 = 2;
            // setting ace true
            kQj = true;
        }
        if(p1c1Rank == p1c2Rank || p1c1Rank == p1c3Rank || p1c2Rank == p1c3Rank)
        {
            // setting level of win opportunity for player 1
            // for set of two or 3 of a kind 
            winLvlP1 = 3;
            // setting two/three of a kind true
            twoOrThree = true;
        }
        if(p1Hand[0].getSuit().equals(p1Hand[1].getSuit()) || p1Hand[2].getSuit().equals(p1Hand[1].getSuit()) || p1Hand[0].getSuit().equals(p1Hand[2].getSuit()))
        {
            // checking for suit mattch
            // setting to highest value for 3 of a suite
            winLvlP1 = 3;
            twoOrThree = true;
        }
        if((p1c1Rank == p1c2Rank) && (p1c2Rank == p1c3Rank))
        {
            // determining of 3 of a kind of 3
            // determining if ranking is of value 3 for 3 of a kind
            if(p1c1Rank == 3 && p1c2Rank == 3 && p1c3Rank == 3)
            {
                // setting to highest lvl of win opportunity
                winLvlP1 = 4;
                // setting Three3 which is 3 of 3 true
                Three3 = true;
            }
        }
        if(p1c1Rank == 14 || p1c2Rank == 14 || p1c3Rank == 14)
        {
            // determining if ace exists by temp ranking
            // highest value of win opportunity
            winLvlP1 = 4;
            // setting ace true
            ace = true;
        }
        if(kQj == false && twoOrThree == false && Three3 == false && ace == false)
        {
            winLvlP1 = 0;
        }
        
       
        // resetting boolean values for hand types
        twoOrThree = false;
        Three3 = false;
        ace = false;
        kQj = false;
        
        // Determining Player 2 win opportunity value
        // determining if 3 or 2 of a kind is true
        if(p2c1Rank == 13 || p2c1Rank == 12 || p2c1Rank == 11 || p2c2Rank == 13 || p2c2Rank == 12 || p2c2Rank == 11 || p2c3Rank == 13 || p2c3Rank == 12 || p2c3Rank == 11)
        {
            // determining if kingQueenJack exists by temp ranking
            // highest value of win opportunity
            winLvlP2 = 2;
            // setting kQJ true
            kQj = true;
        }
        if(p2c1Rank == p2c2Rank || p2c1Rank == p2c3Rank || p2c2Rank == p2c3Rank)
        {
            // setting level of win opportunity for player 2
            // for set of two or 3 of a kind 
            winLvlP2 = 3;
            // setting two/three of a kind true
            twoOrThree = true;
        }
        if(p2Hand[0].getSuit().equals(p2Hand[1].getSuit()) || p2Hand[2].getSuit().equals(p2Hand[1].getSuit()) || p2Hand[0].getSuit().equals(p2Hand[2].getSuit()))
        {
            // setting to highest value for 3 of a suite
            winLvlP2 = 3;
            twoOrThree = true;
        }
        if((p2c1Rank == p2c2Rank) && (p2c2Rank == p2c3Rank))
        {
            // determining of 3 of a kind of 3
            // determining if ranking is of value 3 for 3 of a kind
            if(p2c1Rank == 3 && p2c2Rank == 3 && p2c3Rank == 3)
            {
                // setting to highest lvl of win opportunity
                winLvlP2 = 4;
                // setting Three3 which is 3 of 3 true
                Three3 = true;
            }
        }
        if(p2c1Rank == 14 || p2c2Rank == 14 || p2c3Rank == 14)
        {
            // determining if ace exists by temp ranking
            // highest value of win opportunity
            winLvlP2 = 4;
            // setting ace true
            ace = true;
        }
        if(kQj == false && twoOrThree == false && Three3 == false && ace == false)
        {
            winLvlP2 = 0;

        }
        
        twoOrThree = false;
        Three3 = false;
        ace = false;
        kQj = false;
        
        // determining winner based on lvl of players
        if(winLvlP1 > winLvlP2)
        {
            winner = 1;
        }
        else if(winLvlP2 > winLvlP1)
        {
            winner = 2;
        }
        else if(winLvlP2 == winLvlP1)
        {
            if(winLvlP1 == 0 || winLvlP2 == 0)
            {
                winner = 0;
            }
            else
                winner = 3;
        }
        else if(p1Hand == null || p2Hand == null)
        {
            winner = 0;
        }
        else
        {
            winner = 0;
        }
        
        // what were these prints for?
//        System.out.println(p1Hand[0].getRank());
//        System.out.println(p1Hand[0].getSuit());
//        System.out.println(p1Hand[1].getRank());
//        System.out.println(p1Hand[1].getSuit());
//        System.out.println(p1Hand[2].getRank());
//        System.out.println(p1Hand[2].getSuit());
//        System.out.println(p2Hand[0].getRank());
//        System.out.println(p2Hand[0].getSuit());
//        System.out.println(p2Hand[1].getRank());
//        System.out.println(p2Hand[1].getSuit());
//        System.out.println(p2Hand[2].getRank());
//        System.out.println(p2Hand[2].getSuit());
        
        // returning winner
        return winner;
    }
}