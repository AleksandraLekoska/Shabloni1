package multiple.patterns.logic;

/**
 * 
 * The interface used in the Prototype design patter
 *
 */
public interface CloneableShape {
	
	/**
	 * Cloning method
	 * @param relativeX
	 * @param relativeY
	 * @return clone
	 */
	Shape clone(int relativeX, int relativeY);

}
