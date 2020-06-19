/**
 * 
 */
package model;



import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Ashely Palmer
 * @date 6/11/2020
 *
 */
public class Item {
	private IntegerProperty itemId;
	private StringProperty itemName;
	private StringProperty itemDescription;
	private IntegerProperty itemType;
	private IntegerProperty isQuestItem;
	private IntegerProperty isImplicitItem;
	
	
	/**
	 * Constructor for testing
	 */
	public Item(){
		
	}
	
	/**
	 * Constructs Items Object
	 * @param itemId
	 * @param itemName
	 * @param itemDescription
	 * @param itemType
	 * @param isQuestItem
	 * @param isImplicitItem
	 */
	
	public Item(int itemId, String itemName, String itemDescription, int itemType, int isQuestItem, int isImplicitItem) {
		this.itemId = new SimpleIntegerProperty(itemId);
		this.itemName = new SimpleStringProperty(itemName);
		this.itemDescription = new SimpleStringProperty(itemDescription);
		this.itemType = new SimpleIntegerProperty(itemType);
		this.isQuestItem = new SimpleIntegerProperty(isQuestItem);
		this.isImplicitItem = new SimpleIntegerProperty(isImplicitItem);
	}
	/**
	 * Gets the Item Id
	 * @return itemdID
	 */
	public int GetItemId() {
		return itemId.get();
	}
	
	/**
	 * Sets the Item Id
	 * @param setItemID
	 */
	
	public void SetItemId(int setItemID) {
		this.itemId = new SimpleIntegerProperty(setItemID);
	}
	
	/**
	 * Gets the ItemID property
	 * @return Property for TableView
	 */
	public final IntegerProperty ItemIDProperty() {
	   return itemId;
	}
	
	/**
	 * Gets the Item Name
	 * @return itemName
	 */
	
	public String GetItemName() {
		return itemName.get();
	}
	
	/**
	 * Sets the Item Name
	 * @param setItemName
	 */
	public void SetItemName(String setItemName) {
		this.itemName = new SimpleStringProperty(setItemName);
	}
	
	/**
	 * Gets the ItemName property
	 * @return Property for TableView
	 */
	public final StringProperty ItemNameProperty() {
	   return itemName;
	}
	
	/**
	 * Gets the Item Description
	 * @return itemDescription
	 */
	public String GetItemDescription() {
		return itemDescription.get();
	}
	
	/**
	 * Sets the Item Description
	 * @param setItemDescription
	 */
	public void SetItemDescription(String setItemDescription) {
		this.itemDescription = new SimpleStringProperty(setItemDescription);
	}
	
	/**
	 * Gets the ItemDescription property
	 * @return Property for TableView
	 */
	public final StringProperty ItemDescriptionProperty() {
	   return itemName;
	}
	
	/**
	 * Gets the Item Type
	 * @return itemType
	 */
	public int GetItemType() {
		return itemType.get();
	}
	
	/**
	 * Sets the Item Type
	 * @param setItemType
	 */
	public void SetItemType(int setItemType) {
		this.itemType = new SimpleIntegerProperty(setItemType);
	}
	
	/**
	 * Gets the ItemType property
	 * @return Property for TableView
	 */
	public final StringProperty ItemTypeIDProperty() {
	   return itemName;
	}
	
	/**
	 * Gets the Is Quest Item
	 * @return isQuestItem
	 */
	public int GetIsQuestItem() {
		return isQuestItem.get();
	}
	
	/**
	 * Sets the IsQuestItem
	 * @param setIsQuestItem
	 */
	public void SetIsQuestItem(int setIsQuestItem) {
		this.isQuestItem = new SimpleIntegerProperty(setIsQuestItem);
	}
	
	/**
	 * Gets the IsItemQuest property
	 * @return Property for TableView
	 */
	public final IntegerProperty IsItemQuestroperty() {
	   return isQuestItem;
	}
	
	/**
	 * Gets the isImplicitItem
	 * @return isImplicitItem
	 */
	public int GetIsImplicitItem() {
		return isImplicitItem.get();
	}
	
	/**
	 * Sets the IsImplicitItem
	 * @param setIsImplicitItem
	 */
	public void SetIsImplicitItem(int setIsImplicitItem) {
		this.isImplicitItem = new SimpleIntegerProperty(setIsImplicitItem);
	}
	
	/**
	 * Gets the ImplicitItem property
	 * @return Property for TableView
	 */
	public final IntegerProperty ImplicitItemProperty() {
	   return isImplicitItem;
	}
	

}