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
}
