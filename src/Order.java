
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Order {


    public int orderID;
    public String orderTrackingNumber;
    public int totalQuantity;
    public double totalPrice;
    public String status;
    public Date dateCreated;
    public Date lastUpdated;
    public Customer customer;
    public Address shippingAddress;
    public Address billingAddress;
    public List<OrderItem> orderItems;
	int creditCardNo;
	int month;
	int year;
	String nameOnCard;
	
	public Order(int desiredOrderID, int desiredTotalQuantity, double desiredTotalPrice, Customer desiredCustomer, Address desiredShippingAddress, Address desiredBillingAddress, List<OrderItem> desiredOrderItems, int desiredCreditCardNo, int desiredMonth, int desiredYear, String desiredNameOnCard)
	{
		orderID = desiredOrderID;
		orderTrackingNumber = "Not Shipped Yet";
		totalQuantity = desiredTotalQuantity;
		totalPrice = desiredTotalPrice;
		status = "Not Fulfilled";
		customer = desiredCustomer;
		shippingAddress = desiredShippingAddress;
		billingAddress = desiredBillingAddress;
		orderItems = desiredOrderItems;
		dateCreated=new Date();
		lastUpdated=new Date();
		creditCardNo=desiredCreditCardNo;
		month=desiredMonth;
		year=desiredYear;
		nameOnCard=desiredNameOnCard;
		orderItems = new ArrayList<>();
	}
	
	
	public int getOrderID()
	{
		return orderID;
	}

	public String getOrderTrackingNumber()
	{
		return orderTrackingNumber;
	}

	public int getTotalQuantity()
	{
		return totalQuantity;
	}

	public double getTotalPrice()
	{
		return totalPrice;
	}

	public String getStatus()
	{
		return status;
	}

	public Date getDateCreated()
	{
		return dateCreated;
	}

	public Date getLastUpdated()
	{
		return lastUpdated;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public Address getShippingAddress()
	{
		return shippingAddress;
	}

	public Address getBillingAddress()
	{
		return billingAddress;
	}

	public List<OrderItem> getOrderItems()
	{
		return orderItems;
	}

	public int getCreditCardNo()
	{
		return creditCardNo;
	}
	public int getMonth()
	{
		return month;
	}
	
	public int getYear()
	{
		return year;
	}
	public String getNameOnCard()
	{
		return nameOnCard;
	}



	public void setOrderTrackingNumber(String newNumber)
	{
		lastUpdated=new Date();
		orderTrackingNumber=newNumber;
	}
	
	public void setStatus(String newStatus)
	{
		lastUpdated=new Date();
		status=newStatus;
	}


	
	public OrderItem selectItem(int chosenOrderItemID)
	{
		for(OrderItem i : orderItems)
		{
			if(i.getOrderItemID()==chosenOrderItemID)
				return i;
		}
		return null;
	}
	
    public void add(OrderItem item)
	{
		orderItems.add(item);
		lastUpdated= new Date();
		totalQuantity++;
		totalPrice=totalPrice+item.getQuantity()*item.getProduct().getPrice();
		lastUpdated=new Date();
    }
	
	public void remove(OrderItem item)
	{
		orderItems.add(item);
		lastUpdated= new Date();
		totalQuantity--;
		totalPrice=totalPrice-item.getQuantity()*item.getProduct().getPrice();
		lastUpdated=new Date();
    }
	
	
	public String viewOrder()
	{
		StringBuilder infoString = new StringBuilder();
		for(OrderItem i : orderItems)
		{
			infoString.append(i.displayInfo());
			infoString.append("\n");
		}
		infoString.append("Total Quantity:");
		infoString.append(String.format("%2d",getTotalQuantity()));
		infoString.append("\nTotal Cart Price:");
		infoString.append(String.format("%f",getTotalPrice()));
		infoString.append("\nStatus:");
		infoString.append(status);
		infoString.append("\nTracking:");
		infoString.append(getOrderTrackingNumber());
		infoString.append("\nShipping Address:");
		infoString.append(getShippingAddress().getAddress());
		infoString.append("\nBilling Address:");
		infoString.append(getBillingAddress().getAddress());
		return infoString.toString();
    }

}
