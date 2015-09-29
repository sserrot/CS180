/**
 * Defining class PizzaOrder which stores an array of pizzas and customer name and its methods
 * @author TORRES_SANT
 * Last modified 12/3/2014 22:03
 *
 */
import java.text.NumberFormat;
public class PizzaOrder {
	private String customer;
	private Pizza[] orderedPizzas;
	public PizzaOrder(String name, Pizza[] orders) {
		this.setCustomer(name);
		this.setOrderedPizzas(orders);

	}
	/**
	 * settings and getters for private variables
	 * @return
	 */
	public Pizza[] getOrderedPizzas() {
		return orderedPizzas;
	}
	public void setOrderedPizzas(Pizza[] orderedPizzas) {
		this.orderedPizzas = orderedPizzas;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	/**
	 * totalPrice checks the price of each Pizza in the array and adds them up
	 * @return double which is the sum of all the prices
	 */
	public double totalPrice() {
		double price = 0;
		int numOrderedPizzas = this.orderedPizzas.length;
		for (int i = 0; i < numOrderedPizzas; i++) {
			price =  price + this.orderedPizzas[i].calcPrice();
		}
		return price;

	}
	/**
	 * 
	 * @return true if all the pizzas in array orderedPizzas are ready, else return false
	 */
	public boolean isReady() {
		boolean ready = false;
		int numOrderedPizzas = this.orderedPizzas.length;
		int countReady = 0;
		for (int i = 0; i < numOrderedPizzas; i++) {
			if (this.orderedPizzas[i].getStatus() == Pizza.READY)
				countReady++;
		}
		if (countReady == numOrderedPizzas)
			ready = true;
		return ready;

	}
	/**
	 * toString formats PizzaOrder to print the customer name and then
	 *  a list of the orders with the size of the pizza, number of toppings, price, and status
	 *  and the order total	price
	 */
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();  // taken from https://docs.oracle.com/javase/7/docs/api/java/text/DecimalFormat.html
		int numOrderedPizzas = this.orderedPizzas.length;
		String name = "Order for customer " + this.getCustomer() + ":";
		String all = name + "\n";
		String size;
		for (int i = 0; i < numOrderedPizzas; i++) {
			size = " " + this.orderedPizzas[i].getSize();
			// if statement inside loop for full words of Small, Medium, and Large
			if (this.orderedPizzas[i].getSize() == 'S')
				size = size + "mall ";
			else if (this.orderedPizzas[i].getSize() == 'M')
				size = size + "edium ";
			else if (this.orderedPizzas[i].getSize() == 'L')
				size = size + "arge ";
			all = all +  size + "pizza, " + this.orderedPizzas[i].numToppings() + " toppings, " + nf.format(this.orderedPizzas[i].calcPrice())
			+ " -- " + this.orderedPizzas[i].statusPhrase() + "\n";
		}
		all = all + "Order total: " + nf.format(this.totalPrice());
		return all;
	}
}


