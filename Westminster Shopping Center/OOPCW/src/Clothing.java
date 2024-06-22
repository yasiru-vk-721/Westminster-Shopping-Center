public class Clothing extends Product{
    private String color;
    private String size;

    public Clothing (String productID, String productName, int numberOfAvailableItems, double price, String color, String size){
        super(productID, productName, numberOfAvailableItems, price);
        this.size = size;
        this.color = color;
    }

    public String getColor (){
        return color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size){
        this.size = size;
    }

    @Override
    public String getProductType(){
        return "Clothing";
    }

    @Override
    public String toString() {
        return "Clothing = { " +
                " productID = " + getProductID()+
                " productName = "+ getProductName()+
                " numberOfAvailableItems =  "+ getNumberOfAvailableItems()+
                " price = "+ getPrice() +
                " color = "+ color +
                " size = "+size + " }";
    }

    @Override
    public String displayInfo(){
        return "Size: "+  size + " \n" + "Colour: " + color;
    }
    @Override
    public String displayInfo2(){
        return getProductID() + "  " + getProductName() + "  " + size + "  " + color;
    }
}
