public class Cost {
    private float TVA;
    private float fee;


    public Cost(float TVA, float fee) {
        this.TVA = TVA;
        this.fee = fee;
    }

    public float getTVA() {
        return TVA;
    }

    public void setTVA(float TVA) {
        this.TVA = TVA;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }
}
