package com.company;


import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class ViewManager {
	
	private static final int HEIGHT = 710;
	private static final int WIDTH = 1024;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTON_START_X = 100;
	private final static int MENU_BUTTON_START_Y = 150;
	
	
	private SpaceRunnerSubScene creditsSubscene;
	private SpaceRunnerSubScene helpSubscene;
	private SpaceRunnerSubScene scoreSubscene;

	//
	private SpaceRunnerSubScene creditsSubScene;
	private SpaceRunnerSubScene helpSubScene;
	private SpaceRunnerSubScene scoreSubScene;
	//
	
	private SpaceRunnerSubScene sceneToHide;

	
	List<SpaceRunnerButton> menuButtons;
	
	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT );
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createSubScenes();
		CreateButtons();
		createBackground();
		createLogo();
		
	}
	
	private void showSubScene(SpaceRunnerSubScene subScene) {
		if (sceneToHide != null) {
			sceneToHide.moveSubScene();
		}

		subScene.moveSubScene();
		sceneToHide = subScene;
	}
	
	private void createSubScenes() {
		
		creditsSubscene = new SpaceRunnerSubScene();
		mainPane.getChildren().add(creditsSubscene);
		helpSubscene = new SpaceRunnerSubScene();
		mainPane.getChildren().add(helpSubscene);
		scoreSubscene = new SpaceRunnerSubScene();
		mainPane.getChildren().add(scoreSubscene);


		createScoreSubScene();
		createHelpSubScene();
		createCreditsSubScene();
	
	}

	private SpaceRunnerButton createStartButton()	{
		SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
		AddMenuButtons(startButton);

		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				GameViewManager gameManager = new GameViewManager();
				gameManager.playGame();
			}
		});
		return startButton;
	}

	public Stage getMainStage() {
		return mainStage;
	}
	
	private void AddMenuButtons(SpaceRunnerButton button) {
		button.setLayoutX(MENU_BUTTON_START_X);
		button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size() * 100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	private void CreateButtons() {
		createStartButton();
		createScoresButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();
	}
	
	private void createScoresButton() {
		SpaceRunnerButton scoresButton = new SpaceRunnerButton("SCORES");
		AddMenuButtons(scoresButton);
		
		scoresButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(scoreSubScene);
				
			}
		});
	}

	private void createScoreSubScene() {
		scoreSubScene = new SpaceRunnerSubScene();
		mainPane.getChildren().add(scoreSubScene);
		InfoLabel score = new InfoLabel("<<<< Scores >>>>");
		score.setLayoutX(115);
		score.setLayoutY(20);
		VBox scoreContainer = new VBox();
		scoreContainer.setLayoutX(180);
		scoreContainer.setLayoutY(100);

		Label scoreHeading = new Label(" Name		  Score ");
		scoreHeading.setUnderline(true);
		Label score1 = new Label("Player 1		  	100");
		Label score2 = new Label("Player 2		  	100");
		Label score3 = new Label("Player 3		  	100");
		Label score4 = new Label("Player 4		  	100");
		Label score5 = new Label("Player 5		  	100");
		Label score6 = new Label("Player 6		  	100");
		Label score7 = new Label("Player 7		  	100");
		Label score8 = new Label("Player 8		  	100");
		Label score9 = new Label("Player 9		  	100");
		Label score10 = new Label("Player 10		  	100");
		scoreHeading.setFont(Font.font("Verdana",20));
		score1.setFont(Font.font("Verdana",17));
		score2.setFont(Font.font("Verdana",17));
		score3.setFont(Font.font("Verdana",17));
		score4.setFont(Font.font("Verdana",17));
		score5.setFont(Font.font("Verdana",17));
		score6.setFont(Font.font("Verdana",17));
		score7.setFont(Font.font("Verdana",17));
		score8.setFont(Font.font("Verdana",17));
		score9.setFont(Font.font("Verdana",17));
		score10.setFont(Font.font("Verdana",17));
		scoreContainer.setBackground(new Background(new BackgroundFill(Color.MEDIUMAQUAMARINE, new CornerRadii(20), new Insets(-20,-20,-20,-20))));
		scoreContainer.getChildren().addAll(scoreHeading, score1, score2, score3, score4, score5, score6, score7, score8, score9, score10);

		scoreSubScene.getPane().getChildren().addAll(score, scoreContainer);//, score1, score2, score3);

	}

