public class Electronics extends Product {
    private String brand;
    private double warrantyPeriod;

    public Electronics (String productID, String productName, int numberOfAvailableItems, double price, String brand, double warrantyPeriod){
        super(productID, productName, numberOfAvailableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getBrand (){
        return brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public double getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(double warrantyPeriod){
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String getProductType(){
        return "Electronics";
    }

    @Override
    public String toString() {
        return "Electronics = { " +
                " productID = " + getProductID()+
                " productName = "+ getProductName()+
                " numberOfAvailableItems =  "+ getNumberOfAvailableItems()+
                " price = "+ getPrice() +
                " brand = "+ brand +
                " warrantyPeriod = "+warrantyPeriod + " }";
    }

    @Override
    public String displayInfo(){
        return "Brand: "+ brand + " \n" + "Warranty Period: " + warrantyPeriod;
    }
    @Override
    public String displayInfo2(){
        return getProductID() + "  " + getProductName() + "  " + brand + "  " + warrantyPeriod;
    }
}
