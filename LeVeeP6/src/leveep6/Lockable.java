/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leveep6;

/**
 *
 * @author Angela
 */
public interface Lockable 
{
    public void setKey(int key);   
    public void lock();
    public void unlock(int key);
    public boolean locked();
}
