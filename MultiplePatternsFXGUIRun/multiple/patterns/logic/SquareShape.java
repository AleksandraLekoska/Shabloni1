package multiple.patterns.logic;

/**
 * 
 * A concrete shape class that represents a square
 *
 */
public class SquareShape extends Shape {
	
	/**
	 * Constructor of the Square
	 * @param x
	 * @param y
	 * @param dimension
	 * @param color
	 */
	public SquareShape(int x, int y, float dimension, ShapeColor color) {
		super();
		this.id = Shape.getNextSequence();
		this.x = x;
		this.y = y;
		this.dimension = dimension;
		this.color = color;
		this.clone = false;
		this.type = ShapeType.SQUARE;
	}

	/**
	 * Specific constructor to copy values
	 * @param id
	 * @param x
	 * @param y
	 * @param dimension
	 * @param relativeX
	 * @param relativeY
	 * @param color
	 */
	private SquareShape(int id, int x, int y, float dimension, int relativeX, int relativeY, ShapeColor color) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.dimension = dimension;
		this.color = color;
		this.clone = false;
		this.type = ShapeType.SQUARE;
		this.relativeX = relativeX;
		this.relativeY = relativeY;
	}


	/**
	 *  Clone method with relative coordinates
	 */
	public SquareShape clone(int relativeX, int relativeY) {
			SquareShape clone = new SquareShape(this.x, this.y, this.dimension, this.color);
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
				clone.original =origine;
				
				origine.addObserver(clone);
				origine.clonesList.add(clone.getId());
			}

			return clone;
	}
	
	/**
	 * Save a copy of the square
	 */
	public SquareShape copy() {
		SquareShape shapeCopy = new SquareShape(id, x, y, dimension, relativeX, relativeY, color);
		shapeCopy.clonesList = this.clonesList;
		return shapeCopy;
	}

}
