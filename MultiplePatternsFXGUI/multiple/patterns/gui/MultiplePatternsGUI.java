package multiple.patterns.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import multiple.patterns.action.CloneAction;
import multiple.patterns.action.CommandStack;
import multiple.patterns.action.CreateAction;
import multiple.patterns.action.DeleteAction;
import multiple.patterns.action.MoveAction;
import multiple.patterns.action.PaintAction;
import multiple.patterns.action.ScaleAction;
import multiple.patterns.app.MultiplePatternsApp;
import multiple.patterns.logic.CircleShape;
import multiple.patterns.logic.Shape;
import multiple.patterns.logic.Shape.ShapeColor;
import multiple.patterns.logic.Shape.ShapeType;
import multiple.patterns.logic.SquareShape;

/**
 * This class represent the client class in the command design pattern
 *
 */
public class MultiplePatternsGUI extends Application implements Initializable {
	private static final String POSITIVE_FLOAT_REGEX_MATCH = "[0-9]*\\.?[0-9]+";
	private static final String POSITIVE_INTEGER_REGEX_MATCH = "\\d+";
	MultiplePatternsApp app = new MultiplePatternsApp();
	CommandStack stack = new CommandStack();
	Shape selected = null;

	@FXML
	private Button addCircleButton;

	@FXML
	private FontAwesomeIconView circleIcon;

	@FXML
	private Button addSquareButton;

	@FXML
	private FontAwesomeIconView squareIcon;

	@FXML
	private Button cloneButton;

	@FXML
	private FontAwesomeIconView cloneIcon;

	@FXML
	private Button moveButton;

	@FXML
	private FontAwesomeIconView moveIcon;

	@FXML
	private Button scaleButton;

	@FXML
	private FontAwesomeIconView scaleIcon;

	@FXML
	private Button colorButton;

	@FXML
	private FontAwesomeIconView colorIcon;

	@FXML
	private Button deleteButton;

	@FXML
	private FontAwesomeIconView deleteIcon;

	@FXML
	private Button undoButton;

	@FXML
	private FontAwesomeIconView undoIcon;

	@FXML
	private Button redoButton;

	@FXML
	private FontAwesomeIconView redoIcon;

	@FXML
	private TabPane TabPlane;

	@FXML
	private Tab tabVisual;

	@FXML
	private Group canvas;

	@FXML
	private Tab tabTextual;

	@FXML
	private TableView<Shape> shapesTable;

	@FXML
	private TableColumn<Shape, Integer> idShape;

	@FXML
	private TableColumn<Shape, ShapeType> shapeType;

	@FXML
	private TableColumn<Shape, Integer> x;

	@FXML
	private TableColumn<Shape, Integer> y;

	@FXML
	private TableColumn<Shape, Float> dimension;

	@FXML
	private TableColumn<Shape, ShapeColor> color;

	@FXML
	private TableColumn<Shape, Boolean> isClone;

	@FXML
	private TextField xInput;

	@FXML
	private TextField yInput;

	@FXML
	private TextField dimensionInput;

	@FXML
	private TextField relativeXInput;

	@FXML
	private TextField relativeYInput;

	@FXML
	private Button circleValidateButton;

	@FXML
	private FontAwesomeIconView circleValidateIcon;

	@FXML
	private Button cancelButton;

	@FXML
	private FontAwesomeIconView cancelIcon;

	@FXML
	private Button squareValidateButton;

	@FXML
	private FontAwesomeIconView squareValidateIcon;

	@FXML
	private Button cloneValidateButton;

	@FXML
	private FontAwesomeIconView cloneValidateIcon;

	@FXML
	private Button paintValidateButton;

	@FXML
	private FontAwesomeIconView paintValidateIcon;

	@FXML
	private ChoiceBox<String> colorInput;

	@FXML
	private Label labelDetail;

	@FXML
	private Button moveValidateButton;

	@FXML
	private FontAwesomeIconView moveValidateIcon;

	@FXML
	private CheckBox cloneCheckBox;
	
    @FXML
    private Label scaleTypeLabel;

    @FXML
    private RadioButton scaleIncreaseRadio;

    @FXML
    private ToggleGroup scaleToggle;

    @FXML
    private RadioButton scaleDecreaseRadio;

    @FXML
    private Label scaleFactorLabel;

    @FXML
    private TextField factorInput;
    
