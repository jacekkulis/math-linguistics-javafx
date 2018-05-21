/**
 * 
 */
package interfaces;

/**
 * @author Jacek
 *
 */
public interface ITransition {
	boolean hasRule(String val);
	IState getStartState();
	IState getGetEndState();
	String getRule();
}
