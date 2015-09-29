/* A graphical user interface for a pizza ordering application.
 * Uses classes Pizza and PizzaOrder defined in homework 7 
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class OrderGUI extends JFrame implements ActionListener{
		
		public static DecimalFormat df = new DecimalFormat("0.00");	
	
		// variables storing order data 
		private ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
		private String customer;

		private JTextField customerTF; 
		
		// to display a scrollable list of Library items
		private JList lstItems;
		private JScrollPane scrlPane;
		
		private JButton addPizzaB = new JButton("Add Pizza");
		private JButton done = new JButton("Done"); 

		private JTextField tfCustomer;  
		private JTextField tfOrderTime; 
		
		// dialog box
		private PizzaGUI pizzaGUI = new  PizzaGUI(this);

		
		
		/** Constructor */
		public OrderGUI (String customer){
			super("Neighborhood Pizza");
			this.customer = customer;
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			// Define instance vars, simultaneously initializing them
			JLabel label = new JLabel("Enter Pizza Order for Customer");
	 		customerTF = new JTextField(customer);
			customerTF.setEditable(false);
					
			lstItems = new JList();			
			scrlPane = new JScrollPane(lstItems);
			scrlPane.setHorizontalScrollBarPolicy
			(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrlPane.setVerticalScrollBarPolicy
			(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			

			// set a few properties of the window
		   	this.setSize(370, 275);
		  // 	this.setResizable(false);
		   	
 		   	
			//TO add the components to the container pane of the frame
			// 1. get the content pane of the JFrame
			Container contentPane = this.getContentPane();
			
			// 2. tell the pane how to arrange components: 
			// using FlowLayout, componentes arranged around the center
			// by default
		   	contentPane.setLayout(new FlowLayout());
			
		   	// add checkboxes  	
		   	contentPane.add(label);
 
			contentPane.add(customerTF);	
			contentPane.add(scrlPane);

			// add other components
		   	contentPane.add(addPizzaB);

			addPizzaB.addActionListener(this);
			done.addActionListener(this);

			contentPane.add(done);
		   		   	
			this.setVisible(true);
			
		}
		


		/**	public void actionPerformed(ActionEvent e) 
		    React to various button presses	*/
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == addPizzaB){// process checkout					
				pizzaGUI.setVisible(true);
				lstItems.repaint();
			}			
			else if (e.getSource() == done){// process order or exit
				if (this.pizzas.size()>0){
				JOptionPane.showMessageDialog(this,"Thank you for your order");
				this.setVisible(false);
				new OrderProgressWindow (new PizzaOrder(this.customer,
						this.pizzas.toArray(new Pizza[this.pizzas.size()] )));
				}
				else 
					System.exit(0);
			}
		}
		
		/**	public void addPizzaToList (Pizza p)
		 * Add a pizza to the arraylist and also update the
		 * listbox with the new description */
		
		public void addPizzaToList (Pizza newpizza){

		
			this.pizzas.add(newpizza);
			//create an array of pizza descriptions for the listbox
			String [] pizzaLstStr =  new String[this.pizzas.size()];
			for (int i=0; i<this.pizzas.size();i++){
				Pizza p = this.pizzas.get(i);
				pizzaLstStr[i]= "Pizza "+ p.getSize() + " "
					+ p.numToppings() + " topping -- " + p.statusPhrase() + 
					"$" + df.format (p.calcPrice());
			}
				
			this.lstItems.setListData(pizzaLstStr);	
		}
		 
		public static void main(String[] args) {
			// Create an instance of the OrderGUI class
			 new OrderGUI (JOptionPane.showInputDialog("Please " +
			 		"enter the customer name"));
			
		}
 
		
/** ****************************************************************
	 		inner class PizzaGUI
*******************************************************************/
 
class PizzaGUI extends JDialog implements ActionListener {

	private OrderGUI orderWindow; // the parent window
	
	private JRadioButton small = new JRadioButton("Small", true);
	private JRadioButton medium = new JRadioButton("Medium", false);
	private JRadioButton large = new JRadioButton("Large", false);
	private JButton done = new JButton("Done");

