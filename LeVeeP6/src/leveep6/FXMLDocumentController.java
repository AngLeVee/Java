/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leveep6;

import static java.lang.Double.parseDouble;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;

/**
 *
 * @author Angela
 */
public class FXMLDocumentController implements Initializable
{
    @FXML private Button buttAcc;
    @FXML private Button buttInq;
    @FXML private Button buttWith;
    @FXML private Button buttDep;
    @FXML private Button buttDone;
    @FXML private RadioButton rButtLock;
    @FXML private RadioButton rButtSec;
    @FXML private RadioButton rButtUnlock;
    @FXML private TextField tFieldName;
    @FXML private TextField tFieldPIN;
    @FXML private TextField tFieldDep;
    @FXML private TextField tFieldTrans;
    @FXML private PasswordField pFieldPIN;
    @FXML private Label labBal;
    @FXML private Label labInfo;
    
    DecimalFormat form = new DecimalFormat ("#0.00");
    BankAccount account;
    boolean secured;
    int pin;
    double balance;
    double amount;
    
    @FXML private void handleButtAccAction(ActionEvent event) 
    {
        if (tFieldPIN.getText().equals(""))
        {
            labInfo.setText("Please enter a PIN");
        }
        else
        {
            account.setKey ((int)parseDouble(tFieldPIN.getText()));
            tFieldPIN.setText("");
            tFieldName.setText("");
            
            if (tFieldDep.getText().equals(""))
            {
                //Nothing, opening deposit is optional
            }
            else
            {
                account.deposit (parseDouble(tFieldDep.getText()));
                tFieldDep.setText("");
            }
            account.lock();
            tFieldName.setDisable(true);
            tFieldPIN.setDisable(true);
            tFieldDep.setDisable(true);
            buttAcc.setDisable(true);
            
            rButtLock.setDisable(false);
            rButtSec.setDisable(false);
            rButtUnlock.setDisable(false);
            pFieldPIN.setDisable (false);
            tFieldTrans.setDisable (false);
            buttInq.setDisable(false);
            buttWith.setDisable(false);
            buttDep.setDisable(false);
            buttDone.setDisable(false);
            labInfo.setText("Account created");
        }
    }
    
    @FXML private void handleButtInqAction(ActionEvent event) 
    {
        if (secured)
        {
            if (pFieldPIN.getText().equals(""))
            {
                infoLabel(0);
            }
            else
            {
                pin = (int)parseDouble(pFieldPIN.getText());
                transaction(pin, 2, 0);
            }
        }
        else
        {
            transaction(0, 2, 0);
        }
//        if (secured)
//        {
//            if (pFieldPIN.getText().equals(""))
//            {
//                infoLabel(0);
//            }
//            else
//            {
//                
//            }
//            pFieldPIN.setText("");
//        }
//        else if (account.locked())
//        {
//            labInfo.setText("Please unlock or secure account first.");
//        }
//        else
//        {
//            labBal.setText(String.valueOf(account.inquiry()));
//        }
    }
    
    @FXML private void handleButtWithAction(ActionEvent event) 
    {
        if (tFieldTrans.getText().equals(""))
        {
            infoLabel(2);
        }
        else
        {
            amount = parseDouble(tFieldTrans.getText());
            if (secured)
            {
                if (pFieldPIN.getText().equals(""))
                {
                    infoLabel(0);
                }
                else
                {
                    pin = (int)parseDouble(pFieldPIN.getText());
                    transaction(pin, 0, amount);
                }
            }
            else
            {
                transaction(0, 0, amount);
            }
        }
//        if (secured)
//        {
//            if (pFieldPIN.getText().equals(""))
//            {
//                infoLabel(0);
//            }
//            else
//            {
//                ifSecured((int)parseDouble(pFieldPIN.getText()), 0, parseDouble(tFieldTrans.getText()));
//            }
//            pFieldPIN.setText("");
//        }
//        else if (account.locked())
//        {
//            labInfo.setText("Please unlock or secure account first.");
//        }
//        else
//        {
//            account.withdraw(parseDouble(tFieldTrans.getText()));
//        }
    }
    
    @FXML private void handleButtDepAction(ActionEvent event) 
    {
        if (tFieldTrans.getText().equals(""))
        {
            infoLabel(2);
        }
        else
        {
            amount = parseDouble(tFieldTrans.getText());
            if (secured)
            {
                if (pFieldPIN.getText().equals(""))
                {
                    infoLabel(0);
                }
                else
                {
                    pin = (int)parseDouble(pFieldPIN.getText());
                    transaction(pin, 1, amount);
                }
            }
            else
            {
                transaction(0, 1, amount);
            }
        }
    }
    
