/**
 *  PizzaGUI - a supplemental class for hw6 - Pizza
 *  Defines a GUI for the Pizza class.
 */

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PizzaGUI extends JFrame implements ActionListener {

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

	public static void main(String[] args) throws IOException {
		PizzaGUI ordersystem = new PizzaGUI();

		ordersystem.setVisible(true);

	}

	/**
	 * Cosntructor method - lays out the GUI components.
	 */
	public PizzaGUI()  {

		super("Pizza interface");

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("./pic3.jpg"));
		} catch (IOException e) {
			System.out.println("Cannot locate image file pic3.jpg. It should"
					+ " be placed in the project folder.");
		}
		
		JLabel picLabel; 
		if (myPicture != null)
			picLabel =new JLabel(new ImageIcon(myPicture));
		else {
			picLabel = new JLabel("Can't find image file. ");
			picLabel.setFont(picLabel.getFont().deriveFont(Font.ITALIC));
			
		}

		JPanel title = new JPanel();


		// title.setLayout(new BorderLayout());
		title.add(new JLabel("Welcome! Make your pizza order below: "));

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
		sizepic.add(picLabel);

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(ordermaker);
	}





	/**public void actionPerformed(ActionEvent e) 
	 * Process events from buttons
	 */
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

			String pizzaDescription = "";
			pizzaDescription += "Created a pizza of size " + newpizza.getSize() + 
					" with " + newpizza.numToppings() + " toppings: " ;

			for (int i = 0; i<=newpizza.numToppings()-1; i++){
				pizzaDescription += "\n " + (i+1)+ ". " + newpizza.getToppings()[i];
			}

			DecimalFormat df = new DecimalFormat("0.00");	
			double price = newpizza.calcPrice();
			pizzaDescription += "\n " +"This pizza costs $"+ df.format (price);
			pizzaDescription += "\n " +"Pizza readiness status is  "+ newpizza.getStatus();



			// show a joptionpane dialog using showMessageDialog
			JOptionPane.showMessageDialog( this, 
					pizzaDescription
					);


		}

	}




}
