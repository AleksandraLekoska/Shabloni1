package multiple.patterns.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import multiple.patterns.logic.CircleShape;
import multiple.patterns.logic.Shape;
import multiple.patterns.logic.SquareShape;
import multiple.patterns.logic.Shape.ShapeColor;
import multiple.patterns.logic.Shape.ShapeType;

/**
 * The receiver class of the command design pattern
 * The Originator in the memento design pattern
 *
 */
public class MultiplePatternsApp implements MementoManager {

	// Map of all the created shapes
	private HashMap<Integer, Shape> shapes = new HashMap<Integer, Shape>();

	/**
	 * Returns a collection of the shapes created so far
	 * @return Collection of the shapes created so far
	 */
	public Collection<Shape> getShapes() {
		return shapes.values();
	}

	
	/**
	 * Add a new shape to the list
	 * @param shape
	 */
	public void add(Shape shape) {
		shapes.put(Integer.valueOf(shape.getId()), shape);
	}
	
	/**
	 * Gets a shape identified by its ordinal ID
	 * @param ordinalID
	 * @return
	 */
	public Shape getShape(Integer ordinalID) {
		return shapes.get(ordinalID);
	}
	
	/**
	 * deletes a shape from the list
	 * @param shape
	 */
	public void delete(Shape shape) {
		// Removes only the current shape if it is a clone
		if (shape.isClone()) {
			shapes.remove(shape.getId());
			shape.getOriginal().getClonesList().remove(shape.getId());
		}
		// Removes the shape and its clones if it is an original shape
		else {
			for (Integer idClone : shape.getClonesList()) {
				shapes.remove(idClone);
			}
			shapes.remove(shape.getId());
		}
	}

	/**
	 * Make a copy of the current state using the Memento design patter
	 */
	public Memento getMemento() {
		// Get a copy of the shapes
		HashMap<Integer, Shape> shapeCopies = new HashMap<Integer, Shape>();
		shapeCopies.putAll(shapes);
		
		// Get a value copy of the shape objects in the list
		List<Shape> shapeValuesCopy = new ArrayList<Shape>();
		shapes.forEach((id, shape) -> shapeValuesCopy.add(shape.copy()));
		
		return new Memento(shapeCopies, shapeValuesCopy);
	}

	/**
	 * Get back to the previous state using the Memento design pattern
	 */
	@Override
	public void setMemento(Memento memento) {
		// clear the shapes map
		shapes.clear();
		// put the new map from stored state
		shapes.putAll(memento.getShapes());
	}
	
	/**
	 * Clone shape action
	 * @param relativeX
	 * @param relativeY
	 * @param ordinalID
	 * @return
	 */
	public Shape cloneShape(int relativeX, int relativeY, int ordinalID) {
		// Get the selected shape
		Shape original = this.getShape(ordinalID);
		// Clone the shape
		Shape clone = original.clone(relativeX, relativeY);
		// add the new shape to the list
		this.add(clone);
		// return the clones object
		return clone;
	}
	
	/**
	 * Create shape action
	 * @param x
	 * @param y
	 * @param dimension
	 * @param color
	 * @param type
	 * @return
	 */
	public Shape createShape(int x, int y, float dimension, ShapeColor color, ShapeType type) {
		// Construct the right object depending on the type sent
		Shape shape = null;
		if (type.equals(ShapeType.CIRCLE)) {
			shape = new CircleShape(x, y, dimension, color);
		} else if (type.equals(ShapeType.SQUARE)) {
			shape = new SquareShape(x, y, dimension, color);
		}
		this.add(shape);
		return shape;
	}
	
	/**
	 * Move shape action
	 * @param x
	 * @param y
	 * @param ordinalID
	 * @return
	 */
	public Shape moveShape(int x, int y, int ordinalID) {
		// get the selected shape
		Shape shapeToMove = this.getShape(ordinalID);
		// Move shape
		if (shapeToMove != null) {
			shapeToMove.move(x, y);	
		}
		return shapeToMove;
	}
	
	/**
	 * Paint shape action
	 * @param color
	 * @param ordinalID
	 * @return
	 */
	public Shape paintShape(ShapeColor color, int ordinalID) {
		// get the selected shape
		Shape shapeToPaint = this.getShape(ordinalID);
		// paint shape
		if (shapeToPaint != null) {
			shapeToPaint.paint(color);
		}
		return shapeToPaint;
	}
	
	/**
	 * Scale shape action
	 * @param factor
	 * @param increase
	 * @param ordinalID
	 * @return
	 */
	public Shape scaleShape(int factor, boolean increase, int ordinalID) {
		// get the selected shape
		Shape shapeToScale = this.getShape(ordinalID);
		// paint shape
		if (shapeToScale != null) {
			shapeToScale.scale(factor, increase);
		}
		return shapeToScale;
	}
	

}
