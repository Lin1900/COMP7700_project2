import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductSearchUI {

    public JFrame view;
    public JTable productTable;
    public UserModel user = null;
    public JLabel lblSearch = new JLabel("SEARCH:\t");
    public JTextField txtSearch = new JTextField(20);
    public JButton btnSearch = new JButton("Search");


    public ProductSearchUI(UserModel user) {
        this.user = user;

        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Search Product");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(lblSearch);
        line1.add(txtSearch);
        line1.add(btnSearch);
        view.getContentPane().add(line1);

        JLabel title = new JLabel("Search results for " + user.mFullname);

        title.setFont (title.getFont().deriveFont (24.0f));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        view.getContentPane().add(title);

        ProductListModel list = StoreClient.getInstance().getDataAdapter().searchProduct("Apple", 0, 10000);
        DefaultTableModel tableData = new DefaultTableModel();

        tableData.addColumn("ProductID");
        tableData.addColumn("Product Name");
        tableData.addColumn("Price");
        tableData.addColumn("Quantity");

        for (ProductModel p : list.products) {
            Object[] row = new Object[tableData.getColumnCount()];

            row[0] = p.mProductID;
            row[1] = p.mName;
            row[2] = p.mPrice;
            row[3] = p.mQuantity;
            tableData.addRow(row);
        }

//        purchases = new JList(data);

        productTable = new JTable(tableData);

        JScrollPane scrollableList = new JScrollPane(productTable);

        view.getContentPane().add(scrollableList);


    }
}

