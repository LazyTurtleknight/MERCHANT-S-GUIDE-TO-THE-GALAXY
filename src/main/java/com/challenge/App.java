package com.challenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class containing main function.
 */
public class App {

  /**
   * Main function. Reads input from the console.
   * 
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    // variable to handle loop condition
    Boolean condition = true;
    // establish reader to read from console
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // intermediate variable to parse input
    String input = "";
    String[] inputComponents;
    Integer i = 1;

    while (condition) {

      input = br.readLine();
      try {
        inputComponents = input.split("\\s+");
        if (inputComponents.length == 3) {
          i = 0;
        }
        for (String component : inputComponents) {
          System.out.println(component);
        }
      } catch (NumberFormatException nfe) {
        System.err.println("Invalid Format!");
      }
      if (i == 0) {
        condition = false;
      }

    }

  }
}
