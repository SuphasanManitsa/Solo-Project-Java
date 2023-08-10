public class ok {
    private String type;
    private String model;
    private int price;

    public ok() {
    }

    public ok(String type, String model, int pice) {
        this.type = type;
        this.model = model;
        this.price = pice;
    }


    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
