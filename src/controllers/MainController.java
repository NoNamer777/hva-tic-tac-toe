package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import views.*;

/**
 * Main screen logic
 *
 * @author HvA HBO-ICT
 */
public class MainController {

    private MenuItem mainMenuMenuItem;
    private MenuItem closeMenuItem;
    private MenuItem statsPlayedMatchesMenuItem;
    private MenuItem statsPlayedCompMenuItem;
    private MenuItem statsLeaderBoardMenuItem;
    private MenuItem aboutMenuItem;
    private MenuItem playMenuItem;
    private BorderPane borderPane;

    public void initialize() {
        AboutController aboutController = new AboutController();
        AboutView aboutView = new AboutView(aboutController);

        PlayController playController = new PlayController();
        PlayView playView = new PlayView(playController);

        PlayerStatsController playerStatsController = new PlayerStatsController();
        PlayerStatsView playerStatsView = new PlayerStatsView(playerStatsController);

        MatchHistoryController matchHistoryController = new MatchHistoryController();
        MatchHistoryView matchHistoryView = new MatchHistoryView(matchHistoryController);

        CompetitionHistoryController competitionHistoryController = new CompetitionHistoryController();
        CompetitionHistoryView competitionHistoryView = new CompetitionHistoryView(competitionHistoryController);

        mainMenuMenuItem.setOnAction(t -> {
            System.out.println("Main menu called");
        });

        statsPlayedMatchesMenuItem.setOnAction(t-> {
            System.out.println("Stats played matches called");
            matchHistoryController.resetInputFields();
            matchHistoryController.getMatchHistory();
            borderPane = matchHistoryView.changeBorderPane(borderPane);
        });

        statsPlayedCompMenuItem.setOnAction(t-> {
            System.out.println("Stats played competitions called");

            competitionHistoryController.clearInputFields();
            borderPane = competitionHistoryView.changeBorderPane(borderPane);
        });

        statsLeaderBoardMenuItem.setOnAction(t-> {
            System.out.println("LeaderBoard called");

            playerStatsController.getPlayersArchive();
            playerStatsController.populateTable();
            borderPane = playerStatsView.changeBorderPane(borderPane);
        });

        playMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Play called");

                playView.show();
            }
        });

        closeMenuItem.setOnAction(t -> {
            System.out.println("Close called");

            System.exit(0);
        });

        aboutMenuItem.setOnAction(t -> {
            System.out.println("About called");

            borderPane = aboutView.changeBorderPane(borderPane);
        });
    }

    public void setStatsPlayedMatchesMenuItem(MenuItem statsPlayedMatchesMenuItem) {
        this.statsPlayedMatchesMenuItem = statsPlayedMatchesMenuItem;
    }

    public void setStatsPlayedCompMenuItem(MenuItem statsPlayedCompMenuItem) {
        this.statsPlayedCompMenuItem = statsPlayedCompMenuItem;
    }

    public void setStatsLeaderBoardMenuItem(MenuItem statsLeaderBoardMenuItem) {
        this.statsLeaderBoardMenuItem = statsLeaderBoardMenuItem;
    }

    public void setCloseMenuItem(MenuItem closeMenuItem) {
        this.closeMenuItem = closeMenuItem;
    }

    public void setAboutMenuItem(MenuItem aboutMenuItem) {
        this.aboutMenuItem = aboutMenuItem;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public void setMainMenuMenuItem(MenuItem mainMenuMenuItem) {
        this.mainMenuMenuItem = mainMenuMenuItem;
    }

    public void setPlayMenuItem(MenuItem playMenuItem) {
        this.playMenuItem = playMenuItem;
    }
}
