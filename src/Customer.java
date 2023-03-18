public class Customer {
    private int OrderNumber;
    private String Name;
    private String Sweetness;
    private String Amount;
    private int Price;

    public Customer(int OrderNumber,String Name,String Sweetness,String Amount,int Price) {
        this.OrderNumber = OrderNumber;
        this.Name = Name;
        this.Sweetness = Sweetness;
        this.Amount = Amount;
        this.Price = Price;
    }


    /**
     * @return int return the OrderNumber
     */
    public int getOrderNumber() {
        return OrderNumber;
    }

    /**
     * @param OrderNumber the OrderNumber to set
     */
    public void setOrderNumber(int OrderNumber) {
        this.OrderNumber = OrderNumber;
    }

    /**
     * @return String return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return String return the Sweetness
     */
    public String getSweetness() {
        return Sweetness;
    }

    /**
     * @param Sweetness the Sweetness to set
     */
    public void setSweetness(String Sweetness) {
        this.Sweetness = Sweetness;
    }

    /**
     * @return String return the Amount
     */
    public String getAmount() {
        return Amount;
    }

    /**
     * @param Amount the Amount to set
     */
    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    /**
     * @return int return the Price
     */
    public int getPrice() {
        return Price;
    }

    /**
     * @param Price the Price to set
     */
    public void setPrice(int Price) {
        this.Price = Price;
    }

}