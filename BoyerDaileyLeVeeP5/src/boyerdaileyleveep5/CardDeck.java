/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boyerdaileyleveep5;

/**
 *
 * @author John
 */
public class CardDeck {
    //class variables
    private Card[] cardArray;
    private final boolean cardDealt[];
    private int numberCardsDealt = 0;
    private int randomIndex = 0;
    private int rank;
    private double doubleIndex;
    private double doubleSuit;
    private int intSuit;

    //contructor - default only -
    public CardDeck(){
        //load rank and suit
        cardDealt = new boolean[52];
        loadCards();
    }

    private void loadCards(){ //loads suit, rank and maybe icon
        cardArray = new Card[52];

        for (int x = 0; x<52; x++){
            cardArray[x] = new Card();
            cardDealt[x] = false;
            rank = (x+1)%13;
            if (rank == 1) rank = 14;
            cardArray[x].setRank(rank);
            cardArray[x].setImageName(String.valueOf(x+1) + ".png");

            //Spades, hearts, diamonds, clubs
            doubleIndex = (float)x;
            doubleSuit = (doubleIndex)/13 +1;
            doubleSuit = Math.floor(doubleSuit);
            intSuit = (int)doubleSuit;
            switch (intSuit) {
                case 1:  cardArray[x].setSuit("spades");
                         break;
                case 2:  cardArray[x].setSuit("hearts");
                         break;
                case 3:  cardArray[x].setSuit("diamonds");
                         break;
                case 4:  cardArray[x].setSuit("clubs");
                         break;
                default: cardArray[x].setSuit("error");
                         break;
            }
        }
    }    
    public Card getNextCard(){
        
        boolean searching = true;
        while (searching && numberCardsDealt < 52){

            // randomly choose an index between 0 and 51
            double random = Math.floor(Math.random()*52);
            randomIndex = (int)random;

            //check if the card at the chosen index has already been dealt
            //if yes, randomly choose another index
            //if no, return index
            if (!cardDealt[randomIndex]){
                numberCardsDealt++;
                cardDealt[randomIndex] = true;
                searching = false;
            }
        }                                   
        return cardArray[randomIndex];
    }
    
}
