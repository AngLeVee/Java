// Angela LeVee (alevee@cnm.edu)
// LeVeeP7, FXMLDocumentController.java
// Holds data and event handlers for form

package leveep7;

import levee.p7.enigma.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;


public class FXMLDocumentController implements Initializable
{
    
    @FXML private TextField tFieldMess;
    @FXML private TextField tFieldKey;
    @FXML private TextArea tAreaInfo;
    @FXML private TextArea tAreaResult;
    @FXML private Button buttEncode;
    @FXML private Button buttDecode;
    @FXML private RadioButton rButtGen;
    @FXML private RadioButton rButtEnter;
    @FXML private RadioButton rButtpse;
    @FXML private RadioButton rButtse;
    @FXML private RadioButton rButtde;
    
    
    Enigma messages [] = new Enigma [4];
    PrimeShiftEnigma pse = new PrimeShiftEnigma();
    ShiftyEnigma se = new ShiftyEnigma();
    DaileyEnigma de = new DaileyEnigma();
    boolean generate;
    String message;
    String codedMess;
    String result;
    int key;
    int eType;
    
    @FXML private void handleButtEncodeAction(ActionEvent event) 
    {
        message = tFieldMess.getText();
        
        if (generate)
        {
            messages[eType].setMessage(message);
        }
        else
        {
            key = (int)parseDouble(tFieldKey.getText());
            messages[eType].setMessage(message, key);
        }
        
        key = messages[eType].getKey();
        codedMess = messages[eType].getCodedMessage();
        result = String.format ("Original: %s\r\nKey: %d\r\nCoded Message: %s", message, key, codedMess);
        tAreaResult.setText (result);
    }
    
    @FXML private void handleButtDecodeAction(ActionEvent event) 
    {
        tFieldMess.setText(messages[eType].getDecodedMessage());
        tFieldKey.setDisable(false);
        tFieldKey.setText(String.valueOf(messages[eType].getKey()));
        tAreaResult.setText(codedMess);
        buttDecode.setDisable(true);
        rButtEnter.setSelected(true);
        generate = false;
    }
    
    //Reset form
    @FXML private void handleButtClearAction(ActionEvent event) 
    {
        tFieldMess.setText("");
        tFieldKey.setText("");
        tFieldKey.setDisable(true);
        tAreaResult.setText("");
        generate = true;
        buttDecode.setDisable(true);
        
        messages[eType].setMessage("");
        messages[eType].setCodedMessage("", 1);
    }
    
    @FXML private void handleRButtGenAction(ActionEvent event) 
    {
        generate = true;
        tFieldKey.setText("");
        tFieldKey.setDisable(true);
    }
    
    @FXML private void handleRButtEnterAction(ActionEvent event) 
    {
        generate = false;
        tFieldKey.setDisable(false);
    }
    
    @FXML private void handleRButtpseAction(ActionEvent event)
    {
        eType = 0;
    }
    
    @FXML private void handleRButtseAction(ActionEvent event)
    {
        eType = 1;
    }
    
    @FXML private void handleRButtdeAction(ActionEvent event)
    {
        eType = 2;
    }
    
    @FXML private void handleMiOpenAction(ActionEvent event) 
    {
        tFieldMess.setText("");
        tFieldKey.setText("");
        tAreaResult.setText("");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(null);
        
        if (file != null) 
        {
            try
            {
                BufferedReader in = new BufferedReader(new FileReader(file));                
                codedMess = in.readLine();
                key = parseInt(in.readLine());
                eType = parseInt(in.readLine());
                messages[eType].setCodedMessage(codedMess, key);
                in.close();
                tAreaResult.setText("Open Successful");
                
                switch (eType)
                {
                    case 0:
                        rButtpse.setSelected(true);
                        break;
                    case 1:
                        rButtse.setSelected(true);
                        break;
                    case 2:
                        rButtde.setSelected(true);
                        break;
                }
            }
            catch (Exception e)
            {
                tAreaResult.setText ("Opening failed, please try again.");
            }
        }
        
        buttDecode.setDisable(false);
    }
    
    @FXML private void handleMiSaveAction(ActionEvent event) 
    {
        tFieldMess.setText("");
        tFieldKey.setText("");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Save a Coded Message in a File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(null);
        if(file != null)
        {
            try
            {
                FileWriter fstream = new FileWriter(file);
                BufferedWriter out = new BufferedWriter (fstream);
                out.write (messages[eType].getCodedMessage() + "\r\n");
                out.write (String.valueOf(messages[eType].getKey()) + "\r\n");
                out.write (String.valueOf(eType));
                out.close ();
                tAreaResult.setText("Save Successful");
            }
            catch (Exception e)
            {
                tAreaResult.setText ("Saving failed, please try again.");
            }
        }
    }
    
    @FXML private void handleMiExitAction (ActionEvent event)
    {
        System.exit(0);
    }
    
    @FXML private void handleMiAboutAction (ActionEvent event)
    {
        tAreaInfo.setText("Welcome to Program 7 by Angela LeVee.");
    }
    
    @FXML private void handleMiPIAction (ActionEvent event)
    {
        tAreaInfo.setText("Please enter a message and a key if desired,"
                + " then click encode and save your file.\r\n"
                + "To decode a message please open a file created by "
                + "the program and click decode.");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        eType = 0;
        messages [0] = pse;
        messages [1] = se;
        messages [2] = de;
        generate = true;
    }
}
