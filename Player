import java.util.ArrayList;
//ELIZABETH BURNSIDE

public class Player {
    int score = 0;
    ArrayList<Card> hand = new ArrayList<>();
    ArrayList<Card> captured = new ArrayList<>();

    public Player(){};

    public void playCard(Card currentCard, Deck playingDeck){
        playingDeck.discardPile.add(0, currentCard);
        hand.remove(0);
    }

    public void redraw(Deck playingDeck){
        if(playingDeck.drawPile.size() > 10)
        {
            for(int i = 0; i < 5; i++)
                playingDeck.draw(this);
        }

    }

    public void collect(Deck playingDeck){
        for(int i = 0; i < playingDeck.discardPile.size(); i++)
        {
            captured.add(playingDeck.discardPile.get(i));
            playingDeck.discardPile.remove(i);
        }
    }

    public void score(){}

    //score

}
