package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StockGame extends Thread {
	private static double stockPrice = 100.00;
	private static int availableShares = 100;
	private static int tradeCount = 0;

	private String name;
	private int numShares;
	private String fileName;

	public StockGame(String name, String fileName) {
		this.name = name;
		this.fileName = fileName;
		this.numShares = 0;
	}

	public static synchronized double getStockPrice() {
		return stockPrice;
	}

	public static synchronized int getAvailableShares() {
		return availableShares;
	}

	public static synchronized int getTradeCount() {
		return tradeCount;
	}

	private synchronized void processTrade(String action, int num) {
		System.out.println(" --------- ");
		System.out.println("Stock Price: " + stockPrice);
		System.out.println("Available Shares: " + availableShares);
		System.out.println("Trade Number: " + tradeCount);
		System.out.println("Name of user: " + name);

		if ((action.equals("BUY") && num <= availableShares) || (action.equals("SELL") && num <= numShares)) {
			
			if (action.equals("BUY")) {
				numShares += num;
				availableShares -= num;
				stockPrice += 1.5 * num;
			} else {
				numShares -= num;
				availableShares += num;
				stockPrice -= 2.0 * num;
			}

			tradeCount++;
		} else {

			System.out.println(action.equals("BUY") ? "Insufficient shares available, cancelling order..."
					: "Insufficient owned shares, cancelling order...");
		}

		System.out.println("Name: " + name);
		System.out.println(action + " " + num + " shares at " + stockPrice + "...");
	}

	@Override
	public void run() {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 2) {
					String action = parts[0].trim();
					int num = Integer.parseInt(parts[1].trim());
					if (action.equals("BUY") || action.equals("SELL")) {
						processTrade(action, num);
					} else {
						System.out.println("Error, invalid input!");
					}
				} else {
					System.out.println("Error, invalid input!");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			StockGame[] stockTraders = { new StockGame("Xander", "E:\\NEEBAL\\MiniProjectStock\\TraderOneMoves.txt"),
					new StockGame("Afua", "E:\\NEEBAL\\MiniProjectStock\\TraderTwoMoves.txt") };
			for (int i = 0; i < stockTraders.length; i++) {
				stockTraders[i].start();
			}
			for (int i = 0; i < stockTraders.length; i++) {
				stockTraders[i].join();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return;
		}
	}
}
