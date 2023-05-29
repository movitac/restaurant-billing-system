public class Drink implements Menu{
	
	String [] drinkid = {"D001","D002","D003","D004","D005"};
	String [] drinkname = {"Milo Ais","Susu Ais","Sirap Ais","Teh O Panas","Kopi O Panas"};
	String [] drinkprice = {"3.50","4.00","2.00","1.50","2.50"};
	
	public String[] getID() {
		return drinkid;
	}
	public String[] getName() {
		return drinkname;
	}
	public String[] getPrice() {
		return drinkprice;
	}
	
}