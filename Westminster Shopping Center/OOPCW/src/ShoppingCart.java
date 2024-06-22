import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Represents a shopping cart application with a graphical user interface.
 */
public class ShoppingCart extends JFrame {
    private ArrayList<Product> checkList; // Placeholder, make sure to initialize and use appropriately
    DefaultTableModel model1;
    JTable sCart;
    Double totalPrice;
    JLabel totalPriceLabel;
    JLabel discountPriceLabel;
    JLabel finalPriceLabel;

    /**
     * Constructs a new instance of the ShoppingCart class.
     */
    public ShoppingCart() {
        setLayout(new BorderLayout());

        // Create a panel for the table
        JPanel tablePanel = new JPanel(new BorderLayout());
        sCart = new JTable();
        String[] columnNames1 = {"Product", "Quantity", "Price"};
        model1 = new DefaultTableModel(columnNames1, 2);
        sCart = new JTable(model1);
        sCart.setDefaultEditor(Object.class, null);
        sCart.setPreferredScrollableViewportSize(new Dimension(500, 100));
        sCart.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(sCart);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(tablePanel, BorderLayout.CENTER);

        // Create a panel for labels and prices
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Align to the left initially
        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));

        totalPriceLabel = new JLabel("Total price: ");
        pricePanel.add(createRightAlignedLabel(totalPriceLabel));

        discountPriceLabel = new JLabel("Discounted price: ");
        pricePanel.add(createRightAlignedLabel(discountPriceLabel));

        finalPriceLabel = new JLabel("Final price: ");
        pricePanel.add(createRightAlignedLabel(finalPriceLabel));

        getContentPane().add(pricePanel, BorderLayout.SOUTH);

        populateTable2();
        updateTotalPriceLabel();
        discountPrice();
    }

    /**
     * Creates a JLabel with right alignment.
     *
     * @param label The JLabel to be aligned to the right.
     * @return The right-aligned JLabel.
     */
    private JLabel createRightAlignedLabel(JLabel label) {
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);
        return label;
    }

    /**
     * Populates the table with product information.
     */
    private void populateTable2() {
        model1.setRowCount(0);
        for (Product product : WestminsterShoppingManager.shoppingCartProducts) {
            Object[] rowData = {
                    product.displayInfo2(),
                    1,
                    product.getPrice()
            };
            model1.addRow(rowData);
        }
    }

    /**
     * Updates the total price label with the calculated total price.
     */
    private void updateTotalPriceLabel() {
        totalPrice = calculateTotalPrice();
        totalPriceLabel.setText("Total price     :         " + totalPrice+ "  ");
    }

    /**
     * Calculates the total price based on the items in the shopping cart.
     *
     * @return The total price.
     */
    private double calculateTotalPrice() {
        totalPrice = 0.0;
        int rowCount = model1.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            double price = (double) model1.getValueAt(i, 2);
            totalPrice += price;
        }

        return totalPrice;
    }

    /**
     * Applies discounts to the total price and updates the discount and final price labels.
     */
    public void discountPrice() {
        int eItems = 0;
        int cItems = 0;
        double discount = 0;
        for (Product p1 : WestminsterShoppingManager.shoppingCartProducts) {
            if (p1.getProductType().equals("Electronics")) {
                eItems++;
            } else if (p1.getProductType().equals("Clothing")) {
                cItems++;
            }
        }

        double discountPrice1 = totalPrice;

        if (eItems >= 3 || cItems >= 3) {
            discount = totalPrice * 0.20;
            discountPrice1 = totalPrice - discount;
        }

        discountPriceLabel.setText("Discounted price    :         " + (discount)+ "  ");
        finalPriceLabel.setText("Final Price    :         " + (discountPrice1)+ "  ");
    }

    /**
     * The main method to start the shopping cart application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setSize(600, 400);
            shoppingCart.setTitle("Shopping Cart");
            shoppingCart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            shoppingCart.setVisible(true);
        });
    }
}
