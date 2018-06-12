package oocoursework;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class AuctionSystem {
	//hardcoded data for auction system
	public static ArrayList<String> arrayUsername = new ArrayList<String>();
	public static ArrayList<String> arrayPassword = new ArrayList<String>();
	public static ArrayList<String> arraySellers = new ArrayList<String>();
	public static ArrayList<String> arrayBuyers = new ArrayList<String>();
	public static ArrayList<String> arrayItems = new ArrayList<String>();
	public static ArrayList<Double> arrayStartingPrice = new ArrayList<Double>();
	public static ArrayList<Double> arrayReservePrice = new ArrayList<Double>();
	public static ArrayList<String> arrayCloseDate = new ArrayList<String>();
	public static ArrayList<Double> arrayHighestBid = new ArrayList<Double>();
	public static ArrayList<Double> arrayCurrentBids = new ArrayList<Double>();
	public static ArrayList<String> arrayActiveAuctions = new ArrayList<String>();

	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	//private static final String ACTIVEAUCTIONS = "activeauctions.txt";
	private static Scanner scan = new Scanner(System.in);
	
	static String userChoice;
	static String currentUser;
	static String auctionItem;
	static Double startPrice;
	static Double reservePrice;
	static String closeDate;
	static Double highestBid;
	
	public static void main(String[] args) throws ParseException, IOException 
	{
		
		//data for arrays
		arrayUsername.add("Matt");
		arrayUsername.add("Kirsty");
		arrayUsername.add("Lauren");
		
		arrayPassword.add("123");
		arrayPassword.add("456");
		arrayPassword.add("789");
		
		arraySellers.add("Matt");
		arraySellers.add("Kirsty");
		
		arrayBuyers.add("Lauren");
		
		arrayItems.add("Watch");
		arrayItems.add("Laptop");
		arrayStartingPrice.add((double) 10);
		arrayStartingPrice.add((double) 100);
		arrayReservePrice.add((double) 20);
		arrayReservePrice.add((double) 150);
		arrayCloseDate.add("25/03/2017");
		arrayCloseDate.add("26/03/2017");
		arrayHighestBid.add((double) 12);
		arrayHighestBid.add((double) 100);
		
		arrayCurrentBids.add((double) 12);
		
		//System.out.println(arrayUsername);
		//System.out.println(arrayPassword);
		
		//Auction.checkDate(currentDate, closeDate);
		login();
	}
	
	public static void login() throws ParseException, IOException
	{
		//main menu for user who is not logged in
		System.out.println("Welcome to the auction system! Please select an option:");
		System.out.println("[1] Login");
		System.out.println("[2] Setup Account");
		System.out.println("[3] Browse Auctions");
		System.out.println("[0] Exit");
		userChoice = scan.next();
		
		switch(userChoice)
		{
			case "1":
			{
				System.out.println("Input username:");
				String username = scan.next();
				
				System.out.println("Input Password:");
				String password = scan.next();
				
				for(int a = 0; a < arrayUsername.size(); a++) for(int b = 0; b < arrayPassword.size(); b++)
				{
					if(arrayUsername.contains(username) && (arrayPassword.contains(password)))
					//if(arrayUsername.get(a).equals(username) && arrayPassword.get(b).equals(password))
					{
						System.out.println("Logged in as: " + username);
						User.loggedIn = true;
						currentUser = username;
						mainMenu();
					} 
					
					else 
					{
						//returns to program entry
						System.out.println("Incorrect Login");
						login();
					}
				}
				break;
			}
			
			case "2":
			{
				//Setup account code, adds new username and password to array, error checks if both username AND password exist in array.
				setupAccount();
				break;
			}
			
			case "3":
			{
				System.out.format("Active Auctions%n%nPlease Login to Setup an auction or Make a Bid%n%n");
				browseAuction();
				login();
				break;
			}
			
			case "0":
			{
				System.out.println("Goodbye");
				scan.close();
				System.exit(0);
			}
		}
	}
	
	public static void mainMenu() throws ParseException, IOException
	{
		do
		{
			//main menu for logged in user
			System.out.format("Welcome to the Auction System! %n%nPlease Select an Option:%n%n");
			System.out.println("[1] Logout");
			System.out.println("[2] Setup Auction");
			System.out.println("[3] Browse Auctions");
			System.out.println("[4] View Bids");
			System.out.println("[0] Exit");
			userChoice = scan.next();
			
			switch(userChoice)
			{
				case "1":
				{
					logout();
					break;
				}
				
				case "2":
				{
					setupAuction();
					break;
				}
				
				case "3":
				{
					browseAuction();
					Auction.placeBid();
					break;
				}
				
				case "4":
				{
					viewBids();
					break;
				}
			}
			
		}while(!userChoice.equals("0"));
		System.out.println("Goodbye!");
		scan.close();
		System.exit(0);
	}

	public static void logout() throws ParseException, IOException
	{
		User.loggedIn = false;
		System.out.println("Logged out");
		AuctionSystem.login();
	}
	
	public static void setupAuction() throws ParseException, IOException
	{
		System.out.println("Please specify the name of new auction item:");
		auctionItem = scan.next();
		
		System.out.println("Please specify the start price:");
		startPrice = scan.nextDouble();
		
		System.out.println("Please specify the reserve price:");
		reservePrice= scan.nextDouble();
		
		Auction.checkReservePrice(startPrice, reservePrice);
		
		System.out.format("Please specify the close date (Must be within 7 days of the start date!):%nFormat: dd/mm/yyyy%n");
		closeDate = scan.next();
		
		Date date = formatter.parse(closeDate);
		System.out.println("Auction will end on: "+ date);
		
		highestBid = startPrice;
		Auction.setPending();
		//System.out.println(Auction.getStatus());
		Auction.verify();
		
		
		//need to store data in array for current auctions
	}
	
	public static void browseAuction() throws FileNotFoundException
	{
		//will show the current auctions from array
		/*scan = new Scanner(new FileReader(ACTIVEAUCTIONS));
		int a = 0;
		
		while(scan.hasNext())
		{
			arrayActiveAuctions.add(scan.next());
			System.out.println(scan.next());
		}*/
		
		for(int b = 0; b < arraySellers.size(); b++)
		{
			System.out.println("Seller: " + arraySellers.get(b));
			System.out.println("Item: " + arrayItems.get(b));
			System.out.println("Starting Price: £" + arrayStartingPrice.get(b));
			System.out.println("Reserve Price: £" + arrayReservePrice.get(b));
			System.out.println("Close Date: " + arrayCloseDate.get(b));
			System.out.format("Highest Bid: £" + arrayHighestBid.get(b) + "%n%n");
		}
		
		/*new Item();
		System.out.println(Item.Description()[0]);
		
		System.out.println(Item.Description()[1]);
		
		System.out.println(Item.Description()[2]);
		
		System.out.println(Item.Description()[3]);
		
		System.out.println(Item.Description()[4]);*/
	}
	
	public static void viewBids()
	{
		for(int a = 0; a < arrayBuyers.size(); a++)
		{
			System.out.println("Buyer: " + arrayBuyers.get(a));
			System.out.format("Bid: " + arrayCurrentBids.get(a) + "%n%n");
		}
	}
	
	public static void setupAccount() throws ParseException, IOException
	{
		System.out.println("Please specify username: ");
		String username = scan.next();
		
		System.out.println("Please specify password: ");
		String password = scan.next();
		
		for(int a = 0; a  <arrayUsername.size(); a++)
		{
			if(arrayUsername.contains(username))
			{
				System.out.println("Username already exists!");
				login();
			}
			
			else
				//stored temporarily while program is active
				arrayUsername.add(username);
				arrayPassword.add(password);
				
				System.out.println("Account Created");
				login();
		}
		
	}
}