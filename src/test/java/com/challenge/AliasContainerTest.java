package com.challenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for AliasContainer.
 */
public class AliasContainerTest {

  AliasContainer aliasContainer;

  @Before
  public void setUp() {
    aliasContainer = new AliasContainer();
  }

  @Test
  public void testAddAlias() {
    // Test if the addAlias function correctly accepts every correct Roman letter.
    for (RomanLetters letter : RomanLetters.values()) {
      assertTrue(aliasContainer.addAlias(letter.letter + letter.letter, letter.letter));
    }

    // Test if the addAlias function correctly declines false input.
    assertFalse(aliasContainer.addAlias("Random", "B"));

    // Test if the addAlias function correctly declines already used aliases.
    assertTrue(aliasContainer.addAlias("Random", RomanLetters.I.letter));
    assertFalse(aliasContainer.addAlias("Random", "B"));

  }

}