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
public class Card {
    //class variables
    private int rank;
    private String suit;
    private String imageName;
    
    //constructor - default only -
    public Card(){
        rank = 1;
        suit = "spades";
        imageName = "1.png";
    }
    
    public void setRank(int rankInput){
        rank = rankInput;
    }
    
    public void setSuit(String suitInput){
        suit = suitInput;
    }
    
    public void setImageName(String imageNameInput){
        imageName = imageNameInput;
    }
    
    public int getRank(){
        return rank;
    }
    
    public String getSuit(){
        return suit;
    }
    
    public String getImageName(){
        return imageName;
    }


}
