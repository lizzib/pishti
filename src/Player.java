import java.util.ArrayList;
//ELIZABETH BURNSIDE
//Lilith Wroth 4/22/2017

public class Player {
    int score = 0;
    ArrayList<Card> hand = new ArrayList<>();
    ArrayList<Card> captured = new ArrayList<>();

    public Player(){};

    public void playCard(Card currentCard, Deck playingDeck, Player you){
        playingDeck.discardPile.add(0, currentCard);
        hand.remove(0);
    }

    public void redraw(Deck playingDeck){
            for(int i = 0; i < 4; i++)
                playingDeck.draw(this);
        }

    }
