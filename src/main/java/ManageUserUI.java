import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageUserUI {
    public JFrame view;
    public UserModel u = null;
    public JButton btnLoad = new JButton("Load User");
    public JButton btnSave = new JButton("Save User");

    public JTextField txtUsername = new JTextField(20);
    public JTextField txtPassword = new JPasswordField(20);
    public JTextField txtFullname = new JTextField(20);
    public JTextField txtUsertype = new JTextField(20);
    public JTextField txtCustomerID = new JTextField(20);


    public ManageUserUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Update User Information");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnLoad);
        panelButtons.add(btnSave);
        view.getContentPane().add(panelButtons);

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("UserName "));
        line1.add(txtUsername);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Password"));
        line2.add(txtPassword);
        view.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("FullName"));
        line3.add(txtFullname);
        view.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("UserType"));
        line4.add(txtUsertype);
        view.getContentPane().add(line4);

        JPanel line5 = new JPanel(new FlowLayout());
        line4.add(new JLabel("CustomerID"));
        line4.add(txtCustomerID);
        view.getContentPane().add(line5);

        btnLoad.addActionListener(new ManageUserUI.LoadButtonListener());
        btnSave.addActionListener(new ManageUserUI.SaveButtonListener());
    }

    public ManageUserUI(UserModel u) {
        this.view = new JFrame();
        this.u = u;
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Update User Information");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        //panelButtons.add(btnLoad);
        panelButtons.add(btnSave);
        view.getContentPane().add(panelButtons);

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("UserName "));
        line1.add(txtUsername);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Password"));
        line2.add(txtPassword);
        view.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("FullName"));
        line3.add(txtFullname);
        view.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("UserType"));
        line4.add(txtUsertype);
        view.getContentPane().add(line4);

        JPanel line5 = new JPanel(new FlowLayout());
        line4.add(new JLabel("CustomerID"));
        line4.add(txtCustomerID);
        view.getContentPane().add(line5);

        btnLoad.addActionListener(new ManageUserUI.LoadButtonListener());
        btnSave.addActionListener(new ManageUserUI.SaveButtonListener());

        UserModel user = new UserModel();

        user = StoreClient.getInstance().getDataAdapter().loadUser(u.mUsername);

        txtUsername.setText(user.mUsername);
        txtUsername.setEnabled(false);
        txtPassword.setText(user.mPassword);
        txtFullname.setText(user.mFullname);
        txtUsertype.setText(Integer.toString(user.mUserType));
        txtUsertype.setEnabled(false);
        txtCustomerID.setText(Integer.toString(user.mCustomerID));
        txtCustomerID.setEnabled(false);

    }

    public void run() {
        view.setVisible(true);
    }

    class LoadButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            UserModel user = new UserModel();

            String username = txtUsername.getText();
            if (username.length() == 0) {
                JOptionPane.showMessageDialog(null, "Username cannot be null!");
                return;
            }
            user = StoreClient.getInstance().getDataAdapter().loadUser(user.mUsername);

            if(user.mUserType == 0){
                txtUsertype.setEnabled(false);
            }

            if (user == null) {
                JOptionPane.showMessageDialog(null, "User does NOT exist!");
            }
            else {
                txtPassword.setText(user.mPassword);
                txtFullname.setText(user.mFullname);
                txtUsertype.setText(Integer.toString(user.mUserType));
                txtCustomerID.setText(Integer.toString(user.mCustomerID));
            }
        }
    }

    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            UserModel user = new UserModel();
            String username = txtUsername.getText();
            if (username.length() == 0) {
                JOptionPane.showMessageDialog(null, "Username cannot be null!");
                return;
            }
            user.mUsername = username;

            String password = txtPassword.getText();
            if (password.length() == 0) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty!");
                return;
            }
            user.mPassword = password;

            String fullname = txtFullname.getText();
            if (fullname.length() == 0) {
                JOptionPane.showMessageDialog(null, "Fullname cannot be empty!");
                return;
            }
            user.mFullname = fullname;

            String usertype = txtUsertype.getText();
            try {
                user.mUserType = Integer.parseInt(usertype);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Usertype is invalid!");
                return;
            }

            String cid = txtCustomerID.getText();
            try {
                user.mCustomerID = Integer.parseInt(cid);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }


            int  res = StoreClient.getInstance().getDataAdapter().saveUser(user);

            if (res == IDataAdapter.USER_SAVE_FAILED)
                JOptionPane.showMessageDialog(null, "User is NOT saved successfully!");
            else
                JOptionPane.showMessageDialog(null, "User is SAVED successfully!");
        }
    }
}