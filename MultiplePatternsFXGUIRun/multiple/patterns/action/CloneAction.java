package multiple.patterns.action;

import multiple.patterns.app.MultiplePatternsApp;
import multiple.patterns.logic.Shape;


/**
 * Command class for the clone action
 *
 */
public class CloneAction extends AbstractShapeAction {

	// identify fields necessary to this action
	protected int relativeX;
	protected int relativeY;
	protected int ordinalID;
	
	/**
	 *  Constructor depending on the fields needed for this action
	 * @param app
	 * @param relativeX
	 * @param relativeY
	 * @param ordinalID
	 */
	public CloneAction(MultiplePatternsApp app,int relativeX, int relativeY, int ordinalID) {
		super();
		this.relativeX = relativeX;
		this.relativeY = relativeY;
		this.ordinalID = ordinalID;
		this.app = app;
	}

	@Override
	/**
	 * execution of the action
	 */
	public Shape performAction() {
		return app.cloneShape(relativeX, relativeY, ordinalID);
	}

}
