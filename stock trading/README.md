# Stock Trading Platform (Simulation)

A Java Swing desktop app that simulates buying and selling stocks with a virtual wallet. It is not connected to a live market.

## Features
- Virtual starting wallet of ₹100,000
- Four sample stocks with fixed prices: TCS (₹3500), INFY (₹1800), RELIANCE (₹2900), HDFC (₹1700)
- Buy and sell shares by quantity
- Validation: positive whole-number quantity, sufficient balance on buy, sufficient shares on sell
- View portfolio holdings and the current wallet balance

## Tech Stack
- Java 17+
- Swing / AWT (GUI)

## Project Structure
```
StockTradingPlatform/
└── src/
    ├── Main.java        # Entry point (launches the GUI)
    ├── TradingGUI.java  # Swing window: stock picker, buy/sell, portfolio view
    ├── Portfolio.java   # Wallet balance and holdings; buy/sell logic
    └── Stock.java       # Stock model (symbol, company, price)
```

## Requirements
- JDK 17 or later

## How to Run
From the `StockTradingPlatform` folder:
```bash
javac -d out src/*.java
java -cp out Main
```

## Usage
1. Select a stock and enter a quantity.
2. Click **Buy** or **Sell**.
3. Click **View Portfolio** to see holdings and the wallet balance.

## Notes
- Prices are fixed sample values; there is no live data.
- Data is kept in memory only and resets when the app closes.
- Possible extensions: live prices via an API, transaction history, profit/loss, database persistence, user login.
