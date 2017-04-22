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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

//Kaleb Eads

public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane pane2 = new Pane();

        /*Computer's Hand*/
        VBox vBox1 = new VBox();
        HBox hBox1 = new HBox();
        Label lbl1 = new Label("Computer");
        lbl1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        lbl1.setPadding(new Insets(10,0,10,0));
        cardBackImages(hBox1);
        vBox1.setAlignment(Pos.CENTER);
        vBox1.setPadding(new Insets(25,500,0,360));
        vBox1.getChildren().addAll(lbl1,hBox1);
        pane2.getChildren().add(vBox1);

        /*User's Hand*/
        VBox vBox2 = new VBox();
        HBox hBox2 = new HBox();
        Label lbl2 = new Label("User");
        lbl2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        lbl2.setPadding(new Insets(10,0,10,0));
        cardBackImages(hBox2);// need to adjust image to show current cards
        vBox2.setAlignment(Pos.BOTTOM_CENTER);
        vBox2.setPadding(new Insets(700,500,0,360));
        vBox2.getChildren().addAll(lbl2,hBox2);
        pane2.getChildren().add(vBox2);

        //User's Choices

        Button btnUserChoice1 = new Button();
        //btnUserChoice1.setGraphic();
        btnUserChoice1.setOnAction(e -> {
            System.exit(0);
        });
        Button btUserChoice1 = new Button();
        Button btUserChoice2 = new Button();
        Button btUserChoice3 = new Button();
        Button btUserChoice4 = new Button();


        /* Deck Of Cards*/
        VBox vBox3 = new VBox();
        HBox hBox3 = new HBox();
        Label lbl3 = new Label("Deck");
        lbl3.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        lbl3.setPadding(new Insets(10,0,10,0));
        Image cardImages = new Image("image/card/b1fv.png");
        hBox3.getChildren().add(new ImageView(cardImages));
        vBox3.setAlignment(Pos.CENTER);
        vBox3.getChildren().addAll(lbl3,hBox3);
        vBox3.setPadding(new Insets(350,0,0,10));
        pane2.getChildren().add(vBox3);

        //Cards in Center


        //Winner Display

        /*Game Options*/
        //End Game Button
        HBox hBox4 = new HBox();
        Button btnEndGame = new Button("End Game");
        btnEndGame.setOnAction(e -> {
            System.exit(0);
        });
        hBox4.getChildren().addAll(btnEndGame);
        hBox4.setLayoutX(900);
        hBox4.setLayoutY(10);


        //Deal Button
        HBox hBox5 = new HBox();
        Button btnDeal= new Button("Deal Cards");
        btnDeal.setOnAction(e -> {
            ;
        });// Needs method to deal hand

        hBox5.getChildren().addAll(btnDeal);
        hBox5.setLayoutX(900);
        hBox5.setLayoutY(50);
        pane2.getChildren().addAll(hBox4,hBox5);



        /*Scores*/
        HBox hBox6 = new HBox();
        Label lblScoresTitle =  new Label("Scores");
        lbl3.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        Text txtScores =  new Text();//Add Method for scores
        hBox6.getChildren().addAll(lblScoresTitle);
        hBox6.setLayoutX(850);
        hBox6.setLayoutY(700);
        pane2.getChildren().add(hBox6);








        /*Stage|Scene For Card Game*/
        BorderPane pane = new BorderPane();
        pane.getChildren().addAll(pane2);
        Scene scene = new Scene(pane, 1000, 900);
        primaryStage.setTitle("Pishti");
        primaryStage.setScene(scene);
        primaryStage.show();





    }

    /*Card Images for Computer Hand*/
    public static void cardBackImages(HBox hBox){
        int count = 0;
        while(count < 4){
            Image cardImages = new Image("image/card/b1fv.png");
            hBox.getChildren().add(new ImageView(cardImages));
            count++;
        }
    }
public static void main(String[] args){
        Application.launch(args);
}
}
