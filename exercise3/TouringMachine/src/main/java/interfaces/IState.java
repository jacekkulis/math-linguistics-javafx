package interfaces;

import java.util.List;

/**
 * @author Jacek
 *
 */
public interface IState {
	boolean isFinal();
	void setFinal(boolean isFinal);
	String getId();
}
