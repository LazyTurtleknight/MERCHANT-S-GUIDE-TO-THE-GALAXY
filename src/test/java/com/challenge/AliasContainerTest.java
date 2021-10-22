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

  @Test
  public void testConvertRomanToDecimal() {
    String accept = RomanLetters.M.letter + RomanLetters.D.letter + RomanLetters.C.letter + RomanLetters.L.letter
        + RomanLetters.X.letter + RomanLetters.V.letter + RomanLetters.I.letter;
    assertTrue(aliasContainer.convertRomanToDecimal(accept) == 1666);

  }

  @Test
  public void testValidateRomanNumber() {
    String acceptEveryLetter = RomanLetters.M.letter + RomanLetters.D.letter + RomanLetters.C.letter
        + RomanLetters.L.letter + RomanLetters.X.letter + RomanLetters.V.letter + RomanLetters.I.letter;
    String acceptThreeMRepetitions = RomanLetters.M.letter + RomanLetters.M.letter + RomanLetters.M.letter;

    String declineEveryLetterReverse = RomanLetters.I.letter + RomanLetters.V.letter + RomanLetters.X.letter
        + RomanLetters.L.letter + RomanLetters.C.letter + RomanLetters.D.letter + RomanLetters.M.letter;
    String declineFourMRepetitions = RomanLetters.M.letter + RomanLetters.M.letter + RomanLetters.M.letter
        + RomanLetters.M.letter;

    String declineTwoVRepetitions = RomanLetters.V.letter + RomanLetters.V.letter;

    assertTrue(aliasContainer.validateRomanNumber(acceptEveryLetter));
    assertTrue(aliasContainer.validateRomanNumber(acceptThreeMRepetitions));

    assertFalse(aliasContainer.validateRomanNumber(declineEveryLetterReverse));
    assertFalse(aliasContainer.validateRomanNumber(declineFourMRepetitions));
    assertFalse(aliasContainer.validateRomanNumber(declineTwoVRepetitions));

  }

  @Test
  public void testValidateRomanSubRule() {

    String acceptISubV = RomanLetters.I.letter + RomanLetters.V.letter;
    String declineISubM = RomanLetters.I.letter + RomanLetters.M.letter;
    assertTrue(aliasContainer.validateRomanSubRule(acceptISubV));
    assertFalse(aliasContainer.validateRomanSubRule(declineISubM));

    String acceptXSubL = RomanLetters.X.letter + RomanLetters.L.letter;
    String declineXSubM = RomanLetters.X.letter + RomanLetters.M.letter;
    assertTrue(aliasContainer.validateRomanSubRule(acceptXSubL));
    assertFalse(aliasContainer.validateRomanSubRule(declineXSubM));

    String acceptCSubM = RomanLetters.C.letter + RomanLetters.M.letter;
    assertTrue(aliasContainer.validateRomanSubRule(acceptCSubM));

    String declineVSubL = RomanLetters.V.letter + RomanLetters.L.letter;
    String declineLSubC = RomanLetters.L.letter + RomanLetters.C.letter;
    String declineDSubM = RomanLetters.D.letter + RomanLetters.M.letter;
    assertFalse(aliasContainer.validateRomanSubRule(declineVSubL));
    assertFalse(aliasContainer.validateRomanSubRule(declineLSubC));
    assertFalse(aliasContainer.validateRomanSubRule(declineDSubM));

    String declineDoubleSub = RomanLetters.X.letter + RomanLetters.X.letter + RomanLetters.L.letter;
    assertFalse(aliasContainer.validateRomanSubRule(declineDoubleSub));

  }

}