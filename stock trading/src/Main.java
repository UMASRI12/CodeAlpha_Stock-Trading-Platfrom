import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TradingGUI app = new TradingGUI();
            app.setVisible(true);
        });
    }
}
