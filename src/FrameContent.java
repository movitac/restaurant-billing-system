import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class FrameContent extends JFrame implements ActionListener{

	//Initialize Panel
	JPanel bigPanel = new JPanel(new GridLayout(1, 2));
	JPanel rightPanel = new JPanel(new GridLayout(7, 1));
	JPanel rightPanelRow1 = new JPanel(new GridLayout(1,1)); //Panel for label food
	JPanel rightPanelRow2 = new JPanel(new GridLayout(1,2)); //Panel for combo box and button
	JPanel rightPanelRow3 = new JPanel(new GridLayout(1,1)); //Panel for label drink
	JPanel rightPanelRow4 = new JPanel(new GridLayout(1,2)); //Panel for combo box and button
	JPanel rightPanelRow5 = new JPanel(new GridLayout(1,1)); //Panel for label discount
	JPanel rightPanelRow6 = new JPanel(new GridLayout(1,2)); //Panel for combo box
	JPanel rightPanelRow7 = new JPanel(new GridLayout(1,3)); //Panel for button
	JPanel summaryPanel = new JPanel(new GridLayout(4,1));

	//Initialize Table
	DefaultTableModel tableModel = new DefaultTableModel();
	String[] column = {"ID","Item Name","Price"};
	JTable table = new JTable();
	JTableHeader header = table.getTableHeader();
	JScrollPane pane = new JScrollPane(table); //Scroll bar for table

	//Initialize for add food section
	String[] foodid = new String[0];
	String[] foodname = new String[0];
	String[] foodprice = new String[0];
	JLabel foodLabel = new JLabel("Food");
	@SuppressWarnings("rawtypes")
	JComboBox foodCB = new JComboBox();
	JButton foodbtn = new JButton("+");

	//Initialize for add drink section
	String[] drinkid = new String[0];
	String[] drinkname = new String[0];
	String[] drinkprice = new String[0];
	JLabel drinkLabel = new JLabel("Drink");
	@SuppressWarnings("rawtypes")
	JComboBox drinkCB = new JComboBox();
	JButton drinkbtn = new JButton("+");

	//Initialize for add discount section
	String [] discount = new String[0];
	JLabel discountLabel = new JLabel("Discount");
	@SuppressWarnings("rawtypes")
	JComboBox discountCB = new JComboBox();
	JButton discountbtn = new JButton("+");

	//Initialize for remove, clear, calculate button section
	JButton removebtn = new JButton("REMOVE");
	JButton clearbtn = new JButton("CLEAR ALL");
	JButton calculatebtn = new JButton("CALCULATE");

	//Initialize for summary section
	JLabel total_before_discount_label = new JLabel("Total amount before discount : ");
	JLabel discount_coupon_applied_label = new JLabel("Discount coupon applied          : ");
	JLabel discount_amount_label = new JLabel("Discount amount                        : ");
	JLabel total_after_discount_label = new JLabel("Total amount after discount    : ");

	//constructor
	FrameContent(){
		super("Restaurant XXX Order System");
		frameProperties();
		tableProperties();

		this.add(bigPanel);
		this.add(summaryPanel, BorderLayout.SOUTH);

		bigPanel.add(pane);
		bigPanel.add(rightPanel);

		rightPanel.add(rightPanelRow1);
		rightPanel.add(rightPanelRow2);
		rightPanel.add(rightPanelRow3);
		rightPanel.add(rightPanelRow4);
		rightPanel.add(rightPanelRow5);
		rightPanel.add(rightPanelRow6);
		rightPanel.add(rightPanelRow7);

		rightPanelRow1.add(foodLabel);		
		rightPanelRow2.add(foodCB);
		rightPanelRow2.add(foodbtn);		

		rightPanelRow3.add(drinkLabel);		
		rightPanelRow4.add(drinkCB);
		rightPanelRow4.add(drinkbtn);	

		rightPanelRow5.add(discountLabel);		
		rightPanelRow6.add(discountCB);
		rightPanelRow6.add(discountbtn);

		rightPanelRow7.add(removebtn);
		rightPanelRow7.add(clearbtn);
		rightPanelRow7.add(calculatebtn);

		summaryPanel.add(total_before_discount_label);
		summaryPanel.add(discount_coupon_applied_label);
		summaryPanel.add(discount_amount_label);
		summaryPanel.add(total_after_discount_label);

		//Attach action listener to combo box
		foodCB.addActionListener(this);
		drinkCB.addActionListener(this);
		discountCB.addActionListener(this);

		//Attack action listener to buttons
		foodbtn.addActionListener(this);
		drinkbtn.addActionListener(this);
		discountbtn.addActionListener(this);
		removebtn.addActionListener(this);
		clearbtn.addActionListener(this);
		calculatebtn.addActionListener(this);

	}

	//method to set properties of the frame
	public void frameProperties() {
		setSize(1030, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	//method to set properties of the table
	public void tableProperties() {
		header.setBackground(Color.yellow);		
		table.setBackground(Color.white);
		pane.getViewport().setBackground(new Color(176, 196, 222));
		tableModel.setColumnIdentifiers(column);
		table.setModel(tableModel);
	}

	//method to assign/set value to food combo box
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setFoodComboBox() {
		foodCB.setModel(new DefaultComboBoxModel(foodname));
	}

	//method to set food values from "Order method" to "this(FrameContent) method"
	public void setFoodValues(String[] foodid,String [] foodname, String [] foodprice) {
		this.foodid = foodid;
		this.foodname = foodname;
		this.foodprice = foodprice;
	}

	//method to assign/set value to drink combo box
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setDrinkComboBox() {
		drinkCB.setModel(new DefaultComboBoxModel(drinkname));
	}

	//method to set drink values from "Order method" to "this(FrameContent) method"
	public void setDrinkValues(String[] drinkid,String [] drinkname, String [] drinkprice) {
		this.drinkid = drinkid;
		this.drinkname = drinkname;
		this.drinkprice = drinkprice;
	}

	//method to assign/set value to discount combo box
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setDiscountComboBox(String [] discount) {
		this.discount = discount;
		discountCB.setModel(new DefaultComboBoxModel(this.discount));
	}

	Object [] rowfood;
	Object [] rowdrink;

	//add value that is selected from food combo box to the table 
	public void addFoodRowTable() {
		rowfood = new Object[foodid.length];
		for(int i=0;i<foodid.length;i++) {	
			if(foodCB.getSelectedItem() == foodname[i]) {
				rowfood[0] = foodid[i];
				rowfood[1] = foodname[i];
				rowfood[2] = foodprice[i];
				tableModel.addRow(rowfood);
			}
		}
	}

	//add value that is selected from drink combo box to the table 
	public void addDrinkRowTable() {
		rowdrink = new Object[drinkid.length];
		for(int i=0;i<drinkid.length;i++) {	
			if(drinkCB.getSelectedItem() == drinkname[i]) {
				rowdrink[0] = drinkid[i];
				rowdrink[1] = drinkname[i];
				rowdrink[2] = drinkprice[i];
				tableModel.addRow(rowdrink);
			}
		}				
	}

	//method to remove selected row from the table
	public void removeRowTable() {
			JFrame popup = new JFrame();
		//Error handling if the user did not select any row
		try {
			int i=table.getSelectedRow();
			tableModel.removeRow(i);
			JOptionPane.showMessageDialog(popup, "Successfully remove item");
		} catch(Exception exc) {
			JOptionPane.showMessageDialog(popup, "Please select an item to remove");
		}

	}

	Double appliedDiscount = 0.0;
	//method to assign discount values to variable, and change label using set text
	public void setDiscount() {
		
		for(int i=0;i < discount.length ; i++) {
			
			//Error handling if number conversion for applieddiscount variable is mismatch
			try {
				if(discountCB.getSelectedItem() == discount[i]) {
					appliedDiscount = Double.parseDouble(discount[i].replaceAll("[^\\d]", ""))/100; //String to Double format, and divide the double with 100
					discount_coupon_applied_label.setText("Discount coupon applied          : "+ discount[i]);
				}
			} catch (NumberFormatException nfe) {
				appliedDiscount = 0.0;
				discount_coupon_applied_label.setText("Discount coupon applied          : "+ discount[i]);
			}

		}
		
	}

	//method to change label for summary section
	public void setSummaryLabel(Double total_before, Double total_discount, Double total_after) {
		total_before_discount_label.setText("Total amount before discount : RM "+ String.format("%.2f", total_before));
		discount_amount_label.setText("Discount amount                        : RM "+ String.format("%.2f", total_discount));
		total_after_discount_label.setText("Total amount after discount    : RM "+ String.format("%.2f", total_after));
	}

	//method to get data from the table and return it as a object
	public Object GetData(JTable table, int row_index, int col_index) {
		return table.getModel().getValueAt(row_index, col_index);
	}

	//method to calculate the bill
	public void calculateSummary() {

		Object priceObj[] = new Object[table.getRowCount()];
		Double price[] = new Double[table.getRowCount()];
		Double total_before = 0.0;
		Double total_discount = 0.0;
		Double total_after = 0.0;

		for(int i=0; i<table.getRowCount(); i++) {
			priceObj[i] = GetData(table, i,2); //store price object that has been retrieved from GetData method
			price[i] = Double.valueOf((String) priceObj[i]); //Convert object to string, and then store it inside price
		}

		//calculations for the summary
		for(int i=0; i<table.getRowCount(); i++) {
			total_before = total_before + price[i]; //calculate all the items inside the table
		}
		
		total_discount = total_before*appliedDiscount; //Total Discount amount = total before discount * discount
		total_after = total_before-total_discount; //Total after discount = total before discount - total discount amount

		setSummaryLabel(total_before,total_discount,total_after);

	}

	//method to clear table
	public void clearTable() {
		tableModel.setRowCount(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == foodbtn) {
			addFoodRowTable();
		} else if(e.getSource() == drinkbtn) {
			addDrinkRowTable();
		} else if(e.getSource() == discountbtn) {
			setDiscount();
		} else if(e.getSource() == removebtn) {
			removeRowTable();
		}  else if(e.getSource() == clearbtn) {
			clearTable();
		} else if(e.getSource() == calculatebtn) {
			calculateSummary();
		}


	}



}