    @FXML private void handleButtDoneAction(ActionEvent event) 
    {
        account = new BankAccount();
        
        labBal.setText("0.00");
        labInfo.setText("");
        pFieldPIN.setText("");
        tFieldTrans.setText("");
        secured = false;
        
        tFieldName.setDisable(false);
        tFieldPIN.setDisable(false);
        tFieldDep.setDisable(false);
        buttAcc.setDisable(false);
        
        rButtLock.setSelected(true);
        rButtLock.setDisable(true);
        rButtSec.setDisable(true);
        rButtUnlock.setDisable(true);
        pFieldPIN.setDisable (true);
        tFieldTrans.setDisable (true);
        buttInq.setDisable(true);
        buttWith.setDisable(true);
        buttDep.setDisable(true);
        buttDone.setDisable(true);
        
    }
    
    @FXML private void handleRButtLockAction(ActionEvent event) 
    {
        account.lock();
        labInfo.setText ("Locked!");
        if (secured)
        {
            secured = false;
        }
    }
    
    @FXML private void handleRButtSecAction(ActionEvent event) 
    {
        if (pFieldPIN.getText().equals (""))
        {
            rButtLock.setSelected(true);
            infoLabel(0);
        }
        else
        {
            account.unlock((int)parseDouble(pFieldPIN.getText()));
            if (account.locked())
            {
                rButtLock.setSelected(true);
                infoLabel(1);
                pFieldPIN.setText("");
            }
            else
            {
                labInfo.setText("Secured!");
                secured = true;
                account.lock();
            }
        }
    }
    
    @FXML private void handleRButtUnlockAction(ActionEvent event) 
    {
        if (pFieldPIN.getText().equals (""))
        {
            rButtLock.setSelected(true);
            infoLabel(0);
        }
        else
        {
            account.unlock((int)parseDouble(pFieldPIN.getText()));
            if (secured)
            {
                secured = false;
            }
            
            if (account.locked())
            {
                rButtLock.setSelected(true);
                infoLabel(1);
                pFieldPIN.setText("");
            }
            else
            {
                labInfo.setText("Unlocked!");
            }
        }
        pFieldPIN.setText("");
    }
    
    private void infoLabel (int type)
    {
        switch (type)
        {
            case 0:
                labInfo.setText("Please enter your PIN");
                break;
            case 1:
                labInfo.setText("Wrong PIN");
                break;
            case 2:
                labInfo.setText("Please enter an amount");
                break;
            case 3:
                labInfo.setText("Inquiry successful");
            case 4:
                labInfo.setText("Transaction successful");
                break;
            case 5:
                labInfo.setText("Transaction failed\ncheck for sufficient funds");
                break;
        }
        
    }
    
    private void transaction (int keyIn, int trans, double amount)
    {
        if (secured)
        {
            if (trans == 2)
            {
                double string = ifSecured((int)parseDouble(pFieldPIN.getText()), 3, 0);
                labBal.setText(form.format(string));
                infoLabel(3);
            }
            else
            {                    
                ifSecured(keyIn, trans, amount);
            }
            pFieldPIN.setText("");
        }
        else if (account.locked())
        {
            labInfo.setText("Please unlock or secure account");
        }
        else
        {
            switch (trans)
            {
                case 0:
                    if (account.withdraw(amount))
                    {
                        infoLabel(4);
                        labBal.setText(form.format(account.inquiry()));
                    }
                    else
                    {
                        infoLabel(5);
                    }
                    break;
                case 1:
                    account.deposit(amount);
                    labBal.setText(form.format(account.inquiry()));
                    infoLabel(4);
                    break;
                case 2:
                    labBal.setText(form.format(account.inquiry()));
                    infoLabel(3);
                    break;
            }
        }
    }
    
    private double ifSecured (int keyIn, int trans, double amount)
    {
        balance = 0;
        account.unlock(keyIn);
        if (account.locked())
        {
            infoLabel(1);
        }
        else
        {
            switch (trans)
            {
                case 0:
                    if (account.withdraw(amount))
                    {
                        infoLabel(4);
                        labBal.setText(form.format(account.inquiry()));
                    }
                    else
                    {
                        infoLabel(5);
                    }
                    break;
                case 1:
                    account.deposit(amount);
                    labBal.setText(form.format(account.inquiry()));
                    infoLabel(4);
                    break;
                case 2:
                    balance = account.inquiry();
                    labBal.setText(form.format(account.inquiry()));
                    infoLabel(3);
                    break;
            }
        }
        account.lock();
        return balance;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        account = new BankAccount ();
        
        rButtLock.setTooltip(new Tooltip("Lock your account"));
        rButtSec.setTooltip(new Tooltip("Most secure option: requires PIN with each transaction"));
        rButtUnlock.setTooltip(new Tooltip("Unlock your account"));
        buttInq.setTooltip(new Tooltip("Update balance"));
        buttDone.setTooltip(new Tooltip("Close account and open a new one"));
        tFieldTrans.setAlignment(Pos.CENTER_RIGHT);
        pFieldPIN.setAlignment(Pos.CENTER_RIGHT);
        labInfo.setText("");
        secured = false;
    }    
    
}
