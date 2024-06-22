import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * A shopping manager application with a graphical user interface.
 */
public class WestminsterShoppingManager extends JFrame implements ShoppingManager {
    public static ArrayList<Product> productList = new ArrayList<>();
    public static ArrayList<Product> shoppingCartProducts = new ArrayList<>();


    int proQuantity;
    double proPrice = 0;
    private JLabel label1;
    private JButton shoppingCart;
    JTable table;
    DefaultTableModel model;
    JComboBox<String> comboBox;
    JButton addToCart;
    String selectedProductId;



    /**
     * Populates the table with product information.
     */
    private void populateTable() {
        model.setRowCount(0); // Clear any existing data
        for (Product product : productList) {
                Object[] rowData = {
                        product.getProductID(),
                        product.getProductName(),
                        product.getProductType(),
                        product.getPrice(),
                        product.displayInfo()
                       // Assuming your Product class has these getters
                };
                model.addRow(rowData);

        }
    }

    /**
     * Constructor for WestminsterShoppingManager.
     */
    public WestminsterShoppingManager() {

        // create the label
        setLayout(new FlowLayout());
        label1 = new JLabel("Select product category");
        add(label1);

        // create the dropdown menu
        String[] items = {"All", "Electronics", "Clothing"};
        comboBox = new JComboBox<>(items);
        add(comboBox);

        shoppingCart = new JButton("Shopping Cart");

        shoppingCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShoppingCart sCart = new ShoppingCart();
                sCart.setVisible(true);
                sCart.setSize(550, 400);
            }
        });
        add(shoppingCart);

        String[] columnNames = {"Product ID", "Name", "Category", "Price", "Info"};

        model = new DefaultTableModel(columnNames, 2);

        // Create a table with the model
        table = new JTable(model);
        table.setDefaultEditor(Object.class, (TableCellEditor) null);

        // Set some properties for the table (optional)
        table.setPreferredScrollableViewportSize(new Dimension(800, 200));
        table.setFillsViewportHeight(true);
        // Create a scroll pane to contain the table
        JScrollPane scrollPane = new JScrollPane(table);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        populateTable();

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });


        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        add(detailsPanel);

        // Add a label and text area for displaying details
        JLabel detailsLabel = new JLabel("Selected Product Details:");
        detailsPanel.add(detailsLabel);

        JTextArea detailsTextArea = new JTextArea(5, 30);
        detailsTextArea.setEditable(false);
        detailsPanel.add(detailsTextArea);

        addToCart = new JButton("Add To Cart");
        detailsPanel.add(addToCart);

        // Add a selection listener to the table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Get data from the selected row
                        String productId = table.getValueAt(selectedRow, 0).toString();
                        String productName = table.getValueAt(selectedRow, 1).toString();
                        String category = table.getValueAt(selectedRow, 2).toString();
                        String price = table.getValueAt(selectedRow, 3).toString();
                        String info = table.getValueAt(selectedRow, 4).toString();
                        selectedProductId = productId;
                        // Display the data in the text area
                        String detailsText = "Product ID:   " + productId + "\n"
                                + "Name:   " + productName + "\n"
                                + "Category:   " + category + "\n"
                                + "Price:   " + price + "\n"
                                + info;

                        detailsTextArea.setText(detailsText);
                    }
                }
            }
        });


        addToCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findSelectedItem();
            }
        });
    }

    /**
     * Finds and adds the selected item to the shopping cart.
     */
    public void findSelectedItem() {
        for (Product p : productList) {
            if (p.getProductID() == selectedProductId) {
                shoppingCartProducts.add(p);
            }
        }

    }

    /**
     * Updates the table based on the selected category.
     */
    private void updateTable() {
        model.setRowCount(0);

        // Get the selected category from the combo box
        String selectedCategory = (String) comboBox.getSelectedItem();

        // Update the table based on the selected category
        if (selectedCategory.equals("All")) {
            for (Product product : productList) {
                Object[] rowData = {product.getProductID(), product.getProductName(), product.getProductType(),
                        product.getPrice(), product.displayInfo()};
                model.addRow(rowData);
            }
        } else {
            for (Product product : productList) {
                if (product.getProductType().equals(selectedCategory)) {
                    Object[] rowData = {product.getProductID(), product.getProductName(), product.getProductType(),
                            product.getPrice(), product.displayInfo()};
                    model.addRow(rowData);
                }
            }
        }

    }

    Scanner sc = new Scanner (System.in);

    public void addNewProduct (Product newProduct){
        for (Product item : productList) {
            if (Objects.equals(newProduct.getProductID(), item.getProductID())) {
                System.out.println("ProductID already exists.");
                return;
            }
        }
        productList.add(newProduct);
        System.out.println("Product added successfully..");
    }

    public void deleteProducts(){
        System.out.print("Enter productID : ");
        String tempID = sc.next();
        for(Product item : productList){
            if (item.getProductID().equals(tempID)){
                System.out.println(item.getProductType());
                productList.remove(item);
                System.out.println("Product has been removed successfully."+"\n"+"Remaining products: "+ productList.size());
            return;
            }
        }
        System.out.println("ProductID does not exists.");
    }

    @Override
    public void loadMenu() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            //Checking the null existing.
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printProducts(){
        //sort objects in productList arraylist based on the Product ID's
        Collections.sort(productList, Comparator.comparing(Product::getProductID));
        //sort objects in productList arraylist based on the Product types
        Collections.sort(productList, Comparator.comparing(Product::getProductType));


        System.out.println("..Electronics Items..");
        for(Product item : productList) {
            if (productList.size() != 0) {
                if (item.getClass().equals(Electronics.class)) {
                    System.out.println(item.toString());
                }
            } else {
                System.out.println("No electronic items in the inventory.");
            }
        }
        System.out.println("..Clothing Items..");
        for(Product item : productList){
            if(productList.size()!=0){
                if(item.getClass().equals(Clothing.class)) {
                    System.out.println(item.toString());
                }
            }else{
                System.out.println("No clothing items in the inventory.");
            }
        }
    }

    File file = new File("file.txt");

    public void saveProducts(){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))){
            for (Product s : productList) {
                bufferedWriter.write(s.toString() + "\n");
            }
            // Close the BufferedWriter
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        int input = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print(prompt);
                input = scanner.nextInt();
                validInput = true; // Input is valid, exit the loop
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid numeric value.");
                scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
            }
        }

        return input;
    }


    boolean exit = true;

    public void runMenu (){
        while (exit){
            System.out.println("************** Main Menu **************");
            System.out.println("  Press 1 - Add a new product          ");
            System.out.println("  Press 2 - Delete a Product           ");
            System.out.println("  Press 3 - Print Available Products   ");
            System.out.println("  Press 4 - Save Product Detail        ");
            System.out.println("  Press 5 - Open the GUI               ");
            System.out.println("  Press 9 - Exit the Program           ");
            System.out.println("***************************************");

            System.out.print("Enter your choice: ");
            String choice1 = sc.next();



            switch (choice1) {
                case "1": {
                    int numberOfProducts = 50;
                    if(productList.size() < numberOfProducts) {
                        System.out.println("Press 1 - Add electronics");
                        System.out.println("Press 2 - Add clothes");
                        System.out.print("Enter your choice: ");
                        String choice2 = sc.next();

                        System.out.print("Enter product ID: ");
                        String proID = sc.next();


                        System.out.print("Enter product name: ");
                        String proName = sc.next();


                        proQuantity = getIntInput(sc,"Enter item quantity: ");
                        proPrice = getIntInput(sc,"Enter product price: ");



                        switch (choice2) {
                            case "1" -> {
                                System.out.print("Enter electronic's brand: ");
                                String eBrand = sc.next();
                                int eWarrantyPeriod = getIntInput(sc,"Enter electronic's warranty period: ");
                                Electronics e = new Electronics(proID, proName, proQuantity, proPrice, eBrand, eWarrantyPeriod);
                                this.addNewProduct(e);
                            }
                            case "2" -> {
                                System.out.print("Enter clothe's size: ");
                                String cSize = sc.next();
                                System.out.print("Enter clothe's colour: ");
                                String cColor = sc.next();
                                Clothing c = new Clothing(proID, proName, proQuantity, proPrice, cColor, cSize);
                                this.addNewProduct(c);
                            }
                            default ->
                                System.out.println("Invalid product type.");
                        }
                    }else{
                    System.out.println("Maximum numbers of products have been reached.");
                }
                runMenu();
                }
                break;
                case "2": {
                    deleteProducts();
                    break;
                }
                case "3": {
                    printProducts();
                    break;

                }
                case "4": {
                    saveProducts();
                    System.out.println("Products saved successfully..");
                    break;
                }
                case "5": {
                    Collections.sort(productList, Comparator.comparing(Product::getProductID));
                    WestminsterShoppingManager textFieldFrame = new WestminsterShoppingManager();
                    textFieldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    textFieldFrame.setVisible(true);
                    textFieldFrame.setSize(1000,500);
                    break;
                }

                case "9": {
                    System.out.println("..Good Bye..");

                    exit = false;
                    break;
                }
                default:
                    System.out.println("Invalid input. Enter valid option.");
            }

        }
    }


}
