package multiple.patterns.action;

import multiple.patterns.app.MultiplePatternsApp;
import multiple.patterns.logic.Shape;

/**
 * 
 * Command class for the delete action
 *
 */
public class DeleteAction extends AbstractShapeAction {
	// identify fields necessary to this action
	protected Shape shapeToDelete;
	
	/**
	 * Constructor of the delete action
	 * @param app
	 * @param shapeToDelete
	 */
	public DeleteAction(MultiplePatternsApp app, Shape shapeToDelete) {
		super();
		this.shapeToDelete = shapeToDelete;
		this.app = app;
	}
	
	@Override
	/**
	 * Perform delete action
	 */
	protected Shape performAction() {
		//Call the delete action from the shapes list
		app.delete(shapeToDelete);
		return null;
	}

}