	private JCheckBox[] tps= { new JCheckBox("Bacon"), new JCheckBox("Pepperoni"),
			 new JCheckBox("Mushrooms"), new JCheckBox("Olives"),
			 new  JCheckBox("Tomatos"), new JCheckBox("Peppers")
	};




	public static final int WIDTH = 500;
	public static final int HEIGHT = 400;

	public PizzaGUI( OrderGUI parentWindow)   {

		//super("Pizza interface");

		this.orderWindow = parentWindow;
		
		JPanel title = new JPanel();
		 
		
		// title.setLayout(new BorderLayout());
		JLabel titleLabel = new JLabel("Choose pizza toppings and size");
		titleLabel.setFont(new Font("Arial",  Font.BOLD, 15));
		title.add(titleLabel);

		JPanel toppinglist = new JPanel();
		toppinglist.setLayout(new GridLayout(tps.length+1 , 1));
		toppinglist.add(new JLabel("Choose Toppings: "));
		for (int i = 0; i< tps.length; i++)
			toppinglist.add(tps[i]);
 
		
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(5, 1));
		buttons.add(new JLabel("Size "));
		buttons.add(small);
		buttons.add(medium);
		buttons.add(large);
		// buttons.add(picLabel);

		JPanel sizepic = new JPanel();
		sizepic.setLayout(new GridLayout(2, 1));
		sizepic.add(buttons);

		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("./pic3.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			sizepic.add(picLabel);
		} catch (IOException e) {
			System.out.println("Can't locate the image file, continuing without the image.");
		}
	

		
		JPanel all = new JPanel();
		all.setLayout(new GridLayout(1, 2, 20, 0));
		all.add(toppinglist);
		all.add(sizepic);

		// done.setPreferredSize(new Dimension(10, 40));

		JPanel donebutton = new JPanel();
		// donebutton.setLayout(new BorderLayout());
		donebutton.add(done);

		JPanel ordermaker = new JPanel();	
		ordermaker.setLayout(new BorderLayout(20, 20));
		ordermaker.add(title, BorderLayout.NORTH);
		ordermaker.add(all, BorderLayout.CENTER);
		ordermaker.add(donebutton, BorderLayout.SOUTH);


		ButtonGroup bg = new ButtonGroup();
		bg.add(small);
		bg.add(medium);
		bg.add(large);
 
		done.addActionListener(this);

 		setSize(WIDTH, HEIGHT);
		this.setLocation(50, 50);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.add(ordermaker);
	}



	/**	public void actionPerformed(ActionEvent e) 
    React to various button presses	*/
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==done){
			
			// collect selected toppings
			ArrayList<String> top =new ArrayList<String>();
			top.clear();
			for (int i = 0; i< tps.length; i++)
				if (tps[i].isSelected())
					top.add(tps[i].getText());
 			String [] top2 = top.toArray(new String[top.size()]);

			Pizza newpizza= new Pizza();
			newpizza.setToppings(top2);
			char size1=' ';
			if(small.isSelected()){
				size1= 'S';
			}
			if(medium.isSelected()){
				size1 ='M';
			}
			if(large.isSelected()){
				size1='L';
			}

			newpizza.setSize(size1);

			String pizzaDescription = newpizza.toString();
			/*
			pizzaDescription += "Created a pizza of size " + newpizza.getSize() + 
					" with " + newpizza.numToppings() + " toppings: " ;

			for (int i = 0; i<=newpizza.numToppings()-1; i++){
				pizzaDescription += "\n " + (i+1)+ ". " + newpizza.getToppings()[i];
			}

			DecimalFormat df = new DecimalFormat("0.00");	
			double price = newpizza.calcPrice();
			pizzaDescription += "\n " +"This pizza costs $"+ df.format (price);
			pizzaDescription += "\n " +"Pizza readiness status is  "+ newpizza.getStatus();
			 */


			// show a joptionpane dialog using showMessageDialog
			JOptionPane.showMessageDialog( this, 
					pizzaDescription
					);

			this.orderWindow.addPizzaToList(newpizza);
			this.setVisible(false);

		}	
	}	
}



