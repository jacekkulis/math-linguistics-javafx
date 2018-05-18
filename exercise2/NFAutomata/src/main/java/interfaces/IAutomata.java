/**
 * 
 */
package interfaces;

/**
 * @author Jacek
 *
 */
public interface IAutomata {
	void buildAutomata();
	
	IAutomata switchState(String val);
	boolean isFinal();

	
	IState  getCurrentState();
}
