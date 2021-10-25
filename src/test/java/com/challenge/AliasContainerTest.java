package com.challenge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for AliasContainer.
 */
public class AliasContainerTest {

  AliasContainer aliasContainer;

  @BeforeEach
  public void setUp() {
    aliasContainer = new AliasContainer();
  }

  @Test
  public void testAddAlias() {
    // Test if the addAlias function correctly accepts every correct Roman letter.
    for (RomanLetters letter : RomanLetters.values()) {
      Assertions.assertTrue(aliasContainer.addAlias(letter.letter + letter.letter, letter.letter));
    }

    // Test if the addAlias function correctly declines false input.
    Assertions.assertFalse(aliasContainer.addAlias("Random", "B"));

    // Test if the addAlias function correctly declines already used aliases.
    Assertions.assertTrue(aliasContainer.addAlias("Random", RomanLetters.I.letter));
    Assertions.assertFalse(aliasContainer.addAlias("Random", "B"));

  }

  @Test
  public void testValidateAlias() {
    String alias1 = "alias1";
    String alias2 = "alias2";
    String fakeAlias = "fakeAlias";
    String[] aliases = { alias1, alias2 };
    String[] fakeAliases = { alias1, fakeAlias, alias2 };

    Assertions.assertFalse(aliasContainer.validateAliases(aliases));
    aliasContainer.addAlias(alias1, RomanLetters.I.letter);
    aliasContainer.addAlias(alias2, RomanLetters.X.letter);
    Assertions.assertTrue(aliasContainer.validateAliases(aliases));
    Assertions.assertFalse(aliasContainer.validateAliases(fakeAliases));
  }

  @Test
  public void testConvertAliasToRoman() {
    String alias1 = "alias1";
    String alias2 = "alias2";
    String[] aliases = { alias1, alias2 };
    String romanNumber = RomanLetters.I.letter + RomanLetters.X.letter;

    aliasContainer.addAlias(alias1, RomanLetters.I.letter);
    aliasContainer.addAlias(alias2, RomanLetters.X.letter);

    Assertions.assertEquals(aliasContainer.convertAliasToRoman(aliases), romanNumber);
  }

  @Test
  public void testConvertRomanToDecimal() {
    String accept = RomanLetters.M.letter + RomanLetters.D.letter + RomanLetters.C.letter + RomanLetters.L.letter
        + RomanLetters.X.letter + RomanLetters.V.letter + RomanLetters.I.letter;
    Assertions.assertTrue(aliasContainer.convertRomanToDecimal(accept) == 1666);

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

    Assertions.assertTrue(aliasContainer.validateRomanNumber(acceptEveryLetter));
    Assertions.assertTrue(aliasContainer.validateRomanNumber(acceptThreeMRepetitions));

    Assertions.assertFalse(aliasContainer.validateRomanNumber(declineEveryLetterReverse));
    Assertions.assertFalse(aliasContainer.validateRomanNumber(declineFourMRepetitions));
    Assertions.assertFalse(aliasContainer.validateRomanNumber(declineTwoVRepetitions));

  }

  @Test
  public void testValidateRomanSubRule() {

    String acceptISubV = RomanLetters.I.letter + RomanLetters.V.letter;
    String declineISubM = RomanLetters.I.letter + RomanLetters.M.letter;
    Assertions.assertTrue(aliasContainer.validateRomanSubRule(acceptISubV));
    Assertions.assertFalse(aliasContainer.validateRomanSubRule(declineISubM));

    String acceptXSubL = RomanLetters.X.letter + RomanLetters.L.letter;
    String declineXSubM = RomanLetters.X.letter + RomanLetters.M.letter;
    Assertions.assertTrue(aliasContainer.validateRomanSubRule(acceptXSubL));
    Assertions.assertFalse(aliasContainer.validateRomanSubRule(declineXSubM));

    String acceptCSubM = RomanLetters.C.letter + RomanLetters.M.letter;
    Assertions.assertTrue(aliasContainer.validateRomanSubRule(acceptCSubM));

    String declineVSubL = RomanLetters.V.letter + RomanLetters.L.letter;
    String declineLSubC = RomanLetters.L.letter + RomanLetters.C.letter;
    String declineDSubM = RomanLetters.D.letter + RomanLetters.M.letter;
    Assertions.assertFalse(aliasContainer.validateRomanSubRule(declineVSubL));
    Assertions.assertFalse(aliasContainer.validateRomanSubRule(declineLSubC));
    Assertions.assertFalse(aliasContainer.validateRomanSubRule(declineDSubM));

    String declineDoubleSub = RomanLetters.X.letter + RomanLetters.X.letter + RomanLetters.L.letter;
    Assertions.assertFalse(aliasContainer.validateRomanSubRule(declineDoubleSub));

  }

}