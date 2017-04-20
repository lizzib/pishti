//Created By Lilith Wroth 4/13/2017

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
    public int value; //the number of the image and starting index in deck array before shuffling
    public String color; // red or black
    public String Img;
    public ImageView Image;
    public ImageView Back;

    //Card Class
    public Card( int value) {
        this.value = value;

        Img = "file:src/image/card/" + value + ".png";
        Image = new ImageView(new Image(Img));

        if(value > 13 && value < 40)
            this.color = "red";
        else
            this.color = "black";

        this.value = value % 10; // ace = 1, jack = 11, queen = 12, king = 13.
        Back = new ImageView(new Image("file:src/image/card/b1fv.png"));
    }

    // Getters & Setters

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public ImageView getImgage() {
        return Imgage;
    }

    public void setImgage(ImageView imgage) {
        Imgage = imgage;
    }

    public ImageView getBack() {
        return Back;
    }

    public void setBack(ImageView back) {
        Back = back;
    }
}
