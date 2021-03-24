package multiple.patterns.action;

import java.util.LinkedList;

import multiple.patterns.logic.Shape;

/**
 * This class represents the invoker class in the command Design pattern
 * it knows how to execute a given command but doesn’t know how the command has been implemented
 *
 */

public class CommandStack {
		
		// Stack to store the executed commands
	   private LinkedList<Command> commandStack = new LinkedList<Command>();
	   // Stack to store the undone actions to be redone on demand
	   private LinkedList<Command> redoStack = new LinkedList<Command>();

	   /**
	    * Executes the proper operation depending of the command parameter
	    * @param command
	    * @return the shape object created or modified
	    */
	   public Shape execute(Command command) {
		   // Execution of the command
	      Shape result = command.execute();
	      //Add the command to the command stack from undo purposes
	      commandStack.addFirst(command);
	      //clear the redo stack
	      redoStack.clear();
	      return result;
	   }

	   /**
	    * Undo the last command
	    */
	   public void undo() {
	      if (commandStack.isEmpty())
	         return;
	      //Remove the action to be undone from the command stack
	      Command command = commandStack.removeFirst();
	      // Undo the command
	      command.undo();
	      // Add the command to the redo stack
	      redoStack.addFirst(command);
	   }

	   /**
	    * Redo the undone commands
	    */
	   public void redo() {
	      if (redoStack.isEmpty())
	         return;
	      // Remove the command to be redone from the redo stack
	      Command command = redoStack.removeFirst();
	      // Redo the command
	      command.redo();
	      // Send back the redone command to the command stack
	      commandStack.addFirst(command);
	   }
	   
	   
	   /**
	    *  Check if the command stack has elements
	    * @return true if the command stack contains elements, false instead
	    */
	   public boolean hasUndo() {
		   return !commandStack.isEmpty();
	   }
	   
	   /**
	    *  Check if the redo stack has elements
	    * @return true if the redo stack contains elements, false instead
	    */
	   public boolean hasRedo() {
		   return !redoStack.isEmpty();
	   }
	}
