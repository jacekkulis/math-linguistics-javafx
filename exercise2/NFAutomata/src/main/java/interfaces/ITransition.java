/**
 * 
 */
package interfaces;

/**
 * @author Jacek
 *
 */
public interface ITransition {
	boolean compliesRule(String val);
	IState getState();
}
