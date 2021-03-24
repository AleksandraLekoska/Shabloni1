package multiple.patterns.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

/**
 * Abstract Class that defines the common operation of a shape
 * This class extends the Observable class to implement the Observable DP
 * The original shape in case of cloning has the be observable to notify it's observers in case of a change
 * In the same time it implements the Observer interface so that the clones shapes observe the original shape
 * and get notified in case of change
 *
 */
public abstract class Shape extends Observable implements Observer, CloneableShape {
	
	// Enumeration for colors
	public static enum ShapeColor {
		RED, GREEN, BLUE
	}
	
	//Enumeration for shape type
	public static enum ShapeType {
		CIRCLE, SQUARE
	}

	// sequence
	private static int sequence = 1;

	// ordinal number, generated from the sequence, read only
	protected Integer id;

	// x-axis coordinate
	protected Integer x;

	// y-axis coordinate
	protected Integer y;

	// dimension of the shape
	protected Float dimension;

	// identifies if the shape is a clone
	protected Boolean clone;

	// Color of the shape
	protected ShapeColor color;

	// relativeX to the original shape
	protected Integer relativeX=0;

	// relativeY to the original shape
	protected Integer relativeY=0;
	
	//Type of the shape
	protected ShapeType type;
	
	// The original object if this one is a clone
	protected Shape original;
	
	// List of the clones of this shape
	protected List<Integer> clonesList = new ArrayList<Integer>();
	
	/**
	 *  Getter of the shape type
	 * @return
	 */
	public ShapeType getType() {
		return type;
	}


	/**
	 *  Getter of the original shape of a clone
	 * @return
	 */
	public Shape getOriginal() {
		return original;
	}

	/**
	 *  Getter of the relative x-axis coordinate
	 * @return
	 */
	public Integer getRelativeX() {
		return relativeX;
	}

	/**
	 *  Getter of the relative y-axis coordinate
	 * @return
	 */
	public Integer getRelativeY() {
		return relativeY;
	}



	/**
	 *  Getter of the x-axis coordinate
	 * @return
	 */
	public Integer getX() {
		return x;
	}

	/** 
	 * Getter of the y-axis coordinate
	 * @return
	 */
	public Integer getY() {
		return y;
	}


	/**
	 *  Getter of the dimension
	 * @return
	 */
	public Float getDimension() {
		return dimension;
	}

	/**
	 *  Getter of the is clone property
	 * @return
	 */
	public Boolean isClone() {
		return clone;
	}

	/**
	 *  Getter of the color
	 * @return
	 */
	public ShapeColor getColor() {
		return color;
	}

	
	/**
	 *  Method for moving the shape
	 * @param x
	 * @param y
	 */
	public void move(Integer x, Integer y) {
		if (!this.isClone()) {
			this.x = x;
			this.y = y;
			setChanged();
			notifyObservers();
		} else {
			// Adapt the coordinates to the original shape if the selected object is a clone
			this.getOriginal().move(x-this.relativeX, y-this.relativeY);
		}
	}
	
	/**
	 *  Method for moving the shape
	 * @param factor
	 * @param increase
	 */
		public void scale(Integer factor, Boolean increase) {
			if (!this.isClone()) {
				this.dimension = (increase)?this.dimension * factor : this.dimension / factor;
				setChanged();
				notifyObservers();
			} else {
				this.getOriginal().scale(factor, increase);
			}
		}
		
		/**
		 * Mothod for changing the color of the shape
		 * @param color
		 */
		public void paint(ShapeColor color) {
			if (!this.isClone()) {
				this.color = color;
				setChanged();
				notifyObservers();
			} else {
				this.getOriginal().paint(color);
			}
		}

	/**
	 *  Getter of the sequence
	 * @return
	 */
	public static int getNextSequence() {
		return sequence++;
	}

	/**
	 *  Getter of the id
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	@Override
	/**
	 * String representation of the shape
	 */
	public String toString() {
		
		return this.getClass() +  " " + type +" [\n"
				+"  id="+id+"\n"
				+"  x="+x+"\n"
				+"  y="+y+"\n"
				+"  dimension="+dimension+"\n"
				+"  clone="+clone+"\n"
				+"  color="+color+"\n"
				+"  relativeX="+relativeX+"\n"
				+"  relativeY="+relativeY+"\n"
				+"  type="+type+"\n"
				+"  original=" + ((original == null) ? null : original.getId()) + "\n"
				+"  clonesList="+clonesList.stream()
			      .map(n -> String.valueOf(n))
			      .collect(Collectors.joining(",", "{", "}"))+"\n"
				+"]\n";
	}



	@Override
	/**
	 * To update the clones after the original shape is updated Implementing the
	 * Observer design pattern
	 */
	public void update(Observable o, Object arg) {
		Shape original = (Shape) o;
		this.color = original.getColor();
		this.dimension = original.getDimension();
		this.x = original.getX() + this.relativeX;
		this.y = original.getY() + this.relativeY;
	}
	

	public abstract Shape copy(); 
	
	/**
	 * Modify the attributes of an existing shape with a saved values
	 * @param shapeCopy
	 */
	public void paste(Shape shapeCopy) {
		shapeCopy.x = this.x;
		shapeCopy.y = this.y;
		shapeCopy.dimension = this.dimension;
		shapeCopy.color = this.color;
		shapeCopy.type = this.type;
		shapeCopy.relativeX = this.relativeX;
		shapeCopy.relativeY = this.relativeY;
		shapeCopy.clonesList = this.clonesList;
	}


	/**
	 * Returns the list of  ids of the clones of this shape
	 * @return list of the clones ids
	 */
	public List<Integer> getClonesList() {
		return clonesList;
	}

}
