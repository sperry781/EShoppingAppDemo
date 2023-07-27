
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Address {

    public String country;

    public String city;

    public String state;

    public String street;

    public String zipCode;
	
	public Address(String desiredCountry, String desiredCity, String desiredStreet, String desiredZipCode)
	{
		country = desiredCountry;
		city = desiredCity;
		street = desiredStreet;
		zipCode = desiredZipCode;
	}
	
	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getStreet() {
		return street;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
    public String getAddress() {
        return street+" "+zipCode+" "+city+" "+country;
    }
}
