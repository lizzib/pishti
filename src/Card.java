//Created By Lilith Wroth 4/13/2017

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
    public int value; //the number of the image and starting index in deck array before shuffling
    public int suite; // Spade = 0, Heart = 1, Diamond = 2, Club = 3
    public String Img;
    public ImageView Image;
    public ImageView Back;

    //Card Class
    public Card(int value) {
        this.value = value;

        this.Img = "file:src/Cards/" + value + ".png";
        this.Image = new ImageView(new Image(Img));

        this.suite = ((value - 1) / 13); // Spade = 0, Heart = 1, Diamond = 2, Club = 3
        this.value = value % 13; // ace = 1, jack = 11, queen = 12, king = 0.

        Back = new ImageView(new Image("file:src/Cards/b1fv.png"));
    }


}
