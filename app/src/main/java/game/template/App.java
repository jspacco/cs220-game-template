/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package game.template;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application
{
    private static final int NUM_ROWS = 6;
    private static final int NUM_COLS = 5;
    private VBox root;
    private TextField[][] textFields = new TextField[NUM_ROWS][NUM_COLS];

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = new VBox();

        root.getChildren().add(createMenuBar());

        GridPane gridPane = new GridPane();
        root.getChildren().add(gridPane);
        gridPane.getStyleClass().add("grid-pane");

        for (int row = 0; row < NUM_ROWS; row++)
        {
            for (int col = 0; col < NUM_COLS; col++)
            {
                textFields[row][col] = new TextField();
                TextField textField = textFields[row][col];
                
                // 6 rows, 5 columns for WORDLE
                textField.setId(row + "-" + col);
                gridPane.add(textField, col, row);
            }
        }

        root.setOnKeyPressed(event -> {
            System.out.println("Key pressed: " + event.getCode());
            switch (event.getCode())
            {
                // check for the key input
                case ESCAPE:
                    // remove focus from the textfields by giving it to the root VBox
                    root.requestFocus();
                    System.out.println("You pressed ESC key");
                    break;
                case ENTER:
                    System.out.println("You pressed ENTER key");
                    break;
                default:
                    System.out.println("you typed key: " + event.getCode());
                    break;
                
            }
        });

        // don't give a width or height to the scene
        Scene scene = new Scene(root);

        URL styleURL = getClass().getResource("/style.css");
        String stylesheet = styleURL.toExternalForm();
        scene.getStylesheets().add(stylesheet);
        primaryStage.setTitle("GAME TEMPLATE");
        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            System.out.println("oncloserequest");
        });

    }

    private MenuBar createMenuBar()
    {
        MenuBar menuBar = new MenuBar();
    	menuBar.getStyleClass().add("menubar");

        //
        // File Menu
        //
    	Menu fileMenu = new Menu("File");

        addMenuItem(fileMenu, "Load from file", () -> {
            System.out.println("Load from file");
        });

        menuBar.getMenus().add(fileMenu);

        return menuBar;
    }

    private void addMenuItem(Menu menu, String name, Runnable action)
    {
        MenuItem menuItem = new MenuItem(name);
        menuItem.setOnAction(event -> action.run());
        menu.getItems().add(menuItem);
    }

    public static void main(String[] args) 
    {
        launch(args);
    }
}
