import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main {
    public static void main (String args[]){

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        ArrayList<Card> cards = new ArrayList<Card>();

        try {
            Card premium = new Card(6, 2000, formatter.parse("20-Aug-2019"), 0.0015f);
            Card silver = new Card(1, 4000, formatter.parse("23-May-2020"), 0.002f);
            Card gold = new Card(2, 2000, formatter.parse("15-Aug-2018"), 0.001f);
            Card platinum = new Card(3, 3000, formatter.parse("20-Mar-2019"), 0.003f);
            Card iridium = new Card(4, 5000, formatter.parse("23-Jun-2020"), 0.002f);
            Card bronze = new Card(5, 2500, formatter.parse("15-Jul-2019"), 0.005f);
            cards.add(premium);
            cards.add(silver);
            cards.add(gold);
            cards.add(platinum);
            cards.add(iridium);
            cards.add(bronze);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        User user = new User();
        user.setCards(cards);

    }
}
