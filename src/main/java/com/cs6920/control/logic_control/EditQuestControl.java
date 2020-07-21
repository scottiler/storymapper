package com.cs6920.control.logic_control;

import java.sql.SQLException;
import java.util.ArrayList;

import com.cs6920.DAL.ItemDAL;
import com.cs6920.DAL.MySQLAccess;
import com.cs6920.DAL.NpcCharacterDAL;
import com.cs6920.DAL.QuestItemsDAL;
import com.cs6920.model.Item;
import com.cs6920.model.NpcCharacter;
import com.cs6920.model.QuestItems;
import com.cs6920.view.edit.EditConflictQuestsViewControl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EditQuestControl {
	private ArrayList<NpcCharacter> existingNPCArrayList;
	private NpcCharacterDAL theNpcCharacterDAL;
	private ObservableList<NpcCharacter> observableNPCList = FXCollections.observableArrayList();
	private ArrayList<QuestItems> theQuestItemsNeededList;
	private ObservableList<QuestItems> observableTheQuestItemsNeededList = FXCollections.observableArrayList();
	private ArrayList<QuestItems> theQuestItemsRewardList;
	private ObservableList<QuestItems> observableTheQuestItemsRewardList = FXCollections.observableArrayList();
	private ItemDAL theItemDAL;
	private QuestItemsDAL theQuestItemsDAL;
	private ArrayList<Item> existingQuestItems;
	private ArrayList<Item> existingRewardItems;
	private ObservableList<Item> observableQuestItemsList = FXCollections.observableArrayList();
	private ObservableList<Item> observableRewardItemsList = FXCollections.observableArrayList();
	private int questIdToEdit;
	private EditConflictQuestsViewControl theEditConflictQuestsViewControl;
	
	public EditQuestControl(EditConflictQuestsViewControl theEditConflictQuestsViewControl, MySQLAccess theDBConnection, int questIdToEdit, String questArcRole) throws SQLException {
		this.theEditConflictQuestsViewControl = theEditConflictQuestsViewControl;
		this.questIdToEdit = questIdToEdit;
		theNpcCharacterDAL = new NpcCharacterDAL(theDBConnection);
		theItemDAL = new ItemDAL(theDBConnection);
		theQuestItemsDAL = new QuestItemsDAL(theDBConnection);
		this.updateNPCArrayList();
		ArrayList<Item> allItems = theItemDAL.getItems();
		existingQuestItems = new ArrayList<Item>();
		existingRewardItems = new ArrayList<Item>();
		for (Item item : allItems) {
			if (item.getIsQuestItem()) {
				if (questArcRole.contentEquals("insight") && item.getIsImplicitItem()) {
					existingQuestItems.add(item);
				}
				else if ((questArcRole.contentEquals("henchman") || questArcRole.contentEquals("monster") || questArcRole.contentEquals("return and reward")) && item.getIstrophy()) {
					existingQuestItems.add(item);
				}
				else if (questArcRole.contentEquals("return new wisdom") && (item.getIsImplicitItem() || item.getIstrophy())) {
					existingQuestItems.add(item);
				}
				else if (!questArcRole.contentEquals("insight") && !questArcRole.contentEquals("return and reward") && !questArcRole.contentEquals("return new wisdom") && !questArcRole.contentEquals("monster") && !questArcRole.contentEquals("henchman") && !item.getIsImplicitItem() && !item.getIstrophy()){
					existingQuestItems.add(item);
				}
			} 
			else {
				existingRewardItems.add(item);
			}
		}
		theQuestItemsNeededList = new ArrayList<QuestItems>();
		theQuestItemsRewardList = new ArrayList<QuestItems>();
		ArrayList<QuestItems> existingQuestItems = this.theQuestItemsDAL.getQuestItemsByQuestId(theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).getQuestId());
		for (QuestItems questItem : existingQuestItems) {
			Item theItem = this.theItemDAL.getItemById(questItem.getItemId());
			if (theItem.getIsQuestItem()) {
				theQuestItemsNeededList.add(questItem);
			}
			else {
				theQuestItemsRewardList.add(questItem);
			}
		}
		this.updateQuestItemsNeededList();
		this.updateQuestItemsRewardList();
		this.updateQuestItemsArrayList();
		this.updateRewardItemsArrayList();
	}
	
	public EditConflictQuestsViewControl getConflictTemplateTheQuestViewControl() {
		return this.theEditConflictQuestsViewControl;
	}
	
	/**
	 * Update the observable list of objects for any changes
	 * @throws SQLException
	 */
	public void updateNPCArrayList() throws SQLException {
		existingNPCArrayList = new ArrayList<NpcCharacter>();
		existingNPCArrayList = theNpcCharacterDAL.getAllNpc();
		observableNPCList.clear();
		observableNPCList.addAll(existingNPCArrayList);
	}
	
	/**
	 * Update the observable list of objects for any changes
	 * @throws SQLException
	 */
	public void updateQuestItemsArrayList() throws SQLException {
		observableQuestItemsList.clear();
		observableQuestItemsList.addAll(existingQuestItems);
	}
	
	/**
	 * Update the observable list of objects for any changes
	 * @throws SQLException
	 */
	public void updateQuestItemsNeededList() throws SQLException {
		observableTheQuestItemsNeededList.clear();
		observableTheQuestItemsNeededList.addAll(theQuestItemsNeededList);
	}
	
	/**
	 * Update the observable list of objects for any changes
	 * @throws SQLException
	 */
	public void updateQuestItemsRewardList() throws SQLException {
		observableTheQuestItemsRewardList.clear();
		observableTheQuestItemsRewardList.addAll(theQuestItemsRewardList);
	}
	
	/**
	 * Update the observable list of objects for any changes
	 * @throws SQLException
	 */
	public void updateRewardItemsArrayList() throws SQLException {
		observableRewardItemsList.clear();
		observableRewardItemsList.addAll(existingRewardItems);
	}
	
	public void addQuestItemNeeded(String itemName, int quantity) throws SQLException {
		int itemId = this.getItemIdByName(itemName, existingQuestItems);
		this.theQuestItemsNeededList.add(new QuestItems(theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).getQuestId(), itemId, quantity, itemName));
		this.updateQuestItemsNeededList();
	}
	
	public void addQuestItemReward(String itemName, int quantity) throws SQLException {
		int itemId = this.getItemIdByName(itemName, existingRewardItems);
		this.theQuestItemsRewardList.add(new QuestItems(theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).getQuestId(), itemId, quantity, itemName));
		this.updateQuestItemsRewardList();
	}
	
	public void removeQuestItemNeeded(String itemName, int quantity) throws SQLException {
		int itemId = this.getItemIdByName(itemName, existingQuestItems);
		for (QuestItems questItemNeeded : theQuestItemsNeededList) {
			if (questItemNeeded.getItemId() == itemId && questItemNeeded.getItemQuantity() == quantity) {
				theQuestItemsNeededList.remove(questItemNeeded);
				break;
			} 
		}
		this.updateQuestItemsNeededList();
	}
	
	public void removeQuestItemReward(String itemName, int quantity) throws SQLException {
		int itemId = this.getItemIdByName(itemName, existingRewardItems);
		for (QuestItems questItemReward : theQuestItemsRewardList) {
			if (questItemReward.getItemId() == itemId && questItemReward.getItemQuantity() == quantity) {
				theQuestItemsRewardList.remove(questItemReward);
				break;
			} 
		}
		this.updateQuestItemsRewardList();
	}
	
	private int getItemIdByName(String itemName, ArrayList<Item> theItems) {
		for (Item item : theItems) {
			if (item.getItemName().contentEquals(itemName)) {
				return item.getItemId();
			} 
		}
		return 0;
	}
	
	public ObservableList<QuestItems> getObservableQuestItemsNeededList() {
		return this.observableTheQuestItemsNeededList;
	}
	
	public ObservableList<QuestItems> getObservableQuestItemsRewardList() {
		return this.observableTheQuestItemsRewardList;
	}
	
	public ObservableList<NpcCharacter> getTheObservableNPCs() {
		return this.observableNPCList;
	}
	
	public ObservableList<Item> getTheObservableQuestItems() {
		return this.observableQuestItemsList;
	}
	
	public ObservableList<Item> getTheObservableRewardItems() {
		return this.observableRewardItemsList;
	}
	
	public int getQuestIdToEdit() {
		return this.questIdToEdit;
	}
	
	public String getNpcNameFromListById(int npcId) {
		for (NpcCharacter theNPC : existingNPCArrayList) {
			if (theNPC.getNpcId() == npcId) {
				return theNPC.getNpcName();
			}
		}
		return "none";
	}
	
	public int getNpcIdFromListByName(String npcName) {
		for (NpcCharacter theNPC : existingNPCArrayList) {
			if (theNPC.getNpcName().contentEquals(npcName)) {
				return theNPC.getNpcId();
			}
		}
		return 0;
	}
	
	public void updateQuestName(String newQuestName) throws SQLException {
		theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).setQuestName(newQuestName);
	}
	
	public void updateQuestDescription(String newQuestDescription) throws SQLException {
		theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).setQuestDescription(newQuestDescription);
	}
	
	public void updateGiverNPC(int npcId) throws SQLException {
		theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).setQuestGiverNpcId(npcId);
	}
	
	public void updateQuestGiverDialog(String newQuestGiverDialog) throws SQLException {
		theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).setQuestGiverDialog(newQuestGiverDialog);
	}
	
	public void updateQuestReceiverDialog(String newQuestReceiverDialog) throws SQLException {
		theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).setQuestReceiverDialog(newQuestReceiverDialog);
	}
	
	public void updateReceiverNPC(int npcId) throws SQLException {
		theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).setQuestReceiverNpcId(npcId);
	}
	
	public int getQuestGiverNpcId() throws SQLException {
		return theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).getQuestGiverNpcId();
	}
	
	public int getQuestReceiverNpcId() throws SQLException {
		return theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).getQuestReceiverNpcId();
	}
	
	public String getQuestName() throws SQLException {
		return theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).getQuestName();
	}
	
	public String getQuestDescription() throws SQLException {
		return theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).getQuestDescription();
	}
	
	public String getGiverDialog() throws SQLException {
		return theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).getQuesGiverDialog();
	}
	
	public String getReceiverDialog() throws SQLException {
		return theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).getQuestReceiverDialog();
	}
	
	public void refreshQuestDisplay() throws SQLException {
		theEditConflictQuestsViewControl.getTheManageQuestsControl().updateQuestChainInDB();
	}
	
	public void updateQuestItemsInDB() throws SQLException {
		this.theQuestItemsDAL.deleteQuestItemsByQuestId(theEditConflictQuestsViewControl.getTheManageQuestsControl().getExistingQuestList().get(this.questIdToEdit).getQuestId());
		for (QuestItems questItem : theQuestItemsNeededList) {
			this.theQuestItemsDAL.createQuestItem(questItem.getQuestId(), questItem.getItemId(), questItem.getItemQuantity(), questItem.getItemDisplayName());
		}
		for (QuestItems questItem : theQuestItemsRewardList) {
			this.theQuestItemsDAL.createQuestItem(questItem.getQuestId(), questItem.getItemId(), questItem.getItemQuantity(), questItem.getItemDisplayName());
		}
	}
}
