
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class LoginFrame extends JFrame {
    private JTextField nameField;
    private JPasswordField passField;
    private JButton loginBtn, registerBtn;

    private static HashMap<String, String> users = new HashMap<>();
    private static ArrayList<Trip> tripList = new ArrayList<>();

    public LoginFrame() {
        setTitle("Trip Booking Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));
        setLocationRelativeTo(null);

        nameField = new JTextField();
        passField = new JPasswordField();
        loginBtn = new JButton("Login");
        registerBtn = new JButton("Register");

        add(new JLabel("Username:"));
        add(nameField);
        add(new JLabel("Password:"));
        add(passField);
        add(loginBtn);
        add(registerBtn);

        loginBtn.addActionListener(e -> login());
        registerBtn.addActionListener(e -> register());

        setVisible(true);
    }

    private void login() {
        String name = nameField.getText().trim();
        String pass = new String(passField.getPassword());

        if (name.equals("admin") && pass.equals("admin123")) {
            JOptionPane.showMessageDialog(this, "Admin login successful!");
            new AdminDashboard(tripList, this);
            setVisible(false);
        } else if (users.containsKey(name) && users.get(name).equals(pass)) {
            JOptionPane.showMessageDialog(this, "User login successful!");
            new UserDashboard(new User(name, pass), tripList, this);
            setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials.");
        }
    }

    private void register() {
        String name = nameField.getText().trim();
        String pass = new String(passField.getPassword());

        if (users.containsKey(name)) {
            JOptionPane.showMessageDialog(this, "User already exists!");
        } else {
            users.put(name, pass);
            JOptionPane.showMessageDialog(this, "User registered successfully!");
        }
    }
}
