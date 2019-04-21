import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main (String args[]){

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        ArrayList<Card> cards = new ArrayList<Card>();

        try {
            Card premium = new Card(6, 2000, formatter.parse("20-Aug-2019 12:00"), 0.0015f);
            Card silver = new Card(1, 4000, formatter.parse("23-May-2020 12:00"), 0.002f);
            Card gold = new Card(2, 2000, formatter.parse("15-Aug-2018 12:00"), 0.001f);
            Card platinum = new Card(3, 3000, formatter.parse("20-Mar-2019 12:00"), 0.003f);
            Card iridium = new Card(4, 5000, formatter.parse("23-Jun-2020 12:00"), 0.002f);
            Card bronze = new Card(5, 2500, formatter.parse("15-Jul-2019 12:00"), 0.005f);
            cards.add(premium);
            cards.add(silver);
            cards.add(gold);
            cards.add(platinum);
            cards.add(iridium);
            cards.add(bronze);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ATM a1 = new ATM(5, 5000, "ATM 1", 12, 18);
        ATM a2 = new ATM(60, 5000, "ATM 2", 10, 17);
        ATM a3 = new ATM(30, 5000, "ATM 3", 22, 13);
        ATM a4 = new ATM(45, 5000, "ATM 4", 17, 1);
        ArrayList<ATM> atms = new ArrayList<>();
        atms.add(a1); atms.add(a2); atms.add(a3); atms.add(a4);

        User user = new User();
        user.setCards(cards);
        try {
            Date d = formatter.parse("19-Mar-2019 11:30");
            user.getBestCombination(d);
            user.getClosestATMAvailable(atms, d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
