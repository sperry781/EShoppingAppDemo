
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class OrderItem {

	//this ID is only unique within each cart
    public int orderItemID;
    public int quantity;
	public Product product;
	
	public OrderItem(int desiredOrderItemID, int desiredQuantity, Product desiredProduct)
	{
		orderItemID=desiredOrderItemID;
		quantity=desiredQuantity;
		product=desiredProduct;
	}
	
	public int getOrderItemID()
	{
		return orderItemID;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public Product getProduct()
	{
		return product;
	}
	
	
	public void setQuantity(int newQuantity)
	{
		quantity=newQuantity;
	}
	
    public String displayInfo()
	{
        StringBuilder infoString = new StringBuilder();
		infoString.append("OrderItemID:");
		infoString.append(String.format("%2d",getOrderItemID()));
		infoString.append("\nQuantity:");
		infoString.append(String.format("%2d",getQuantity()));
		infoString.append("\n");
		infoString.append(getProduct().displayInfo());
		return infoString.toString();
    }


}
