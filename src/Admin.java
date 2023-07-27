
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Objects;

public class Admin extends User
{
	public ArrayList<Customer> globalCustomerList;
	public ArrayList<Product> globalProductList;
	public ArrayList<ProductCategory> globalCategoryList;
	
	public Admin(String desiredUserID, String desiredPassword) throws IOException
	{
		super(desiredUserID, desiredPassword);
		globalCustomerList = new ArrayList<>();
		globalProductList = new ArrayList<>();
		globalCategoryList = new ArrayList<>();
		
		
		runDemo();
	}
	
	
	public void runDemo() throws IOException
	{
		
		addCategory("Lighting");
		addCategory("Tables");
		addCategory("Couches");
		
		addProduct(new Product(0, "Lighting", "1KG345", "Lamp", "Large white lamp manufactured in Sweden.", 74.99, "EShopping.com/lamp", 10));
		addProduct(new Product(1, "Lighting", "5GHD87", "Desk Light", "Small LED desk light manufactured in China.", 32.99, "EShopping.com/desklight", 12));
		addProduct(new Product(2, "Lighting", "685355", "Chandelier", "Large chandelier with 4 light fixtures manufactured in Germany.", 378.99, "EShopping.com/chandelier", 5));
		
		addProduct(new Product(3, "Tables", "7HDKER", "Patio Table", "Metal table for outside use manufactured in US.", 205.99, "EShopping.com/lamp", 13));
		addProduct(new Product(4, "Tables", "2176DF", "Dining Table", "Large wooden dining table manufactured in Sweden.", 369.99, "EShopping.com/lamp", 23));
		addProduct(new Product(5, "Tables", "890FSD", "Bedside Table", "Small bedside table manufactured in Sweden.", 54.99, "EShopping.com/lamp", 50));
		
		addProduct(new Product(6, "Couches", "55WQ87", "Futon", "Medium size futon manufactured in China.",579.99, "EShopping.com/lamp", 5));
		addProduct(new Product(7, "Couches", "681G56", "Large Sofa", "Large leather sofa manufactured in US.", 1459.99, "EShopping.com/lamp", 3));
		addProduct(new Product(8, "Couches", "098YUT", "Small Couch", "Small linen couch manufactured in India.", 474.99, "EShopping.com/lamp", 17));
		
		Customer currentCustomer=runFindCustomer();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String input = "";
		boolean loop = true;
		StringBuilder infoString = new StringBuilder();
		infoString.append("Welcome ");
		infoString.append(currentCustomer.getFirstName());
		infoString.append(". You are logged in as ");
		infoString.append(currentCustomer.getUserID());
		System.out.println(infoString.toString());
		while(loop) {
			System.out.println("\nType logout to return to beginning page. Type view to view our product screen. Type cart to enter the cart screen. Type order to display your orders.");
			System.out.flush();
			input = in.readLine();
			if (input.equals("logout"))
			{
				currentCustomer.logOut();
				currentCustomer=runFindCustomer();
				infoString = new StringBuilder();
				infoString.append("Welcome ");
				infoString.append(currentCustomer.getFirstName());
				infoString.append(". You are logged in as ");
				infoString.append(currentCustomer.getUserID());
			}
			
			else if(input.equals("view"))
			{
				runProductView(currentCustomer);
				
			}
			
			else if(input.equals("cart"))
			{
				runCartView(currentCustomer);
			}
			
			else if(input.equals("order"))
			{
				for(Order i : currentCustomer.getPurchaseOrders())
				{
					System.out.println(i.viewOrder());
				}
			}
			
			else
			{
				System.out.println("Improper input, try again");
			}
			
		}
		
		
		
	}
	
	
	public void runProductView(Customer currentCustomer) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String input = "";
		boolean loop = true;
		boolean loop2 = true;
		String productName;
		System.out.println("\n");
		for(ProductCategory i : globalCategoryList)
		{
			System.out.println(i.viewProducts());
		}
		while(loop)
		{
			System.out.println("Type the name of the product to add it to your cart or type exit to return to main screen\n");
			System.out.flush();
			input = in.readLine();
			if (input.equals("exit"))
			{
				return;
			}
			else if (!Objects.isNull(findProduct(input)))
			{
				productName=input;
				loop2=true;
				System.out.println("How many would you like to add?\n");
				System.out.flush();
				while(loop2)
				{
					input = in.readLine();
					try {
						currentCustomer.addItem(findProduct(productName),Integer.parseInt(input));
						loop2=false;
					} catch (NumberFormatException e) {
						System.out.println("Input String cannot be parsed to Integer, try again");
					}
				}

			}
			else
			{
				System.out.println("Invalid input\n");
			}
			
		}
	}
	
	public void runCartView(Customer currentCustomer) throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String input = "";
		boolean loop = true;
		boolean loop2 = true;
		String productName;
		int quantity;
		while(loop)
		{
			System.out.println("Current Cart:\n");
			System.out.println(currentCustomer.getCart().viewDetails());
			System.out.println("Type the name of a product to select a product for deletion or edit the quantity. Type checkout to proceed to checkout on your order. Type exit to return to main screen\n");
			System.out.flush();
			input = in.readLine();
			if (input.equals("exit"))
			{
				return;
			}
			else if (input.equals("checkout"))
			{
				runMakePurchaseOrder(currentCustomer);
			}
			else if (!Objects.isNull(currentCustomer.getCart().selectItemByProduct(findProduct(input))))
			{
				productName=input;
				loop2=true;
				System.out.println("Type 0 to delete this item from your cart or type a new quantity to edit the quantity?\n");
				System.out.flush();
				while(loop2)
				{
					input = in.readLine();
					try {
						quantity=Integer.parseInt(input);
						currentCustomer.getCart().updateQuantity(currentCustomer.getCart().selectItemByProduct(findProduct(productName)),quantity);
						loop2=false;
					} catch (NumberFormatException e) {
						System.out.println("Input String cannot be parsed to Integer, try again");
					}
				}

			}
			else
			{
				System.out.println("Invalid input\n");
			}
			
		}
		
		
	}
	
	public void runMakePurchaseOrder(Customer currentCustomer) throws IOException
	{
		int card=0;
		int month=0;
		int year=0;
		String name;
		String country;
		String city;
		String street;
		String zipcode;
		
		boolean useSaved=false;
		
		String country2;
		String city2;
		String street2;
		String zipcode2;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String input = "";
		boolean loop=true;
		
		if(currentCustomer.getSaved())
		{
			System.out.println("You have a billing address and shipping address saved from your previous order, use these? Type yes or no.");
			System.out.flush();
			input = in.readLine();
			if(input.equals("yes"))
				useSaved=true;
		}
		else
			useSaved=false;
		
		
		System.out.println("Enter your credit card number\n");
		System.out.flush();
		while(loop)
		{
			input = in.readLine();
			try {
				card=Integer.parseInt(input);
				loop=false;
			} catch (NumberFormatException e) {
				System.out.println("Input String cannot be parsed to Integer, try again");
			}
		}
		
		input = "";
		loop=true;
		System.out.println("Enter the month of expiration\n");
		System.out.flush();
		while(loop)
		{
			input = in.readLine();
			try {
				month=Integer.parseInt(input);
				loop=false;
			} catch (NumberFormatException e) {
				System.out.println("Input String cannot be parsed to Integer, try again");
			}
		}
		
		input = "";
		loop=true;
		System.out.println("Enter the year of expiration\n");
		System.out.flush();
		while(loop)
		{
			input = in.readLine();
			try {
				year=Integer.parseInt(input);
				loop=false;
			} catch (NumberFormatException e) {
				System.out.println("Input String cannot be parsed to Integer, try again");
			}
		}
		
		
		input = "";
		System.out.println("Enter the name on card.");
		System.out.flush();
		input = in.readLine();
		name=input;
		
		if(useSaved)
		{
			currentCustomer.makePurchaseOrder(currentCustomer.getPurchaseOrders().size(), card, month, year, name);
			return;
		}
		
		else
		{
			input = "";
			System.out.println("Enter the country of the shipping address.");
			System.out.flush();
			input = in.readLine();
			country=input;
			
			input = "";
			System.out.println("Enter the city of the shipping address.");
			System.out.flush();
			input = in.readLine();
			city=input;
			
			input = "";
			System.out.println("Enter the street information of the shipping address.");
			System.out.flush();
			input = in.readLine();
			street=input;
			
			input = "";
			System.out.println("Enter the zipcode of the shipping address.");
			System.out.flush();
			input = in.readLine();
			zipcode=input;
			
			input = "";
			System.out.println("Enter the country of the billing address.");
			System.out.flush();
			input = in.readLine();
			country2=input;
			
			input = "";
			System.out.println("Enter the city of the billing address.");
			System.out.flush();
			input = in.readLine();
			city2=input;
			
			input = "";
			System.out.println("Enter the street information of the billing address.");
			System.out.flush();
			input = in.readLine();
			street2=input;
			
			input = "";
			System.out.println("Enter the zipcode of the billing address.");
			System.out.flush();
			input = in.readLine();
			zipcode2=input;
			
			input = "";
			System.out.println("Would you like to save the address information for future checkouts?");
			System.out.flush();
			input = in.readLine();
			
			if(input.equals("yes"))
				currentCustomer.makePurchaseOrder(currentCustomer.getPurchaseOrders().size(), card, month, year, name, new Address(country,city,street,zipcode),new Address(country2,city2,street2,zipcode2),true);
			else
				currentCustomer.makePurchaseOrder(currentCustomer.getPurchaseOrders().size(), card, month, year, name, new Address(country,city,street,zipcode),new Address(country2,city2,street2,zipcode2),false);
			return;
		}
	}
	
	
	
	public Customer runFindCustomer() throws IOException
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String input = "";
		boolean loop = true;
		
		while(loop) {
			System.out.println("Do you have an account? Type yes to access login page or no to create an account(case sensitive).");
			System.out.flush();
			input = in.readLine();
			if (input.equals("no"))
			{
				loop=false;
				return findCustomer(runAccountCreation());
			}
			
			else if(input.equals("yes"))
			{
				loop=false;
				return findCustomer(runLogin());
			}
			else
			{
				System.out.println("Improper input, try again");
			}
			
		}
		return new Customer("No customer", "No customer");
	}
		
	
	public String runAccountCreation() throws IOException
	{
		String firstname;
		String lastname;
		String email;
		String username="No username";
		String password;
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		String input = "";
		System.out.println("Enter your first name.");
		System.out.flush();
		input = in.readLine();
		firstname=input;
		
		input = "";
		in = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.println("Enter your last name.");
		System.out.flush();
		input = in.readLine();
		lastname=input;
		
		input = "";
		in = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.println("Enter your email.");
		System.out.flush();
		input = in.readLine();
		email=input;
		
		
		input = "";
		in = new BufferedReader(new InputStreamReader(System.in)); 
		boolean loop = true;
		
		while(loop) {
			System.out.println("Enter your desired username.");
			System.out.flush();
			input = in.readLine();
			if (findCustomer(input).getUserID().equals("No customer"))
			{
				loop=false;
				username=input;
			}
			else
			{
				System.out.println("Username taken");
			}
			
		}
		
		input = "";
		in = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.println("Enter your desired password.");
		System.out.flush();
		input = in.readLine();
		password=input;
		
		createAccount(username,password,firstname,lastname,email);
		return username;
	}
	
	public String runLogin() throws IOException
	{
		
		String input = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); 
		boolean loop = true;
		String username;
		while(loop) {
			System.out.println("Enter your username. Or type exit to exit to beginning page.");
			System.out.flush();
			input = in.readLine();
			if(input.equals("exit"))
				return runFindCustomer().getUserID();
			
			else if(findCustomer(input).getUserID().equals("No customer"))
			{
				System.out.println("Username does not exist");
			}
			else
			{
				username=input;
				input = "";
				while(loop) 
				{
					System.out.println("Enter your password. Or type exit to exit to beginning page.");
					System.out.flush();
					input = in.readLine();
					if(input.equals("exit"))
						return runFindCustomer().getUserID();
					if(findCustomer(username).verifyLogin(username,input))
					{
						System.out.println("You are now logged in");
						return username;
					}
					else
					{
						System.out.println("Wrong password or you are already logged in somewhere else.");
					}
				}
			}
			
		}
		return "No login";
	}
	
	
	public Product findProduct(String productName)
	{
		for(Product i : globalProductList)
		{
			if(i.getName().equals(productName))
				return i;
		}
		return null;
	}
	
	
	
	public Product findProduct(int productID)
	{
		for(Product i : globalProductList)
		{
			if(i.getItemID()==productID)
				return i;
		}
		return null;
	}
	
	public int findProductIndex(int productID)
	{
		int indexCount = 0;
		for(Product i : globalProductList)
		{
			if(i.getItemID()==productID)
				return indexCount;
			indexCount++;
		}
		return 0;
	}
	
	
	public Customer findCustomer(String customerID)
	{
		for(Customer i : globalCustomerList)
		{
			if(i.getUserID().equals(customerID))
				return i;
		}
		return new Customer("No customer", "No customer");
	}
	
	public void addCategory(String categoryName)
	{
		globalCategoryList.add(new ProductCategory(globalCategoryList.size(),categoryName));
	}

	public void updateAllCategories()
	{
		for(ProductCategory c : globalCategoryList)
		{
			c.clearList();
			for(Product i : globalProductList)
			{
				if(i.getCategory().equals(c.getProductCategory()))
					c.addProduct(i);
			}
		}
	}

	
	public void addProduct(Product newProduct)
	{
		globalProductList.add(newProduct);
		updateAllCategories();
	}
	

	public void deleteProduct(Product newProduct)
	{
		globalProductList.remove(findProductIndex(newProduct.getItemID()));
		updateAllCategories();
	}
	
	public void editProductStock(Product newProduct, int newStock)
	{
		globalProductList.get(findProductIndex(newProduct.getItemID())).setUnitsInStock(newStock);
	}
	public void createAccount(String userID, String password, String firstName, String lastName, String email)
	{
		globalCustomerList.add(new Customer(userID,password,firstName,lastName,email,globalCustomerList.size()));
	}

	public ArrayList<Order> viewAllPurchaseOrders(ArrayList<Customer> customerList)
	{
		ArrayList<Order> globalOrders = new ArrayList<>();
		for(Customer i : globalCustomerList)
		{
			globalOrders.addAll(i.getPurchaseOrders());
		}
		return globalOrders;
	}
	
	public ArrayList<Order> viewCustomersFulfilledOrders(Customer customer)
	{
		ArrayList<Order> customerOrders = new ArrayList<>();
		for(Order i : customer.getPurchaseOrders())
		{
			if(i.getStatus().equals("Fulfilled"))
				customerOrders.add(i);
		}
		return customerOrders;
	}
	public ArrayList<Order> viewCustomersUnfulfilledOrders(Customer customer)
	{
		ArrayList<Order> customerOrders = new ArrayList<>();
		for(Order i : customer.getPurchaseOrders())
		{
			if(i.getStatus().equals("Not Fulfilled"))
				customerOrders.add(i);
		}
		return customerOrders;
	}
	
	public String orderListToString(ArrayList<Order> orderList)
	{
		StringBuilder infoString = new StringBuilder();
		for(Order i : orderList)
		{
			infoString.append(i.viewOrder());
			infoString.append("\n");
		}
		return infoString.toString();
	}
	
	public void fulfillPurchase(Order order)
	{
		order.setStatus("Fulfilled");
	}
	
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("Welcome to our EShopping App");
		Admin newAdmin = new Admin("0", "0");
		
	}
	
}
