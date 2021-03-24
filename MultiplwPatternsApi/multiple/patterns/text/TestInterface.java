package multiple.patterns.text;

import multiple.patterns.action.CloneAction;
import multiple.patterns.action.CommandStack;
import multiple.patterns.action.CreateAction;
import multiple.patterns.action.MoveAction;
import multiple.patterns.action.PaintAction;
import multiple.patterns.action.ScaleAction;
import multiple.patterns.app.MultiplePatternsApp;
import multiple.patterns.logic.Shape.ShapeColor;
import multiple.patterns.logic.Shape.ShapeType;

public class TestInterface {

	
	
	public static void test(String[] args) {
		MultiplePatternsApp app = new MultiplePatternsApp();
		CommandStack stack=new CommandStack();
		
		
		stack.execute(new CreateAction(app, 20, 44, 7, ShapeColor.RED, ShapeType.CIRCLE));
		stack.execute(new CreateAction(app, 10, 50, 80, ShapeColor.GREEN, ShapeType.SQUARE));
		System.out.println("############ After creation");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.undo();
		System.out.println("############ After undo 1");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.undo();
		System.out.println("############ After undo 2");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.redo();
		System.out.println("############ After redo 1");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.redo();
		System.out.println("############ After redo 2");
		app.getShapes().forEach(shape -> System.out.println(shape));
		
		System.out.println("");
		System.out.println("");
		
		stack.execute(new CloneAction(app, 50, 100, 1));
		stack.execute(new CloneAction(app, 10, 20, 3));
		System.out.println("############ After Cloning");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.undo();
		System.out.println("############ After undo cloning 1");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.undo();
		System.out.println("############ After undo cloning 2");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.redo();
		System.out.println("############ After redo cloning 1");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.redo();
		System.out.println("############ After redo cloning 2");
		app.getShapes().forEach(shape -> System.out.println(shape));
		
		System.out.println("");
		System.out.println("");
		
		stack.execute(new MoveAction(app,0, 0, 4));
		System.out.println("############ After Move");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.undo();
		System.out.println("############ After undo move");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.redo();
		System.out.println("############ After redo move");
		app.getShapes().forEach(shape -> System.out.println(shape));
		
		System.out.println("");
		System.out.println("");

		stack.execute(new PaintAction(app,ShapeColor.BLUE, 2));
		System.out.println("############ After Paint");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.undo();
		System.out.println("############ After undo Paint");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.redo();
		System.out.println("############ After redo Paint");
		app.getShapes().forEach(shape -> System.out.println(shape));
		
		System.out.println("");
		System.out.println("");

		stack.execute(new ScaleAction(app, 2, true, 1));
		System.out.println("############ After Scale increase");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.undo();
		System.out.println("############ After undo Scale increase");
		app.getShapes().forEach(shape -> System.out.println(shape));
		
		stack.execute(new ScaleAction(app, 3, false, 1));
		System.out.println("############ After Scale decrease");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.undo();
		System.out.println("############ After undo Scale decrease");
		app.getShapes().forEach(shape -> System.out.println(shape));
		stack.redo();
		System.out.println("############ After redo Scale decrease");
		app.getShapes().forEach(shape -> System.out.println(shape));
		

		

	}

}
