package controller.logicController;

import java.sql.SQLException;

import DAL.ItemDAL;
import DAL.MySQLAccess;
import model.Item;

public class EditItemsControl {
	private ItemDAL itemDAL;
	private Item selectedItem;
	
	public EditItemsControl(MySQLAccess theDBConnection) {
		this.itemDAL = new ItemDAL(theDBConnection);
	}
	
	public void SetSelectedItem(int itemId) {
		try {
			selectedItem = itemDAL.GetItemById(itemId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Item GetSelectedItem() {
		return selectedItem;
	}
	
	public String UpdateItem(String itemName, String itemDescription, int itemType, Boolean isQuestItem, Boolean isImplicitItem) throws SQLException {
		if (itemName == null || itemName.trim().length() == 0) {
			return "The Item Name cannot be empty";
		}
		else if (itemDescription == null || itemDescription.trim().length() == 0) {
			return "The Item Description cannot be empty";
		}
		
		if (itemDAL.UpdateItem(selectedItem, new Item(selectedItem.GetItemId(), itemName, itemDescription, itemType, isQuestItem, isImplicitItem))) {
			return null;
		}
		else {
			return "There was a problem updating the Item";
		}
	}
}