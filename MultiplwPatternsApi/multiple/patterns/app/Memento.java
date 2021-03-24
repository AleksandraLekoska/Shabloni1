package multiple.patterns.app;

import java.util.HashMap;
import java.util.List;

import multiple.patterns.logic.Shape;

/**
 * 
 * The memento class in the memento design pattern it is used to store the state
 * of the wanted object
 *
 */
public class Memento {

	// Elements to save
	private HashMap<Integer, Shape> shapes;
	private List<Shape> shapesByValues;

	/**
	 * Constructor of this class
	 * @param shapes
	 * @param shapesByValues
	 */
	public Memento(HashMap<Integer, Shape> shapes, List<Shape> shapesByValues) {
		this.shapes = shapes;
		this.shapesByValues = shapesByValues;
	}

	/**
	 * Getter of the saved state
	 * @return
	 */
	public HashMap<Integer, Shape> getShapes() {
		// gets a copy of the shapes
		shapesByValues.forEach(shape -> {
			if (shapes.containsKey(shape.getId())) {
				shape.paste(shapes.get(shape.getId()));
				if (shape.isClone()) {
					shape.getOriginal().addObserver(shape);
				}
			}
		});
		return shapes;
	}

}
