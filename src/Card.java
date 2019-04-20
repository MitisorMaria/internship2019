import java.util.Date;

public class Card {

    private int ID;
    private static int limit = 2000;
    private float fee;
    private int availableSum;
    private Date expirationDate;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Card(Card c){
        this.ID = c.getID();
        this.expirationDate = c.getExpirationDate();
        this.fee = c.getFee();
        this.availableSum = c.getAvailableSum();
    }

    public Card (int ID, int availableSum, Date exp, float fee){
        this.availableSum = availableSum;
        this.ID = ID;
        this.fee = fee;
        this.expirationDate = exp;
    }



    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }


    public int getAvailableSum() {
        return availableSum;
    }

    public void setAvailableSum(int availableSum) {
        this.availableSum = availableSum;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getLimit() {
        return limit;
    }


    public int withdraw (int sum){
        if ((sum + fee * sum)<=limit)
        {
            availableSum -= (sum + fee*sum);
            return sum;
        }
        return 0;
    }

    public void receive (int sum) {
        availableSum += sum;
    }

}
