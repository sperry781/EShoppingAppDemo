

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class User {

    private String userID;
    private String password;
    private String loginStatus;

	public User(String desiredUserID, String desiredPassword)
	{
		//this is username
		userID=desiredUserID;
		password=desiredPassword;
		loginStatus="Logged out";
	}

	//Used to verify a login by checking if conditions are met and then updating the login status before returning true if they are
    public boolean verifyLogin(String inputUserID, String inputPassword){
        //Check if the entered userID and password match this user
		if(userID.equals(inputUserID)&&password.equals(inputPassword))
		{
			//User is a match, check if they are logged in already
			if(loginStatus.equals("Logged in"))
			{
				return false;
			}
			else
			{
				//Not logged in yet and user matches so update login status and return true
				loginStatus="Logged in";
				return true;
			}
		}
		else
		{
			return false;
		}
    }
	
	//Used when user is logging out to update their login status
	public void logOut()
	{
		loginStatus="Logged out";
	}
	
	//Return the user's username
	public String getUserID()
	{
		return userID;
	}

}
