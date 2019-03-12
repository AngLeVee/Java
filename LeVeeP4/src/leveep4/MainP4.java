//Angela LeVee (alevee@cnm.edu)
//MainP4.java
//opens form

package leveep4;

/**
 *
 * @author alevee
 */
public class MainP4 
{
    JFrame frame;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        MainP4 main = new MainP4 ();
    }
    
    public MainP4 ()
    {
        frame = new JFrame ();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
