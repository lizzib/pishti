import java.util.ArrayList;
//ELIZABETH BURNSIDE
//Lilith Wroth 4/22/2017

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
            for(int i = 0; i < 4; i++)
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

    public void capture(Deck playingDeck, Player player){

        boolean Captured = playingDeck.discardPile.get(0).value == playingDeck.discardPile.get(1).value;

        //runs anytime a player puts down a card to see if captured
        if (Captured) {
            //Checks for Pişti
            if (playingDeck.discardPile.size() == 2 && playingDeck.discardPile.get(0) == playingDeck.discardPile.get(1)) {
                player.score += 10;

                //Double Pişti
                if (playingDeck.discardPile.get(0).value == 11) {
                    player.score += 10;
                }
            }
            player.captured.addAll(playingDeck.discardPile);

            while (playingDeck.discardPile.size() != 0) {

                //10's, J's, Q's, K's, A's.
                if (playingDeck.discardPile.get(0).value > 2 || playingDeck.discardPile.get(0).value <= 10) {
                    score += 1;
                }

                //diamond 10
                if (playingDeck.discardPile.get(0).value == 10 && playingDeck.discardPile.get(0).suite == 2) {
                    score += 2;
                }

                //2 of clubs
                if (playingDeck.discardPile.get(0).value == 2 && playingDeck.discardPile.get(0).suite == 3) {
                    score += 2;
                }

                //removes card
                captured.add(playingDeck.discardPile.get(0));
                playingDeck.discardPile.remove(0);
            }

        }
    }

    public void score(){}

    //score

}
