import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UserDashboard extends JFrame {
    private JComboBox<String> tripDetailsBox;
    private JTextField seatsField;
    private JButton bookBtn, backBtn;
    private ArrayList<Trip> tripList;
    private User user;
    private JFrame loginFrame;

    public UserDashboard(User user, ArrayList<Trip> tripList, JFrame loginFrame) {
        this.user = user;
        this.tripList = tripList;
        this.loginFrame = loginFrame;

        setTitle("User Dashboard");
        setSize(700, 350);
        setLayout(new GridLayout(6, 1, 10, 10));
        setLocationRelativeTo(null);

        // Components
        tripDetailsBox = new JComboBox<>();
        tripDetailsBox.setFont(new Font("Monospaced", Font.PLAIN, 13)); // aligned & clean
        refreshTripDropdown();

        seatsField = new JTextField();
        seatsField.setFont(new Font("SansSerif", Font.PLAIN, 13));

        bookBtn = new JButton("Book Trip");
        backBtn = new JButton("Logout");

        // Add to frame
        add(new JLabel("Select a Trip (with full details):"));
        add(tripDetailsBox);
        add(new JLabel("Enter number of seats to book:"));
        add(seatsField);
        add(bookBtn);
        add(backBtn);

        // Book Trip Button Logic
        bookBtn.addActionListener(e -> {
            int selectedIndex = tripDetailsBox.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(this, "⚠️ Please select a trip.");
                return;
            }

            Trip selectedTrip = tripList.get(selectedIndex);

            String seatsInput = seatsField.getText().trim();
            if (seatsInput.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Please enter number of seats.");
                return;
            }

            try {
                int seatsRequested = Integer.parseInt(seatsInput);

                if (seatsRequested <= 0) {
                    JOptionPane.showMessageDialog(this, "⚠️ Enter a positive number.");
                    return;
                }

                if (selectedTrip.getSeatsAvailable() >= seatsRequested) {
                    for (int i = 0; i < seatsRequested; i++) {
                        selectedTrip.bookSeat();
                    }

                    double totalPrice = selectedTrip.getPrice() * seatsRequested;

                    String message = "✅ Booking Successful!\n\n"
                            + "Trip Code     : " + selectedTrip.getCode() + "\n"
                            + "Destination   : " + selectedTrip.getDestination() + "\n"
                            + "Date          : " + selectedTrip.getDate() + "\n"
                            + "Price         : INR" + selectedTrip.getPrice() + "\n"
                            + "Seats Booked  : " + seatsRequested + "\n"
                            + "Total Fare    : $" + totalPrice + "\n"
                            + "Seats Left    : " + selectedTrip.getSeatsAvailable();

                    JOptionPane.showMessageDialog(this, message);

                    seatsField.setText(""); // Clear input
                    refreshTripDropdown();  // Refresh drop-down
                } else {
                    JOptionPane.showMessageDialog(this,
                            "❌ Booking not possible. Only " + selectedTrip.getSeatsAvailable() + " seat(s) available.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "❌ Please enter a valid number.");
            }
        });

        // Logout Button
        backBtn.addActionListener(e -> {
            this.dispose();
            loginFrame.setVisible(true);
        });

        setVisible(true);
    }

    // Helper to refresh dropdown
    private void refreshTripDropdown() {
        tripDetailsBox.removeAllItems();
        for (Trip trip : tripList) {
            tripDetailsBox.addItem(trip.toString());
        }
    }
}

