import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private double balance = 100000.00;
    private final Map<String, Integer> holdings = new HashMap<>();

    public double getBalance() { return balance; }

    public Map<String, Integer> getHoldings() {
        return Collections.unmodifiableMap(holdings);
    }

    public void buy(Stock stock, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive.");
        double cost = stock.getPrice() * quantity;
        if (cost > balance) throw new IllegalArgumentException("Insufficient wallet balance.");
        balance -= cost;
        holdings.put(stock.getSymbol(), holdings.getOrDefault(stock.getSymbol(), 0) + quantity);
    }

    public void sell(Stock stock, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive.");
        int owned = holdings.getOrDefault(stock.getSymbol(), 0);
        if (quantity > owned) throw new IllegalArgumentException("You do not own enough shares.");
        balance += stock.getPrice() * quantity;
        int remaining = owned - quantity;
        if (remaining == 0) holdings.remove(stock.getSymbol());
        else holdings.put(stock.getSymbol(), remaining);
    }
}
