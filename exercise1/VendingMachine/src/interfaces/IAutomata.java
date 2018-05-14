/**
 * 
 */
package interfaces;

import models.Product;

/**
 * @author Jacek
 *
 */
public interface IAutomata {
	void buildAutomata();
	
	IAutomata switchState(int val);
	boolean isFinal();
	
	void setProduct(Product product);
	Product getProduct();
	
	IState getCurrentState();
}
