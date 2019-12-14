import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserUI {
    public JFrame view;

    public JButton btnSave = new JButton("Save User");

    public JTextField txtName = new JTextField(20);
    public JTextField txtUserName = new JTextField(20);
    public JPasswordField txtPassword = new JPasswordField(20);

    String s1[] = { "Customer", "Cashier", "Manager", "Admin" };
    public JComboBox txtUserType = new JComboBox(s1);

    public AddUserUI() {
        this.view = new JFrame();
        txtUserType.setPrototypeDisplayValue("sdlfkhsdlkfdefault text heresdlk"); //sets the width of the combo

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Manage Customer Information");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("Full Name "));
        line1.add(txtName);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Username "));
        line2.add(txtUserName);
        view.getContentPane().add(line2);

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("Password"));
        line.add(txtPassword);
        view.getContentPane().add(line);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("User Type "));
        line3.add(txtUserType);
        view.getContentPane().add(line3);


        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnSave);
        view.getContentPane().add(panelButtons);

        btnSave.addActionListener(new AddUserUI.SaveButtonListener());

    }

    public void run() {
        view.setVisible(true);
    }

    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            UserModel user = new UserModel();

            String name = txtName.getText();
            if (name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Full name cannot be empty!");
                return;
            }
            user.mFullname = name;

            String userName = txtUserName.getText();
            if (userName.length() == 0) {
                JOptionPane.showMessageDialog(null, "Username cannot be empty!");
                return;
            }
            user.mUsername = userName;

            String password = txtPassword.getText();
            if (password.length() == 0) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty!");
                return;
            }
            user.mPassword = password;

            int userType = txtUserType.getSelectedIndex();
            if (txtUserType.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "User type cannot be empty!");
                return;
            }
            user.mUserType = userType;

            try {
                int res = StoreClient.getInstance().getDataAdapter().saveUser(user);
                //JOptionPane.showMessageDialog(null, "User is SAVED successfully!");

                if (res == IDataAdapter.USER_SAVE_FAILED)
                    JOptionPane.showMessageDialog(null, "User is NOT saved successfully!");
                else
                    JOptionPane.showMessageDialog(null, "User is SAVED successfully!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}