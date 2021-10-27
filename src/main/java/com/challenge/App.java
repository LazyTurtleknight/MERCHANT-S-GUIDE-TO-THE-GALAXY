package com.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Class containing main function.
 */
public class App {

  static AliasContainer aliasContainer;
  static ItemContainer itemContainer;
  static Boolean loopCondition = true;

  /**
   * Parse input for key words to identify queries and call the appropriate
   * functions to process the queries.
   * 
   * Simple parsing: decide by length and the last string the query.
   * 
   * @param input to parse
   */
  public static void parseInput(String input) {

    String[] inputComponents = input.split("\\s+");

    if (inputComponents.length == 1) {
      loopCondition = false;
    } else if (inputComponents.length <= 2) {
      System.out.println("I have no idea what you are talking about");
    } else if (inputComponents.length == 3) {
      handleAddAlias(inputComponents);
    } else if (inputComponents.length >= 5 && inputComponents[inputComponents.length - 1].equals("Credits")) {
      handleAddItem(inputComponents);
    } else if (inputComponents.length >= 7 && inputComponents[inputComponents.length - 1].equals("?")) {
      handleQuestion(inputComponents);
    } else {
      System.out.println("I have no idea what you are talking about");
    }
  }

  /**
   * Add an alias to the container.
   * 
   * Expects the input to have length 3. Expected form of the input: {Alias, "is",
   * Roman numeral}
   * 
   * @param input to add
   */
  public static void handleAddAlias(String[] input) {
    if (!aliasContainer.addAlias(input[0], input[2])) {
      System.out.println("Either " + input[2] + " is not a valid Roman numeral or " + input[0] + " is already used.");
      return;
    }
  }

  /**
   * Add an item to the item container.
   * 
   * Expects the input to have length at least 5. Expected form of the input:
   * {Alias, ... , Alias, "is", Number, "Credits"}
   * 
   * @param input
   */
  public static void handleAddItem(String[] input) {
    String[] aliases = Arrays.copyOfRange(input, 0, input.length - 4);
    // check if every alias is valid
    for (String alias : aliases) {
      if (!aliasContainer.validateAliases(new String[] { alias })) {
        System.out.println(alias + " does not correspond to a Roman numeral.");
        return;
      }
    }
    String convertedAliases = aliasContainer.convertAliasToRoman(aliases);
    // check if the Roman number is valid
    if (aliasContainer.validateRomanNumber(convertedAliases)) {
      String item = input[input.length - 4];
      Integer quantity = aliasContainer.convertRomanToDecimal(convertedAliases);
      Double cost = Double.valueOf(input[input.length - 2]);
      if (itemContainer.valiadteItem(item)) {
        System.out.println(item + " was already added with cost: " + itemContainer.getItemCost(item));
        return;
      }
      itemContainer.addItem(item, cost / quantity.doubleValue());
    } else {
      System.out.println(convertedAliases + " is not a valid number.");
      return;
    }

  }

  /**
   * Answer a well-formed question.
   * 
   * Expects input to have at length at least 7. Expected form of the input:
   * {"how", "many", "Credits", "is", Alias, ... , Alias, item, "?"} or {"how",
   * "much", "is", Alias, ... , Alias, "?"}
   * 
   * @param input
   */
  public static void handleQuestion(String[] input) {
    String item = "";
    String[] aliases = new String[] {};
    // differentiate the two question formats by the second word "much" and "many"
    if (input[1].equals("much")) {
      aliases = Arrays.copyOfRange(input, 3, input.length - 1);
      // check if every alias is valid
      for (String alias : aliases) {
        if (!aliasContainer.validateAliases(new String[] { alias })) {
          System.out.println(alias + " does not correspond to a Roman numeral.");
          return;
        }
      }
      String convertedAliases = aliasContainer.convertAliasToRoman(aliases);
      // check if the Roman number is valid
      if (aliasContainer.validateRomanNumber(convertedAliases)) {
        Integer quantity = aliasContainer.convertRomanToDecimal(convertedAliases);
        System.out.println(String.join(" ", aliases) + " is " + quantity);
        return;
      } else {
        System.out.println("The given number is not valid.");
        return;
      }
    } else if (input[1].equals("many")) {
      item = input[input.length - 2];
      aliases = Arrays.copyOfRange(input, 4, input.length - 2);
      // check if every alias is valid
      for (String alias : aliases) {
        if (!aliasContainer.validateAliases(new String[] { alias })) {
          System.out.println(alias + " does not correspond to a Roman numeral.");
          return;
        }
      }
      String convertedAliases = aliasContainer.convertAliasToRoman(aliases);
      // check if the Roman number is valid
      if (aliasContainer.validateRomanNumber(convertedAliases) && itemContainer.valiadteItem(item)) {
        Integer quantity = aliasContainer.convertRomanToDecimal(convertedAliases);
        System.out.println(String.join(" ", aliases) + " " + item + " is "
            + (int) Math.round((itemContainer.getItemCost(item) * quantity)) + " Credits");
        return;
      } else {
        System.out.println("Either the item has not been registered yet or the given number is not valid.");
        return;
      }
    } else {
      System.out.println("I do not understand this question.");
      return;
    }
  }

  /**
   * Main function. Reads input from the console.
   * 
   * @param args
   */
  public static void main(String[] args) {

    aliasContainer = new AliasContainer();
    itemContainer = new ItemContainer();
    // establish reader to read from console
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // intermediate variable to parse input
    String input = "";

    while (loopCondition) {

      try {
        input = br.readLine();
        parseInput(input);

      } catch (NumberFormatException nfe) {
        System.err.println("Invalid Format!");
      } catch (IOException e) {
        System.out.println(e);
      }
    }
  }
}
