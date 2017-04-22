import java.util.ArrayList;
//ELIZABETH BURNSIDE

public class Deck {
    ArrayList<Card> drawPile = new ArrayList<>();
    ArrayList<Card> discardPile = new ArrayList<>();

    public Deck(){
        for(int i = 1; i < 53; i++)
        {
            Card draw = new Card(i);
            drawPile.add(draw);
        }
        java.util.Collections.shuffle(drawPile);
        for(int i = 0; i < 4; i++) {
            this.discardPile.add(drawPile.get(i));
            drawPile.remove(i);
        }
    }

    public void draw(Player currentPlayer){
        Player lastPlayer = currentPlayer;
        if(drawPile.size() > 2)
        {
            lastPlayer.hand.add(drawPile.get(0));
            updateDrawPile(drawPile.get(0));
        }

        else{
            lastPlayer.hand.add(drawPile.get(0));
            lastPlayer.hand.add(drawPile.get(0));
            endGame();
        }

    }


    public void updateDrawPile(Card currentCard){drawPile.remove(currentCard);}


    public void discard(Player currentPlayer, Card currentCard){
        discardPile.add(0, currentCard);
        currentPlayer.hand.remove(currentCard);
    }

    public void endGame(){};

}
