/**
 * 
 */
package game;

import java.util.ArrayList;

/**
 * @author $Pat Moran 40044102
 *
 */
public class SystemGroup {

	private ArrayList<Element> elements = new ArrayList<Element>();
	private String name;

	SystemGroup (String name) {
		this.name = name;
		return;
	}
	
	public void addSystem (Element element) {
		elements.add(element);
		return;
	}
	
	public ArrayList<Element> getElements() {
		return elements;
	}
	
	public String getName() {
		return name;
		
	}
	
	public int size() {
		return elements.size();
	}
	
}
