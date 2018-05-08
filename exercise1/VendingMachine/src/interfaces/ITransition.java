/**
 * 
 */
package interfaces;

/**
 * @author Jacek
 *
 */
public interface ITransition {
	boolean isPossible(int val);
	IState state();
}
