public class Main {
    public static void main(String[] args) {

        ShoppingManager s1 = new WestminsterShoppingManager();
        s1.loadMenu();
        System.out.println();
        s1.runMenu();


    }
}