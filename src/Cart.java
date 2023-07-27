
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Cart {

    public int cartId;
    public int totalQuantity;
    public double totalPrice;
	public List<OrderItem> cartItems;
	public Customer customer;

	public Cart(int desiredCartID)
	{
		cartId=desiredCartID;
		totalQuantity=0;
		totalPrice=0.0;
		cartItems = new ArrayList<>();
	}

	public int getTotalQuantity()
	{
		return totalQuantity;
	}

	public double getTotalPrice()
	{
		return totalPrice;
	}
	
	
	public List<OrderItem> getCartItems()
	{
		return cartItems;
	}
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	
	public void clearCart()
	{
		totalQuantity=0;
		totalPrice=0.0;
		cartItems=new ArrayList<>();
	}
	
	public boolean isProductInCart(Product product)
	{
		for(OrderItem i : cartItems)
		{
			if(i.getProduct().getItemID()==product.getItemID())
				return true;
		}
		return false;
	}
	
    public void addItem(OrderItem newItem)
	{
        totalPrice=totalPrice+newItem.getProduct().getPrice()*newItem.getQuantity();
		totalQuantity=totalQuantity+newItem.getQuantity();
		if(isProductInCart(newItem.getProduct()))
		{
			//If product is already in the cart then just update the current quantity of the item order
			updateQuantity(selectItemByProduct(newItem.getProduct()), selectItemByProduct(newItem.getProduct()).getQuantity()+newItem.getQuantity());
		}
		else
			cartItems.add(newItem);
    }
	
	public void removeItem(OrderItem item)
	{
		int indexCount=0;
		for(OrderItem i : cartItems)
		{
			if(item.getOrderItemID()==i.getOrderItemID())
			{
				break;
			}
			indexCount++;
		}
		cartItems.remove(indexCount);
	}
	
    public void updateQuantity(OrderItem item, int newQuantity){

		if(newQuantity==0)
		{
			totalQuantity=totalQuantity-item.getQuantity();
			totalPrice=totalPrice-item.getQuantity()*item.getProduct().getPrice();
			removeItem(item);
		}
		else
		{
			totalQuantity=totalQuantity+newQuantity-item.getQuantity();
			totalPrice=totalPrice+newQuantity*item.getProduct().getPrice()-item.getQuantity()*item.getProduct().getPrice();
			item.setQuantity(newQuantity);
		}
    }
	
	public OrderItem selectItem(int chosenOrderItemID)
	{
		for(OrderItem i : cartItems)
		{
			if(i.getOrderItemID()==chosenOrderItemID)
				return i;
		}
		return null;
	}
	
	public OrderItem selectItemByProduct(Product chosenProduct)
	{
		for(OrderItem i : cartItems)
		{
			if(i.getProduct().getItemID()==chosenProduct.getItemID())
				return i;
		}
		return null;
	}
	
    public String viewDetails()
	{
		StringBuilder infoString = new StringBuilder();
		for(OrderItem i : cartItems)
		{
			infoString.append(i.displayInfo());
			infoString.append("\n");
		}
		infoString.append("Total Cart Quantity:");
		infoString.append(String.format("%2d",getTotalQuantity()));
		infoString.append("\nTotal Cart Price:");
		infoString.append(String.format("%f",getTotalPrice()));
		infoString.append("\n");
		return infoString.toString();
    }
	
    public Order checkOut(int orderID, int creditCardNo, int month, int year, String name, Address billing, Address shipping)
	{
        Order newOrder = new Order(orderID, totalQuantity, totalPrice, customer, shipping, billing, cartItems, creditCardNo, month, year, name);
		clearCart();
		return newOrder;
    }
}
