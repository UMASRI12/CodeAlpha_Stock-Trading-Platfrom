public class Stock {
    private final String symbol;
    private final String company;
    private final double price;

    public Stock(String symbol, String company, double price) {
        if (price <= 0) throw new IllegalArgumentException("Price must be positive.");
        this.symbol = symbol;
        this.company = company;
        this.price = price;
    }

    public String getSymbol() { return symbol; }
    public String getCompany() { return company; }
    public double getPrice() { return price; }
}