    @FXML
    private Button scaleValidateButton;

    @FXML
    private FontAwesomeIconView scaleValidateIcon;

	@Override
	public void start(Stage primaryStage) {
		try {

			// Define the fxml file that contains the shape of the GUI
			Parent root = FXMLLoader.load(getClass().getResource("MultiplePatternsMain.fxml"));

			// Create the scene and define its style
			Scene scene = new Scene(root, 1000, 750);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// Link the scene with the stage and define its title
			primaryStage.setTitle("Multiple Patterns");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create circle command
	 * @param x
	 * @param y
	 * @param dimension
	 * @param color
	 */
	public void createCircle(int x, int y, float dimension, ShapeColor color) {
		// Call the command
		selected = (CircleShape) stack.execute(new CreateAction(app, x, y, dimension, color, ShapeType.CIRCLE));
		// Refresh the GUI
		paint();
	}

	/**
	 * Create square ethod
	 * @param x
	 * @param y
	 * @param dimension
	 * @param color
	 */
	public void createSquare(int x, int y, float dimension, ShapeColor color) {
		// Call the command
		selected = (SquareShape) stack.execute(new CreateAction(app, x, y, dimension, color, ShapeType.SQUARE));
		// Refresh the GUI
		paint();
	}

	/**
	 * Clone shape method
	 * @param relativeX
	 * @param relativeY
	 */
	public void cloneShape(int relativeX, int relativeY) {
		// Call the command
		selected = stack.execute(new CloneAction(app, relativeX, relativeY, selected.getId()));
		// Refresh the GUI
		paint();
	}

	/**
	 * Change shape color
	 * @param color
	 */
	public void paintShape(ShapeColor color) {
		// Call the command
		selected = stack.execute(new PaintAction(app, color, selected.getId()));
		// Refresh the GUI
		paint();
	}

	/**
	 * Move shape
	 * @param x
	 * @param y
	 */
	public void moveShape(int x, int y) {
		// Call the command
		selected = stack.execute(new MoveAction(app, x, y, selected.getId()));
		// Refresh the GUI
		paint();
	}
	
	/**
	 * Increase or decrease the shape size with a factor
	 * @param factor
	 * @param increase
	 */
	public void scaleShape(int factor, boolean increase) {
		// Call the command
		selected = stack.execute(new ScaleAction(app, factor, increase, selected.getId()));
		// Refresh the GUI
		paint();
	}

	/**
	 * Delete the selected shape, if the shape is a clone, only the current is deleted
	 * If the selected shape is not a clone, it is deleted with all its clones
	 */
	public void deleteShape() {
		// Call the command
		selected = stack.execute(new DeleteAction(app, selected));
		// Refresh GUI
		paint();
	}

	/**
	 * Adapt the GUI to the creation of a circle
	 */
	public void initCreateCircle() {
		// Reset the selected shape
		selected = null;

		// Enable creation fields
		xInput.setDisable(false);
		yInput.setDisable(false);
		dimensionInput.setDisable(false);
		colorInput.setDisable(false);
		relativeXInput.setDisable(true);
		relativeYInput.setDisable(true);

		// Reset the creation fields
		xInput.setText("");
		yInput.setText("");
		dimensionInput.setText("");
		relativeXInput.setText("");
		relativeYInput.setText("");
		colorInput.getSelectionModel().clearSelection();
		cloneCheckBox.setSelected(false);

		// Show cancel and create Circle validation buttons
		cancelButton.setVisible(true);
		circleValidateButton.setVisible(true);
		
		// Disable the other command validation buttons
		squareValidateButton.setVisible(false);
		cloneValidateButton.setVisible(false);
		paintValidateButton.setVisible(false);
		moveValidateButton.setVisible(false);
		scaleValidateButton.setVisible(false);

		// Disable the modification buttons
		cloneButton.setDisable(true);
		scaleButton.setDisable(true);
		moveButton.setDisable(true);
		colorButton.setDisable(true);
		deleteButton.setDisable(true);

		// Show the current command label
		labelDetail.setText("Circle creation");
	}
	
	/**
	 * Adapt the GUI to the scale of a shape
	 */
	public void initScale() {
		// Disable form fields
		xInput.setDisable(true);
		yInput.setDisable(true);
		dimensionInput.setDisable(true);
		colorInput.setDisable(true);
		relativeXInput.setDisable(true);
		relativeYInput.setDisable(true);
		scaleFactorLabel.setVisible(true);
		scaleTypeLabel.setVisible(true);
		scaleIncreaseRadio.setVisible(true);
		scaleDecreaseRadio.setVisible(true);
		factorInput.setVisible(true);
		
		// Show scale command fields
		factorInput.setText("");
		scaleDecreaseRadio.setSelected(false);
		scaleIncreaseRadio.setSelected(true);

		// Show cancel and scale validation buttons
		cancelButton.setVisible(true);
		scaleValidateButton.setVisible(true);
		
		// Hide the other command validation buttons
		circleValidateButton.setVisible(false);
		squareValidateButton.setVisible(false);
		cloneValidateButton.setVisible(false);
		paintValidateButton.setVisible(false);
		moveValidateButton.setVisible(false);

		// Show the current operation's label
		labelDetail.setText("Shape scaling");
	}

	/**
	 * Adapt the GUI to the creation of a square
	 */
	public void initCreateSquare() {
		// Reset the selected shape
		selected = null;

		// Enable the creation fields
		xInput.setDisable(false);
		yInput.setDisable(false);
		dimensionInput.setDisable(false);
		colorInput.setDisable(false);
		
		// Disable the relative coordinates fields
		relativeXInput.setDisable(true);
		relativeYInput.setDisable(true);

		// Reset the fields values
		xInput.setText("");
		yInput.setText("");
		dimensionInput.setText("");
		relativeXInput.setText("");
		relativeYInput.setText("");
		colorInput.getSelectionModel().clearSelection();
		cloneCheckBox.setSelected(false);

		// Show cancel and create square validation buttons
		cancelButton.setVisible(true);
		squareValidateButton.setVisible(true);
		
		// Hide the other command validation buttons
		circleValidateButton.setVisible(false);
		cloneValidateButton.setVisible(false);
		paintValidateButton.setVisible(false);
		moveValidateButton.setVisible(false);
		scaleValidateButton.setVisible(false);

		// Display a message with the current operation
		labelDetail.setText("Square creation");

		// Display the modification action buttons
		cloneButton.setDisable(true);
		scaleButton.setDisable(true);
		moveButton.setDisable(true);
		colorButton.setDisable(true);
		deleteButton.setDisable(true);
	}

	/**
	 * Adapt the GUI to the clone operation
	 */
	public void initClone() {
		// Enable relativeX , relativeY fields
		relativeXInput.setDisable(false);
		relativeYInput.setDisable(false);
		
		// Disable the others form's fieldsÒ
		xInput.setDisable(true);
		yInput.setDisable(true);
		dimensionInput.setDisable(true);
		colorInput.setDisable(true);

		// Show cancel and clone validation buttons
		cancelButton.setVisible(true);
		cloneValidateButton.setVisible(true);
		
		// Hide the other command validation buttons
		circleValidateButton.setVisible(false);
		squareValidateButton.setVisible(false);
		paintValidateButton.setVisible(false);
		moveValidateButton.setVisible(false);
		scaleValidateButton.setVisible(false);

		// Display the current operation
		labelDetail.setText("Shape cloning");

		// Initialize the relativeX and relativeY values
		relativeXInput.setText("0");
		relativeYInput.setText("0");
	}

	/**
	 * Adapt the GUI to the move operation
	 */
	public void initMove() {

		// Enable the X, Y fields
		xInput.setDisable(false);
		yInput.setDisable(false);
		
		// Disable the other fields of the form
		dimensionInput.setDisable(true);
		colorInput.setDisable(true);
		relativeXInput.setDisable(true);
		relativeYInput.setDisable(true);

		// Show the cancel and validate move buttons
		cancelButton.setVisible(true);
		moveValidateButton.setVisible(true);
		
		// Hide the other validation buttons
		circleValidateButton.setVisible(false);
		squareValidateButton.setVisible(false);
		cloneValidateButton.setVisible(false);
		paintValidateButton.setVisible(false);
		scaleValidateButton.setVisible(false);

		// Display a message that shows which command is initialized
		labelDetail.setText("Shape cloning");
	}

	/**
	 * Adapt the GUI to the paint operation
	 */
	public void initPaint() {

		// Enable the color field
		colorInput.setDisable(false);
		// Disable the other fields of the form
		xInput.setDisable(true);
		yInput.setDisable(true);
		dimensionInput.setDisable(true);
		relativeXInput.setDisable(true);
		relativeYInput.setDisable(true);

		// Show the cancel and paint button to validation the operation
		cancelButton.setVisible(true);
		paintValidateButton.setVisible(true);
		
		// Hide the other validation buttons
		circleValidateButton.setVisible(false);
		squareValidateButton.setVisible(false);
		cloneValidateButton.setVisible(false);
		moveValidateButton.setVisible(false);
		scaleValidateButton.setVisible(false);

		// Display a message that shows which command is initialized
		labelDetail.setText("Shape painting");

	}

	/**
	 * Refresh the GUI and reset the status of the buttons and fields
	 */
	public void paint() {
		if (selected == null) {
			
			// Disable the modification command buttons when no element is selected
			cloneButton.setDisable(true);
			scaleButton.setDisable(true);
			moveButton.setDisable(true);
			colorButton.setDisable(true);
			deleteButton.setDisable(true);
			
			// Reset the creation fields
			xInput.setText("");
			yInput.setText("");
			dimensionInput.setText("");
			relativeXInput.setText("");
			relativeYInput.setText("");
			colorInput.getSelectionModel().clearSelection();
			cloneCheckBox.setSelected(false);
			
			// Hide the validate modification command buttons
			cancelButton.setVisible(false);
			circleValidateButton.setVisible(false);
			squareValidateButton.setVisible(false);
			cloneValidateButton.setVisible(false);
			paintValidateButton.setVisible(false);
			moveValidateButton.setVisible(false);
			scaleValidateButton.setVisible(false);
			
			// Disable the input fields
			xInput.setDisable(true);
			yInput.setDisable(true);
			dimensionInput.setDisable(true);
			colorInput.setDisable(true);
			relativeXInput.setDisable(true);
			relativeYInput.setDisable(true);
			
			// Reset label value
			labelDetail.setText("");
			
		} else {
			// Enable the modification command buttons when an element is selected
			cloneButton.setDisable(false);
			scaleButton.setDisable(false);
			moveButton.setDisable(false);
			colorButton.setDisable(false);
			deleteButton.setDisable(false);

			// Display the selected shape's properties on the detail area
			xInput.setText(String.valueOf(selected.getX()));
			yInput.setText(String.valueOf(selected.getY()));
			dimensionInput.setText(String.valueOf(selected.getDimension()));
			colorInput.getSelectionModel().select(selected.getColor().toString());
			relativeXInput.setText(String.valueOf(selected.getRelativeX()));
			relativeYInput.setText(String.valueOf(selected.getRelativeY()));
			cloneCheckBox.setSelected(selected.isClone());

			// Disable the input fields
			xInput.setDisable(true);
			yInput.setDisable(true);
			dimensionInput.setDisable(true);
			colorInput.setDisable(true);
			relativeXInput.setDisable(true);
			relativeYInput.setDisable(true);

			// Hide the validate modification command buttons
			cancelButton.setVisible(false);
			circleValidateButton.setVisible(false);
			squareValidateButton.setVisible(false);
			cloneValidateButton.setVisible(false);
			paintValidateButton.setVisible(false);
			moveValidateButton.setVisible(false);
			scaleValidateButton.setVisible(false);

			// Show a message identifying the selected element under the detail area
			labelDetail.setText(
					"The selected shape is a " + selected.getType().toString() + " with ID " + selected.getId());
		}
		
		// Hide the scale inputs (only used during the scale process)
		scaleFactorLabel.setVisible(false);
		scaleTypeLabel.setVisible(false);
		scaleIncreaseRadio.setVisible(false);
		scaleDecreaseRadio.setVisible(false);
		factorInput.setVisible(false);

		// Enable/Disable the undo button depending of the status of the command stack
		if (stack.hasUndo()) {
			undoButton.setDisable(false);
		} else {
			undoButton.setDisable(true);
		}

		// Enable/Disable the redo button depending of the status of the redo stack
		if (stack.hasRedo()) {
			redoButton.setDisable(false);
		} else {
			redoButton.setDisable(true);
		}

		// Display the list of shape in a textual form
		populateShapeTable();
		
		// Clear the drawing area
		canvas.getChildren().clear();
		
		// Get an iterator over the shapes list
		Iterator<Shape> shapesIterator = app.getShapes().iterator();
		//Check if the iterator has elements
		while (shapesIterator.hasNext()) {
			// Get the next element from the iterator
			Shape shape = shapesIterator.next();
			// Draw the shapes on the drawing area depending on the type
			switch (shape.getType()) {
				case CIRCLE:
					drawCircleFromShape(shape);
					break;
				case SQUARE:
					drawSquareFromShape(shape);
					break;
				}
		}
	}

	/**
	 * Fill the TableView data from the shapes list
	 */
	private void populateShapeTable() {
		// Create the observableList from the list of shapes
		ObservableList<Shape> data = FXCollections.observableList(new ArrayList<>(app.getShapes()));
		
		// Set the mapping between the columns and the fields of the shape
		x.setCellValueFactory(new PropertyValueFactory<Shape, Integer>("x"));
		y.setCellValueFactory(new PropertyValueFactory<Shape, Integer>("y"));
		dimension.setCellValueFactory(new PropertyValueFactory<Shape, Float>("dimension"));
		color.setCellValueFactory(new PropertyValueFactory<Shape, ShapeColor>("color"));
		shapeType.setCellValueFactory(new PropertyValueFactory<Shape, ShapeType>("type"));
		isClone.setCellValueFactory(new PropertyValueFactory<Shape, Boolean>("clone"));
		idShape.setCellValueFactory(new PropertyValueFactory<Shape, Integer>("id"));
		
		// Set the table data
		shapesTable.getItems().setAll(data);
	}

	/**
	 * Method to draw the visual circle from the logical shape
	 * @param shape
	 */
	private void drawCircleFromShape(Shape shape) {
		// Create a circle shape with a radius equals to dimension, and the identified color
		// (X,Y) are the coordinates of the center of the circle
		Circle circle = new Circle(shape.getX(), shape.getY(), shape.getDimension(),
				Color.valueOf(shape.getColor().toString()));
		// Identify the visual shape with the same id of the logical shape
		circle.setId(String.valueOf(shape.getId()));
		// Add a stroke to identify visually the selected square
		if (selected!= null && shape.getId() == selected.getId()) {
			circle.setStroke(Color.CORAL);
		} else {
			circle.setStroke(Color.BLACK);
		}
		// Add an event to get the selected circle one the user clicks on the visual circle
		circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				// Get the circle by it's id from the shapes map
				Shape sel = app.getShape(Integer.parseInt(circle.getId()));
				// set the selected shape
				selected = sel;
				// Refresh the GUI
				paint();
			}
		});
		// Set the stroke width
		circle.setStrokeWidth(1);
		// Add the circle to the visual area
		canvas.getChildren().add(circle);
	}

	/**
	 * Method to draw the visual square from the logical shape
	 * @param shape
	 */
	private void drawSquareFromShape(Shape shape) {
		// Create a special case of the rectangle shape (both sides are equal to dimension)
		// (X,Y) are the coordinates of the top left point of the square, so half the side is subtracted
		Rectangle square = new Rectangle(shape.getX()-shape.getDimension()/2, shape.getY()-shape.getDimension()/2, shape.getDimension(), shape.getDimension());
		// Identify the visual shape with the same id of the logical shape
		square.setId(String.valueOf(shape.getId()));
		// Set the square's color
		square.setFill(Color.valueOf(shape.getColor().toString()));
		// Add a stroke to identify visually the selected square
		if (selected!=null && shape.getId() == selected.getId()) {
			square.setStroke(Color.CORAL);
		} else {
			square.setStroke(Color.BLACK);
		}
		
		// Add an event to get the selected square one the user clicks on the visual square
		square.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent t) {
				
				// Get the square by it's id from the shapes map
				Shape sel = app.getShape(Integer.parseInt(square.getId()));
				System.out.println("Selected " + sel);
				// set the selected shape
				selected = sel;
				// Refresh the GUI
				paint();
			}
		});
		// Set the stroke width
		square.setStrokeWidth(1);
		// Add the square to the visual area
		canvas.getChildren().add(square);
	}

	/**
	 * Validation of the circle creation form inputs (X, Y, dimension, Color)
	 * 
	 * @param event
	 */
	public void validateCircleCreationForm() {
		// Validation the inputs for circle creation
		boolean isXValide = validateText(xInput, POSITIVE_INTEGER_REGEX_MATCH);
		boolean isYValide = validateText(yInput, POSITIVE_INTEGER_REGEX_MATCH);
		boolean isDimensionValide = validateText(dimensionInput, POSITIVE_FLOAT_REGEX_MATCH);
		boolean isColorValide = validateChoiceBox(colorInput);
		
		ObservableList<String> styleClass = labelDetail.getStyleClass();
		if (isXValide && isYValide && isDimensionValide && isColorValide) {
			// Call the create Circle command
			createCircle(Integer.parseInt(xInput.getText()), Integer.parseInt(yInput.getText()),
					Float.parseFloat(dimensionInput.getText()),
					ShapeColor.valueOf(colorInput.getSelectionModel().getSelectedItem().toString()));
			// Remove the error style from the message label if any
			styleClass.removeAll(Collections.singleton("error"));
		} else {
			// Display an error message if the validation fails
			labelDetail.setText("Please fill the form with the appropriate values");
			// Add the error style to the message label
			if (!styleClass.contains("error")) {
				styleClass.add("error");
			}
		}

	}

	/**
	 * Validation of the clone shape form inputs (relativeX, relative Y)
	 * 
	 * @param event
	 */
	public void validateCloneForm() {
		// Validate relative coordinates
		boolean isRelativeXValide = validateText(relativeXInput, "-?\\d+");
		boolean isRelativeYValide = validateText(relativeYInput, "-?\\d+");
		ObservableList<String> styleClass = labelDetail.getStyleClass();
		if (isRelativeXValide && isRelativeYValide) {
			// Call the clone command
			cloneShape(Integer.parseInt(relativeXInput.getText()), Integer.parseInt(relativeYInput.getText()));
			// Remove the error style from the message label if any
			styleClass.removeAll(Collections.singleton("error"));
		} else {
			// Display an error message if the validation fails
			labelDetail.setText("Please fill the form with the appropriate values");
			// Add the error style to the message label
			if (!styleClass.contains("error")) {
				styleClass.add("error");
			}
		}

	}
	
	/**
	 * Validation of the shape form inputs (scaleType, factor)
	 * 
	 * @param event
	 */
	public void validateScaleForm() {
		
		// Validation the scale type and factor
		boolean isFactorValide = validateText(factorInput, POSITIVE_INTEGER_REGEX_MATCH);
		RadioButton scaleTypeRadio = (RadioButton) scaleToggle.getSelectedToggle();
		
		ObservableList<String> styleClass = labelDetail.getStyleClass();
		if (isFactorValide) {
			// Call the scale command
			scaleShape(Integer.parseInt(factorInput.getText()), scaleTypeRadio.equals(scaleIncreaseRadio));
			// Remove the error style from the message label if any
			styleClass.removeAll(Collections.singleton("error"));
		} else {
			
			// Display an error message if the validation fails
			labelDetail.setText("Please fill the form with the appropriate values");
			// Add the error style to the message label
			if (!styleClass.contains("error")) {
				styleClass.add("error");
			}
		}

	}

	/**
	 * Validation of the paint shape form inputs (Color)
	 * 
	 * @param event
	 */
	public void validatePaintForm() {
		// Validate the color field
		boolean isColorValide = validateChoiceBox(colorInput);
		ObservableList<String> styleClass = labelDetail.getStyleClass();
		if (isColorValide) {
			// Call the paint command if the form is valid
			paintShape(ShapeColor.valueOf(colorInput.getSelectionModel().getSelectedItem().toString()));
			// Remove the error style from the message label if any
			styleClass.removeAll(Collections.singleton("error"));
		} else {
			// Display an error message if the validation fails
			labelDetail.setText("Please fill the form with the appropriate values");
			// Add the error style to the message label
			if (!styleClass.contains("error")) {
				styleClass.add("error");
			}
		}

	}

	/**
	 * Validation of the square creation form inputs (X, Y, dimension, Color)
	 * 
	 * @param event
	 */
	public void validateSquareCreationForm() {

		// Validate creation properties
		boolean isXValide = validateText(xInput, POSITIVE_INTEGER_REGEX_MATCH);
		boolean isYValide = validateText(yInput, POSITIVE_INTEGER_REGEX_MATCH);
		boolean isDimensionValide = validateText(dimensionInput, POSITIVE_FLOAT_REGEX_MATCH);
		boolean isColorValide = validateChoiceBox(colorInput);
		
		
		ObservableList<String> styleClass = labelDetail.getStyleClass();
		if (isXValide && isYValide && isDimensionValide && isColorValide) {
			// Call the createSquare command if the form is valid
			createSquare(Integer.parseInt(xInput.getText()), Integer.parseInt(yInput.getText()),
					Float.parseFloat(dimensionInput.getText()),
					ShapeColor.valueOf(colorInput.getSelectionModel().getSelectedItem().toString()));
			styleClass.removeAll(Collections.singleton("error"));
		} else {
			
			// Display an error message if the validation fails
			labelDetail.setText("Please fill the form with the appropriate values");
			// Add the error style to the message label
			if (!styleClass.contains("error")) {
				styleClass.add("error");
			}
		}

	}

	/**
	 * Validation of the move shape form inputs ( X, Y)
	 * 
	 * @param event
	 */
	public void validateMoveForm() {

		// Validate coordinates
		boolean isXValide = validateText(xInput, POSITIVE_INTEGER_REGEX_MATCH);
		boolean isYValide = validateText(yInput, POSITIVE_INTEGER_REGEX_MATCH);

		ObservableList<String> styleClass = labelDetail.getStyleClass();
		// Check if the form is valid
		if (isXValide && isYValide) {
			// Call the move command
			moveShape(Integer.parseInt(xInput.getText()), Integer.parseInt(yInput.getText()));
			styleClass.removeAll(Collections.singleton("error"));
		} else {
			// display and error message if the validation fails with an error style
			labelDetail.setText("Please fill the form with the appropriate values");
			// Add the error style to the message label
			if (!styleClass.contains("error")) {
				styleClass.add("error");
			}
		}

	}

	/**
	 *  Basic text validation based on a regex match
	 * @param input
	 * @param match
	 * @return true if the validation succeeds, false otherwise
	 */
	public boolean validateText(TextField input, String match) {
		// Get the input styleClass
		ObservableList<String> styleClass = input.getStyleClass();
		// Check if the content of the input is not empty and respects the regex match
		if (input.getText().trim().length() == 0 || !input.getText().matches(match)) {
			// Change the style to error in case the validation failed
			if (!styleClass.contains("error")) {
				styleClass.add("error");
			}
			return false;
		} else {
			// remove the error style when the validation succeeds
			styleClass.removeAll(Collections.singleton("error"));
			return true;
		}
	}

	/**
	 * Method to validate the choice of a choiceBox
	 * @param input
	 * @return
	 */
	public boolean validateChoiceBox(ChoiceBox<String> input) {
		ObservableList<String> styleClass = input.getStyleClass();
		if (input.getSelectionModel().getSelectedItem() == null
				|| input.getSelectionModel().getSelectedItem().toString().isEmpty()) {
			// Add the error style to the message label
			if (!styleClass.contains("error")) {
				styleClass.add("error");
			}
			return false;
		} else {
			// Remove the error style from the message label if any
			styleClass.removeAll(Collections.singleton("error"));
			return true;
		}

	}
	
	/**
	 * Cancel action
	 */
	public void cancel() {
		//reset the selected element
		selected = null;
		//refresh the GUI
		paint();
	}

	/**
	 * Undo last action
	 */
	public void undo() {
		//undo the last command
		stack.undo();
		//reset the selected element
		selected = null;
		//refresh the GUI
		paint();
	}

	/**
	 * Redo undone actions
	 */
	public void redo() {
		//redo the last undone command
		stack.redo();
		//reset the selected element
		selected = null;
		//refresh the GUI
		paint();
	}

	/**
	 * main method to launch the app
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 *  Set the current selected element from the table view
	 */
	public void selectFromTable() {
		// Get the index of the selected row
		int index = shapesTable.getSelectionModel().getSelectedIndex();
		// Check if what it is clicked ha an id
		if (index != -1) {
			// Get the corresponding shape from the shape map
			Shape shape = shapesTable.getItems().get(index);
			// Set the selected shape
			selected = shape;
			// Refresh the GUI
			paint();
		}
	}

	/**
	 * Initializing values of the color list
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Get the color list from the shapeColor enumeration
		ObservableList<String> colors = FXCollections
				.observableList(Stream.of(Shape.ShapeColor.values()).map(Enum::name).collect(Collectors.toList()));

		// Set the choiceBox items from the observable list
		colorInput.setItems(colors);


	}
}
