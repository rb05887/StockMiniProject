package test;

import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;

class MyClass{
	static double stockPrice=100.0;
	static int availableShares=100;
	static int tradeCount=0;
}


public class StockGame extends Thread{
	private static MyClass my_class=new MyClass();
	private String name;
	private int numShares;
	private String ﬁleName;
	
	public StockGame(String name, String ﬁleName) {
		super("Demo Thread");
		this.name=name;
		this.ﬁleName=ﬁleName;
		this.numShares=0;
		
		
	}
	
	private void processTrade(String s1, String s2) {
		
			int val;
			
			if(s1.equals("BUY")) {
				
				System.out.println("---------");
				val=Integer.parseInt(s2);
					if(val>this.my_class.availableShares) {
						System.out.println("Insufﬁcient shares available, cancelling order...");
						
					}
					else {
						System.out.println("Stock Price: "+this.my_class.stockPrice);
						System.out.println("Available Shares: "+this.my_class.availableShares);
						this.my_class.tradeCount++;
						System.out.println("Trade number: "+this.my_class.tradeCount);
						System.out.println("Name: "+name);
						System.out.println("Purchasing "+ val+ " shares at "+ this.my_class.stockPrice + "...");
						
						this.my_class.stockPrice+=(val*1.5);
						this.my_class.availableShares-=val;
						numShares+=val;
					}
					
					
				
				}
				else{
					
				System.out.println("---------");
				val=Integer.parseInt(s2);
					if(val>numShares) {
						System.out.println("Insufﬁcient owned shares, cancelling order...");
					}
					else {
						System.out.println("Stock Price: "+this.my_class.stockPrice);
						System.out.println("Available Shares: "+this.my_class.availableShares);
						this.my_class.tradeCount++;
						System.out.println("Trade number: "+this.my_class.tradeCount);
						System.out.println("Name: "+name);
						System.out.println("Selling "+ val+ " shares at "+this.my_class.stockPrice+"...");
						
						this.my_class.stockPrice-=(val*2);
						this.my_class.availableShares+=val;
						numShares-=val;
					}
					
					
		}
		
	}
	
	public void run() {
		int i,val;
		BufferedReader reader;
		
		try {
			int ind;
			String s1="",s2="",s="";
			//reader = new BufferedReader(new FileReader(this.ﬁleName));
			File file=new File(this.ﬁleName);
			Scanner sc=new Scanner(file);
			
			String line = sc.nextLine();
			
		while (sc.hasNextLine()) {
				ind=line.indexOf(",");
				s1=line.substring(0,ind);
				s2=line.substring(ind+1);
				synchronized(this.my_class) {
					processTrade(s1,s2);
				}
						
			
				line = sc.nextLine();
			}
			

			//reader.close();
			while(sc.hasNextLine())
			System.out.println(sc.nextLine());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}



}