package com.challenge;

import java.util.HashMap;

/**
 * Class to store items with their cost and provide basic container
 * functionalities.
 */
public class ItemContainer {
  /**
   * This HashMap is used to store item, cost pairs.
   */
  private HashMap<String, Double> itemDictionary = new HashMap<String, Double>();

  /**
   * Add an item to the dictionary.
   * 
   * 
   * @param item to add
   * @param cost of the item
   * @return true if the item was added otherwise false
   */
  public boolean addItem(String item, Double cost) {

    if (!this.itemDictionary.containsKey(item)) {
      this.itemDictionary.put(item, cost);
      return true;
    }
    return false;
  }

  /**
   * Check if an item is in the dictionary.
   * 
   * @param item to check
   * @return true if item is in dictionary otherwise false
   */
  public boolean valiadteItem(String item) {
    if (this.itemDictionary.containsKey(item)) {
      return true;
    }
    return false;
  }

  /**
   * Get cost of given item.
   * 
   * Expects the item to be in the dictionary.
   * 
   * @param item
   * @return
   */
  public Double getItemCost(String item) {
    return this.itemDictionary.get(item);
  }
}
