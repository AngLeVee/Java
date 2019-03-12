//Angela LeVee (alevee@cnm.edu)
//
//

package leveep6;

/**
 *
 * @author Angela
 */
public class BankAccount implements Lockable
{
    private double balance;
    private int key;
    private boolean bLocked;
    
    public void BankAccount ()
    {
        balance = 0.0;
        key = 0;
        bLocked = false;
    }
    
    public boolean withdraw (double withdrawal)
    {
        if (!bLocked)
        {
            if ((balance - withdrawal)>=0)
            {                
                balance -= withdrawal;
                return true;
            }
            else
            {
                return false;
            }
        }
        return false;
    }
    
    public void deposit (double deposit)
    {
        if (!bLocked)
        {
            balance += deposit;
        }
    }
    
    public double inquiry ()
    {
        if (!bLocked)
        {            
            return balance;
        }
        
        return 0;
    }
    
    
    @Override
    public void setKey(int keyIn)
    {
        key = keyIn;
    }

    @Override
    public void lock()
    {
        if (!bLocked)
        {
            bLocked = true;
        }
    }

    @Override
    public void unlock(int keyIn)
    {
        if (bLocked)
        {
            if (keyIn == key)
            {
                bLocked = false;
            }
            else
            {
                lock();
            }
        }
    }

    @Override
    public boolean locked()
    {
        return bLocked;
    }
    
}
