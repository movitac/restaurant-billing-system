public class Order {

		FrameContent fc = new FrameContent();
		Food fd = new Food();
		Drink dk = new Drink();
		Discount dc = new Discount();
		String [] foodid;
		String [] foodname;
		String [] foodprice;
		
		String [] drinkid;
		String [] drinkname;
		String [] drinkprice;
		
		String [] discount;
	Order(){
		setFoodVariables();
		setDrinkVariables();
		setDiscount();
		
		fc.setFoodValues(foodid,foodname,foodprice);
		fc.setDrinkValues(drinkid, drinkname, drinkprice);
		fc.setFoodComboBox();
		fc.setDrinkComboBox();
		fc.setDiscountComboBox(discount);	
	}

	public void setFoodVariables() {
		foodid = fd.getID();
		foodname = fd.getName();
		foodprice = fd.getPrice();
	}
	
	public void setDrinkVariables() {
		drinkid = dk.getID();
		drinkname = dk.getName();
		drinkprice = dk.getPrice();
	}
	
	public void setDiscount() {
		discount = dc.getDiscount();
	}

}