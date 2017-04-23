import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;

//Kaleb Eads

public class GUI extends Application {
    public GridPane PlayerText;
    public GridPane CPUText;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Deck playingDeck = new Deck();
        GridPane computerPane = new GridPane();
        computerPane.setAlignment(Pos.CENTER);
        computerPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        computerPane.setHgap(5.5);
        computerPane.setVgap(5.5);
//        /*Computer's Hand*/
        Computer computer = new Computer();
        computer.redraw(playingDeck);
        cardBackImages(computerPane, computer);

        GridPane playerPane = new GridPane();
        playerPane.setAlignment(Pos.CENTER);
        playerPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        playerPane.setHgap(5.5);
        playerPane.setVgap(5.5);
//      /*Player's Hand*/
        Player player = new Player();
        player.redraw(playingDeck);
        cardBackImages(playerPane, player);
//      /*Draw and Discard Pile*/
        GridPane deckPane = new GridPane();
        deckPane.setAlignment(Pos.CENTER);
        deckPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        deckPane.setHgap(5.5);
        deckPane.setVgap(5.5);
        deckPane.add(playingDeck.drawPile.get(0).Back, 0, 0);
        deckPane.add(playingDeck.discardPile.get(0).Image, 1, 0);
//      /* Scene Creation*/
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);

        pane.add(computerPane, 0, 0);
        pane.add(deckPane, 0, 1);
        pane.add(playerPane, 0, 2);
        /*Stage|Scene For Card Game*/
        Scene scene = new Scene(pane, 1000, 900);
        ImagePattern pattern = new ImagePattern(new Image("file:src/Cards/table.jpg"));
        scene.setFill(pattern);
        primaryStage.setTitle("Pishti");
        primaryStage.setScene(scene);
        primaryStage.show();
        pane.setOnMouseClicked(event -> {
            updateButtons(player, computer, playingDeck, playerPane, computerPane, deckPane);
        });
    }


    /*Card Images for Computer Hand*/
    public static void cardBackImages(GridPane pane, Player player) {

        if (player.getClass() == Computer.class) {
            for (int i = 0; i < player.hand.size(); i++) {
                pane.add(player.hand.get(i).Image, i, 0);
            }
        } else {
            for (int i = 0; i < player.hand.size(); i++) {
                pane.add(player.hand.get(i).Image, i, 0);
            }
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void shift(Player player, Deck playingDeck, GridPane playerPane, GridPane deckPane, int index) {
        if (player.getClass() == Computer.class)
            playerPane.getChildren().remove(player.hand.get(index).Back);
        if (player.hand.size() == 0 && playingDeck.drawPile.size() >= 8) {
            player.redraw(playingDeck);
            cardBackImages(playerPane, player);
        }
        else if(playingDeck.drawPile.size() < 8 && player.hand.size() == 0){
            //END STATE
            System.out.print("\n\n\n\nEND STATE");
            EndState(playingDeck, player);
            deckPane.getChildren().remove(1);
            return;
        }
        else
            playerPane.getChildren().remove(player.hand.get(index).Image);

        playingDeck.discardPile.add(0, player.hand.get(index));
        player.hand.remove(index);
        if (player.hand.size() == 0) {
            player.redraw(playingDeck);
            cardBackImages(playerPane, player);
        }
        captureMe(playingDeck, player);
        if(playingDeck.discardPile.size() == 0 && playingDeck.drawPile.size() > 1) {
            playingDeck.discardPile.add(playingDeck.drawPile.get(0));
            playingDeck.drawPile.remove(0);
        }
        deckPane.add(playingDeck.discardPile.get(0).Image, 1, 0);
    }

    public void updateButtons(Player player, Computer computer, Deck playingDeck, GridPane playerPane, GridPane computerPane, GridPane deckPane) {
        if (player.hand.size() > 0)
            player.hand.get(0).Image.setOnMouseClicked(event -> {
                shift(player, playingDeck, playerPane, deckPane, 0);
                int index = computer.doTurn(playingDeck);
                shift(computer, playingDeck, computerPane, deckPane, index);

                DEBUG(player,computer);
                System.out.print("\nDEBUG: DECK NUMBER OF CARDS: " + playingDeck.discardPile.size());
                String[] suite = {"Spade", "Heart", "Diamond", "Club"};
                System.out.print("\nDEBUG: TOP CARD :\t" + suite[playingDeck.discardPile.get(0).suite] + " " +playingDeck.discardPile.get(0).value + ".");
            });

        if (player.hand.size() > 1)
            player.hand.get(1).Image.setOnMouseClicked(event -> {
                shift(player, playingDeck, playerPane, deckPane, player.hand.indexOf(player.hand.get(1)));
                int index = computer.doTurn(playingDeck);
                shift(computer, playingDeck, computerPane, deckPane, index);
                DEBUG(player,computer);
                System.out.print("\nDEBUG: DECK NUMBER OF CARDS: " + playingDeck.discardPile.size());
                String[] suite = {"Spade", "Heart", "Diamond", "Club"};
                System.out.print("\nDEBUG: TOP CARD :\t" + suite[playingDeck.discardPile.get(0).suite] + " " +playingDeck.discardPile.get(0).value + ".");
            });

        if (player.hand.size() > 2)
            player.hand.get(2).Image.setOnMouseClicked(event -> {
                shift(player, playingDeck, playerPane, deckPane, player.hand.indexOf(player.hand.get(2)));
                int index = computer.doTurn(playingDeck);
                shift(computer, playingDeck, computerPane, deckPane, index);
                DEBUG(player,computer);
                System.out.print("\nDEBUG: DECK NUMBER OF CARDS: " + playingDeck.discardPile.size());
                String[] suite = {"Spade", "Heart", "Diamond", "Club"};
                System.out.print("\nDEBUG: TOP CARD :\t" + suite[playingDeck.discardPile.get(0).suite] + " " +playingDeck.discardPile.get(0).value + ".");
            });

        if (player.hand.size() > 3)
            player.hand.get(3).Image.setOnMouseClicked(event -> {
                shift(player, playingDeck, playerPane, deckPane, player.hand.size() - 1);
                int index = computer.doTurn(playingDeck);
                shift(computer, playingDeck, computerPane, deckPane, index);
                DEBUG(player,computer);
                System.out.print("\nDEBUG: DECK NUMBER OF CARDS: " + playingDeck.discardPile.size());
                String[] suite = {"Spade", "Heart", "Diamond", "Club"};
                System.out.print("\nDEBUG: TOP CARD :\t" + suite[playingDeck.discardPile.get(0).suite] + " " +playingDeck.discardPile.get(0).value + ".");
            });
    }

    public void DEBUG(Player player, Computer computer) {
            System.out.print("\n\n");
            System.out.print("\nDEBUG: COMPUTER HAND: " + computer.hand.size());
            System.out.print("\nDEBUG: COMPUTER CAPTURED: " + computer.captured.size());
            System.out.print("\nDEBUG: COMPUTER SCORE: " + computer.score);
            System.out.print("\nDEBUG: COMPUTER CAPTURED CARDS:\t");
            for (int debuger = 0; debuger < computer.captured.size(); debuger++) {
                String[] suite = {"Spade", "Heart", "Diamond", "Club"};
                if (computer.captured.get(debuger).value != 0)
                    System.out.print(suite[computer.captured.get(debuger).suite] + " " + computer.captured.get(debuger).value + ", ");
                else
                    System.out.print(suite[computer.captured.get(debuger).suite] + " KING, ");
            }
            System.out.print("\nDEBUG: PLAYER HAND: " + player.hand.size());
            System.out.print("\nDEBUG: PLAYER CAPTURED: " + player.score);
            System.out.print("\nDEBUG: PLAYER SCORE: " + player.captured.size());
            System.out.print("\nDEBUG: PLAYER CAPTURED CARDS:\t");
            for (int debuger = 0; debuger < player.captured.size(); debuger++) {
                String[] suite = {"Spade", "Heart", "Diamond", "Club"};
                if (player.captured.get(debuger).value != 0)
                    System.out.print(suite[player.captured.get(debuger).suite] + " " + player.captured.get(debuger).value + ", ");
                else
                    System.out.print(suite[player.captured.get(debuger).suite] + " KING, ");
            }
        }

    public void captureMe(Deck playingDeck, Player player){
                //runs anytime a player puts down a card to see if captured
                if(playingDeck.discardPile.size() > 1) {
                    if (playingDeck.discardPile.get(0).value == playingDeck.discardPile.get(1).value || playingDeck.discardPile.get(0).value == 11) {
                        System.out.println("\n***GOTCHA!!!***\nDEBUG: " + playingDeck.discardPile.size() + "\nDEBUG: " + (playingDeck.discardPile.get(0) == playingDeck.discardPile.get(1)));
                        //Checks for Pişti
                        if (playingDeck.discardPile.size() == 2 && playingDeck.discardPile.get(0).value == playingDeck.discardPile.get(1).value) {
                            player.score += 10;
                            System.out.print("BAD GAME");

                            //Double Pişti
                            if (playingDeck.discardPile.get(0).value == 11) {
                                player.score += 10;
                                System.out.print("BAD GAMEx2");
                            }
                        }

                        while (playingDeck.discardPile.size() != 0) {

                            //10's, J's, Q's, K's, A's.
                            if (playingDeck.discardPile.get(0).value > 2 || playingDeck.discardPile.get(0).value <= 10) {
                                player.score += 1;
                            }

                            //diamond 10
                            if (playingDeck.discardPile.get(0).value == 10 && playingDeck.discardPile.get(0).suite == 2) {
                                player.score += 2;
                            }

                            //2 of clubs
                            if (playingDeck.discardPile.get(0).value == 2 && playingDeck.discardPile.get(0).suite == 3) {
                                player.score += 2;
                            }

                            //removes cards from discard and adds them to the captured cards
                            player.captured.add(0, playingDeck.discardPile.get(0));
                            playingDeck.discardPile.remove(0);
                        }
                    }
                }
                //Do End Turn
            }

    public void EndState(Deck playingDeck, Player player){
        // calculates score at end
        int size = player.captured.size();

        for(int i = 0; i < playingDeck.drawPile.size();i++)
            player.captured.add(playingDeck.drawPile.get(i));

        for(int i = 0; i < playingDeck.drawPile.size();i++)
            player.captured.add(playingDeck.discardPile.get(i));

        for(int i = 0; i < playingDeck.drawPile.size();i++)
            player.captured.add(player.hand.get(i));

                while (size != 0) {

                    //10's, J's, Q's, K's, A's.
                    if (player.captured.get(size).value > 2 || player.captured.get(size).value <= 10) {
                        player.score += 1;
                    }

                    //diamond 10
                    if (player.captured.get(size).value == 10 && player.captured.get(size).suite == 2) {
                        player.score += 2;
                    }

                    //2 of clubs
                    if (player.captured.get(size).value == 2 && player.captured.get(size).suite == 3) {
                        player.score += 2;
                    }

                    size--;
                }
            }
        }
        //Do End Turn
