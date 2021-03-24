package multiple.patterns.action;

import multiple.patterns.app.MultiplePatternsApp;
import multiple.patterns.logic.Shape;

/**
 * 
 * Command class for the scale action
 *
 */
public class ScaleAction extends AbstractShapeAction {
	// identify fields necessary to this action
	protected int factor;
	protected boolean increase;
	protected int ordinalID;
	
	/**
	 * Constructor for Scale Action
	 * @param app
	 * @param factor
	 * @param increase
	 * @param ordinalID
	 */
	public ScaleAction(MultiplePatternsApp app, int factor, boolean increase, int ordinalID) {
		super();
		this.factor = factor;
		this.increase = increase;
		this.ordinalID = ordinalID;
		this.app = app;
	}
	
	@Override
	/**
	 * Execute the command
	 */
	protected Shape performAction() {
		return app.scaleShape(factor, increase, ordinalID);
	}

}
