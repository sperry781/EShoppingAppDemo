
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Customer extends User{

    public String firstName;
    public String lastName;
    public String email;
	
	public Address shippingAddress;
	public Address billingAddress;
	public boolean addressesSaved;
	
    public Cart cart;
	public ArrayList<Order> purchaseOrders;

	public Customer(String desiredUserID, String desiredPassword, String desiredFirstName, String desiredLastName, String desiredEmail, int desiredCartID)
	{
		super(desiredUserID, desiredPassword);
		firstName=desiredFirstName;
		lastName=desiredLastName;
		email=desiredEmail;
		cart = new Cart(desiredCartID);
		purchaseOrders = new ArrayList<>();
		addressesSaved=false;
	}
	
	public Customer(String desiredUserID, String desiredPassword)
	{
		super(desiredUserID, desiredPassword);
	}
	
	public String getFirstName()
	{
		return firstName;
	}
    public String getLastName()
	{
		return firstName;
	}
    public String getEmail()
	{
		return email;
	}
	
	public Cart getCart()
	{
        return cart;
    }
	
	public boolean getSaved()
	{
		return addressesSaved;
	}
	
	public ArrayList<Order> getPurchaseOrders()
	{
        return purchaseOrders;
    }
	
	public double getTotalCartPrice()
	{
        return cart.getTotalPrice();
    }
	
    public void deleteItem(OrderItem item)
	{
        cart.removeItem(item);
    }
	
	public OrderItem findItemByProduct(Product product)
	{
		return cart.selectItemByProduct(product);
	}
	
	public OrderItem findItemByOrderItemID(int id)
	{
		return cart.selectItem(id);
	}
	
    public void addItem(Product item, int quantity)
	{
        OrderItem newItem;
		if(cart.getCartItems().size()==0)
			newItem = new OrderItem(0, quantity, item);
		else
			newItem = new OrderItem(cart.getCartItems().get(cart.getCartItems().size()-1).getOrderItemID()+1, quantity, item);
		
		cart.addItem(newItem);
    }
	
    public void editQuantity(OrderItem item, int newQuantity)
	{
        findItemByOrderItemID(item.getOrderItemID()).setQuantity(newQuantity);
    }
	
    public void clearCart()
	{
        cart.clearCart();
    }
	
	public void makePurchaseOrder(int orderID, int creditCardNo, int month, int year, String name)
	{
		purchaseOrders.add(cart.checkOut(orderID, creditCardNo, month, year, name, billingAddress, shippingAddress));
	}
	
    public void makePurchaseOrder(int orderID, int creditCardNo, int month, int year, String name, Address billing, Address shipping, boolean saveAddress)
	{
        if(saveAddress)
		{
			billingAddress=billing;
			shippingAddress=shipping;
			addressesSaved=true;
		}
		purchaseOrders.add(cart.checkOut(orderID, creditCardNo, month, year, name, billing, shipping));
    }
    
	
}
