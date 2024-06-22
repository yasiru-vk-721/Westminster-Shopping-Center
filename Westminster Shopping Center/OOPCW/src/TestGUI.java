import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TestGUI extends WestminsterShoppingManager {

    static String[] s1 = { "All", "Electronics", "Clothings" };
    static String[] s2 = { "ProductID", "Name", "Category", "Price", "Info" };
    String[][] data;

    private void arr() {
        data = new String[productList.size()][5];

        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            data[i][0] = product.getProductID();
            data[i][1] = product.getProductName();
            data[i][2] = product.getClass().getSimpleName(); // Category (Electronics or Clothing)
            data[i][3] = String.valueOf(product.getPrice()); // Price
            data[i][4] = product.toString(); // Info (using the toString method, you may need to modify this)
        }
    }

    private void updateTable() {
        arr(); // Reinitialize the data array
        TableModel model = new DefaultTableModel(data, s2);
        productTable.setModel(model);
    }


    // create checkbox
    static JComboBox<String> c1 = new JComboBox<>(s1);

    // create labels
    static JLabel l = new JLabel("Select Product Category ");

    static JButton shoppingCartButton = new JButton("Shopping Cart");

    static JTable productTable = new JTable();

    JScrollPane scrollPane = new JScrollPane(productTable);

    public void runGUI() {
        JFrame frame = new JFrame("Westminster Shopping Centre");
        frame.setSize(800, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use a BorderLayout for the main frame
        frame.setLayout(new BorderLayout());

        // Panel for the top section
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(l);
        topPanel.add(c1);

        // Set the preferred size for the button (adjust as needed)
        shoppingCartButton.setPreferredSize(new Dimension(135, 40));
        topPanel.add(shoppingCartButton);

        // Add the topPanel to the NORTH position of BorderLayout
        frame.add(topPanel, BorderLayout.NORTH);

        // Call arr() before setting the table model
        ArrayList<Product> products = productList;
        String[][] data = new String[products.size()][5];
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            data[i][0] = product.getProductID();
            data[i][1] = product.getProductName();
            data[i][2] = product.getProductType();
            data[i][3] = String.valueOf(product.getPrice());
            // Add additional attributes as needed, e.g., for Electronics and Clothing
        }

        // Create the table model
        TableModel model = new DefaultTableModel(data, s2);
        productTable.setModel(model);
        // Create the table model after initializing the data array



        // Add the productTable to the CENTER position of BorderLayout
        frame.add(scrollPane, BorderLayout.CENTER);

        c1.setPreferredSize(new Dimension(250, 30));

        // ActionListener for the Shopping Cart button
        shoppingCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openShoppingCartWindow();
            }
        });
    }

    private void openShoppingCartWindow() {
        JFrame shoppingCartFrame = new JFrame("Shopping Cart");
        shoppingCartFrame.setSize(400, 300);

        // You can add components and logic for the shopping cart here

        shoppingCartFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TestGUI test = new TestGUI();
            test.runGUI();
        });
    }
}