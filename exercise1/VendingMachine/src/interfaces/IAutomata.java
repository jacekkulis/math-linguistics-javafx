/**
 * 
 */
package interfaces;

/**
 * @author Jacek
 *
 */
public interface IAutomata {
	IAutomata switchState(int val);
	boolean canStop();
}