/** ****************************************************************
	 		inner class OrderProgressWindow
*******************************************************************/

/**
	A class to update and monitor pizza order status
 */
class OrderProgressWindow extends JFrame implements ActionListener{
	
	// variable holding a Problem object
	private  PizzaOrder  order; 
	private JTextField customerTF; 
	
	// to display a scrollable list of Library items
	private JList lstItems;
	private JScrollPane scrlPane;
	
	private JButton UpgradeBtn = new JButton("Upgrade Status");
	private JButton exit = new JButton("Exit"); 

	private JTextField tfCustomer;  
	private JTextField tfOrderTime;
	private JLabel bottomLabel; 
		
	/** Constructor */
	public   OrderProgressWindow(PizzaOrder order) {
	
		super(order.getCustomer() + "'s Order");
		this.order = order;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// Define instance vars, simultaneously initializing them
		JLabel label = new JLabel("Pizzas "); 
 		
		lstItems = new JList();			
		scrlPane = new JScrollPane(lstItems);
		scrlPane.setHorizontalScrollBarPolicy
		(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrlPane.setVerticalScrollBarPolicy
		(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.setPizzasList();
		this.lstItems.setSelectedIndex(0);
		// set a few properties of the window
	   	this.setSize(300, 275);
	   	this.setResizable(false);
	   	
		   	
		//TO add the components to the container pane of the frame
		// 1. get the content pane of the JFrame
		Container contentPane = this.getContentPane();
		
		// 2. tell the pane how to arrange components: 
		// using FlowLayout, componentes arranged around the center
		// by default
	   	contentPane.setLayout(new FlowLayout());
	   	contentPane.add(new JLabel("Update pizza order"));

		contentPane.add(scrlPane);
		this.bottomLabel = new JLabel ("               Order total: $" +
				OrderGUI.df.format(this.order.totalPrice()) +"                  ");
		contentPane.add(bottomLabel);
		// add other components
	   	contentPane.add(UpgradeBtn);	   	
		UpgradeBtn.addActionListener(this);
		exit.addActionListener(this);

		contentPane.add(exit);
	   		   	
		this.setVisible(true);
		
	}
	
	/**	 public  void setPizzasList ( )
	 * Set the content of the list box according to the order
	 */
	
	public  void setPizzasList ( ){
		String [] pizzaLstStr =  new String[this.order.getOrderedPizzas().length];
		for (int i=0; i<this.order.getOrderedPizzas().length;i++){
			Pizza p = this.order.getOrderedPizzas()[i];
			pizzaLstStr[i]= "Pizza "+ p.getSize() + " "
				+ p.numToppings() + " topping -- " + p.statusPhrase() +
				"  $" + OrderGUI.df.format (p.calcPrice()) + "   ";			
		}			
		this.lstItems.setListData(pizzaLstStr);	
	}


	/**	public void actionPerformed(ActionEvent e) 
	    React to various button presses	*/
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == UpgradeBtn){// process upgrade of the selected pizza
			int selectedIndex = this.lstItems.getSelectedIndex(); // index of selected item
			// update the status of the selected pizza 
			Pizza p = this.order.getOrderedPizzas()[selectedIndex];
			int status = p.getStatus();
			if (status != Pizza.READY)
				p.setStatus(status+1);
			this.setPizzasList(); 
			this.lstItems.setSelectedIndex(selectedIndex);
			this.lstItems.repaint();
			
			// Check and report if order is ready
			if (order.isReady() && this.bottomLabel.getForeground()!=Color.BLUE){				
				JOptionPane.showMessageDialog(this, this.order.getCustomer() + "'s order is ready for pick-up");
				this.bottomLabel.setText( bottomLabel.getText().trim() + "  READY   " );
				this.bottomLabel.setForeground(Color.BLUE);
			}
		}			
		else if (e.getSource() == exit){// process exit
			System.exit(0);
		}
	}


}


}