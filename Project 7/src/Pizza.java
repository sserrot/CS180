/**
 * @author Santiago Torres
 * Program: Represent a pizza for an order from a pizza shop
 * Last modified: 12/3/2014 22:04
 */
public class Pizza {
	private char size; // s, m, l
	private String[] toppings; 
	private int status; // 0 not started, 1 in progress, 2 ready
	/** Initialize public static variables for Status and Pricing
	 * 
	 */
	//STATUS
	public final static int NOT_STARTED = 0;
	public final static int IN_PROGRESS = 1;
	public final static int READY = 2;
	//PRICE
	public final static double SMALL_BASE_PRICE = 8;
	public final static double SMALL_TOPPINGS_PRICE = 1;
	public final static double MEDIUM_BASE_PRICE = 9;
	public final static double MEDIUM_TOPPINGS_PRICE = 1.5;
	public final static double LARGE_BASE_PRICE = 10;
	public final static double LARGE_TOPPINGS_PRICE = 2;
			
	
	/**
	 * No argument constructor making a medium pizza with no toppings that has not been started
	 */
	public Pizza() {
		this.setSize('M');
		this.setStatus(NOT_STARTED);
		this.setToppings(null);
	}
	/**
	 * Two argument constructor taking Char pizza size and an array of toppings
	 */
	public Pizza(char size , String[] toppings) {
		this.setSize(size);
		this.setStatus(NOT_STARTED);
		this.setToppings(toppings);
	}
	/**
	 * method statusPhrase, no arguments and returns a string saying the status of the pizza
	 */
	public String statusPhrase() {
		String status = "Not started";
		switch (this.getStatus()) {
		case NOT_STARTED :
			status = "Not started.";
			break;
		case IN_PROGRESS :
			status = "In progess.";
			break;
		case READY :
			status = "Ready.";
			break;
		}
		return status;
		
	}
	/**
	 * method toString() that displays pizza size, toppings and status 
	 */
	public String toString() {
		String size = "Pizza size " + this.getSize() + ".";
		String toppings;
		if (this.numToppings() == 0) {
			toppings = "No toppings.";
		}
		else
		toppings = "Toppings: ";
		String status = this.statusPhrase();
		String all = "********************\n" + size + " " + toppings;
		for (int i = 0; i<=this.numToppings()-1; i++){
			all = (all + "\n" + (i+1)+ ". " + this.getToppings()[i]);
		}
		all = all + "\n" + status + "\n******************** ";
		return all;
	}
	/** 
	 * 
	 * @param size is a char that sets the size of the pizza to small medium or large. if size is not recognized, set to medium. 
	 * @return - boolean value, true if the user inputs correct size, false if not (sets size to medium).
	 * 
	 */
	public boolean setSize(char size) {
		boolean valid;
		if (size == 's' || size == 'S' || size == 'm' || size == 'M' || size =='l' || size =='L') {
			this.size = size;
			valid = true;
		}
		else{
			System.out.println("Size value not recognized, setting size to 'M' ");
			this.size = 'M';
			valid = false;
		}
		return valid;
	}
/**
 * 
 * @param - takes int status and checks if status btwn 0 - 2 then sets status 0 = not ready 1 = getting ready or 2 = ready. if status is not recognized, set it to 0
 * takes integer status
 * @return - boolean value, true if user inputs correct status, false if not (sets status to 0).
 */
	public boolean setStatus(int status) {
		boolean valid;
		if ( status >= NOT_STARTED && status <= READY) {	
			valid = true;
			this.status = status;
		}
		else{
			System.out.println("The status value is not recognized. Setting status to 0");
			valid = false;
			this.status = NOT_STARTED;
		}
		return valid;
	}

	// set toppings taking a string array of toppings
	
	public void setToppings(String[] toppings){
		this.toppings = toppings;
	}

	//Accessors

	public char getSize() {
		return this.size;
	}
	public String[] getToppings() {
		return this.toppings;
	}
	public int getStatus() {
		return this.status;
	}
	/**
	 * 
	 * @return integer which is the length of all the toppings set. if it is null, then return 0
	 */
	public int numToppings() {
		int toppingsLength;
		if (toppings != null)
			toppingsLength = toppings.length;
		else
			toppingsLength = 0;
		return toppingsLength;
	}
	/**
	 * 
	 * @return price based on size and toppings, returns a double
	 * 
	 */

	public double calcPrice() {
		double price =0;
		if (this.size == 'S' || this.size == 's') {
			price = SMALL_BASE_PRICE + this.numToppings()*SMALL_TOPPINGS_PRICE;
		}
		if (this.size == 'M' || this.size == 'm') {
			price = MEDIUM_BASE_PRICE + this.numToppings()*MEDIUM_TOPPINGS_PRICE;
		}
		if (this.size == 'L' || this.size == 'l') {
			price = LARGE_BASE_PRICE + this.numToppings()*LARGE_TOPPINGS_PRICE;
		}
		return price;
	}
/** 
 * testing new methods
 * @param args
 */
	public static void main(String[] args) {
		 Pizza testNoConstructors = new Pizza();
		 System.out.println(testNoConstructors);
		 String[] tops = new String[] {"Chicken", "Mushrooms", "Feta Cheese"}; 
		 Pizza testConstructor = new Pizza ('M' , tops);
		 testConstructor.setStatus(READY);
		 System.out.println(testConstructor);
	}


}
