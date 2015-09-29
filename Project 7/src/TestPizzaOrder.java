/**
 * Main method creating a PizzaOrder, initializing it, printing it, and updating its status
 * @author TORRES_SANT
 * Last modified 12/3/2014 22:02
 *
 */
public class TestPizzaOrder {

	public static void main(String[] args) {
		final int notStarted = 0;
		final int inProgress = 1;
		final int ready = 2;
		// new 3 element array of pizzas
		Pizza[] test = new Pizza[3];
		// create array of strings for toppings
		String[] largeToppings = new String[] {"Salami", "Pepperoni"};
		String[] smallToppings = new String[] {"Bacon", "Pepperoni", "Mushrooms", "Pineapple"};
		// initialize pizza array with pizzas
		test[0] = new Pizza('L', largeToppings);
		test[1] = new Pizza('S', smallToppings);
		test[2] = new Pizza('M', null);
		// create new pizza order with pizza array and customer name
		PizzaOrder PeteStoneOrder = new PizzaOrder("Peter Stone", test);
		System.out.println(PeteStoneOrder);
		//update status
		PeteStoneOrder.getOrderedPizzas()[0].setStatus(inProgress);
		PeteStoneOrder.getOrderedPizzas()[2].setStatus(ready);
		System.out.println(PeteStoneOrder);
		//check if pizzas are all ready
		System.out.println(PeteStoneOrder.isReady());
		//update status
		PeteStoneOrder.getOrderedPizzas()[0].setStatus(ready);
		PeteStoneOrder.getOrderedPizzas()[1].setStatus(ready);
		System.out.println(PeteStoneOrder);
		//check if pizzas are all ready
		System.out.println(PeteStoneOrder.isReady());
	}

}
