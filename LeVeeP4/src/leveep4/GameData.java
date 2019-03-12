//Angela LeVee (alevee@cnm.edu)
//DataClass.java
//Holds the data and manages it

package leveep4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author alevee
 */
public class GameData 
{
    //<editor-fold desc="Variables">
    private double price;
    private String sPrice;
    private String name;
    private String gName;  
    private String viewType;
    private String contrScheme;    
    private String dimension;
    private String summary;
    private String [] gTypeName;
    private boolean [] gType;
    private int gTypeNum;
    private int days;
    //</editor-fold>
    
    
    public GameData ()
    {
        //<editor-fold desc="Arrays and Variables">
        gType = new boolean [4];
        gType [0] = false;
        gType [1] = false;
        gType [2] = false;
        gType [3] = false;
        
        gTypeName = new String [4];
        gTypeName [0] = "Shooter";
        gTypeName [1] = "RPG";
        gTypeName [2] = "Adventure";
        gTypeName [3] = "Platformer";

        price = 0.0;
        name = "";
        gName = "";
        viewType = "First Person";
        contrScheme = "Point and Click";
        dimension = "2D";
        days = 1;
        //</editor-fold>
        calculate ();
    }
    
    //<editor-fold desc="Sets">
    public void setName (String nameIn)
    {
        name = nameIn;
    }
    
    public void setGameName (String gNameIn)
    {
        gName = gNameIn;
    }
    public void setDays (int daysIn)
    {
        days = daysIn;
        calculate ();
    }
    
    public void setCombo (String dimensionIn)
    {
        dimension = dimensionIn;
        calculate ();
    }
    
    public void setGType (boolean yesNo, int index)
    {
        gType [index] = yesNo;
        
        if (yesNo)
        {
            gTypeNum ++;
        }
        else
        {
            gTypeNum --;
        }
        
        calculate ();
    }
    
    public void setViewType (String viewIn)
    {
        viewType = viewIn;
        calculate ();
    }
    
    public void setContrScheme (String schemeIn)
    {
        contrScheme = schemeIn;
        calculate ();
    }
    //</editor-fold>
    
    private void calculate ()
    {
        price = 50.00;
        price += (int)Math.pow(days, 1.77);
        
        for (int i = 0; i<4; i++)
        {
            if (gType[i])
            {
                price += 10.00;
            }
        }
        
        if (viewType.equals("Menu Option"))
        {
            price += 10.00;
        }
        if (contrScheme.equals("Game controller"))
        {
            price +=5.00;
        }
        if (dimension.equals("Progresses"))
        {
            price += 10.00;
        }
    }
    
    //<editor-fold desc="Gets">
    public String getPrice ()
    {
        sPrice = String.format("%.2f", price);
        return sPrice;
    }
    
    public String getSummary ()
    {
        if (gTypeNum == 0)
        {
            return "Please choose at least one genre for your game";
        }
        
        String gTypes = "";
        boolean first = false;
        
        for (int i = 0; i < gType.length; i++)
        {
            if (gType [i] == true)
            {
                if (first == false)
                {
                    gTypes = "    * " + gTypeName [i];
                    first = true;
                }
                else
                {
                    gTypes += "\r\n    * " + gTypeName [i];
                }
            }
        }
        summary = "You bought a game!\r\nYour name: " + name + "\r\nYour game's name: " + gName
                + "\r\nDays to make game: " + days + "\r\nGame genre(s):\r\n" + gTypes
                + "\r\nView type: " + viewType + "\r\nControl scheme: " + contrScheme
                + "\r\nDimension choice: " + dimension + "\r\nYour price: $" + sPrice
                + "\r\n\r\nContact me at ajensen95@outlook.com with any extra comments or specifications.";
        return summary;
    }
    //</editor-fold>

    public String writeFile(File gameFile) 
    {
        try
        {
            FileWriter fstream = new FileWriter(gameFile);
            BufferedWriter out = new BufferedWriter (fstream);
            out.write (summary);
            out.close ();
        }
        catch (Exception e)
        {
            return "Error: " + e.getMessage ();
        }
        return "Your file has saved!";
    }
        
}
