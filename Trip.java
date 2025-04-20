public class Trip {
    private String code;
    private String destination;
    private String date;
    private double price;
    private int seatsAvailable;

    public Trip(String code, String destination, String date, double price, int seatsAvailable) {
        this.code = code;
        this.destination = destination;
        this.date = date;
        this.price = price;
        this.seatsAvailable = seatsAvailable;
    }

    public String getCode() { return code; }
    public String getDestination() { return destination; }
    public String getDate() { return date; }
    public double getPrice() { return price; }
    public int getSeatsAvailable() { return seatsAvailable; }

    public boolean bookSeat() {
        if (seatsAvailable > 0) {
            seatsAvailable--;
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return String.format("Code: %s | Destination: %s | Date: %s | Price: INR%.2f | Seats Available: %d",
                code, destination, date, price, seatsAvailable);
    }}
    