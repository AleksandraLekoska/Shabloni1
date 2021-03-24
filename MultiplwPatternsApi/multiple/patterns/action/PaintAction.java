package multiple.patterns.action;

import multiple.patterns.app.MultiplePatternsApp;
import multiple.patterns.logic.Shape;
import multiple.patterns.logic.Shape.ShapeColor;

/**
 * 
 * Command class for the paint action
 *
 */
public class PaintAction extends AbstractShapeAction {
	// identify fields necessary to this action
	protected ShapeColor color;
	protected int ordinalID;
	
	/**
	 * Constructor for paint action
	 * @param app
	 * @param color
	 * @param ordinalID
	 */
	public PaintAction(MultiplePatternsApp app, ShapeColor color, int ordinalID) {
		super();
		this.color = color;
		this.ordinalID = ordinalID;
		this.app = app;
	}
	
	@Override
	/**
	 * Execute the command
	 */
	protected Shape performAction() {
		return app.paintShape(color, ordinalID);
	}

}
