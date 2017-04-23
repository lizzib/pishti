//Lilith Wroth 4/22/2017

public class Computer extends Player {
    public Card cpuCard;

    //check if capture possible
    //Advanced Ai Algorithm
    public int doTurn(Deck playingDeck) {
        for (int x = 0; x < hand.size(); x++)//Analizes what is in hand
        {
            if (playingDeck.discardPile.get(0).value == hand.get(x).value && playingDeck.discardPile.size() == 2) {
                return x;
            }

            //Tries to match cards ignoring jacks to get the most out of them
            if (playingDeck.discardPile.get(0).value == hand.get(x).value && hand.get(x).value != 11) {
                return x;
            }

            //if no matching card
            else if (x == hand.size() - 1) {

                //goes through looking for the best card to discard
                for (int j = 0; j < hand.size(); j++) {

                    //ignores jacks
                    if (hand.get(j).value != 11) {
                        return j;
                    }

                    //if nothing else left plays a jack
                    else if (j == hand.size() - 1) {
                        return 0;
                    }

                }
            }
        }
        return 0;
    }
}
