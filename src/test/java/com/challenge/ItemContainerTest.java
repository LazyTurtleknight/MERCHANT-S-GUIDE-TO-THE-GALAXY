package com.challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for ItemContainer class.
 */
public class ItemContainerTest {

  @Test
  public void testAddItem() {
    ItemContainer itemContainer = new ItemContainer();
    String item = "item";
    Double cost = 10.0d;
    Assertions.assertTrue(itemContainer.addItem(item, cost));
    // Items can not be added twice
    Assertions.assertFalse(itemContainer.addItem(item, cost));
    // Items with same key but different cost are not considered as different items
    Assertions.assertFalse(itemContainer.addItem(item, cost * 2));
  }

  @Test
  public void testValidateItem() {
    ItemContainer itemContainer = new ItemContainer();
    String item = "item";
    Double cost = 10.0d;

    // Correctly invalidates unregistered items
    Assertions.assertFalse(itemContainer.valiadteItem(item));

    // Correctly validates registered items
    itemContainer.addItem(item, cost);
    Assertions.assertTrue(itemContainer.valiadteItem(item));

  }

  @Test
  public void testGetItemCost() {
    ItemContainer itemContainer = new ItemContainer();
    String item = "item";
    Double cost = 10.0d;
    itemContainer.addItem(item, cost);
    Assertions.assertEquals(itemContainer.getItemCost(item), cost);
  }
}
