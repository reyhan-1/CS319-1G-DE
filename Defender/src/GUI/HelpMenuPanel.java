package GUI;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class HelpMenuPanel extends Pane {

    private ScreenManager screenManager;
    private boolean mainHelp;

    public HelpMenuPanel(ScreenManager sm, boolean mH){
        screenManager = sm;
        mainHelp = mH;
        this.setPrefSize(800,600);


        String[][] helpMenus = {
                { " >", "Go right" },
                { " <", "Go Left" },
                { " ↑","Go up"  },
                { " ↓", "Go down" }
        };

        String helpMenuStr = "\n";
        for(int i=0; i < helpMenus.length; i++){
            helpMenuStr += helpMenus[i][0] + "\t" + helpMenus[i][1] + "\n";
        }

        helpMenuStr +=
                "\nMission:\n\nGain points by defeating enemies and saving astronauts\nfrom them.\n" +
                        "Advance through the waves by defeating\n a number of spesific enemy types.";
        Label helpMenu = new Label(helpMenuStr);
        Font labelFont = new Font( "Arial", 24);
        helpMenu.setFont( labelFont);
        helpMenu.setStyle("-fx-text-fill: yellow");
        this.getChildren().add(helpMenu);

        Button backButton = new Button("Back");
        backButton.setStyle( "-fx-background-color: Yellow; -fx-text-fill: Black");

        this.getChildren().add(backButton);

        this.setBackground( new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY, Insets.EMPTY)));
        this.setStyle("-fx-border-color: yellow");

        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if ( mainHelp == true) {
                    screenManager.viewMainMenu();
                }
                else{
                    screenManager.viewPauseMenu();
                }
            }
        });
    }


}
