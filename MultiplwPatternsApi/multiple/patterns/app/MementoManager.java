package multiple.patterns.app;

/**
 * 
 * Defining the interface that a memento has to implement
 *
 */
public interface MementoManager {
	
	/**
	 * Get the state saved
	 * @return
	 */
	Memento getMemento();

	/**
	 * set the state
	 * @param memento
	 */
	void setMemento(Memento memento);
}
