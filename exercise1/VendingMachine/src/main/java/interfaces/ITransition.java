/**
 * 
 */
package interfaces;

/**
 * @author Jacek
 *
 */
public interface ITransition {
	boolean compliesRule(int val);
	IState getState();
}
