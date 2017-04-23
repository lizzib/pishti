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
    int turn = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{
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
        Card secondCard = player.hand.get(1);
        Card thirdCard = player.hand.get(2);
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
        pane.add(computerPane, 0,0);
        pane.add(deckPane, 0,1);
        pane.add(playerPane, 0,2);
        /*Stage|Scene For Card Game*/
        Scene scene = new Scene(pane, 1000, 900);
        ImagePattern pattern = new ImagePattern(new Image("file:src/Cards/table.jpg"));
        scene.setFill(pattern);
        primaryStage.setTitle("Pishti");
        primaryStage.setScene(scene);
        primaryStage.show();
                player.hand.get(0).Image.setOnMouseClicked(event -> {
                    shift(player, playingDeck, playerPane, deckPane, 0);
                    int index = computer.doTurn(playingDeck);
                    System.out.println(index);
                    shift(computer, playingDeck, computerPane, deckPane, index);
                });

                player.hand.get(1).Image.setOnMouseClicked(event -> {
                    shift(player, playingDeck, playerPane, deckPane, player.hand.indexOf(secondCard));
                    int index = computer.doTurn(playingDeck);
                    System.out.println(index);
                    shift(computer, playingDeck, computerPane, deckPane, index);
                });

                player.hand.get(2).Image.setOnMouseClicked(event -> {
                    shift(player, playingDeck, playerPane, deckPane, player.hand.indexOf(thirdCard));
                    int index = computer.doTurn(playingDeck);
                    System.out.println(index);
                    shift(computer, playingDeck, computerPane, deckPane, index);

                });

                player.hand.get(3).Image.setOnMouseClicked(event -> {
                    shift(player, playingDeck, playerPane, deckPane, player.hand.size() - 1);
                    int index = computer.doTurn(playingDeck);
                    System.out.println(index);
                    shift(computer, playingDeck, computerPane, deckPane, index);
                });
        }


    /*Card Images for Computer Hand*/
    public static void cardBackImages(GridPane pane, Player player){

        if(player.getClass() == Computer.class) {
            for (int i = 0; i < player.hand.size(); i++) {
                pane.add(player.hand.get(i).Back, i, 0);
            }
        }
        else{
            for (int i = 0; i < player.hand.size(); i++) {
                pane.add(player.hand.get(i).Image, i, 0);
            }
        }
    }

    public static void main(String[] args){
        Application.launch(args);
    }

    public void shift(Player player, Deck playingDeck, GridPane playerPane, GridPane deckPane, int index){
        if(player.getClass() == Computer.class)
            playerPane.getChildren().remove(player.hand.get(index).Back);
            if(player.hand.size() == 0) {
                player.redraw(playingDeck);
                cardBackImages(playerPane, player);
            }

        else
            playerPane.getChildren().remove(player.hand.get(index).Image);
        playingDeck.discardPile.add(0,player.hand.get(index));
        deckPane.add(playingDeck.discardPile.get(0).Image, 1, 0);
        player.hand.remove(index);
        if(player.hand.size() == 0) {
            player.redraw(playingDeck);
            cardBackImages(playerPane, player);
        }
    }



}
