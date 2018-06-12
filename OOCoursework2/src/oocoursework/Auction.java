package oocoursework;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;



public class Auction 
{
	private static final String ACTIVEAUCTIONS = "activeauctions.txt";
	static Scanner scan = new Scanner(System.in);
	static String userChoice;
	
	private static double startPrice;
	private static double reservePrice;
	private Date closeDate;
	static char status;
	
	public Auction(double startPrice, double reservePrice, Date closeDate, char status) 
	{
		this.startPrice = startPrice;
		this.reservePrice = reservePrice;
		this.closeDate = closeDate;
		Auction.status = status;
	}
	
	public double getStartPrice() 
	{
		return startPrice;
	}
	
	public void setStartPrice(double startPrice) 
	{
		this.startPrice = startPrice;
	}
	
	public double getReservePrice() 
	{
		return reservePrice;
	}
	
	public void setReservePrice(double reservePrice) 
	{
		this.reservePrice = reservePrice;
	}
	
	public Date getCloseDate() 
	{
		return closeDate;
	}
	
	public void setCloseDate(Date closeDate) 
	{
		this.closeDate = closeDate;
	}
	
	public char getStatus() 
	{
		return status;
	}
	
	public void setStatus(char status) 
	{
		this.status = status;
	}
	
	public static void placeBid() throws ParseException, IOException 
	{
		System.out.println("Would you like to place a bid?");
		System.out.println("[1] Yes");
		System.out.println("[0] No");
		String userChoice = scan.next(); 
		
		switch(userChoice)
		{
			case "1":
			{
				System.out.println("Please enter your bid: ");
			    double amount = scan.nextDouble();
			    System.out.println("Please select an item: ");
			    String itemName = scan.next();
			    
			    for(int a = 0; a < AuctionSystem.arrayItems.size(); a++)
			    {
			    	if(AuctionSystem.arrayItems.contains(itemName))
			    	{
			    		//System.out.println("Bid Placed!");
			    		AuctionSystem.arrayItems.get(a);
			    		AuctionSystem.arrayBuyers.add(AuctionSystem.currentUser);
			    		AuctionSystem.arrayCurrentBids.add(amount);
			    		AuctionSystem.arrayHighestBid.add(a , amount); //place at same position to item in arrayItems to link them.
			    	}
			    	
			    	else
			    	{
			    		System.out.println("Item does not exist!");
			    		AuctionSystem.mainMenu();
			    	}
			    }
			}
			
			case "2":
			{
				AuctionSystem.mainMenu();
			}
		}
		

	}
	
	public static void verify() throws ParseException, IOException 
	{
		//sets status of auction depending on input
		System.out.println("Do you wish to start the auction!");
		System.out.println("[1] Yes");
		System.out.println("[0] No");
		userChoice = scan.next();
		
		switch(userChoice)
		{
			case "1":
			{
				//scan = new Scanner((Readable) new FileWriter(ACTIVEAUCTIONS));
				//startAuction
				AuctionSystem.arraySellers.add(AuctionSystem.currentUser);
				AuctionSystem.arrayItems.add(AuctionSystem.auctionItem);
				AuctionSystem.arrayStartingPrice.add(AuctionSystem.startPrice);
				AuctionSystem.arrayReservePrice.add(AuctionSystem.reservePrice);
				AuctionSystem.arrayCloseDate.add(AuctionSystem.closeDate);
				AuctionSystem.arrayHighestBid.add(AuctionSystem.highestBid);
				setOpen();
				break;
			}
			case "0":
			{
				AuctionSystem.mainMenu();
				break;
			}
		}
	}
	
	public void close() 
	{
		//close auction
		setClosed();
	}
	
	//public boolean isBlocked() 
	//{
		
	//}
	
	//public void setBlocked() 
	//{
		
	//}
	
	public static void setPending(){
		// p = pending state for auction

		//occurs once user has input data for new auction
		System.out.println("Auction Pending...");
		status = 'p';
	}
	
	public static void setOpen(){
		// o = open state for auction
		
		System.out.println("Auction Open!");
		status = 'o';
	}
	
	public static void setClosed(){
		//close when close date = current date
		
		// c = closed state for auction
		System.out.println("Auction Closed!");
		status = 'c';
	}
	
	public static void checkReservePrice(Double startPrice, Double reservePrice) throws ParseException, IOException
	{
		if(startPrice > reservePrice)
		{
			System.out.format("Reserve Price must be greater than Start Price%n%n");
			AuctionSystem.setupAuction();
		}
	}
	
	public static void checkDate(Date currentDate, Date closeDate)
	{
		if(currentDate == closeDate)
		{
			setClosed();
		}
	}
}
