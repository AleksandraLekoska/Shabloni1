package multiple.patterns.action;

import multiple.patterns.logic.Shape;

/**
 * The interface to be implemented by the command object in the command Design pattern
 *
 */
public interface Command {
	/**
	 * Execute operation
	 * @return
	 */
	Shape execute();
	/**
	 * undo operation
	 */
	void undo();
	/**
	 * redo operation
	 */
	void redo();

}
