//Lilith Wroth 4/22/2017

public class Computer extends Player {
    //check if capture possible

    public void doTurn(Deck playingDeck) {
        for (int x = 0; x < hand.size(); x++)//Analizes what is in hand
        {
            if (playingDeck.discardPile.get(0).value == hand.get(x).value && hand.get(x).value != 11) {
                playingDeck.discardPile.add(hand.get(x));
                hand.remove(x);
                return;
            }

            else if (x == hand.size() - 1) {
                for (int j = 0; j < hand.size(); j++) {
                    if (hand.get(j).value != 11) {
                        playingDeck.discardPile.add(hand.get(j));
                        hand.remove(j);
                        return;
                    }
                    else if(j == hand.size() - 1 ){
                        playingDeck.discardPile.add(hand.get(0));
                        hand.remove(0);
                        return;
                    }

                }
            }


        }
    }
    //play jack last
}
