public class Food implements Menu{
	
	String [] foodid = {"F001","F002","F003","F004","F005"};
	String [] foodname = {"Nasi Lemak","Nasi Kerabu","Nasi Dagang","Nasi Goreng Ayam","Nasi Ayam Penyet"};
	String [] foodprice = {"4.00","5.00","7.00","8.00","8.00"};
	
	public String[] getID() {
		return foodid;
	}
	public String[] getName() {
		return foodname;
	}
	public String[] getPrice() {
		return foodprice;
	}
	
}