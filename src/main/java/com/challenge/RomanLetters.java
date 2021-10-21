package com.challenge;

/**
 * Enumeration class to store letters for
 * the Roman numbers to avoid string literals in code.
 * They will be used as keys for a dictionary.
 */
public enum RomanLetters {
    I("I"),
    V("V"),
    X("X"),
    L("L"),
    C("C"),
    D("D"),
    M("M");

    private final String letter;

    RomanLetters(String literal) {
        this.letter = literal;
    }
    
    public String getLetter(RomanLetters romanLetter) {
        return romanLetter.letter;
    }
}