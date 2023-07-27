import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProductCategory {

    public int categoryID;
    public String categoryName;
    public List<Product> products;
	
	public ProductCategory(int desiredCategoryID, String desiredCategoryName)
	{
		categoryID=desiredCategoryID;
		categoryName=desiredCategoryName;
		products = new ArrayList<>();
	}
	public int getCategoryID() 
	{
		return categoryID;
	}

    public String getProductCategory() 
	{
        return categoryName;
    }
	
	public List<Product> getProductsinthisCategory() 
	{
        return products;
    }
	
	public void addProduct(Product newProduct)
	{
		products.add(newProduct);
	}
	
	public String viewProducts()
	{
		StringBuilder infoString = new StringBuilder();
		infoString.append(getProductCategory());
		infoString.append("\n\n");
		for(Product i : products)
		{
			infoString.append(i.displayInfo());
			infoString.append("\n");
		}
		return infoString.toString();
    }
	
	public void clearList()
	{
		products = new ArrayList<>();
	}
}
