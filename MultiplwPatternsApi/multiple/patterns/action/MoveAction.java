package multiple.patterns.action;

import multiple.patterns.app.MultiplePatternsApp;
import multiple.patterns.logic.Shape;

/**
 * 
 * Command class for the move action
 *
 */
public class MoveAction extends AbstractShapeAction {
	// identify fields necessary to this action
	protected int x;
	protected int y;
	protected int ordinalID;
	
	/**
	 * Constructor for the mode action
	 * @param app
	 * @param x
	 * @param y
	 * @param ordinalID
	 */
	public MoveAction(MultiplePatternsApp app, int x, int y, int ordinalID) {
		super();
		this.x = x;
		this.y = y;
		this.ordinalID = ordinalID;
		this.app = app;
	}
	
	@Override
	/**
	 * Execute of the command
	 */
	protected Shape performAction() {
		return app.moveShape(x, y, ordinalID);
	}

}
