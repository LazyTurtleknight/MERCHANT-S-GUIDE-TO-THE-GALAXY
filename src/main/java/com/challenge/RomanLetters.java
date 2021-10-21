package com.challenge;

/**
 * Enumeration class to store letters for the Roman numbers to avoid string
 * literals in code. They will be used as keys for a dictionary.
 */
public enum RomanLetters {
  I("I"), V("V"), X("X"), L("L"), C("C"), D("D"), M("M");

  public final String letter;

  private RomanLetters(String literal) {
    this.letter = literal;
  }

  /**
   * Helper function to find RomanLetters object that corresponds to a given
   * value.
   * 
   * @param value to find
   * @return RomanLetters object corresponding to value if there is one otherwise
   *         null indicating that there is no
   */
  public static RomanLetters findLetter(String value) {
    for (RomanLetters letter : RomanLetters.values()) {
      if (letter.letter.equals(value)) {
        return letter;
      }
    }
    return null;
  }
}