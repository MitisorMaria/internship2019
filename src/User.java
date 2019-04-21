import java.util.*;

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

    public void orderByFee(ArrayList<Card> validCards) {
        boolean change;
        do {
            change = false;
            for (int i = 0; i < validCards.size() - 1; i++) {
                if (validCards.get(i).getFee() > validCards.get(i + 1).getFee()) {
                    Card c1 = validCards.get(i);
                    Card c2 = validCards.get(i + 1);
                    validCards.remove(c1);
                    validCards.remove(c2);
                    validCards.add(i, c2);
                    validCards.add(i + 1, c1);
                    change = true;
                }
            }
        } while (change);
        for (Card c : validCards){
            System.out.println(c.getFee());
        }

    }

    public void transferFromCardToCard (Card from, Card to, int sum){
        int beforeSum = from.getAvailableSum();
        from.withdraw(sum);
        if (from.getAvailableSum() == beforeSum){
            System.out.println("Could not withdraw " + sum + " lei from card " + from.getID());
            from.receive(sum);
            return;
        }
        to.receive(sum);
        System.out.println("From card "+ from.getID() + " pay " + sum );
    }


    public void getBestCombination(Date d){
        ArrayList<Card> validCards = this.getValidCards(d);
        orderByFee(validCards);
        Map<Card, Cost> resultMap =  getCardsCost(validCards);
        for (Card c : resultMap.keySet()){
            System.out.println("From card "+ c.getID() + " pay a fee of " + resultMap.get(c).getFee() + " lei and TVA of " + resultMap.get(c).getTVA());
        }

    }

    public Map<Card, Cost> getCardsCost(ArrayList<Card> cardsOrdered) {
        Map<Card, Cost> cardCostMap = new HashMap<>();
        Card start = cardsOrdered.get(0);
        for (Card c : cardsOrdered.subList(1, cardsOrdered.size())){
            if ((start.getAvailableSum()<10000) && (start.getAvailableSum() + c.getLimit() <= 10000)){
                transferFromCardToCard(c, start, c.getLimit());
                cardCostMap.put(c, new Cost(0.19f * c.getLimit(), c.getFee() * c.getLimit()));
            }
            else if ((start.getAvailableSum()<10000) && (start.getAvailableSum() + c.getLimit() > 10000))
            {
                transferFromCardToCard(c, start, 10000-start.getAvailableSum());
                cardCostMap.put(c, new Cost(0.19f * (10000-start.getAvailableSum()), c.getFee() * (10000-start.getAvailableSum())));
            }


            if (start.getAvailableSum() == 10000)
                break;
        }

        return cardCostMap;
    }

    public ATM getClosestATMAvailable(ArrayList<ATM> atms, Date d){
        int minTime = 120;
        ATM minATM = atms.get(0);
        String s = d.toString();
        int hour = Integer.parseInt(s.substring(11, 12));
        int minute = Integer.parseInt(s.substring(14, 15));
        float floatTime = (hour + minute/60)%24; //11,5
        for (ATM a : atms){
            if ((floatTime <= a.getOpeningTime()) && (floatTime + a.getDistanceMins() < a.getClosingTime())){
                if (a.getDistanceMins()<minTime) {
                    minTime = a.getDistanceMins();
                    minATM=a;
                }
            }
        }
        System.out.println("The closest ATM available is "+ minATM.getName());
        return minATM;
    }
}
