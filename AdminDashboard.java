import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminDashboard extends JFrame {
    private JTextField codeField, destField, dateField, priceField, seatField;
    private JButton addBtn, backBtn;
    private ArrayList<Trip> tripList;
    private JFrame loginFrame;

    public AdminDashboard(ArrayList<Trip> tripList, JFrame loginFrame) {
        this.tripList = tripList;
        this.loginFrame = loginFrame;

        setTitle("Admin Dashboard");
        setSize(400, 300);
        setLayout(new GridLayout(7, 2));
        setLocationRelativeTo(null);

        codeField = new JTextField();
        destField = new JTextField();
        dateField = new JTextField();
        priceField = new JTextField();
        seatField = new JTextField();

        addBtn = new JButton("Add Trip");
        backBtn = new JButton("Logout");

        add(new JLabel("Trip Code:"));
        add(codeField);
        add(new JLabel("Destination:"));
        add(destField);
        add(new JLabel("Date:"));
        add(dateField);
        add(new JLabel("Price:"));
        add(priceField);
        add(new JLabel("Seats Available:"));
        add(seatField);
        add(addBtn);
        add(backBtn);

        addBtn.addActionListener(e -> {
            String code = codeField.getText().trim();
            String dest = destField.getText().trim();
            String date = dateField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int seats = Integer.parseInt(seatField.getText().trim());

            tripList.add(new Trip(code, dest, date, price, seats));
            JOptionPane.showMessageDialog(this, "Trip added successfully!");

            codeField.setText("");
            destField.setText("");
            dateField.setText("");
            priceField.setText("");
            seatField.setText("");
        });

        backBtn.addActionListener(e -> {
            this.dispose();
            loginFrame.setVisible(true);
        });

        setVisible(true);
    }
}
