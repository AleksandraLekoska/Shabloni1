package multiple.patterns.logic;

public class CircleShape extends Shape {
	
	/**
	 * Circle constructor
	 * @param x
	 * @param y
	 * @param dimension
	 * @param color
	 */
	public CircleShape(int x, int y, float dimension, ShapeColor color) {
		super();
		this.id = Shape.getNextSequence();
		this.x = x;
		this.y = y;
		this.dimension = dimension;
		this.color = color;
		this.clone = false;
		this.type = ShapeType.CIRCLE;
	}


	/**
	 *  Clone method with relative coordinates
	 */
	public CircleShape clone(int relativeX, int relativeY) {
			CircleShape clone = new CircleShape(this.x, this.y, this.dimension, this.color);
			clone.clone = true;
			// calculate the new coordinates from the relative coordinates
			clone.x += relativeX;
			clone.y += relativeY;
			
			// Process the observers in relation with the fact that the shape is a clone or not
			if (!this.isClone()) {
				clone.relativeX = relativeX;
				clone.relativeY = relativeY;
				clone.original = this;
				this.addObserver(clone);
				this.clonesList.add(clone.getId());
			} else {
				Shape origine = this.getOriginal();
				
				// set the relative coordinates for clones created from another clones
				clone.relativeX = this.relativeX + relativeX;
				clone.relativeY = this.relativeY + relativeY;
				
				//Set the origin shape and add the observer
				clone.original =origine;
				origine.addObserver(clone);
				origine.clonesList.add(clone.getId());
			}

			return clone;
	}
	
	/**
	 * Private constructor to copy object by value
	 * @param id
	 * @param x
	 * @param y
	 * @param dimension
	 * @param relativeX
	 * @param relativeY
	 * @param color
	 */
	private CircleShape(int id, int x, int y, float dimension, int relativeX, int relativeY, ShapeColor color) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.dimension = dimension;
		this.color = color;
		this.clone = false;
		this.type = ShapeType.CIRCLE;
		this.relativeX = relativeX;
		this.relativeY = relativeY;
	}
	
	//Copying the values of the shape to save them for undo/redo purposes
	public CircleShape copy() {
		CircleShape circleCopy = new CircleShape(id, x, y, dimension, relativeX, relativeY, color);
		circleCopy.clonesList = this.clonesList;
		return circleCopy;
	}

}