//	private void setScoreSubscene() {
//		String names = "(name, difficulty, level, score)";
//		String values = "('" + NameTextField.getText() + "', '" + LevelChoiceBox.getValue().toString() + "', '" + LevelLabel.getText().split(" ")[1] + "', '" + ScoreLabel.getText().split(" ")[1] + "')";
//		String dbCommand = "INSERT INTO player " + names + "VALUES" + values;
//		DbConnect dbConn = new DbConnect();
//		dbConn.insert(dbCommand);
//
//		String dbCommand = "SELECT * FROM player WHERE difficulty='" + LevelChoiceBox.getValue().toString() + "' ORDER BY score DESC";
//		DbConnect dbConn =  new DbConnect();
//		dbConn.start(dbCommand);
//	}
	
	private void createHelpButton() {
		SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
		AddMenuButtons(helpButton);
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(helpSubScene);
				
			}
		});
	}

	private void createHelpSubScene() {
		helpSubScene = new SpaceRunnerSubScene();
		mainPane.getChildren().add(helpSubScene);
		InfoLabel help = new InfoLabel("   <<<< Help >>>>");
		help.setLayoutX(110);
		help.setLayoutY(20);
		GridPane helpGrid = new GridPane();
		helpGrid.setLayoutX(100);
		helpGrid.setLayoutY(100);
		helpGrid.setHgap(20);
		helpGrid.setVgap(20);

//		ImageView ship = new ImageView(new Image(SHIP.RED.getUrl(), 80, 80, true, false));
		ImageView meteor1 = new ImageView(), meteor2 = new ImageView();
//		ImageView star = new ImageView(new Image(GameViewManager.GOLD_STAR, 20, 20, true, false));
//		ImageView life = new ImageView(new Image(GameViewManager.LIFE, 20, 20, true, false));
//
//		meteor1.setImage(new Image(GameViewManager.METEOR_BROWN_IMAGE, 80, 80, true, false));
//		meteor2.setImage(new Image(GameViewManager.METEOR_GREY, 80, 80, true, false));

		Label shipHelp 	 = new Label("This is your ship. Choose colour from the \nPlay menu. Control it with arrow keys.");
		Label meteorHelp = new Label("These are enemy meteors.\nAvoid them or shoot them.\nMore \"smart\" enemies coming soon ...");
		Label starHelp   = new Label("The stars give you points,\nIF you can grab them!");
		Label lifeHelp   = new Label("This is extra life.\nGrab it to gain an extra ship\nif you have less than three ships.");


		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				meteor1.setRotate(90+now/10000000l);
				meteor2.setRotate(180+now/10000000l);
//				ship.setRotate(-now/10000000l);
			}
		};
		timer.start();
		/* gridpane:
		 * ___0_|__1_|__2_|_3_
		 * 0|___|____|____|__
		 * 1|___|____|____|__
		 * 2|___|____|____|__
		 * 3|___|____|____|___
		 */

//		helpGrid.add(ship, 0, 0);
		helpGrid.add(shipHelp, 1, 0);
		helpGrid.add(meteor1, 0, 1);
		helpGrid.add(meteor2, 2, 1);
		helpGrid.add(meteorHelp, 1, 1);
//		helpGrid.add(life, 0, 2);
		helpGrid.add(lifeHelp, 1, 2);
//		helpGrid.add(star, 0, 3);
		helpGrid.add(starHelp, 1, 3);
		helpSubScene.getPane().getChildren().addAll(help, helpGrid);

	}

	
	private void createCreditsButton() {
		
		SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
		AddMenuButtons(creditsButton);
		
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(creditsSubScene);
				
			}
		});
	}

	private void createCreditsSubScene() {
		creditsSubScene = new SpaceRunnerSubScene();
		mainPane.getChildren().add(creditsSubScene);

		InfoLabel credits = new InfoLabel("  <<< Credits >>>");
		credits.setLayoutX(120);
		credits.setLayoutY(20);
		Label credit0 = new Label("Original programming by javacraving - youtube.");
		Label credit1 = new Label("Sounds and images from ");
		Label credit2 = new Label("Heavily modified by Kumar Dhakal");

		String[]link    = new String[6];
		link[0] = "https://www.youtube.com/playlist?list=PL4wcbt63yAbdtY-GOeuRjIePfUsukSJZ9";
		link[1] = "https://freesound.org/";
		link[2] = "http://soundbible.com/";
		link[3] = "https://www.freesoundslibrary.com/";
		link[4] = "https://kenney.nl/";
		link[5] = "https://dhakalkumar.github.io/";

		Hyperlink link0, link1, link2, link3, link4, link5;
		link0 = new Hyperlink(link[0]);
		link1 = new Hyperlink(link[1]);
		link2 = new Hyperlink(link[2]);
		link3 = new Hyperlink(link[3]);
		link4 = new Hyperlink(link[4]);
		link5 = new Hyperlink(link[5]);

		VBox creditsBox = new VBox(10, credit0,link0, credit1, link1, link2, link3, link4, credit2, link5);

		creditsBox.setLayoutX(50);
		creditsBox.setLayoutY(80);
		creditsSubScene.getPane().getChildren().addAll(credits, creditsBox);

		Application app = new Application() {@Override public void start(Stage primaryStage) throws Exception{}};
		HostServices services = app.getHostServices();

		link0.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				services.showDocument(link[0]);
			}
		});
		link1.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				services.showDocument(link[1]);
			}
		});
		link2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				services.showDocument(link[2]);
			}
		});
		link3.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				services.showDocument(link[3]);
			}
		});
		link4.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				services.showDocument(link[4]);
			}
		});
		link5.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				services.showDocument(link[5]);
			}
		});
	}
	
	private void createExitButton() {
		SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
		AddMenuButtons(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
				
			}
		});
		
	}
	
	private void createBackground() {

		Image backgroundImage = new Image("jungle.png", 1200, 750, false, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));

	}

	private void createLogo() {
		ImageView logo = new ImageView("PBOPBOPBOPBO.png");
		logo.setLayoutX(440);
		logo.setLayoutY(30);

		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());

			}
		});

		logo.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);

			}
		});

		mainPane.getChildren().add(logo);

	}



}
