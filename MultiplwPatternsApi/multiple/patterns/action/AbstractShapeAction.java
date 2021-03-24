package multiple.patterns.action;

import multiple.patterns.app.Memento;
import multiple.patterns.app.MultiplePatternsApp;
import multiple.patterns.logic.Shape;

/**
 * This abstract class represents a base for command classes This class has to
 * implement the command interface, it stores all the information required for
 * executing an action, including the method to call, the method arguments, and
 * the receiver that implements the method MultiplePatternsApp
 * This class represents also the care taker in the Memento design pattern
 *
 */
public abstract class AbstractShapeAction implements Command {
	// The memento object
	private Memento memento;
	// the receiver object
	protected MultiplePatternsApp app;

	/**
	 * Execution of the command
	 */
	public Shape execute() {
		// Store the image of the memento before execution
		this.memento = app.getMemento();
		// execute the action
		return performAction();
	}

	// Method signature to be implemented by each action
	abstract protected Shape performAction();

	/**
	 * Undo operation
	 */
	public void undo() {
		// Store the state of the object before the undo
		Memento redoMemento = app.getMemento();
		// Set the state to the previous one
		app.setMemento(memento);
		// Store the previous state 
		memento = redoMemento;
	}

	/**
	 * Redo operation
	 */
	public void redo() {
		// Get the state of the object before the redo
		Memento undoMemento = app.getMemento();
		// Set the state to the previous one
		app.setMemento(memento);
		// Store the previous state
		memento = undoMemento;
	}

}
