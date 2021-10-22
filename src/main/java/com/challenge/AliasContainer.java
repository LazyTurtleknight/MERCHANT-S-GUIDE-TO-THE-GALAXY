package com.challenge;

import java.util.HashMap;

/**
 * AliasContainer is a container class to store aliases given by customers.
 * Consider aliases for Roman letters e.g. plok is I. It provides basic
 * functionalities like adding an alias.
 */
public class AliasContainer {

  // This HashMap is used to map the Roman letters to numbers
  // e.g. maps V to 5
  private HashMap<RomanLetters, Integer> romanLetterDict = new HashMap<RomanLetters, Integer>();

  // This HashMap is used to store aliases defined by customers,
  // e.g. plok is I, in this case, we use plok as key and the RomanLetters object
  // corresponding to I as value.
  private HashMap<String, RomanLetters> aliasDictionary = new HashMap<String, RomanLetters>();

  /**
   * Constructor initializes romanLetterDict.
   */
  AliasContainer() {
    /**
     * Initialize romanLetterDict by adding key value pairs of form (RomanLetters,
     * number). These will be used to convert from aliases to numbers.
     */
    romanLetterDict.put(RomanLetters.I, 1);
    romanLetterDict.put(RomanLetters.V, 5);
    romanLetterDict.put(RomanLetters.X, 10);
    romanLetterDict.put(RomanLetters.L, 50);
    romanLetterDict.put(RomanLetters.C, 100);
    romanLetterDict.put(RomanLetters.D, 500);
    romanLetterDict.put(RomanLetters.M, 1000);

  }

  /**
   * This function adds an alias to the aliasDictionary.
   * 
   * First, it checks if the provided value is valid. Then, it gets the
   * corresponding RomanLetter and adds the key, value pair to the dictionary.
   * 
   * @return false if the key is already present or the value is not a valid Roman
   *         letter otherwise true
   */
  public boolean addAlias(String key, String value) {

    RomanLetters romanValue = null;

    if (!(RomanLetters.findLetter(value) == null)) {
      romanValue = RomanLetters.findLetter(value);
      if (!this.aliasDictionary.containsKey(key)) {
        this.aliasDictionary.put(key, romanValue);
        return true;
      }
    }
    return false;
  }

  /**
   * This function takes a Roman number as string and converts it to a decimal
   * number.
   * 
   * This function searches for the first letter with the highest value in the
   * string and splits it in two, the substring to the left of the highest value
   * and the substring to the right of the highest value. Subtracting the left and
   * adding the right part.
   * 
   * @param romanNumber to convert to decimal number
   * @return converted decimal number
   */
  public Integer convertRomanToDecimal(String romanNumber) {
    // Base cases for recursion
    if (romanNumber.length() == 0) {
      return 0;
    }

    if (romanNumber.length() == 1) {
      return this.romanLetterDict.get(RomanLetters.findLetter(romanNumber));
    }

    Integer decimalNumber = 0;
    Integer biggestIndex = 0;
    String biggest = romanNumber.substring(biggestIndex, biggestIndex + 1);
    // Search for the first appearance of the highest Roman letter
    for (Integer index = 0; index < romanNumber.length(); index++) {
      if (this.romanLetterDict
          .get(RomanLetters.findLetter(romanNumber.substring(index, index + 1))) > this.romanLetterDict
              .get(RomanLetters.findLetter(biggest))) {
        biggestIndex = index;
        biggest = romanNumber.substring(index, index + 1);
      }
    }

    // Split the number, left is subtracted from the highest value and the right is
    // added to the highest value
    decimalNumber += -this.convertRomanToDecimal(romanNumber.substring(0, biggestIndex))
        + this.romanLetterDict.get(RomanLetters.findLetter(biggest))
        + this.convertRomanToDecimal(romanNumber.substring(biggestIndex + 1));
    return decimalNumber;

  }

