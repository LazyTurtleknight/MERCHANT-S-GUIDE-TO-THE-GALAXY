package com.challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RomanLettersTest {

  @Test
  public void testValidateLetter() {

    // Test if the findLetter function correctly returns a RomanLetter object when a
    // corresponding object to the given string is present.
    for (RomanLetters letter : RomanLetters.values()) {
      Assertions.assertTrue(RomanLetters.findLetter(letter.letter) instanceof RomanLetters);
    }

    // Test if the findLetter function correctly declines false input.
    Assertions.assertFalse(RomanLetters.findLetter("B") instanceof RomanLetters);
  }
}
