
import java.util.Date;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Product{

    public int productID;
    public String category;
    public String sku;
    public String name;
    public String description;
    public double unitPrice;
    public String imageUrl;
    public boolean active;
    public int unitsInStock;
    public Date dateCreated;
    public Date lastUpdated;
	
	public Product(int desiredProductID, String desiredCategory, String desiredSku, String desiredName, String desiredDescription, double desiredUnitPrice, String desiredImageUrl, int desiredUnitsInStock)
	{
		productID=desiredProductID;
		category=desiredCategory;
		sku=desiredSku;
		name=desiredName;
		description=desiredDescription;
		unitPrice=desiredUnitPrice;
		imageUrl=desiredImageUrl;
		unitsInStock=desiredUnitsInStock;
		if(unitsInStock>0)
			active=true;
		else
			active=false;
		dateCreated=new Date();
		lastUpdated=new Date();
	}
	
    public int getItemID()
	{
        return productID;
    }
    public String getCategory()
	{
        return category;
    }
	public String getSku()
	{
        return sku;
    }
	
	public String getName()
	{
        return name;
    }
    public String getDescription()
	{
        return description;
    }
    public double getPrice()
	{
        return unitPrice;
    }
	
	public String getImageURL()
	{
		return imageUrl;
	}
	
	public boolean getActive()
	{
		return active;
	}
	
	public int getUnitsInStock()
	{
		return unitsInStock;
	}
	
	public Date getLastUpdatedDate()
	{
		return lastUpdated;
	}
	
	public Date getCreationDate()
	{
		return dateCreated;
	}
	
	
	public void setUnitsInStock(int newUnitsInStock)
	{
		lastUpdated=new Date();
        unitsInStock=newUnitsInStock;
		if(!active&&newUnitsInStock>0)
			active=true;
		else if(unitsInStock==0)
			active=false;
    }
	
	public String displayInfo()
	{
		StringBuilder infoString = new StringBuilder();
		infoString.append("Product Name:");
		infoString.append(getName());
		infoString.append("\nDescription:");
		infoString.append(getDescription());
		infoString.append("\nPrice:");
		infoString.append(String.format("%1$,.2f",getPrice()));
		infoString.append("\nProductID:");
		infoString.append(String.format("%2d",getItemID()));
		infoString.append("\nSKU:");
		infoString.append(getSku());
		infoString.append("\nImageURL:");
		infoString.append(getImageURL());
		infoString.append("\nUnits Currently In Stock:");
		infoString.append(String.format("%2d",getUnitsInStock()));
		infoString.append("\n");
		return infoString.toString();
	}
}
