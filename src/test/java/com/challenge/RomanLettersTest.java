package com.challenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RomanLettersTest {

  @Test
  public void testValidateLetter() {

    // Test if the findLetter function correctly returns a RomanLetter object when a
    // corresponding object to the given string is present.
    for (RomanLetters letter : RomanLetters.values()) {
      assertTrue(RomanLetters.findLetter(letter.letter) instanceof RomanLetters);
    }

    // Test if the findLetter function correctly declines false input.
    assertFalse(RomanLetters.findLetter("B") instanceof RomanLetters);
  }
}
