package views;

import controllers.MainController;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * UI van het hoofdscherm
 *
 * @author HvA HBO-ICT
 */
public class MainView {

    private static final String TITLE = "Competition";
    private static final int WIDTH = 660;
    private static final int HEIGHT = 320;

    private MenuBar menuBar;
    private BorderPane borderPane;

    private Menu gameMenu;
    private Menu statsMenu;
    private Menu helpMenu;
    private Menu fileMenu;
    private MenuItem mainMenuMenuItem;
    private MenuItem closeMenuItem;
    private MenuItem statsPlayedMatchesItem;
    private MenuItem statsPlayedCompMenuItem;
    private MenuItem statsLeaderBoardItem;
    private MenuItem aboutMenuItem;
    private MenuItem playMenuItem;

    private MainController controller;
    private Stage stage;

    /**
     * Maakt een instantie van de UI van het hoofdscherm
     *
     * @param controller Een instantie van de controller die nodig is om het hoofdscherm aan te sturen
     */
    public MainView(MainController controller) {
        this.controller = controller;

        stage = new Stage();
        stage.setTitle(TITLE);

        borderPane = new BorderPane();
        Parent root = createRoot();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(true);

        setupController();
    }

    private Parent createRoot() {
        menuBar = createMenuBar();

        return new VBox(menuBar, borderPane);
    }

    private MenuBar createMenuBar() {
        gameMenu = new Menu("Game");
        statsMenu = new Menu("Statistics");
        helpMenu = new Menu("Help");
        fileMenu = new Menu("File");

        mainMenuMenuItem = new MenuItem("Main Menu");
        closeMenuItem = new MenuItem("Close");
        statsPlayedMatchesItem = new MenuItem("Show Played Matches");
        statsPlayedCompMenuItem = new MenuItem("Show Played Competitions");
        statsLeaderBoardItem = new MenuItem("Leaderboard");
        aboutMenuItem = new MenuItem("About: " + TITLE);
        playMenuItem = new MenuItem("Play");

        gameMenu.getItems().addAll(
                mainMenuMenuItem,
                new SeparatorMenuItem(),
                closeMenuItem
        );

        statsMenu.getItems().addAll(
                statsPlayedMatchesItem,
                statsPlayedCompMenuItem,
                statsLeaderBoardItem
        );

        helpMenu.getItems().add(
                aboutMenuItem
        );

        fileMenu.getItems().add(
                playMenuItem
        );

        return new MenuBar(
                gameMenu,
                statsMenu,
                helpMenu,
                fileMenu
        );
    }

    private void setupController() {
        controller.setMainMenuMenuItem(mainMenuMenuItem);
        controller.setCloseMenuItem(closeMenuItem);
        controller.setStatsPlayedMatchesMenuItem(statsPlayedMatchesItem);
        controller.setStatsPlayedCompMenuItem(statsPlayedCompMenuItem);
        controller.setStatsLeaderBoardMenuItem(statsLeaderBoardItem);
        controller.setAboutMenuItem(aboutMenuItem);
        controller.setPlayMenuItem(playMenuItem);

        controller.setBorderPane(borderPane);

        controller.initialize();
    }

    /**
     * Toon dit scherm in het midden van de hoofdmonitor
     */
    public void show() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        stage.setX((primaryScreenBounds.getWidth() - WIDTH) / 2f);
        stage.setY((primaryScreenBounds.getHeight() - HEIGHT) / 2f);
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);

        stage.show();
    }
}
