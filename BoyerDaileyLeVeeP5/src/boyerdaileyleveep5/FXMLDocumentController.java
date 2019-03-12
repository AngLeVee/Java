//Angela LeVee alevee@cnm.edu
//BoyerDailyLeVeeP5 FXMLDocumentController.java
//Interacts with the user

package boyerdaileyleveep5;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Angela
 */
public class FXMLDocumentController implements Initializable 
{
    
    @FXML private ImageView ivLogo;
    @FXML private ImageView ivP1C1;
    @FXML private ImageView ivP1C2;
    @FXML private ImageView ivP1C3;
    @FXML private ImageView ivP2C1;
    @FXML private ImageView ivP2C2;
    @FXML private ImageView ivP2C3;
    @FXML private TextArea tAreaInstruct;
    @FXML private TextField tFieldName;
    @FXML private TextField tFieldP1;
    @FXML private TextField tFieldP2;
    @FXML private TextField tFieldWinner;
    @FXML private Button buttName;
    @FXML private Button buttStart;
    @FXML private Button buttQuit;
    
    private final CardDeck deck = new CardDeck ();
    private final ThreeCardBrag tcBrag = new ThreeCardBrag ();
    private final Card p1Cards [] = new Card [3];
    private final Card p2Cards [] = new Card [3];
    private final ImageView player1 [] = new ImageView [3];
    private final ImageView player2 [] = new ImageView [3];
    private final String p1CardName [] = new String [3];
    private final String p2CardName [] = new String [3];
    private boolean play;
    private int played;
    private int name;
    private int winner;
    private String nameP1;
    private String nameP2;
    Image logo;
    Image cardBack;
    
    
    @FXML private void handleTFieldNameAction (ActionEvent event)
    {
        //Lets you set names on enter
        if (!tFieldName.getText().equals(""))
        {
            if (name == 1)
            {
                nameP1 = tFieldName.getText ();
                tFieldName.setText ("");
                tFieldP1.setText (nameP1);
                buttName.setText ("Player 2");
                name++;
            }
            else if (name == 2)
            {
                nameP2 = tFieldName.getText();
                tFieldName.setText ("");
                tFieldP2.setText (nameP2);
                buttName.setText ("Rename");
                tFieldName.setDisable (true);
                name++;
            }
        }
    }
    
    @FXML private void handleButtNameAction (ActionEvent event)
    {
        if (name == 1) //First click
        {
            nameP1 = tFieldName.getText ();
            tFieldName.setText ("");
            tFieldP1.setText (nameP1);
            buttName.setText ("Player 2");
            name++;
        }
        else if (name == 2) //Second click
        {
            nameP2 = tFieldName.getText();
            tFieldName.setText ("");
            tFieldP2.setText (nameP2);
            buttName.setText ("Rename");
            tFieldName.setDisable (true);
            name++;
        }
        else //Third click resets
        {
            tFieldP1.setText ("");
            tFieldP2.setText ("");
            buttName.setText ("Player 1");
            tFieldName.setDisable(false);
            name = 1;
        }
    }
    
    @FXML private void handleButtStartAction (ActionEvent event)
    {
        if (!play) //If button is pressed when it says play game
        {
            buttStart.setText ("Decide Winner");
            tFieldWinner.setText("");
            tFieldP1.setStyle("-fx-font: 12 DejaVuSans; -fx-text-fill: black;");
            tFieldP2.setStyle("-fx-font: 12 DejaVuSans; -fx-text-fill: black;");
            play = true;
            played ++;
            
            //If names haven't been set
            if (nameP1 == null || nameP1.equals(""))
            {
                nameP1 = "Player 1";
                tFieldP1.setText (nameP1);
            }
            if (nameP2 == null || nameP2.equals (""))
            {
                nameP2 = "Player 2";
                tFieldP2.setText (nameP2);
            }
          
            for (int i = 0; i < 3; i++)
            {
                //Set cards and card images
                p1Cards[i] = deck.getNextCard ();
                p2Cards[i] = deck.getNextCard ();
                p1CardName[i] = "Images/" + p1Cards[i].getImageName ();
                p2CardName[i] = "Images/" + p2Cards[i].getImageName ();
                player1[i].setImage (new Image (this.getClass().getResource(p1CardName[i]).toExternalForm()));
                player2[i].setImage (new Image (this.getClass().getResource(p2CardName[i]).toExternalForm()));
            }
            
            //Set card hands
            tcBrag.setPlayerHand (p1Cards, p2Cards);
        }
        else //When button says determine winner
        {
            if (played == 8) //8 hands dealt
            {
                buttStart.setDisable(true);
            }
            else
            {                
                buttStart.setText ("Play again");
                play = false;
            }
            winner = tcBrag.getWinner (); //Get winner from brag class
            
            switch (winner) //React to winner
            {
                case 0:
                    tFieldWinner.setText("No Winner");
                    break;
                case 1:
                    tFieldWinner.setText(nameP1 + " won");
                    tFieldP1.setStyle("-fx-font: bold 14 DejaVuSans; -fx-text-fill: green;");
                    break;
                case 2:
                    tFieldWinner.setText(nameP2 + " won");
                    tFieldP2.setStyle("-fx-font: bold 14 DejaVuSans; -fx-text-fill: green;");
                    break;
                case 3:
                    tFieldWinner.setText("It's a Tie!");
                    tFieldP1.setStyle("-fx-font: bold 14 DejaVuSans; -fx-text-fill: green;");
                    tFieldP2.setStyle("-fx-font: bold 14 DejaVuSans; -fx-text-fill: green;");
                    break;
            }
        }
    }
    
    @FXML private void handleButtQuitAction (ActionEvent event)
    {
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        logo = new Image (this.getClass().getResource("Images/Logo.png").toExternalForm());
        cardBack = new Image (this.getClass().getResource("Images/cardback.png").toExternalForm());
        play = false;
        name = 1;
        tAreaInstruct.setText (tcBrag.getRules());
        
        player1 [0] = ivP1C1;
        player1 [1] = ivP1C2;
        player1 [2] = ivP1C3;
        player2 [0] = ivP2C1;
        player2 [1] = ivP2C2;
        player2 [2] = ivP2C3;
        ivLogo.setImage (logo);
        
        for (int i = 0; i < 3; i++)
        {
            player1[i].setImage (cardBack);
            player2[i].setImage (cardBack);
        }
        
        tFieldP1.setAlignment(Pos.CENTER);
        tFieldP2.setAlignment(Pos.CENTER);
        tFieldWinner.setAlignment(Pos.CENTER);
        tFieldWinner.setTooltip (new Tooltip ("Displays winner"));
        tFieldName.setTooltip (new Tooltip ("Type names here"));
        buttName.setTooltip (new Tooltip ("Submit name or reset names"));
        buttStart.setTooltip (new Tooltip ("Moves game forward"));
        buttQuit.setTooltip(new Tooltip ("Exit Game"));
    }
}
