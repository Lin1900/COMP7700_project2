import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageAccountUI {
    public JFrame view;

    public JButton btnSave = new JButton("Save User");

    public JTextField txtFullName = new JTextField(20);
    public JTextField txtPassword = new JTextField(20);

    UserModel user;

    public ManageAccountUI() {
        this.view = new JFrame();
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Manage Account Information");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Full Name "));
        line.add(txtFullName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Password "));
        line.add(txtPassword);
        view.getContentPane().add(line);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnSave);
        view.getContentPane().add(panelButtons);

        btnSave.addActionListener(new ManageAccountUI.SaveButtonListener());
    }

    public void run() {
        user = LoginUI.getUser();
        System.out.println(user);
        txtFullName.setText(user.mFullname);
        txtPassword.setText(user.mPassword);

        view.setVisible(true);
    }


    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String password = txtPassword.getText();
            if (password.length() == 0) {
                JOptionPane.showMessageDialog(null, "Password cannot be empty!");
                return;
            }
            user.mPassword = password;

            String fullName = txtFullName.getText();
            if(fullName.length() == 0) {
                JOptionPane.showMessageDialog(null, "Full name cannot be empty!");
                return;
            }
            user.mFullname = fullName;

            try {
                int res = StoreClient.getInstance().getDataAdapter().saveUser(user);
                //JOptionPane.showMessageDialog(null, "Account info is SAVED successfully!");

                if (res == IDataAdapter.USER_SAVE_FAILED)
                    JOptionPane.showMessageDialog(null, "Account info is NOT saved successfully!");
                else
                    JOptionPane.showMessageDialog(null, "Account info is SAVED successfully!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}