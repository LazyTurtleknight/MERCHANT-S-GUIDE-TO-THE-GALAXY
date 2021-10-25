package com.challenge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for ItemContainer class.
 */
public class ItemContainerTest {

  @Test
  public void testAddItem() {
    ItemContainer itemContainer = new ItemContainer();
    String item = "item";
    Double cost = 10.0d;
    assertTrue(itemContainer.addItem(item, cost));
    // Items can not be added twice
    assertFalse(itemContainer.addItem(item, cost));
    // Items with same key but different cost are not considered as different items
    assertFalse(itemContainer.addItem(item, cost * 2));
  }

  @Test
  public void testValidateItem() {
    ItemContainer itemContainer = new ItemContainer();
    String item = "item";
    Double cost = 10.0d;

    // Correctly invalidates unregistered items
    assertFalse(itemContainer.valiadteItem(item));

    // Correctly validates registered items
    itemContainer.addItem(item, cost);
    assertTrue(itemContainer.valiadteItem(item));

  }

  @Test
  public void testGetItemCost() {
    ItemContainer itemContainer = new ItemContainer();
    String item = "item";
    Double cost = 10.0d;
    itemContainer.addItem(item, cost);
    assertEquals(itemContainer.getItemCost(item), cost);
  }
}
