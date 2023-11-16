package test;

public class Stock_Main_Class {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			StockGame[] stockTraders = {new StockGame("Xander", "C:\\Users\\rb058\\OneDrive\\Desktop\\Stock\\TraderOneMoves.txt"),
			new StockGame("Afua", "C:\\Users\\rb058\\OneDrive\\Desktop\\Stock\\TraderTwoMoves.txt")};
			
			for (int i = 0; i < stockTraders.length; i++) {
			stockTraders[i].start();
			}
			for (int i = 0; i < stockTraders.length; i++) {
			stockTraders[i].join();
			}

;			} catch (Exception ex) {
			ex.printStackTrace();
			return;
			}
	}

}