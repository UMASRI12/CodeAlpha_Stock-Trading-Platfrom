import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class TradingGUI extends JFrame {
    private final Portfolio portfolio = new Portfolio();
    private final ArrayList<Stock> stocks = new ArrayList<>();
    private final JComboBox<String> stockBox = new JComboBox<>();
    private final JTextField quantityField = new JTextField();
    private final JTextArea output = new JTextArea();
    private final JLabel balanceLabel = new JLabel();

    public TradingGUI() {
        stocks.add(new Stock("TCS", "Tata Consultancy Services", 3500));
        stocks.add(new Stock("INFY", "Infosys", 1800));
        stocks.add(new Stock("RELIANCE", "Reliance Industries", 2900));
        stocks.add(new Stock("HDFC", "HDFC Bank", 1700));

        setTitle("Stock Trading Platform - Simulation");
        setSize(680, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel top = new JPanel(new GridLayout(3, 2, 8, 8));
        top.setBorder(BorderFactory.createEmptyBorder(12, 12, 4, 12));
        for (Stock stock : stocks) {
            stockBox.addItem(stock.getSymbol() + " - " + stock.getCompany()
                    + " (₹" + stock.getPrice() + ")");
        }
        top.add(new JLabel("Select stock:"));
        top.add(stockBox);
        top.add(new JLabel("Quantity:"));
        top.add(quantityField);
        top.add(new JLabel("Wallet balance:"));
        top.add(balanceLabel);

        JButton buyButton = new JButton("Buy");
        JButton sellButton = new JButton("Sell");
        JButton portfolioButton = new JButton("View Portfolio");
        JPanel buttons = new JPanel();
        buttons.add(buyButton);
        buttons.add(sellButton);
        buttons.add(portfolioButton);

        output.setEditable(false);
        output.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JPanel center = new JPanel(new BorderLayout());
        center.add(buttons, BorderLayout.NORTH);
        center.add(new JScrollPane(output), BorderLayout.CENTER);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        buyButton.addActionListener(e -> trade(true));
        sellButton.addActionListener(e -> trade(false));
        portfolioButton.addActionListener(e -> showPortfolio());
        updateBalance();
        setLocationRelativeTo(null);
    }

    private Stock getSelectedStock() {
        return stocks.get(stockBox.getSelectedIndex());
    }

    private void trade(boolean buying) {
        try {
            int quantity = Integer.parseInt(quantityField.getText().trim());
            Stock stock = getSelectedStock();
            if (buying) portfolio.buy(stock, quantity);
            else portfolio.sell(stock, quantity);

            output.append((buying ? "Bought " : "Sold ") + quantity
                    + " shares of " + stock.getSymbol() + ".\n");
            updateBalance();
            quantityField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid whole-number quantity.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void updateBalance() {
        balanceLabel.setText("₹" + String.format("%.2f", portfolio.getBalance()));
    }

    private void showPortfolio() {
        output.setText("PORTFOLIO\n");
        if (portfolio.getHoldings().isEmpty()) output.append("No shares currently owned.\n");
        for (Map.Entry<String, Integer> entry : portfolio.getHoldings().entrySet()) {
            output.append(entry.getKey() + " : " + entry.getValue() + " shares\n");
        }
        output.append("\nWallet balance: ₹" + String.format("%.2f", portfolio.getBalance()));
    }
}
