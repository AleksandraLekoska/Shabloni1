package multiple.patterns.action;

import multiple.patterns.app.MultiplePatternsApp;
import multiple.patterns.logic.Shape;
import multiple.patterns.logic.Shape.ShapeColor;
import multiple.patterns.logic.Shape.ShapeType;

/**
 * 
 * The command class for the creation of the shape
 *
 */
public class CreateAction extends AbstractShapeAction {

	// Attributes necessary for the execution of the action
	protected Shape shape;
	protected int x,  y;
	protected float dimension;
	protected ShapeColor color;
	protected ShapeType type;

	/**
	 * Constructor for the create action
	 * @param app
	 * @param x
	 * @param y
	 * @param dimension
	 * @param color
	 * @param type
	 */
	public CreateAction(MultiplePatternsApp app, int x, int y, float dimension, ShapeColor color, ShapeType type) {
		super();
		this.x=x;
		this.y=y;
		this.dimension = dimension;
		this.type = type;
		this.color=color;
		this.app = app;
	}

	@Override
	/**
	 * Execute of the command
	 */
	public Shape performAction() {
		return app.createShape(x, y, dimension, color, type);
	}

}
