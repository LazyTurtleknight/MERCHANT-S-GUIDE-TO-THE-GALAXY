package com.challenge;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * Unit test for simple App.
 */
@TestInstance(Lifecycle.PER_CLASS)
public class AppTest {

  private final ByteArrayOutputStream output = new ByteArrayOutputStream();
  private final PrintStream originalOutput = System.out;

  @BeforeAll
  public void setUpStreams() {
    System.setOut(new PrintStream(output, true));
  }

  @BeforeAll
  public void setUpApp() {
    App.aliasContainer = new AliasContainer();
    App.itemContainer = new ItemContainer();
  }

  @AfterAll
  public void restoreStreams() {
    System.setOut(originalOutput);
  }

  @Test
  public void testExample() {

    // The app has to cope with the given example.
    App.parseInput("glob is I");
    App.parseInput("prok is V");
    App.parseInput("pish is X");
    App.parseInput("tegj is L");
    App.parseInput("glob glob Silver is 34 Credits");
    App.parseInput("glob prok Gold is 57800 Credits");
    App.parseInput("pish pish Iron is 3910 Credits");

    output.reset();
    App.parseInput("how much is pish tegj glob glob ?");
    Assertions.assertTrue("pish tegj glob glob is 42\n".equals(output.toString()), output.toString());

    output.reset();
    App.parseInput("how many Credits is glob prok Silver ?");
    Assertions.assertTrue("glob prok Silver is 68 Credits\n".equals(output.toString()), output.toString());

    output.reset();
    App.parseInput("how many Credits is glob prok Gold ?");
    Assertions.assertTrue("glob prok Gold is 57800 Credits\n".equals(output.toString()), output.toString());

    output.reset();
    App.parseInput("how many Credits is glob prok Iron ?");
    Assertions.assertTrue("glob prok Iron is 782 Credits\n".equals(output.toString()), output.toString());

    output.reset();
    App.parseInput("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
    Assertions.assertTrue("could does not correspond to a Roman numeral.\n".equals(output.toString()),
        output.toString());
  }
}