  public boolean validateRomanNumber(String romanNumber) {

    for (Integer index = 0; index < romanNumber.length(); index++) {
      if (RomanLetters.findLetter(romanNumber.substring(index, index + 1)) == null) {
        return false;
      }
    }

    String substring = "";
    for (RomanLetters letter : RomanLetters.values()) {

      // Numbers that start with five can not be repeated
      if (String.valueOf(this.romanLetterDict.get(letter)).charAt(0) == '5') {
        substring = letter.letter + letter.letter;
      }
      // Numbers that do not start with five (with one) can only be repeated thrice
      else {
        substring = letter.letter + letter.letter + letter.letter + letter.letter;
      }

      if (romanNumber.contains(substring)) {
        return false;
      }
    }

    return this.validateRomanSubRule(romanNumber);
  }

  /**
   * This is a helper function starts the check if a given Roman number is valid
   * and is used by the recursion of validateRomanSubRule function below.
   * 
   * This function except to be called by validateRomanNumber because it needs
   * some preprocessing from that function.
   * 
   * @param romanNumber to check
   * @return true is the number is valid otherwise false
   */
  public boolean validateRomanSubRule(String romanNumber) {

    if (romanNumber.length() == 0) {
      return true;
    }

    Integer biggestIndex = 0;
    String biggest = romanNumber.substring(biggestIndex, biggestIndex + 1);
    // Search for the first appearance of the highest Roman letter
    for (Integer index = 0; index < romanNumber.length(); index++) {
      if (this.romanLetterDict
          .get(RomanLetters.findLetter(romanNumber.substring(index, index + 1))) > this.romanLetterDict
              .get(RomanLetters.findLetter(biggest))) {
        biggestIndex = index;
        biggest = romanNumber.substring(index, index + 1);
      }
    }

    return this.validateRomanSubRule(romanNumber.substring(0, biggestIndex), RomanLetters.findLetter(biggest))
        && this.validateRomanSubRule(romanNumber.substring(biggestIndex + 1));
  }

  /**
   * 
   * 
   * @param romanNumber to check
   * @param lastBiggest last first biggest Roman letter found (not included in
   *                    string romanNumber)
   * @return true is the number is valid otherwise false
   */
  public boolean validateRomanSubRule(String romanNumber, RomanLetters lastBiggest) {

    // Base cases for recursion
    if (romanNumber.length() == 0) {
      return true;
    }

    Integer biggestIndex = 0;
    String biggest = romanNumber.substring(biggestIndex, biggestIndex + 1);
    Boolean twice = false;
    // Search for the first appearance of the highest Roman letter
    for (Integer index = 0; index < romanNumber.length(); index++) {

      /**
       * Only one small-value symbol may be subtracted from any large-value symbol.
       */
      if (this.romanLetterDict
          .get(RomanLetters.findLetter(romanNumber.substring(index, index + 1))) == this.romanLetterDict
              .get(RomanLetters.findLetter(biggest))
          && biggestIndex != index) {
        twice = true;
      }

      if (this.romanLetterDict
          .get(RomanLetters.findLetter(romanNumber.substring(index, index + 1))) > this.romanLetterDict
              .get(RomanLetters.findLetter(biggest))) {
        biggestIndex = index;
        biggest = romanNumber.substring(index, index + 1);
        twice = false;
      }

    }
    if (twice) {
      return false;
    }

    // "V", "L", and "D" can never be subtracted.
    if (String.valueOf(this.romanLetterDict.get(RomanLetters.findLetter(biggest))).charAt(0) == '5') {
      return false;
    }

    /**
     * "I" (1) can be subtracted from "V" (5) and "X" (10) only. "X" (10) can be
     * subtracted from "L" (50) and "C" (100) only. "C" (100) can be subtracted from
     * "D" (500) and "M" (1000) only.
     */
    if (!(this.romanLetterDict.get(RomanLetters.findLetter(biggest)) * 10 == this.romanLetterDict.get(lastBiggest)
        || this.romanLetterDict.get(RomanLetters.findLetter(biggest)) * 5 == this.romanLetterDict.get(lastBiggest))) {
      return false;
    }

    return this.validateRomanSubRule(romanNumber.substring(0, biggestIndex), RomanLetters.findLetter(biggest))
        && this.validateRomanSubRule(romanNumber.substring(biggestIndex + 1));

  }
}
