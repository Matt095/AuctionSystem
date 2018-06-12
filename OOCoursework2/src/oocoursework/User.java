package oocoursework;

public class User 
{

	//static User[] arrayUsers;
	
	private String username;
	private String password;
	static boolean loggedIn = false;
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
		
		//arrayUsers = new User[1];

        //arrayUsers[0] = new User("Matt", "123"); // sample hard coded data for tests
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	
	
}
