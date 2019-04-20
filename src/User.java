import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User {
    private ArrayList<Card> cards = new ArrayList<>();

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getValidCards(Date d){
        ArrayList<Card> validCards = new ArrayList<>();
        for (Card c : cards){
            if (c.getExpirationDate().after(d)){
                validCards.add(c);
            }
        }
        return validCards;
    }

    public void transferFromCardToCard (Card from, Card to, int sum){
        int beforeSum = from.getAvailableSum();
        from.withdraw(sum);
        if (from.getAvailableSum() == beforeSum){
            System.out.println("Could not withdraw " + sum + " lei from card " + from.getID());
            return;
        }
        to.receive(sum);
    }

    public static ArrayList<Card> cloneList(ArrayList<Card> CardList) {
        ArrayList<Card> clonedList = new ArrayList<Card>(CardList.size());
        for (Card Card : CardList) {
            clonedList.add(new Card(Card));
        }
        return clonedList;
    }



    public void getBestCombination(Date d){
        ArrayList<Card> validCards = cloneList(this.getValidCards(d));

    }

    public Map<Card, Cost> getCardsCost(ArrayList<Card> cardsOrdered) {
        Map<Card, Cost> cardCostMap = new HashMap<>();
        Card start = cardsOrdered.get(0);
        for (Card c : cardsOrdered.subList(1, cardsOrdered.size()-1)){
            if ((start.getAvailableSum()<10000) && (start.getAvailableSum() + c.getAvailableSum() <= 10000)){
                transferFromCardToCard(c, start, c.getAvailableSum());
                cardCostMap.put(c, new Cost(0.19f * c.getAvailableSum(), c.getFee() * c.getAvailableSum()));
            }
            else if ((start.getAvailableSum()<10000) && (start.getAvailableSum() + c.getAvailableSum() > 10000))
            {
                transferFromCardToCard(c, start, 10000-start.getAvailableSum());
                cardCostMap.put(c, new Cost(0.19f * (10000-start.getAvailableSum()), c.getFee() * (10000-start.getAvailableSum())));
            }


            if (start.getAvailableSum() == 10000)
                break;
        }

        return cardCostMap;
    }
}
