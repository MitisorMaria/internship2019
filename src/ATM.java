public class ATM {

    public int getDistanceMins() {
        return distanceMins;
    }

    public void setDistanceMins(int distanceMins) {
        this.distanceMins = distanceMins;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(int openingTime) {
        this.openingTime = openingTime;
    }

    public int getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(int closingTime) {
        this.closingTime = closingTime;
    }

    private int distanceMins;
    private int sum;
    private String name;
    private int openingTime, closingTime;

    public ATM(int distanceMins, int sum, String name, int openingTime, int closingTime) {
        this.distanceMins = distanceMins;
        this.sum = sum;
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

}
