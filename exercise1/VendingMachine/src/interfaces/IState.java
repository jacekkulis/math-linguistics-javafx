/**
 * 
 */
package interfaces;

/**
 * @author Jacek
 *
 */
public interface IState {
	IState with(final ITransition transition);
	IState transit(final int val);
	boolean isFinal();
	String getId();
}
