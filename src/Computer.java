//Lilith Wroth 4/22/2017

public class Computer extends Player {
public Card cpuCard;

    //check if capture possible
    //Advanced Ai Algorithm
    public int doTurn(Deck playingDeck) {
        System.out.print("Yes i have done my turn myaster");
        for (int x = 0; x < hand.size(); x++)//Analizes what is in hand
        {
            //Tries to match cards ignoring jacks to get the most out of them
            if (playingDeck.discardPile.get(0).value == hand.get(x).value && hand.get(x).value != 11) {
                playingDeck.discardPile.add(hand.get(x));
                capture(playingDeck, this);
                return x;
            }

            //if no matching card
            else if (x == hand.size() - 1) {

                //goes through looking for the best card to discard
                for (int j = 0; j < hand.size(); j++) {

                    //ignores jacks
                    if (hand.get(j).value != 11) {
                        playingDeck.discardPile.add(hand.get(j));
                        capture(playingDeck, this);
                        return j;
                    }

                    //if nothing else left plays a jack
                    else if (j == hand.size() - 1) {
                        playingDeck.discardPile.add(hand.get(0));
                        capture(playingDeck, this);
                        return 0;
                    }

                }
            }
        }
        hand.remove(0);
        return 0;
    }
}
